package nextoo.weather.sport.app.service.external.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record WeatherDTO(
        @JsonAlias("city_info")
        CityInfoDTO cityInfo,
        @JsonAlias("forecast_info")
        ForecastInfoDTO forecastInfo,
        @JsonAlias("current_condition")
        CurrentConditionDTO currentCondition,
        @JsonAlias("fcst_day_0")
        DayDTO day0,
        @JsonAlias("fcst_day_1")
        DayDTO day1,
        @JsonAlias("fcst_day_2")
        DayDTO day2,
        @JsonAlias("fcst_day_3")
        DayDTO day3,
        @JsonAlias("fcst_day_4")
        DayDTO day4
) {
}
