package nextoo.weather.sport.app.validators;

// import...

import io.micrometer.common.util.StringUtils;
import nextoo.weather.sport.app.data.dto.LicenseesDTO;
import nextoo.weather.sport.app.data.dto.SportDTO;
import nextoo.weather.sport.app.exception.BadInputDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LicenseesValidator implements Validator<LicenseesDTO> {

    @Override
    public void validate(LicenseesDTO licenseesDTO) throws BadInputDTO {
        List<String> errors = new ArrayList<>();
        if(StringUtils.isBlank(licenseesDTO.getPersonFirstName())) {
            errors.add("FirstName is empty");
        }
        if(StringUtils.isBlank(licenseesDTO.getPersonLastName())) {
            errors.add("LastName is empty");
        }
        if(StringUtils.isBlank(licenseesDTO.getSportName())) {
            errors.add("Sport name is empty");
        }
        if(licenseesDTO.getStartDate() == null) {
            errors.add("start date is null");
        }
        if(licenseesDTO.getEndDate() == null
                || licenseesDTO.getEndDate().isBefore(licenseesDTO.getStartDate())) {
            errors.add("end date is null");
        }
        if(!errors.isEmpty()){
            throw new BadInputDTO(SportDTO.class, errors);
        }
    }
}