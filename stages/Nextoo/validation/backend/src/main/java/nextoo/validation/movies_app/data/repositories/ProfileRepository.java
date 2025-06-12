package nextoo.validation.movies_app.data.repositories;

import nextoo.validation.movies_app.data.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {

}
