package nextoo.weather.sport.app.service;

import org.springframework.stereotype.Service;

@Service
public interface WeatherParametersService {
    void addWeatherParameters(String cityName, int nbDays);
}
