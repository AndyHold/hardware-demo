package co.nz.hardwarestore.quickpractical.model.response;

import co.nz.hardwarestore.quickpractical.model.entity.ProductOrderEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * A response data transfer object to represent a product order to be viewed by a customer.
 *
 * @author Andrew Holden
 */
@Getter
@Setter
public class ProductOrderDTO {

  private Long id;
  private Long userId;
  private List<ProductQuantityDTO> items;

  public ProductOrderDTO(ProductOrderEntity entity) {
    this.id = entity.getId();
    this.userId = entity.getUser().getId();
    this.items = entity.getProducts().stream().map(ProductQuantityDTO::new).toList();
  }
}
