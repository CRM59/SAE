package nextoo.weather.sport.app.service.external.implementation;

import nextoo.weather.sport.app.models.Weather;
import nextoo.weather.sport.app.service.external.ApiWeatherIOService;
import nextoo.weather.sport.app.service.external.dto.WeatherDTO;
import nextoo.weather.sport.app.service.external.dto.mappers.WeatherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Objects;

@Service
public class ApiWeatherIOServiceImpl implements ApiWeatherIOService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiWeatherIOServiceImpl.class);
    private final RestClient restClient;
    private final WeatherMapper weatherMapper;

    public ApiWeatherIOServiceImpl(@Qualifier("previsionMeteoAPI") RestClient restClient, WeatherMapper weatherMapper) {
        this.restClient = restClient;
        this.weatherMapper = weatherMapper;
    }

    public Weather getWeatherByCity(String city, int nbDays) {
        WeatherDTO weatherDTO = restClient.get()
                .uri("/" + city)
                .retrieve()
                .body(WeatherDTO.class);
        try {
            return this.weatherMapper.toModel(Objects.requireNonNull(weatherDTO), nbDays);
        } catch (RestClientException except) {
            LOGGER.error("Rest Client Exception");
            throw except;
        }
    }
}
