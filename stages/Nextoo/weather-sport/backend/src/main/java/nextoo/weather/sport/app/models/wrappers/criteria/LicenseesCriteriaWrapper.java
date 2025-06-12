package nextoo.weather.sport.app.models.wrappers.criteria;

import nextoo.weather.sport.app.models.WeatherType;

import java.util.List;
import java.util.Optional;

public record LicenseesCriteriaWrapper(
        Optional<Boolean> active,
        Optional<String> sportName,
        Optional<Boolean> isIndoor,
        Optional<Boolean> isOutdoor,
        Optional<List<WeatherType>> weathersTypes
) {
}
