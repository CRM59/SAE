package nextoo.weather.sport.app.models;

import java.time.LocalDate;
import java.time.LocalTime;

public record CurrentCondition(
        LocalDate date,
        LocalTime hour,
        int humidity,
        String condition,
        String conditionKey,
        String weatherConditionIcon
) {
}
