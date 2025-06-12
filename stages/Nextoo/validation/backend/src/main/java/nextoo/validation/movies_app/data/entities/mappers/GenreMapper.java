package nextoo.validation.movies_app.data.entities.mappers;

import nextoo.validation.movies_app.data.entities.GenreEntity;
import nextoo.validation.movies_app.modele.Genre;
import org.springframework.stereotype.Component;

@Component
public class GenreMapper {
    public Genre toGenre(GenreEntity genreEntity) {
        return new Genre(genreEntity.getId().getPid().getId(), genreEntity.getId().getGid());
    }
}
