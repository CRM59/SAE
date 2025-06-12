package nextoo.weather.sport.app.data.repositories;

import nextoo.weather.sport.app.data.entities.LicenseesEntity;
import nextoo.weather.sport.app.data.entities.LicenseesEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LicenseesRepository extends JpaRepository<LicenseesEntity, LicenseesEntityId>, CustomLicenseesRepository, JpaSpecificationExecutor<LicenseesEntity> {
}
