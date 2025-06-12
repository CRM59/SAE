package nextoo.weather.sport.app.data.repositories;

import nextoo.weather.sport.app.data.entities.SportsEntity;
import nextoo.weather.sport.app.models.WeatherType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SportsRepository extends JpaRepository<SportsEntity, Integer> {
    List<SportsEntity> findAllByIndoorIsTrue();

    List<SportsEntity> findAllByPreferencesWeatherTypeInAndOutdoorIsTrueOrIndoorIsTrue(Set<WeatherType> weatherType);

    boolean existsSportByNameIgnoreCase(String name);
    SportsEntity findIfExistByNameIgnoreCase(String name);
}
