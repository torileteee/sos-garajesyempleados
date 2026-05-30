package es.upm.sos.garajesyempleados.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GarajeExceptionAdvice {
    @ExceptionHandler(GarajeExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorMessage garajeExistsHandler(GarajeExistsException ex) {
        return new ErrorMessage(ex.getMessage());
    }
}
