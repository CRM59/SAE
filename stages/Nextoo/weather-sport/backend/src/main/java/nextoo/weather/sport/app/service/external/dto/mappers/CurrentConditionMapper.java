package nextoo.weather.sport.app.service.external.dto.mappers;

import nextoo.weather.sport.app.models.CurrentCondition;
import nextoo.weather.sport.app.service.external.dto.CurrentConditionDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Component
public class CurrentConditionMapper {
    public CurrentCondition toModel(CurrentConditionDTO dto) {

        DateTimeFormatter defaultPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dto.date().replaceAll("\\.", "-"), defaultPattern);

        return new CurrentCondition(
                date,
                dto.hour(),
                dto.humidity(),
                dto.condition(),
                dto.conditionKey(),
                dto.conditionIcon()
        );
    }
}
