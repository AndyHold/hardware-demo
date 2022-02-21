package co.nz.hardwarestore.quickpractical.controller;

import co.nz.hardwarestore.quickpractical.exception.Forbidden;
import co.nz.hardwarestore.quickpractical.exception.NotFound;
import co.nz.hardwarestore.quickpractical.model.Role;
import co.nz.hardwarestore.quickpractical.model.entity.ProductEntity;
import co.nz.hardwarestore.quickpractical.model.entity.UserDetailsEntity;
import co.nz.hardwarestore.quickpractical.model.request.AddProductDTO;
import co.nz.hardwarestore.quickpractical.model.request.UpdateProductDTO;
import co.nz.hardwarestore.quickpractical.model.response.ProductDTO;
import co.nz.hardwarestore.quickpractical.repository.IProductRepository;
import co.nz.hardwarestore.quickpractical.repository.IUserDetailsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Contains REST API endpoints relating to products.
 *
 * @author Andrew Holden
 */
@RestController
public class ProductController {

  private final IUserDetailsRepository userDetailsRepository;
  private final IProductRepository productRepository;
  private final ModelMapper modelMapper = new ModelMapper();

  @Autowired
  public ProductController(
      IUserDetailsRepository userDetailsRepository, IProductRepository productRepository) {
    this.userDetailsRepository = userDetailsRepository;
    this.productRepository = productRepository;
  }

  /**
   * Endpoint to get a list of all available products from the data persistence.
   *
   * @return a list of available products.
   */
  @Async
  @GetMapping("/products")
  @ResponseStatus(HttpStatus.OK)
  public CompletableFuture<List<ProductDTO>> getAllProducts() {
    return CompletableFuture.supplyAsync(productRepository::findAll);
  }

  /**
   * Endpoint to add a new product. User must be an administrator.
   *
   * @param requestDTO the new product to be added.
   * @param id the unique identifier of the user adding the product.
   * @return a future containing the product once it has been added.
   */
  @Async
  @PostMapping("/user/{id}/product")
  @ResponseStatus(HttpStatus.CREATED)
  public CompletableFuture<ProductDTO> addProduct(
      @RequestBody AddProductDTO requestDTO, @PathVariable() Long id) {
    return CompletableFuture.supplyAsync(
            () -> {
              validateUserIsAdmin(id);
              requestDTO.validate();

              return modelMapper.map(requestDTO, ProductEntity.class);
            })
        .thenApplyAsync(
            entity -> {
              entity = productRepository.save(entity);
              return new ProductDTO(entity);
            });
  }

  /**
   * Endpoint to update an existing product.
   *
   * @param requestDTO the product details to be updated.
   * @param id the id of the user making the request.
   * @return the product after it has been updated.
   */
  @Async
  @PatchMapping("/user/{id}/product")
  @ResponseStatus(HttpStatus.OK)
  public CompletableFuture<ProductDTO> updateProduct(@RequestBody UpdateProductDTO requestDTO, @PathVariable Long id) {
    return CompletableFuture.supplyAsync(() -> {
      validateUserIsAdmin(id);
      requestDTO.validate();
      Optional<ProductEntity> optionalProduct = productRepository.findById(requestDTO.getId());
      if (optionalProduct.isEmpty()) {
        throw new NotFound("This product could not be found.");
      }
      return modelMapper.map(requestDTO, ProductEntity.class);
    }).thenApplyAsync(product -> {
      productRepository.save(product);
      return new ProductDTO(product);
    });
  }

  /**
   * Endpoint to delete a product from data persistence.
   *
   * @param userId the unique identifier of the user performing the request.
   * @param productId the unique identifier of the product to be deleted.
   * @return a future which deletes the product from the database if admin privileges are established.
   */
  @Async
  @DeleteMapping("/user/{userId}/product/{productId}")
  @ResponseStatus(HttpStatus.OK)
  public CompletableFuture<Void> deleteProduct(@PathVariable Long userId, @PathVariable Long productId) {
    return CompletableFuture.runAsync(() -> {
      validateUserIsAdmin(userId);

      Optional<ProductEntity> product = productRepository.findById(productId);
      if (product.isEmpty()) {
        throw new NotFound("This product does not exist.");
      }

      productRepository.deleteById(productId);
    });
  }

  private void validateUserIsAdmin(Long id) throws NotFound, Forbidden {
    Optional<UserDetailsEntity> optionalUserDetails = userDetailsRepository.findById(id);
    if (optionalUserDetails.isEmpty()) {
      throw new NotFound("User not found");
    }
    if (optionalUserDetails.get().getRole() != Role.ADMINISTRATOR) {
      throw new Forbidden("You do not have permission to perform this action.");
    }
  }
}
