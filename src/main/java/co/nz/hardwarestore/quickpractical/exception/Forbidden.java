package co.nz.hardwarestore.quickpractical.exception;

/**
 * An exception class used when a forbidden action is requested via http.
 *
 * @author Andrew Holden
 */
public class Forbidden extends RuntimeException {

  public Forbidden(String message) {
    super(message);
  }
}
