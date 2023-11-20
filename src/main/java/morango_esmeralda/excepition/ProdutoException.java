package morango_esmeralda.excepition;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class ProdutoException extends RuntimeException{

    public ProdutoException(String message) {
        super(message);
    }

    public ProdutoException(String message, Throwable cause) {
        super(message, cause);
    }
}
