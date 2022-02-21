package co.nz.hardwarestore.quickpractical.model.request;

import co.nz.hardwarestore.quickpractical.exception.BadRequest;
import co.nz.hardwarestore.quickpractical.model.Tuple;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * A data transfer object class to represent a product to update in data persistence.<br>
 * To be received in a http request.
 *
 * @author Andrew Holden
 */
@Getter
@Setter
public class UpdateProductDTO extends RequestDTO {

    private Long id;
    private String name;
    private String price;

    @Override
    public void validate() throws BadRequest {
        Set<Tuple<String, Object>> requiredFields = Set.of(
                new Tuple<>("Id", getId()),
                new Tuple<>("Name", getName()),
                new Tuple<>("Price", getPrice())
        );
        checkRequiredObjects(requiredFields);
    }
}
