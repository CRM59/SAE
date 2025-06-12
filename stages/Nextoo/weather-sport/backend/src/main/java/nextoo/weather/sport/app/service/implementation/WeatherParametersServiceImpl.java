package nextoo.weather.sport.app.service.implementation;

import nextoo.weather.sport.app.data.repositories.WeatherRepository;
import nextoo.weather.sport.app.service.WeatherParametersService;
import org.springframework.stereotype.Service;

@Service
public class WeatherParametersServiceImpl implements WeatherParametersService {

    private final WeatherRepository weatherRepository;

    public WeatherParametersServiceImpl(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public void updateWeatherParameters(String cityName, int nbDays) {
        if (cityName != null && !cityName.isEmpty()) weatherRepository.updateCityName(cityName);
        if (nbDays > 0 && nbDays <= 5) weatherRepository.updateNbDays(nbDays);
    }

    public void addWeatherParameters(String cityName, int nbDays) {
        if (cityName != null && !cityName.isEmpty()) weatherRepository.addCityName(cityName);
        if (nbDays > 0 && nbDays <= 5) weatherRepository.addNbDays(nbDays);
    }

    public void removeWeatherParameters() {
        weatherRepository.removeInfos();
    }
}
