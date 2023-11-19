package morango_esmeralda.excepition;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class UsuarioException extends RuntimeException  {

    public UsuarioException(String message) {
        super(message);
    }

    public UsuarioException(String message, Throwable cause) {
        super(message, cause);
    }
}
