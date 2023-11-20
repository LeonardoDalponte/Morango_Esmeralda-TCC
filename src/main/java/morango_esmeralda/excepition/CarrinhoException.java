package morango_esmeralda.excepition;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class CarrinhoException extends RuntimeException {
    public CarrinhoException(String message) {
        super(message);
    }

    public CarrinhoException(String message, Throwable cause) {
        super(message, cause);
    }
}
