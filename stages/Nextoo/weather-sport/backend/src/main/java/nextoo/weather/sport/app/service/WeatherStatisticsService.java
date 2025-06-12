package nextoo.weather.sport.app.service;

import nextoo.weather.sport.app.models.CurrentCondition;
import nextoo.weather.sport.app.models.Day;
import nextoo.weather.sport.app.models.Hour;
import nextoo.weather.sport.app.models.Weather;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface WeatherStatisticsService {
    Weather getWeatherByCity(String city, int nbDays);

    boolean isRainingByHour(Hour hour);

    boolean isSnowingByHour(Hour hour);

    boolean isWindingByHour(Hour hour);

    List<Day> getHottestDays(String city, int nbDays);

    List<Day> getRainDays(String city, int nbDays);

    Day getDriestDay(Weather weather);

    int getCurrentHumidity(CurrentCondition currentCondition);

    Map<String, Double> getHumidityAverage(Weather weather);
}
