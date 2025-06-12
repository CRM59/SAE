package nextoo.validation.movies_app.validators;

import io.micrometer.common.util.StringUtils;
import nextoo.validation.movies_app.data.dto.ProfileDTO;
import nextoo.validation.movies_app.exceptions.BadInputDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfileValidator implements Validator<ProfileDTO> {
    public void validate(ProfileDTO profileDTO) throws BadInputDTO {
        List<String> errors = new ArrayList<>();
        if(profileDTO.getName() == null) {
            errors.add("Name is null");
        }
        if(profileDTO.getTag() == null) {
            errors.add("Tag is null");
        }
        if(StringUtils.isBlank(profileDTO.getName())) {
            errors.add("Name is empty");
        }
        if(StringUtils.isBlank(profileDTO.getTag())) {
            errors.add("Tag is empty");
        }
        if(profileDTO.getTag().length() != 4) {
            errors.add("Bad tag size");
        }

        if(!errors.isEmpty()) {
            throw new BadInputDTO(ProfileDTO.class, errors);
        }
    }
}
