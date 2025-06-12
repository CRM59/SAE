package nextoo.validation.movies_app.services.external.dto.mappers;

import nextoo.validation.movies_app.modele.MovieInfos;
import nextoo.validation.movies_app.services.external.dto.MovieInfosDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

import java.time.LocalDate;
import java.util.List;

@TestComponent
class MovieInfosMapperTests {

    private final MovieInfosMapper movieInfosMapper = new MovieInfosMapper();

    @Test
    void should_map_movie_infos() {
        MovieInfosDTO movieInfosDTO = new MovieInfosDTO(true, List.of(12,28), 1, "en", "overview", "title", 10, LocalDate.of(2020, 1, 1));

        MovieInfos movieInfos = movieInfosMapper.toMovieInfos(movieInfosDTO);

        Assertions.assertNotNull(movieInfos);
        Assertions.assertEquals(1, movieInfos.id());
    }
}
