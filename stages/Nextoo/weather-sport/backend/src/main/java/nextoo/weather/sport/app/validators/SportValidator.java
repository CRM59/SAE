package nextoo.weather.sport.app.validators;

// import...

import io.micrometer.common.util.StringUtils;
import nextoo.weather.sport.app.data.dto.SportDTO;
import nextoo.weather.sport.app.exception.BadInputDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SportValidator implements Validator<SportDTO> {

    @Override
    public void validate(SportDTO sportDTO) throws BadInputDTO {
        List<String> errors = new ArrayList<>();
        if(StringUtils.isBlank(sportDTO.getName())) {
            errors.add("Name is empty");
        }
        if(!errors.isEmpty()){
            throw new BadInputDTO(SportDTO.class, errors);
        }
    }


    public void validateSportName(String sportName) throws BadInputDTO {
        List<String> errors = new ArrayList<>();
        if(StringUtils.isBlank(sportName)) {
            errors.add("Sport name is empty");
        }
        if(!errors.isEmpty()){
            throw new BadInputDTO(SportDTO.class, errors);
        }
    }
}