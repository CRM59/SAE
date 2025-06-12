package nextoo.validation.movies_app.services;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import nextoo.validation.movies_app.data.dto.WatchedDTO;
import nextoo.validation.movies_app.exceptions.ConflictException;
import nextoo.validation.movies_app.modele.MovieInfos;
import nextoo.validation.movies_app.modele.Movies;
import nextoo.validation.movies_app.modele.MoviesWatched;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MoviesService {
    List<MovieInfos> getBestMovies(int pid);
    MoviesWatched addWatchedMovie(WatchedDTO watchedDTO) throws ConflictException;
    Movies getBestMoviesPerYear(@Valid @NotNull int year);
}
