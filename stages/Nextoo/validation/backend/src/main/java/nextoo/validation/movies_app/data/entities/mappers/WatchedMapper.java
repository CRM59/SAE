package nextoo.validation.movies_app.data.entities.mappers;

import nextoo.validation.movies_app.data.entities.MoviesWatchedEntity;
import nextoo.validation.movies_app.modele.MoviesWatched;
import org.springframework.stereotype.Component;

@Component
public class WatchedMapper {
    public MoviesWatched toWatched(MoviesWatchedEntity moviesWatchedEntity) {
        return new MoviesWatched(moviesWatchedEntity.getId().getPid().getId(), moviesWatchedEntity.getId().getMid());
    }
}
