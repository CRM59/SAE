package nextoo.weather.sport.app.validators;


import nextoo.weather.sport.app.exception.BadInputDTO;

public interface Validator <T> {

    void validate(T input) throws BadInputDTO;

}