package co.nz.hardwarestore.quickpractical.model.request;

import co.nz.hardwarestore.quickpractical.exception.BadRequest;
import co.nz.hardwarestore.quickpractical.model.Tuple;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * A data transfer object class to represent a quantity of products to add to data persistence.<br>
 * To be received in a http request.
 *
 * @author Andrew Holden
 */
@Getter
@Setter
public class AddProductQuantityDTO extends RequestDTO {

  private Long id;
  private Integer quantity;

  @Override
  public void validate() throws BadRequest {
    Set<Tuple<String, Object>> requiredObjects = Set.of(
        new Tuple<>("Quantity", getQuantity()),
        new Tuple<>("Id", getId())
    );
    checkRequiredObjects(requiredObjects);
  }
}
