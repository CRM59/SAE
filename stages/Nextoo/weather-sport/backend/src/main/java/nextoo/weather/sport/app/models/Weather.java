package nextoo.weather.sport.app.models;


import java.util.List;

public record Weather(
        CityInfo cityInfo,
        ForecastInfo forecastInfo,
        CurrentCondition currentCondition,
        List<Day> days
) {
}
