package nextoo.weather.sport.app.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import nextoo.weather.sport.app.data.dto.SportDTO;
import nextoo.weather.sport.app.exception.BadInputDTO;
import nextoo.weather.sport.app.exception.DatabaseAccessException;
import nextoo.weather.sport.app.models.Sport;
import nextoo.weather.sport.app.models.WeatherType;
import nextoo.weather.sport.app.service.implementation.SportsServiceImpl;
import nextoo.weather.sport.app.validators.SportValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(path = "/sports")
public class SportController {

    private final SportsServiceImpl sportsServiceImpl;
    private final SportValidator sportValidator;

    public SportController(SportsServiceImpl sportsServiceImpl, SportValidator sportValidator) {
        this.sportsServiceImpl = sportsServiceImpl;
        this.sportValidator = sportValidator;
    }

    @GetMapping
    public ResponseEntity<List<Sport>> getAllSports() throws DatabaseAccessException {
        return ResponseEntity.ok(this.sportsServiceImpl.getAllSports());
    }


    @GetMapping(path = "/indoor")
    public ResponseEntity<List<Sport>> getAllIndoorSports() throws DatabaseAccessException {
        return ResponseEntity.ok(this.sportsServiceImpl.getAllIndoorSports());
    }

    @GetMapping(path = "/by-weather")
    public ResponseEntity<List<Sport>> getAllSportsByWeather(@Valid @RequestParam Collection<WeatherType> weathers)
            throws DatabaseAccessException {
        return ResponseEntity.ok(this.sportsServiceImpl.getAllSportsByWeather(weathers));
    }

    @GetMapping(path = "/by-day-and-hour")
    public ResponseEntity<List<Sport>> getAllSportsByDay(@Valid @RequestParam String cityName,
                                                         @Valid @RequestParam Optional<LocalDate> date,
                                                         @Valid @RequestParam Optional<@Min(value=0) @Max(value=23) Integer> hour)
            throws DatabaseAccessException {

        List<Sport> sports = this.sportsServiceImpl.getAllSportsByDate(
                cityName,
                date.orElse(LocalDate.now()),
                hour.orElse(null)
        );

        return ResponseEntity.ok(sports);
    }

    @PostMapping(path = "/add-sport")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Sport> addNewSport(@RequestBody SportDTO sportDTO) throws BadInputDTO {
        sportValidator.validate(sportDTO);


        try {
            Sport newSport = this.sportsServiceImpl.addNewSport(
                    sportDTO.getName(),
                    sportDTO.isIndoor(),
                    sportDTO.isOutdoor(),
                    sportDTO.getWeatherTypes()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(newSport);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
    }


    @DeleteMapping(path = "/delete-sport/{sportName}")
    public ResponseEntity<Sport> deleteSport(@PathVariable String sportName) throws BadInputDTO {
        sportValidator.validateSportName(sportName);

        try {
            Sport deletedSport = this.sportsServiceImpl.deleteSport(sportName);

            return ResponseEntity.ok(deletedSport);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
