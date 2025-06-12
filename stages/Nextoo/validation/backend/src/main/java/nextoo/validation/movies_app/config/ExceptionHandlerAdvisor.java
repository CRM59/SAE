package nextoo.validation.movies_app.config;

import nextoo.validation.movies_app.exceptions.ConflictException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@ControllerAdvice
public class ExceptionHandlerAdvisor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvisor.class);

    @ExceptionHandler({NoSuchElementException.class})
    public ResponseEntity<Object> handleNoSuchElementException() {
        LOGGER.error("Problem with data");
        return new ResponseEntity<>("Problem with data : data not found", FORBIDDEN);
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<Object> handleConflictException() {
        LOGGER.error("Conflict with data");
        return new ResponseEntity<>("Conflict with data : data already exists", CONFLICT);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleHttpMessageNotReadableException() {
        LOGGER.error("Conflict with request body");
        return new ResponseEntity<>("Conflict with request body : bad json data format", FORBIDDEN);
    }
}