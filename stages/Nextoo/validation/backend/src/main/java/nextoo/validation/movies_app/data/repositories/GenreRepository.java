package nextoo.validation.movies_app.data.repositories;

import nextoo.validation.movies_app.data.entities.GenreEntity;
import nextoo.validation.movies_app.data.entities.GenreEntityId;
import nextoo.validation.movies_app.data.entities.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, GenreEntityId> {
    List<GenreEntity> findGenreEntitiesById_Pid(ProfileEntity profileEntity);
}
