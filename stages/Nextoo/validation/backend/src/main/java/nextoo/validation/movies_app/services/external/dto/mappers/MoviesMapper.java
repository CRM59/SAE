package nextoo.validation.movies_app.services.external.dto.mappers;

import nextoo.validation.movies_app.modele.Movies;
import nextoo.validation.movies_app.services.external.dto.MoviesDTO;
import org.springframework.stereotype.Component;

@Component
public class MoviesMapper {
    private final MovieInfosMapper movieInfosMapper;

    public MoviesMapper(MovieInfosMapper movieInfosMapper) {
        this.movieInfosMapper = movieInfosMapper;
    }

    public Movies toMovies(MoviesDTO moviesDTO) {
        return new Movies(
                moviesDTO.getPage(),
                moviesDTO.getResults()
                        .stream()
                        .map(movieInfosMapper::toMovieInfos)
                        .toList(),
                moviesDTO.getTotalPages()
        );
    }
}
