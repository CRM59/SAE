package nextoo.validation.movies_app.services.external.implementations;

import nextoo.validation.movies_app.modele.MovieInfos;
import nextoo.validation.movies_app.modele.Movies;
import nextoo.validation.movies_app.services.external.dto.MovieInfosDTO;
import nextoo.validation.movies_app.services.external.dto.MoviesDTO;
import nextoo.validation.movies_app.services.external.dto.mappers.MoviesMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ApiMoviesIOServiceImplTests {

    @Mock
    private RestClient restClient;

    @Mock
    private MoviesMapper moviesMapper;

    @InjectMocks
    private ApiMoviesIOServiceImpl apiMoviesIOServiceImpl;


    public Movies generateMovies() {
        return new Movies(
                1,
                List.of(new MovieInfos(100,"title", "overview", "fr", List.of(12,28), true, 10, LocalDate.of(2020,1,1))),
                1
        );
    }


    public MoviesDTO generateMoviesDTO() {
        return new MoviesDTO(
                1,
                List.of(new MovieInfosDTO(true, List.of(12,28), 100, "fr", "overview","title", 10, LocalDate.of(2020,1,1))),
                1
        );
    }

    @Test
    void should_return_movies_from_api(){
        var mockRequest = Mockito.mock(RestClient.RequestHeadersUriSpec.class);
        var mockUri = Mockito.mock(RestClient.RequestHeadersUriSpec.class);
        var mockRetrieve = Mockito.mock(RestClient.ResponseSpec.class);


        Mockito.doReturn(mockRequest).when(restClient).get();
        Mockito.doReturn(mockUri).when(mockRequest).uri(Mockito.anyString());
        Mockito.doReturn(mockRetrieve).when(mockUri).retrieve();
        Mockito.doReturn(generateMoviesDTO()).when(mockRetrieve).body(MoviesDTO.class);

        Mockito.doReturn(generateMovies()).when(moviesMapper).toMovies(Mockito.any(MoviesDTO.class));

        Movies movies = apiMoviesIOServiceImpl.getMoviesByProfileOrYear("");

        Assertions.assertNotNull(movies);
        Assertions.assertEquals(100, movies.getMoviesList().get(0).id());
    }
}
