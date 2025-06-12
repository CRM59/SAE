package nextoo.weather.sport.app.service.external;

import nextoo.weather.sport.app.models.Weather;


public interface ApiWeatherIOService {
    Weather getWeatherByCity(String city, int nbDays);
}
