package nextoo.weather.sport.app.service.implementation;

import nextoo.weather.sport.app.config.Database;
import nextoo.weather.sport.app.models.*;
import nextoo.weather.sport.app.service.WeatherStatisticsService;
import nextoo.weather.sport.app.service.external.ApiWeatherIOService;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.*;

@Service
public class WeatherStatisticsServiceImpl implements WeatherStatisticsService {
    protected final ApiWeatherIOService weatherIOService;
    private final Database database;

    public WeatherStatisticsServiceImpl(ApiWeatherIOService weatherIOService, Database database) {
        this.weatherIOService = weatherIOService;
        this.database = database;
    }

    public Weather getWeatherByCity(String city, int nbDays) {
        String newCity = city;
        int newNbDays = nbDays;

        if (city == null || city.isEmpty()) newCity = database.getCityName();
        if (nbDays <= 0) newNbDays = database.getNbDays();

        return this.weatherIOService.getWeatherByCity(newCity, newNbDays);
    }

    public List<Day> getHottestDays(String city, int nbDays) {
        Weather weather = this.getWeatherByCity(city, nbDays);

        return weather.days().stream()
                .collect(groupingBy(
                        Day::tempMax,
                        TreeMap::new,
                        toList()
                ))
                .lastEntry()
                .getValue();
    }

    public List<Day> getRainDays(String city, int nbDays) {
        Weather weather = this.getWeatherByCity(city, nbDays);

        return weather.days().stream()
                .filter(
                        day -> day.hourlyData().stream()
                                .anyMatch(hour -> hour.precipitation() > 0)
                )
                .toList();
    }

    public Humidity getHumidityStats(String city, int nbDays) {
        Weather weather = this.getWeatherByCity(city, nbDays);

        int currentHumidity = getCurrentHumidity(weather.currentCondition());
        Map<String, Double> humidityAverage = getHumidityAverage(weather);
        Day driestDay = getDriestDay(weather);

        return new Humidity(currentHumidity, humidityAverage, driestDay);
    }

    public int getCurrentHumidity(CurrentCondition currentCondition) {
        return currentCondition.humidity();
    }

    public Map<String, Double> getHumidityAverage(Weather weather) {
        Map<String, Double> humidityAverages = new HashMap<>();

        Map<String, Double> f = weather.days()
                .stream()
                .collect(
                        toMap(
                                Day::day,
                                day -> day.hourlyData().stream()
                                        .collect(
                                                averagingInt(Hour::relativeHumidity)
                                        )
                        )
                );


        weather.days().forEach(day ->
                humidityAverages.put(day.day() + "_humidity_average",
                        day.hourlyData().stream()
                                .collect(
                                        averagingInt(Hour::relativeHumidity)
                                )
                )
        );

        return humidityAverages;
    }


    public Day getDriestDay(Weather weather) {
        Map<Day, Double> humidityAverages = new HashMap<>();

        weather.days().forEach(day ->
                humidityAverages.put(day,
                        day.hourlyData().stream()
                                .collect(
                                        averagingInt(Hour::relativeHumidity)
                                )
                )
        );

        return humidityAverages
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow()
                .getKey();
    }


    private static final int NO_PRECIPITATION = 0;
    private static final int SNOWING = 2;
    private static final int NO_WINDING = 70;


    public boolean isRainingByHour(Hour hour) {
        return hour.precipitation() > NO_PRECIPITATION;
    }

    public boolean isSnowingByHour(Hour hour) {
        return hour.isSnow() == SNOWING;
    }

    public boolean isWindingByHour(Hour hour) {
        return hour.windSpeed() > NO_WINDING;
    }
}
