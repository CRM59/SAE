package nextoo.weather.sport.app.service.external.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalTime;

public record CurrentConditionDTO(
        String date,
        LocalTime hour,
        int humidity,
        String condition,
        @JsonAlias("condition_key")
        String conditionKey,
        @JsonAlias("icon")
        String conditionIcon
) {
}
