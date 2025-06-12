package nextoo.weather.sport.app.models;

import java.time.LocalDate;
import java.util.List;

public record Day(
        LocalDate date,
        String day,
        int tempMin,
        int tempMax,
        String condition,
        List<Hour> hourlyData
) {
}
