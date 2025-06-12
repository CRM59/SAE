package nextoo.validation.movies_app.controllers;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import nextoo.validation.movies_app.data.dto.WatchedDTO;
import nextoo.validation.movies_app.exceptions.ConflictException;
import nextoo.validation.movies_app.modele.MovieInfos;
import nextoo.validation.movies_app.modele.Movies;
import nextoo.validation.movies_app.modele.MoviesWatched;
import nextoo.validation.movies_app.services.MoviesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(path="/movies")
public class MoviesController {
    private final MoviesService moviesService;

    public MoviesController(MoviesService moviesService) {
        this.moviesService = moviesService;
    }


    @GetMapping(path = "/{pid}")
    public ResponseEntity<List<MovieInfos>> getMoviesByFilters(@Valid @NotNull @PathVariable @Min(value=0) int pid) {
        return ResponseEntity.ok(moviesService.getBestMovies(pid));
    }


    @PostMapping(path = "/watched")
    public ResponseEntity<MoviesWatched> getMoviesByFilters(@RequestBody @NotNull WatchedDTO watchedDTO) throws ConflictException {
        return ResponseEntity.status(HttpStatus.CREATED).body(moviesService.addWatchedMovie(watchedDTO));
    }


    @GetMapping(path = "/best-movies-per-year/{year}")
    public ResponseEntity<Movies> getBestMoviesByYear(@Valid @NotNull @PathVariable int year) {
        return ResponseEntity.status(HttpStatus.CREATED).body(moviesService.getBestMoviesPerYear(year));
    }
}
