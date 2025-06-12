package nextoo.weather.sport.app.service.external.dto.mappers;

import nextoo.weather.sport.app.models.Day;
import nextoo.weather.sport.app.models.Hour;
import nextoo.weather.sport.app.service.external.dto.DayDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class DayMapper {
    public Day toModel(DayDTO dto) {
        final List<Hour> hours = new ArrayList<>();

        dto.hourlyData().forEach((hourLabel, hour) ->
                hours.add(
                        new Hour(
                                LocalTime.parse(
                                        hourLabel.replace("H", ":"),
                                        DateTimeFormatter.ofPattern("H:mm")
                                ),
                                hour.condition(),
                                hour.conditionKey(),
                                hour.humidity(),
                                hour.precipitation(),
                                hour.isSnow(),
                                hour.windSpeed()
                        )
                )
        );

        DateTimeFormatter defaultPattern = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.parse(dto.date().replaceAll("\\.", "-"), defaultPattern);

        return new Day(
                date,
                dto.dayName(),
                dto.tempMin(),
                dto.tempMax(),
                dto.condition(),
                hours
        );
    }
}
