package nextoo.weather.sport.app.service.external.dto.mappers;

import nextoo.weather.sport.app.models.CityInfo;
import nextoo.weather.sport.app.service.external.dto.CityInfoDTO;
import org.springframework.stereotype.Component;


@Component
public class CityInfoMapper {
    public CityInfo toModel(CityInfoDTO dto) {

        return new CityInfo(
                dto.name(),
                dto.country()
        );
    }
}
