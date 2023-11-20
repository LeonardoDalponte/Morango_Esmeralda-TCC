package morango_esmeralda.excepition.handler;

import morango_esmeralda.excepition.CarrinhoException;
import morango_esmeralda.excepition.ProdutoException;
import morango_esmeralda.excepition.UsuarioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = UsuarioException.class)
    public ResponseEntity<ErrorResponse> handlerUsuarioException(UsuarioException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                badRequest.value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(errorResponse, badRequest);
    }

    @ExceptionHandler(value = CarrinhoException.class)
    public ResponseEntity<ErrorResponse> handlerCarrinhoException(CarrinhoException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                badRequest.value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(errorResponse, badRequest);
    }

    @ExceptionHandler(value = ProdutoException.class)
    public ResponseEntity<ErrorResponse> handlerProdutoException(ProdutoException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                badRequest.value(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(errorResponse, badRequest);
    }



    public static class ErrorResponse {

        private final String message;
        private final int status;
        private final ZonedDateTime timestamp;

        public ErrorResponse(String message, int status, ZonedDateTime timestamp) {
            this.message = message;
            this.status = status;
            this.timestamp = timestamp;
        }

        public String getMessage() {
            return message;
        }

        public int getStatus() {
            return status;
        }

        public ZonedDateTime getTimestamp() {
            return timestamp;
        }
    }
}



