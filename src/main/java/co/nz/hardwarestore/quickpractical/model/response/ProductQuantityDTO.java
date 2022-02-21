package co.nz.hardwarestore.quickpractical.model.response;

import co.nz.hardwarestore.quickpractical.model.entity.ProductProductOrderEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * A response data transfer object to represent a product quantity to be viewed by a customer.
 *
 * @author Andrew Holden
 */
@Getter
@Setter
public class ProductQuantityDTO extends ProductDTO {

  private Integer quantity;

  public ProductQuantityDTO(ProductProductOrderEntity entity) {
    super(entity);
    this.quantity = entity.getQuantity();
  }
}
