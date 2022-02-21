package co.nz.hardwarestore.quickpractical.model.request;

import co.nz.hardwarestore.quickpractical.exception.BadRequest;
import co.nz.hardwarestore.quickpractical.model.Tuple;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

/**
 * A data transfer object class to represent a product order to be added to data persistence.<br>
 * To be received in a http request.
 *
 * @author Andrew Holden
 */
@Getter
@Setter
public class AddProductOrderDTO extends RequestDTO {

  private List<AddProductQuantityDTO> products;

  @Override
  public void validate() throws BadRequest {
    Set<Tuple<String, Object>> requiredObjects = Set.of(new Tuple<>("Products", products));
    checkRequiredObjects(requiredObjects);

    if (products.isEmpty()) {
      throw new BadRequest("Product order cannot be empty.");
    }

    for (AddProductQuantityDTO product : products) {
        product.validate();
    }
  }
}
