package co.nz.hardwarestore.quickpractical.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * A class used to send an error message in an HTTP response.
 *
 * @author Andrew Holden
 */
@Getter
public class ResponseError {

    private final String timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;

    public ResponseError(HttpStatus status, String message, String path) {
        this.timestamp = ZonedDateTime.now().toString();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.path = path;
    }
}
