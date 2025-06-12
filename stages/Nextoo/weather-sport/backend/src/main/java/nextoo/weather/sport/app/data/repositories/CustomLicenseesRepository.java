package nextoo.weather.sport.app.data.repositories;

import nextoo.weather.sport.app.data.entities.LicenseesEntity;
import nextoo.weather.sport.app.models.wrappers.criteria.LicenseesCriteriaWrapper;

import java.util.List;

public interface CustomLicenseesRepository {
    List<LicenseesEntity> findAllByCriteria(LicenseesCriteriaWrapper criteria);
}
