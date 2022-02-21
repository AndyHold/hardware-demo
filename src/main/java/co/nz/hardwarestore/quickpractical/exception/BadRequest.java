package co.nz.hardwarestore.quickpractical.exception;

/**
 * An exception class used when an HTTP request contains invalid data.
 *
 * @author Andrew Holden
 */
public class BadRequest extends RuntimeException {

  public BadRequest(String message) {
    super(message);
  }
}
