package co.nz.hardwarestore.quickpractical.controller;

import co.nz.hardwarestore.quickpractical.exception.BadRequest;
import co.nz.hardwarestore.quickpractical.exception.NotFound;
import co.nz.hardwarestore.quickpractical.model.entity.ProductEntity;
import co.nz.hardwarestore.quickpractical.model.entity.ProductOrderEntity;
import co.nz.hardwarestore.quickpractical.model.entity.ProductProductOrderEntity;
import co.nz.hardwarestore.quickpractical.model.entity.UserDetailsEntity;
import co.nz.hardwarestore.quickpractical.model.request.AddProductOrderDTO;
import co.nz.hardwarestore.quickpractical.model.request.AddProductQuantityDTO;
import co.nz.hardwarestore.quickpractical.model.response.ProductOrderDTO;
import co.nz.hardwarestore.quickpractical.repository.IProductOrderRepository;
import co.nz.hardwarestore.quickpractical.repository.IProductRepository;
import co.nz.hardwarestore.quickpractical.repository.IUserDetailsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Contains REST API endpoints relating to product orders.
 *
 * @author Andrew Holden
 */
@RestController
public class ProductOrderController {

  private final IUserDetailsRepository userDetailsRepository;
  private final IProductOrderRepository productOrderRepository;
  private final IProductRepository productRepository;
  private final ModelMapper modelMapper = new ModelMapper();

  @Autowired
  public ProductOrderController(
      IUserDetailsRepository userDetailsRepository,
      IProductOrderRepository productOrderRepository,
      IProductRepository productRepository) {
    this.userDetailsRepository = userDetailsRepository;
    this.productOrderRepository = productOrderRepository;
    this.productRepository = productRepository;
  }

  @Async
  @PostMapping("/user/{id}/order")
  @ResponseStatus(HttpStatus.CREATED)
  public CompletableFuture<ProductOrderDTO> addOrder(
      @RequestBody AddProductOrderDTO requestDTO, @PathVariable Long id) {
    return CompletableFuture.supplyAsync(
            () -> {
              Optional<UserDetailsEntity> optionalUserDetails = userDetailsRepository.findById(id);
              if (optionalUserDetails.isEmpty()) {
                throw new NotFound("User cannot be found.");
              }
              requestDTO.validate();

              ProductOrderEntity orderEntity = modelMapper.map(requestDTO, ProductOrderEntity.class);

              List<ProductProductOrderEntity> productEntityList = new ArrayList<>();
              for (AddProductQuantityDTO productDTO: requestDTO.getProducts()) {
                Optional<ProductEntity> productEntity = productRepository.findById(productDTO.getId());
                if (productEntity.isEmpty()) {
                  throw new BadRequest("One or more products is invalid.");
                }

                ProductProductOrderEntity productProductEntity = new ProductProductOrderEntity();
                productProductEntity.setProduct(productEntity.get());
                productProductEntity.setOrder(orderEntity);
                productProductEntity.setQuantity(productDTO.getQuantity());

                productEntityList.add(productProductEntity);
              }

              orderEntity.setUser(optionalUserDetails.get());
              orderEntity.setProducts(productEntityList);
              return orderEntity;
            })
        .thenApplyAsync(
            entity -> {
              entity = productOrderRepository.save(entity);
              return new ProductOrderDTO(entity);
            });
  }
}
