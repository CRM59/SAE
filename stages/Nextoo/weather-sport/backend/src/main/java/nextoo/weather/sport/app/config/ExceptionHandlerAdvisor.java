package nextoo.weather.sport.app.config;

import nextoo.weather.sport.app.exception.ConflictException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.FORBIDDEN;

@ControllerAdvice
public class ExceptionHandlerAdvisor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvisor.class);

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGlobalException(Exception e) {
        LOGGER.error("ERROR : Exception thrown by global handler");
        return new ResponseEntity<>("Something bad happened", FORBIDDEN);
    }

    @ExceptionHandler({ConflictException.class})
    public ResponseEntity<Object> handleConflictException(ConflictException e) {
        LOGGER.error("Conflict with data");
        return new ResponseEntity<>("Conflict with data : data already exists", CONFLICT);
    }

    @ExceptionHandler({NoResourceFoundException.class})
    public ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException e) {
        LOGGER.error("No ressource found");
        return new ResponseEntity<>("No city chosen", FORBIDDEN);
    }
}