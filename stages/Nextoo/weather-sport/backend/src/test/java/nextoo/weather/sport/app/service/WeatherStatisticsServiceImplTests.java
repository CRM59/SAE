package nextoo.weather.sport.app.service;

import nextoo.weather.sport.app.models.*;
import nextoo.weather.sport.app.service.external.implementation.ApiWeatherIOServiceImpl;
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

@ExtendWith(MockitoExtension.class)
public class WeatherStatisticsServiceImplTests {

    @Mock
    private ApiWeatherIOServiceImpl apiWeatherIOServiceImpl;

    @InjectMocks
    private WeatherStatisticsServiceImpl weatherStatisticsServiceImpl;


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
                LocalDate.of(2025,4,11),
                LocalTime.of(11,0),
                50,
                "Ensoleillé",
                "ensoleille",
                ""
        );
    }


    public List<Day> getDays() {
        List<Day> days = new ArrayList<>();

        days.add(new Day(
                LocalDate.of(2025,04,10),
                "Jeudi",
                3,
                16,
                "Ensoleillé",
                getHoursWithoutRain()));

        days.add(new Day(
                LocalDate.of(2025,04,11),
                "Vendredi",
                6,
                14,
                "Pluie faible",
                getHoursWithRain()));

        days.add(new Day(
                LocalDate.of(2025,04,12),
                "Samedi",
                4,
                24,
                "Ensoleillé",
                getHoursWithoutRain()));

        return days;
    }


    public List<Hour> getHoursWithoutRain() {
        List<Hour> hours = new ArrayList<>();

        hours.add(new Hour(LocalTime.of(0,0),
                "Nuit claire",
                "nuit-claire",
                50,
                0,
                0,
                0));

        hours.add(new Hour(LocalTime.of(10,0),
                "Ciel voilé",
                "ciel-voile",
                25,
                0,
                0,
                0));

        hours.add(new Hour(LocalTime.of(16,0),
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

        hours.add(new Hour(LocalTime.of(0,0),
                "Nuit claire",
                "nuit-claire",
                100,
                0,
                0,
                0));

        hours.add(new Hour(LocalTime.of(10,0),
                "Pluie faible",
                "pluie-faible",
                50,
                2,
                0,
                0));

        hours.add(new Hour(LocalTime.of(16,0),
                "Ensoleillé",
                "ensoleille",
                0,
                0,
                0,
                0));

        return hours;
    }







    @Test
    public void should_return_Samedi_as_hottest_day(){
        Mockito.when(apiWeatherIOServiceImpl.getWeatherByCity(Mockito.anyString(),Mockito.anyInt()))
                .thenReturn(this.getWeatherByCity());

        Assertions.assertEquals("Samedi", weatherStatisticsServiceImpl
                                                        .getHottestDays("Lille",5)
                                                        .get(0)
                                                        .day());
    }

    @Test
    public void should_return_Vendredi_as_rain_day(){
        Mockito.when(apiWeatherIOServiceImpl.getWeatherByCity(Mockito.anyString(),Mockito.anyInt()))
                .thenReturn(this.getWeatherByCity());

        Assertions.assertEquals("Vendredi", weatherStatisticsServiceImpl
                                                        .getRainDays("Lille",5)
                                                        .get(0)
                                                        .day());
    }

    @Test
    public void should_return_50_as_current_humidity(){
        Mockito.when(apiWeatherIOServiceImpl.getWeatherByCity(Mockito.anyString(),Mockito.anyInt()))
                .thenReturn(this.getWeatherByCity());

        Weather weather = weatherStatisticsServiceImpl.getWeatherByCity("Lille",5);

        Assertions.assertEquals(50, weatherStatisticsServiceImpl.getCurrentHumidity(weather.currentCondition()));
    }

    @Test
    public void should_return_25_as_Jeudi_humidity_average_of(){
        Mockito.when(apiWeatherIOServiceImpl.getWeatherByCity(Mockito.anyString(),Mockito.anyInt()))
                .thenReturn(this.getWeatherByCity());

        Weather weather = weatherStatisticsServiceImpl.getWeatherByCity("Lille",5);

        Assertions.assertEquals(25, weatherStatisticsServiceImpl
                                                        .getHumidityAverage(weather)
                                                        .get("Jeudi_humidity_average"));
    }

    @Test
    public void should_return_50_as_Vendredi_humidity_average(){
        Mockito.when(apiWeatherIOServiceImpl.getWeatherByCity(Mockito.anyString(),Mockito.anyInt()))
                .thenReturn(this.getWeatherByCity());

        Weather weather = weatherStatisticsServiceImpl.getWeatherByCity("Lille",5);

        Assertions.assertEquals(50, weatherStatisticsServiceImpl
                                                        .getHumidityAverage(weather)
                                                        .get("Vendredi_humidity_average"));
    }

    @Test
    public void should_return_Samedi_as_driest_day(){
        Mockito.when(apiWeatherIOServiceImpl.getWeatherByCity(Mockito.anyString(),Mockito.anyInt()))
                .thenReturn(this.getWeatherByCity());

        Weather weather = weatherStatisticsServiceImpl.getWeatherByCity("Lille",5);

        Assertions.assertEquals("Samedi", weatherStatisticsServiceImpl
                                                        .getDriestDay(weather)
                                                        .day());
    }
}
