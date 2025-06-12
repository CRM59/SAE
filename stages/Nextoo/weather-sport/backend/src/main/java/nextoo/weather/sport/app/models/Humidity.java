package nextoo.weather.sport.app.models;

import java.util.Map;

public record Humidity(
        int currentHumidity,
        Map<String, Double> humidityAverage,
        Day driestDay
) {
}
