package nextoo.weather.sport.app.service.implementation;

import jakarta.persistence.PersistenceException;
import nextoo.weather.sport.app.config.ConfigurationApp;
import nextoo.weather.sport.app.data.entities.PreferencesEntity;
import nextoo.weather.sport.app.data.entities.SportsEntity;
import nextoo.weather.sport.app.data.repositories.SportsRepository;
import nextoo.weather.sport.app.exception.DatabaseAccessException;
import nextoo.weather.sport.app.models.Hour;
import nextoo.weather.sport.app.models.Sport;
import nextoo.weather.sport.app.models.WeatherType;
import nextoo.weather.sport.app.service.SportsService;
import nextoo.weather.sport.app.service.WeatherStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SportsServiceImpl implements SportsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SportsServiceImpl.class);

    private final SportsRepository sportsRepository;
    private final WeatherStatisticsService weatherStatisticsService;

    public SportsServiceImpl(SportsRepository sportsRepository,
                             WeatherStatisticsService weatherStatisticsService) {
        this.sportsRepository = sportsRepository;
        this.weatherStatisticsService = weatherStatisticsService;
    }

    public List<Sport> getAllSports() throws DatabaseAccessException {
        try {
            return this.sportsRepository.findAll()
                    .stream()
                    .map(SportsEntity::toSport
                    ).toList();
        } catch (PersistenceException e) {
            LOGGER.error("Database Access Exception");
            throw new DatabaseAccessException();
        }
    }

    public List<Sport> getAllIndoorSports() throws DatabaseAccessException {
        try {
            return this.sportsRepository.findAllByIndoorIsTrue()
                    .stream()
                    .map(SportsEntity::toSport)
                    .toList();
        } catch (PersistenceException e) {
            throw new DatabaseAccessException();
        }
    }

    public List<Sport> getAllSportsByWeather(Collection<WeatherType> weathers)
            throws DatabaseAccessException {
        try {
            return this.sportsRepository
                    .findAllByPreferencesWeatherTypeInAndOutdoorIsTrueOrIndoorIsTrue(new HashSet<>(weathers))
                    .stream()
                    .map(SportsEntity::toSport)
                    .toList();
        } catch (PersistenceException e) {
            throw new DatabaseAccessException();
        }
    }


    public List<Sport> getAllSportsByDate(String cityName, LocalDate date, Integer hourIndex)
            throws DatabaseAccessException {

        Set<WeatherType> weathers = getAllHours(cityName, date, hourIndex)
                .stream()
                .flatMap(hour -> generateWeathersByHour(hour).stream())
                .collect(Collectors.toSet());

        try {
            return this.sportsRepository.findAllByPreferencesWeatherTypeInAndOutdoorIsTrueOrIndoorIsTrue(weathers)
                    .stream()
                    .map(SportsEntity::toSport)
                    .collect(Collectors.toList());
        } catch (PersistenceException e) {
            throw new DatabaseAccessException();
        }
    }


    public Set<Hour> getAllHours(String cityName, LocalDate date, Integer hourIndex) {
        return this.weatherStatisticsService.getWeatherByCity(cityName, ConfigurationApp.WEEK_DAYS_NUMBER)
                .days()
                .stream()
                .filter(dayTmp -> dayTmp.date().isEqual(date))
                .findFirst()
                .orElseThrow()
                .hourlyData()
                .stream()
                .filter(
                        hour -> hourIndex != null ?
                                isCorrectHour(hour, hourIndex) :
                                hourIsUnderWeatherHours(hour))
                .collect(Collectors.toSet());
    }

    public boolean isCorrectHour(Hour hour, Integer hourIndex) {
        return hour.hour().getHour() == hourIndex;
    }

    public boolean hourIsUnderWeatherHours(Hour hour) {
        return (hour.hour().getHour() >=ConfigurationApp. WEATHER_START_HOUR
                && hour.hour().getHour() < ConfigurationApp.WEATHER_END_HOUR);
    }


    public Set<WeatherType> generateWeathersByHour(Hour hour) {

        Set<WeatherType> weathersTmp = new HashSet<>();

        if (weatherStatisticsService.isRainingByHour(hour)) {
            if (weatherStatisticsService.isSnowingByHour(hour)) {
                weathersTmp.add(WeatherType.SNOW);
            } else {
                weathersTmp.add(WeatherType.RAIN);
            }
        } else {
            weathersTmp.add(WeatherType.SUN);
        }

        if (weatherStatisticsService.isWindingByHour(hour)) {
            weathersTmp.add(WeatherType.WIND);
        }

        return weathersTmp;
    }


    public Sport addNewSport(String sportName, boolean indoor, boolean outdoor, List<WeatherType> weatherTypes) {

        if (sportsRepository.existsSportByNameIgnoreCase(sportName)) {
            throw new IllegalArgumentException("Sport already exists");
        }

        SportsEntity sportEntity = new SportsEntity();

        sportEntity.setName(sportName);
        sportEntity.setIndoor(indoor);
        sportEntity.setOutdoor(outdoor);

        sportEntity.setPreferences(
                weatherTypes.stream()
                        .map(weatherType -> generatePreferencesEntity(sportEntity, weatherType))
                        .toList());


        return sportsRepository.save(sportEntity).toSport();
    }


    public PreferencesEntity generatePreferencesEntity(SportsEntity sportEntity, WeatherType weatherType) {

        PreferencesEntity preferencesEntity = new PreferencesEntity();
        preferencesEntity.setSportId(sportEntity);
        preferencesEntity.setWeatherType(weatherType);

        return preferencesEntity;
    }


    public Sport deleteSport(String sportName) {
        SportsEntity sportEntity = sportsRepository.findIfExistByNameIgnoreCase(sportName);

        if(sportEntity == null){
            throw new IllegalArgumentException("Sports with name " + sportName + " not found");
        }

        sportsRepository.delete(sportEntity);
        return sportEntity.toSport();
    }
}
