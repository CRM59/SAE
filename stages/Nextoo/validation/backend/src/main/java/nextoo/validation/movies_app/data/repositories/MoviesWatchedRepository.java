package nextoo.validation.movies_app.data.repositories;

import nextoo.validation.movies_app.data.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MoviesWatchedRepository extends JpaRepository<MoviesWatchedEntity, MoviesWatchedEntityId> {
    List<MoviesWatchedEntity> findById_Pid(ProfileEntity profileEntity);
}
