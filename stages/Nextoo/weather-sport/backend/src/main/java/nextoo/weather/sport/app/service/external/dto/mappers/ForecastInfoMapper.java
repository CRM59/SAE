package nextoo.weather.sport.app.service.external.dto.mappers;

import nextoo.weather.sport.app.models.ForecastInfo;
import nextoo.weather.sport.app.service.external.dto.ForecastInfoDTO;
import org.springframework.stereotype.Component;


@Component
public class ForecastInfoMapper {
    public ForecastInfo toModel(ForecastInfoDTO dto) {

        return new ForecastInfo(
                dto.latitude(),
                dto.longitude(),
                dto.elevation()
        );
    }
}
