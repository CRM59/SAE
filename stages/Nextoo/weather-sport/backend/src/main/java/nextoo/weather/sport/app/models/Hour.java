package nextoo.weather.sport.app.models;

import java.time.LocalTime;
import java.util.Objects;

public record Hour(
        LocalTime hour,
        String hourCondition,
        String hourConditionKey,
        int relativeHumidity,
        int precipitation,
        int isSnow,
        int windSpeed
) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Hour hour1 = (Hour) o;
        return Objects.equals(hour, hour1.hour);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(hour);
    }
}
