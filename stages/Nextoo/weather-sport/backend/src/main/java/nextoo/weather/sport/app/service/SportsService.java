package nextoo.weather.sport.app.service;

import nextoo.weather.sport.app.exception.DatabaseAccessException;
import nextoo.weather.sport.app.models.Hour;
import nextoo.weather.sport.app.models.Sport;
import nextoo.weather.sport.app.models.WeatherType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Service
public interface SportsService {
    List<Sport> getAllSports() throws DatabaseAccessException;

    Set<Hour> getAllHours(String cityName, LocalDate date, Integer hour);

    List<Sport> getAllIndoorSports() throws DatabaseAccessException;

    List<Sport> getAllSportsByWeather(Collection<WeatherType> weathers) throws DatabaseAccessException;

    List<Sport> getAllSportsByDate(String cityName, LocalDate date, Integer hourIndex) throws DatabaseAccessException;
}
