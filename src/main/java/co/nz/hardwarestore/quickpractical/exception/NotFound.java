package co.nz.hardwarestore.quickpractical.exception;

/**
 * An exception class used when an HTTP request tries to request data that does not exist.
 *
 * @author Andrew Holden
 */
public class NotFound extends RuntimeException {

  public NotFound(String message) {
    super(message);
  }
}
