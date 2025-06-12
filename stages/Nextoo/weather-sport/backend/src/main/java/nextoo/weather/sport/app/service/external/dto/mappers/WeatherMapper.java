package nextoo.weather.sport.app.service.external.dto.mappers;

import nextoo.weather.sport.app.models.*;
import nextoo.weather.sport.app.service.external.dto.WeatherDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherMapper {
    private final CurrentConditionMapper currentConditionMapper;
    private final ForecastInfoMapper forecastInfoMapper;
    private final CityInfoMapper cityInfoMapper;
    private final DayMapper dayMapper;

    public WeatherMapper(CurrentConditionMapper currentConditionMapper,
                         DayMapper dayMapper,
                         CityInfoMapper cityInfoMapper,
                         ForecastInfoMapper forecastInfoMapper) {
        this.currentConditionMapper = currentConditionMapper;
        this.forecastInfoMapper = forecastInfoMapper;
        this.cityInfoMapper = cityInfoMapper;
        this.dayMapper = dayMapper;
    }

    public Weather toModel(WeatherDTO dto, int nbDays) {
        if (dto.cityInfo() == null) return null;

        CityInfo cityInfo = cityInfoMapper.toModel(dto.cityInfo());
        ForecastInfo forecastInfo = forecastInfoMapper.toModel(dto.forecastInfo());
        CurrentCondition currentCondition = currentConditionMapper.toModel(dto.currentCondition());

        Day today = dayMapper.toModel(dto.day0());
        Day tomorrow = dayMapper.toModel(dto.day1());
        Day thirdDay = dayMapper.toModel(dto.day2());
        Day fourthDay = dayMapper.toModel(dto.day3());
        Day lastDay = dayMapper.toModel(dto.day4());

        return new Weather(
                cityInfo,
                forecastInfo,
                currentCondition,
                List.of(today, tomorrow, thirdDay, fourthDay, lastDay).subList(0, nbDays)
        );
    }
}
