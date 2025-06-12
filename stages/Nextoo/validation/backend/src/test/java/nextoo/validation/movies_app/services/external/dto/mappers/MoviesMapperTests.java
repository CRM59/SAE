package nextoo.validation.movies_app.services.external.dto.mappers;

import nextoo.validation.movies_app.modele.Movies;
import nextoo.validation.movies_app.services.external.dto.MovieInfosDTO;
import nextoo.validation.movies_app.services.external.dto.MoviesDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

import java.time.LocalDate;
import java.util.List;

@TestComponent
class MoviesMapperTests {

    private final MoviesMapper moviesMapper = new MoviesMapper(new MovieInfosMapper());

    @Test
    void should_map_movie_infos() {
        MovieInfosDTO movieInfosDTO = new MovieInfosDTO(true, List.of(12,28), 1, "en", "overview", "title", 10, LocalDate.of(2020,1,1));

        MoviesDTO moviesDTO = new MoviesDTO(2,List.of(movieInfosDTO), 5);

        Movies movies = moviesMapper.toMovies(moviesDTO);

        Assertions.assertNotNull(movies);
        Assertions.assertEquals(2, movies.getPage());
        Assertions.assertEquals(1, movies.getMoviesList().get(0).id());
    }
}
