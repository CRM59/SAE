package nextoo.weather.sport.app.data.repositories;

import nextoo.weather.sport.app.data.entities.PersonsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonsRepository extends JpaRepository<PersonsEntity, Integer> {
    PersonsEntity findIfExistByFirstNameAndLastNameIgnoreCase(String firstname, String lastname);
}
