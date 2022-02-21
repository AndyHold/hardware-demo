package co.nz.hardwarestore.quickpractical.model.response;

import co.nz.hardwarestore.quickpractical.model.entity.ProductEntity;
import co.nz.hardwarestore.quickpractical.model.entity.ProductProductOrderEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * A response data transfer object to represent a product to be viewed by a customer.
 *
 * @author Andrew Holden
 */
@Getter
@Setter
public class ProductDTO {

  private Long id;
  private String name;
  private Double price;

  public ProductDTO(ProductEntity productEntity) {
    this.id = productEntity.getId();
    this.name = productEntity.getName();
    this.price = productEntity.getPrice();
  }

  public ProductDTO(ProductProductOrderEntity entity) {
    this.id = entity.getProduct().getId();
    this.name = entity.getProduct().getName();
    this.price = entity.getProduct().getPrice();
  }
}
