package morango_esmeralda.excepition;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ProdutoException extends RuntimeException{
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public ProdutoException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public ProdutoException(String message, String message1, HttpStatus httpStatus, ZonedDateTime timestamp) {
        super(message);
        this.message = message1;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public ProdutoException(String message, Throwable cause, String message1, HttpStatus httpStatus, ZonedDateTime timestamp) {
        super(message, cause);
        this.message = message1;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public ProdutoException(Throwable cause, String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        super(cause);
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public ProdutoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1, HttpStatus httpStatus, ZonedDateTime timestamp) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

}
