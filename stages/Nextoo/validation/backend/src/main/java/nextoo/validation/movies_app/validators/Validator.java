package nextoo.validation.movies_app.validators;

import nextoo.validation.movies_app.exceptions.BadInputDTO;
import org.springframework.stereotype.Component;

@Component
public interface Validator <T> {
    void validate(T object) throws BadInputDTO;
}
