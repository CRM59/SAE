package nextoo.validation.movies_app.services.external.implementations;

import nextoo.validation.movies_app.modele.Movies;
import nextoo.validation.movies_app.services.external.ApiMoviesIOService;
import nextoo.validation.movies_app.services.external.dto.MoviesDTO;
import nextoo.validation.movies_app.services.external.dto.mappers.MoviesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Objects;

@Service
public class ApiMoviesIOServiceImpl implements ApiMoviesIOService {
    private @Value("${api.key}") String apiKey;
    private final MoviesMapper moviesMapper;
    private final RestClient restClient;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiMoviesIOServiceImpl.class);

    public ApiMoviesIOServiceImpl(MoviesMapper moviesMapper, RestClient restClient) {
        this.moviesMapper = moviesMapper;
        this.restClient = restClient;
    }

    public Movies getMoviesByProfileOrYear(String uriParameters) {
        MoviesDTO moviesDTO = restClient.get()
                .uri("/discover/movie?api_key=" + this.apiKey + "&sort_by=vote_average.desc" + uriParameters)
                .retrieve()
                .body(MoviesDTO.class);
        try {
            return this.moviesMapper.toMovies(Objects.requireNonNull(moviesDTO));
        } catch (RestClientException except) {
            LOGGER.error("Rest Client Exception");
            throw except;
        }
    }
}
