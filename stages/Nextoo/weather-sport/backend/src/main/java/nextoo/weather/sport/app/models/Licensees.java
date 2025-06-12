package nextoo.weather.sport.app.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record Licensees(
        Person person,
        String sport,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate startDate,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate endDate
) {
}
