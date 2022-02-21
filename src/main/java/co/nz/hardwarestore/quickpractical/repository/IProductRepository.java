package co.nz.hardwarestore.quickpractical.repository;

import co.nz.hardwarestore.quickpractical.model.entity.ProductEntity;
import co.nz.hardwarestore.quickpractical.model.response.ProductDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

/**
 * An interface to define persistence methods for products.
 *
 * @author Andrew Holden
 */
public interface IProductRepository extends Repository<ProductEntity, Long> {

  /**
   * Find all available products in the data persistence.
   *
   * @return a list of products.
   */
  @Query(
      value =
          "SELECT new co.nz.hardwarestore.quickpractical.model.response.ProductDTO(e) FROM ProductEntity e")
  List<ProductDTO> findAll();

  /**
   * Find a product entity by it's unique identifier.
   *
   * @param id the unique identifier of the product.
   * @return an optional object containing the product or empty if none exists.
   */
  Optional<ProductEntity> findById(Long id);

  /**
   * Save a product to the data persistence.
   *
   * @param entity the entity to be saved.
   * @return the entity after being saved.
   */
  ProductEntity save(ProductEntity entity);

  /**
   * Delete a product from the data persistence.
   *
   * @param id the id of the product to be deleted.
   */
  void deleteById(Long id);
}
