package es.upm.sos.garajesyempleados.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //Captura excepciones de toda la aplicación y devuelve JSON
public class EmpleadoExceptionAdvice {
    @ExceptionHandler(EmpleadoNotFoundException.class) //Permite capturar una excepción específica y devuelve una respuesta personalizada
    @ResponseStatus(HttpStatus.NOT_FOUND) //Permite indicar el código HTTP que deseamos enviar al capturar esta excepción
    ErrorMessage userNotFoundHandler(EmpleadoNotFoundException ex) {
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler(EmpleadoExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    ErrorMessage userExistsHandler(EmpleadoExistsException ex) {
        return new ErrorMessage(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorMessage handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage); 
        });
        return new ErrorMessage(errors.toString());
    }
}
