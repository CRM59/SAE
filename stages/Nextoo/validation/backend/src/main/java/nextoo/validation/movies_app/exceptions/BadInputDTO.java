package nextoo.validation.movies_app.exceptions;

import java.util.Collections;
import java.util.List;

public class BadInputDTO extends Exception {

    private final List<String> errors;

    public <T> BadInputDTO(Class<T> inputObjectType, List<String> errors) {
        super(String.format("Bad input format for type : %s", inputObjectType));
        this.errors = Collections.unmodifiableList(errors);
    }

    public List<String> getErrors() {
        return errors;
    }
}