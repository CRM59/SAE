package nextoo.weather.sport.app.service.external;

import nextoo.weather.sport.app.models.CityInfo;
import nextoo.weather.sport.app.models.Weather;
import nextoo.weather.sport.app.service.external.dto.CityInfoDTO;
import nextoo.weather.sport.app.service.external.dto.WeatherDTO;
import nextoo.weather.sport.app.service.external.dto.mappers.WeatherMapper;
import nextoo.weather.sport.app.service.external.implementation.ApiWeatherIOServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

@ExtendWith(MockitoExtension.class)
public class ApiWeatherIOServiceImplTests {

    @Mock
    private RestClient restClient;

    @Mock
    private WeatherMapper weatherMapper;

    @InjectMocks
    private ApiWeatherIOServiceImpl ApiWeatherIOServiceImpl;


    public Weather generateWeather() {
        return new Weather(
                new CityInfo("Lille", "France"),
                null,
                null,
                null
        );
    }


    public WeatherDTO generateWeatherDTO() {
        return new WeatherDTO(
                new CityInfoDTO("Lille", "France"),
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    @Test
    public void should_return_data_for_Lille(){
        var mockRequest = Mockito.mock(RestClient.RequestHeadersUriSpec.class);
        var mockUri = Mockito.mock(RestClient.RequestHeadersUriSpec.class);
        var mockRetrieve = Mockito.mock(RestClient.ResponseSpec.class);

        Mockito.doReturn(mockRequest).when(restClient).get();
        Mockito.doReturn(mockUri).when(mockRequest).uri("/Lille");
        Mockito.doReturn(mockRetrieve).when(mockUri).retrieve();
        Mockito.doReturn(generateWeatherDTO()).when(mockRetrieve).body(WeatherDTO.class);

        Mockito.doReturn(generateWeather()).when(weatherMapper).toModel(generateWeatherDTO(), 5);

        Weather weather = ApiWeatherIOServiceImpl.getWeatherByCity("Lille",5);

        Assertions.assertNotNull(weather);
        Assertions.assertEquals("Lille", weather.cityInfo().name());
    }
}
