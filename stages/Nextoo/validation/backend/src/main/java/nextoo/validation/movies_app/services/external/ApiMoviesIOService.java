package nextoo.validation.movies_app.services.external;

import nextoo.validation.movies_app.modele.Movies;
import org.springframework.stereotype.Service;

@Service
public interface ApiMoviesIOService {
    Movies getMoviesByProfileOrYear(String uriParameters);
}
