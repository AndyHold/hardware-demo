package co.nz.hardwarestore.quickpractical.model.request;

import co.nz.hardwarestore.quickpractical.exception.*;
import co.nz.hardwarestore.quickpractical.model.Tuple;

import java.util.Set;

/**
 * An abstract class used to define the functions of a request data transfer object.<br>
 * Used to check requests are valid before any persistence operations are performed.
 *
 * @author Andrew Holden
 */
public abstract class RequestDTO {

  /**
   * Validates the model to see if it is valid before trying to insert it into the database.
   *
   * @throws BadRequest if the model is invalid.
   */
  public abstract void validate() throws BadRequest;

  /**
   * Validates a set of entries to check that the values are not null.
   *
   * @param objects the set of entries.
   * @throws BadRequest when a value of an entry is null.
   */
  public void checkRequiredObjects(Set<Tuple<String, Object>> objects) throws BadRequest {
    for (Tuple<String, Object> entry : objects) {
      if (entry.value() == null) {
        throw new BadRequest(String.format("%s is required", entry.key()));
      }
    }
  }
}
