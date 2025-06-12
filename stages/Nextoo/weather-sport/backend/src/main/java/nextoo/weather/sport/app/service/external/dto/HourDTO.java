package nextoo.weather.sport.app.service.external.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record HourDTO(
        @JsonAlias("CONDITION")
        String condition,
        @JsonAlias("CONDITION_KEY")
        String conditionKey,
        @JsonAlias("RH2m")
        int humidity,
        @JsonAlias("APCPsfc")
        int precipitation,
        @JsonAlias("ISSNOW")
        int isSnow,
        @JsonAlias("WNDSPD10m")
        int windSpeed
) {
}
