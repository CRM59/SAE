package nextoo.validation.movies_app.services.external.dto.mappers;

import nextoo.validation.movies_app.modele.MovieInfos;
import nextoo.validation.movies_app.services.external.dto.MovieInfosDTO;
import org.springframework.stereotype.Component;

@Component
public class MovieInfosMapper {
    public MovieInfos toMovieInfos(MovieInfosDTO movieInfosDTO) {
        return new MovieInfos(
                movieInfosDTO.id(),
                movieInfosDTO.title(),
                movieInfosDTO.overview(),
                movieInfosDTO.lang(),
                movieInfosDTO.genres(),
                movieInfosDTO.adult(),
                movieInfosDTO.rating(),
                movieInfosDTO.releaseDate()
        );
    }
}
