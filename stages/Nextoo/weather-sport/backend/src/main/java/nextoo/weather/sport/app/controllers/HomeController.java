package nextoo.weather.sport.app.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import nextoo.weather.sport.app.models.Day;
import nextoo.weather.sport.app.models.Humidity;
import nextoo.weather.sport.app.models.Weather;
import nextoo.weather.sport.app.models.WeatherParameters;
import nextoo.weather.sport.app.service.implementation.WeatherParametersServiceImpl;
import nextoo.weather.sport.app.service.implementation.WeatherStatisticsServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/weather")
public class HomeController {
    private final WeatherStatisticsServiceImpl weatherStatisticsServiceImpl;
    private final WeatherParametersServiceImpl weatherParametersServiceImpl;

    public HomeController(WeatherStatisticsServiceImpl weatherStatisticsServiceImpl, WeatherParametersServiceImpl weatherParametersServiceImpl) {
        this.weatherStatisticsServiceImpl = weatherStatisticsServiceImpl;
        this.weatherParametersServiceImpl = weatherParametersServiceImpl;
    }

    @PostMapping("/add-infos")
    public void addWeatherParameters(@Valid @RequestBody WeatherParameters parameters) {
        weatherParametersServiceImpl.addWeatherParameters(parameters.cityName(), parameters.nbDays());
    }

    @PutMapping("/update-infos")
    public void updateWeatherParameters(@Valid @RequestBody WeatherParameters parameters) {
        weatherParametersServiceImpl.updateWeatherParameters(parameters.cityName(), parameters.nbDays());
    }

    @DeleteMapping("/remove-infos")
    public void removeWeatherParameters() {
        weatherParametersServiceImpl.removeWeatherParameters();
    }

    @GetMapping("/city")
    public ResponseEntity<Weather> getWeatherByCity(@RequestParam String cityName, @RequestParam Optional<Integer> nbDays) {
        return ResponseEntity.ok(this.weatherStatisticsServiceImpl.getWeatherByCity(cityName, nbDays.orElse(0)));
    }

    @GetMapping("/city/hottest-days")
    public ResponseEntity<List<Day>> getHottestDays(@RequestParam String cityName, @RequestParam @Min(value=1) @Max(value=5) int nbDays) {
        return ResponseEntity.ok(this.weatherStatisticsServiceImpl.getHottestDays(cityName, nbDays));
    }

    @GetMapping("/city/rain-days")
    public ResponseEntity<List<Day>> getRainDays(@RequestParam String cityName, @RequestParam @Min(value=1) @Max(value=5) int nbDays) {
        return ResponseEntity.ok(this.weatherStatisticsServiceImpl.getRainDays(cityName, nbDays));
    }

    @GetMapping("/city/humidity")
    public ResponseEntity<Humidity> getHumidity(@RequestParam String cityName, @RequestParam @Min(value=1) @Max(value=5) int nbDays) {
        return ResponseEntity.ok(this.weatherStatisticsServiceImpl.getHumidityStats(cityName, nbDays));
    }
}
