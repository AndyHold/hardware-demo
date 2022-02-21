package co.nz.hardwarestore.quickpractical.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

/**
 * A class used to define how exceptions are handled.
 *
 * @author Andrew Holden
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
  @ExceptionHandler({BadRequest.class, MethodArgumentTypeMismatchException.class})
  @ResponseBody
  public ResponseError handleBadRequest(Exception ex, HttpServletRequest request) {
    return new ResponseError(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getServletPath());
  }

  @ResponseStatus(HttpStatus.FORBIDDEN) // 403
  @ExceptionHandler(Forbidden.class)
  @ResponseBody
  public ResponseError handleForbidden(Exception ex, HttpServletRequest request) {
    return new ResponseError(HttpStatus.FORBIDDEN, ex.getMessage(), request.getServletPath());
  }

  @ResponseStatus(HttpStatus.NOT_FOUND) // 404
  @ExceptionHandler(NotFound.class)
  @ResponseBody
  public ResponseError handleNotFound(Exception ex, HttpServletRequest request) {
    return new ResponseError(HttpStatus.NOT_FOUND, ex.getMessage(), request.getServletPath());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
  @ExceptionHandler(Exception.class)
  public void handleInternalServerError() { /* No operations to perform. */ }
}
