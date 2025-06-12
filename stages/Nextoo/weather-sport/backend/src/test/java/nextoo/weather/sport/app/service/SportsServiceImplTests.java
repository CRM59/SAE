package nextoo.weather.sport.app.service;

import jakarta.persistence.PersistenceException;
import nextoo.weather.sport.app.data.entities.PreferencesEntity;
import nextoo.weather.sport.app.data.entities.SportsEntity;
import nextoo.weather.sport.app.data.repositories.SportsRepository;
import nextoo.weather.sport.app.exception.DatabaseAccessException;
import nextoo.weather.sport.app.models.*;
import nextoo.weather.sport.app.service.external.implementation.ApiWeatherIOServiceImpl;
import nextoo.weather.sport.app.service.implementation.SportsServiceImpl;
import nextoo.weather.sport.app.service.implementation.WeatherStatisticsServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class SportsServiceImplTests {

    @Mock
    private ApiWeatherIOServiceImpl apiWeatherIOService;

    @Mock
    private WeatherStatisticsServiceImpl weatherStatisticsService;


    @Mock
    private SportsRepository sportsRepository;

    @InjectMocks
    private SportsServiceImpl sportsService;


    public Weather getWeatherByCity() {
        return new Weather(
                getCityInfo(),
                getForecastInfo(),
                getCurrentCondition(),
                getDays()
        );
    }

    public CityInfo getCityInfo() {
        return new CityInfo(
                "Lille",
                "France"
        );
    }

    public ForecastInfo getForecastInfo() {
        return new ForecastInfo(
                10,
                15,
                10
        );
    }

    public CurrentCondition getCurrentCondition() {
        return new CurrentCondition(
                LocalDate.of(2025, 4, 11),
                LocalTime.of(11, 0),
                50,
                "Ensoleillé",
                "ensoleille",
                ""
        );
    }


    public List<Day> getDays() {
        List<Day> days = new ArrayList<>();

        days.add(new Day(
                LocalDate.of(2025, 4, 10),
                "Jeudi",
                3,
                16,
                "Ensoleillé",
                getHoursWithoutRain()));

        days.add(new Day(
                LocalDate.of(2025, 4, 11),
                "Vendredi",
                6,
                14,
                "Pluie faible",
                getHoursWithRain()));

        days.add(new Day(
                LocalDate.of(2025, 4, 12),
                "Samedi",
                4,
                24,
                "Ensoleillé",
                getHoursWithoutRain()));

        return days;
    }


    public List<Hour> getHoursWithoutRain() {
        List<Hour> hours = new ArrayList<>();

        hours.add(new Hour(LocalTime.of(0, 0),
                "Nuit claire",
                "nuit-claire",
                50,
                0,
                0,
                0));

        hours.add(new Hour(LocalTime.of(10, 0),
                "Ciel voilé",
                "ciel-voile",
                25,
                0,
                0,
                0));

        hours.add(new Hour(LocalTime.of(16, 0),
                "Ensoleillé",
                "ensoleille",
                0,
                0,
                0,
                0));

        return hours;
    }

    public List<Hour> getHoursWithRain() {
        List<Hour> hours = new ArrayList<>();

        hours.add(new Hour(LocalTime.of(0, 0),
                "Nuit claire",
                "nuit-claire",
                100,
                0,
                0,
                0));

        hours.add(new Hour(LocalTime.of(10, 0),
                "Pluie faible",
                "pluie-faible",
                50,
                2,
                0,
                0));

        hours.add(new Hour(LocalTime.of(16, 0),
                "Ensoleillé",
                "ensoleille",
                0,
                0,
                0,
                0));

        return hours;
    }


    public List<SportsEntity> getSports() {
        List<SportsEntity> sports = new ArrayList<>();
        sports.add(new SportsEntity(1, "Football", true, true, List.of(new PreferencesEntity(3, WeatherType.SUN))));
        sports.add(new SportsEntity(2, "Rugby", false, true, List.of(new PreferencesEntity(1, WeatherType.SUN))));
        sports.add(new SportsEntity(3, "Handball", true, false, List.of(new PreferencesEntity(2, WeatherType.RAIN))));
        sports.add(new SportsEntity(4, "Natation", true, true, List.of(new PreferencesEntity(4, WeatherType.RAIN))));
        sports.add(new SportsEntity(5, "Athlétisme", false, true, List.of(new PreferencesEntity(5, WeatherType.SUN))));
        sports.add(new SportsEntity(6, "Ski", false, true, List.of(new PreferencesEntity(6, WeatherType.SNOW))));

        return sports;
    }

    public List<SportsEntity> getIndoorSports() {
        List<SportsEntity> indoorSports = new ArrayList<>();
        indoorSports.add(new SportsEntity(1, "Football", true, true, List.of(new PreferencesEntity(3, WeatherType.SUN))));
        indoorSports.add(new SportsEntity(3, "Handball", true, false, List.of(new PreferencesEntity(2, WeatherType.RAIN))));
        indoorSports.add(new SportsEntity(4, "Natation", true, true, List.of(new PreferencesEntity(4, WeatherType.RAIN))));

        return indoorSports;
    }


    @Test
    public void should_return_all_sports() throws DatabaseAccessException {
        Mockito.when(sportsRepository.findAll()).thenReturn(this.getSports());

        List<Sport> sports = sportsService.getAllSports();

        Assertions.assertEquals("Football", sports.get(0).name());
        Assertions.assertEquals("Rugby", sports.get(1).name());
        Assertions.assertEquals("Handball", sports.get(2).name());
    }


    @Test
    public void should_return_10_as_hour_selected() {
        Mockito.when(weatherStatisticsService.getWeatherByCity("Lille", 5)).thenReturn(this.getWeatherByCity());

        List<LocalTime> hours = sportsService.getAllHours(
                        "Lille",
                        LocalDate.of(2025, 4, 10),
                        10
                )
                .stream()
                .map(Hour::hour)
                .toList();

        Assertions.assertEquals(10, hours.get(0).getHour());
    }


    @Test
    public void should_return_10_to_19_as_defaut_hour_selected_if_no_hour_specified() {
        Mockito.when(weatherStatisticsService.getWeatherByCity("Lille", 5))
                .thenReturn(this.getWeatherByCity());

        List<LocalTime> hours = sportsService.getAllHours(
                        "Lille",
                        LocalDate.of(2025, 4, 10),
                        null
                )
                .stream()
                .map(Hour::hour)
                .toList();

        Assertions.assertEquals(10, hours.get(1).getHour());
        Assertions.assertEquals(16, hours.get(0).getHour());
    }


    @Test
    public void should_return_all_indoor_sports() throws DatabaseAccessException {
        Mockito.when(sportsRepository.findAllByIndoorIsTrue())
                .thenReturn(this.getIndoorSports());


        List<Sport> sports = sportsService.getAllIndoorSports();

        Assertions.assertEquals("Football", sports.get(0).name());
        Assertions.assertEquals("Handball", sports.get(1).name());
        Assertions.assertEquals("Natation", sports.get(2).name());
    }

    @Test
    public void should_return_only_ski_for_snow_weather() throws DatabaseAccessException {
        Mockito.when(sportsRepository.findAllByPreferencesWeatherTypeInAndOutdoorIsTrueOrIndoorIsTrue(Set.of(WeatherType.SNOW)))
                .thenReturn(List.of(new SportsEntity(6, "Ski", false, true, List.of(new PreferencesEntity(6, WeatherType.SNOW)))));


        List<Sport> sports = sportsService.getAllSportsByWeather(List.of(WeatherType.SNOW));

        Assertions.assertEquals("Ski", sports.get(0).name());
    }


    @Test
    public void should_return_all_sports_by_date_and_hour_for_raining_day() throws DatabaseAccessException {
        Mockito.when(weatherStatisticsService.getWeatherByCity("Lille", 5))
                .thenReturn(this.getWeatherByCity());

        Mockito.when(weatherStatisticsService.isRainingByHour(this.getWeatherByCity().days().get(2).hourlyData().get(1)))
                .thenReturn(true);

        Mockito.when(sportsRepository.findAllByPreferencesWeatherTypeInAndOutdoorIsTrueOrIndoorIsTrue(Set.of(WeatherType.RAIN)))
                .thenReturn(this.getIndoorSports());

        List<Sport> sports = sportsService.getAllSportsByDate(
                "Lille",
                LocalDate.of(2025, 4, 11),
                10
        );

        Assertions.assertEquals("Football", sports.get(0).name());
        Assertions.assertEquals("Handball", sports.get(1).name());
        Assertions.assertEquals("Natation", sports.get(2).name());
    }


    @Test
    public void should_throw_PersistenceException() {
        Mockito.when(sportsRepository.findAll())
                .thenThrow(new PersistenceException());

        Assertions.assertThrows(DatabaseAccessException.class, () -> sportsService.getAllSports());
    }


    @Test
    public void should_save_new_sport() {
        SportsEntity sportsEntity = new SportsEntity();
        sportsEntity.setName("Golf");

        Mockito.when(
                        sportsRepository.save(
                                Mockito.any(SportsEntity.class)
                        )
                )
                .thenReturn(sportsEntity);

        Sport sport = sportsService.addNewSport(
                "Golf",
                false,
                true,
                List.of(WeatherType.SUN)
        );

        Assertions.assertEquals("Golf", sport.name());
    }



    @Test
    public void should_throw_Exception_if_sport_does_exists() {
        SportsEntity sportsEntity = new SportsEntity();
        sportsEntity.setName("Golf");
        Mockito.when(
                        sportsRepository.existsSportByNameIgnoreCase(
                                Mockito.anyString()
                        )
                )
                .thenThrow(new IllegalArgumentException());

        Assertions.assertThrows(IllegalArgumentException.class, () -> sportsService.addNewSport(
                "Golf",
                false,
                true,
                List.of(WeatherType.SUN)
        ));
    }



    @Test
    public void should_delete_a_sport() {
        SportsEntity sportsEntity = new SportsEntity();
        sportsEntity.setName("Golf");

        Mockito.when(
                        sportsRepository.findIfExistByNameIgnoreCase(
                                Mockito.anyString()
                        )
                )
                .thenReturn(sportsEntity);

        Sport sport = sportsService.deleteSport("Golf");

        Assertions.assertEquals("Golf", sport.name());
    }



    @Test
    public void should_throw_Exception_if_deleted_sport_does_not_exists() {
        SportsEntity sportsEntity = new SportsEntity();
        sportsEntity.setName("Golf");

        Mockito.when(
                        sportsRepository.findIfExistByNameIgnoreCase(
                                Mockito.anyString()
                        )
                )
                .thenReturn(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> sportsService.deleteSport("Golf"));
    }
}
