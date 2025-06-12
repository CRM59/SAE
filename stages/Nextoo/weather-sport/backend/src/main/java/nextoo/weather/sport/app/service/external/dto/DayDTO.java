package nextoo.weather.sport.app.service.external.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.Map;

public record DayDTO(
        String date,
        @JsonAlias("day_long")
        String dayName,
        @JsonAlias("tmin")
        int tempMin,
        @JsonAlias("tmax")
        int tempMax,
        String condition,
        @JsonAlias("hourly_data")
        Map<String, HourDTO> hourlyData
) {
}
