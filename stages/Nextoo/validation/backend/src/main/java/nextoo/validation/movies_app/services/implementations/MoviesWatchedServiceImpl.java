package nextoo.validation.movies_app.services.implementations;

import nextoo.validation.movies_app.data.dto.WatchedDTO;
import nextoo.validation.movies_app.data.entities.MoviesWatchedEntity;
import nextoo.validation.movies_app.data.entities.MoviesWatchedEntityId;
import nextoo.validation.movies_app.data.entities.ProfileEntity;
import nextoo.validation.movies_app.data.entities.mappers.WatchedMapper;
import nextoo.validation.movies_app.data.repositories.GenreRepository;
import nextoo.validation.movies_app.data.repositories.MoviesWatchedRepository;
import nextoo.validation.movies_app.data.repositories.ProfileRepository;
import nextoo.validation.movies_app.exceptions.ConflictException;
import nextoo.validation.movies_app.modele.MovieInfos;
import nextoo.validation.movies_app.modele.Movies;
import nextoo.validation.movies_app.modele.MoviesWatched;
import nextoo.validation.movies_app.services.MoviesService;
import nextoo.validation.movies_app.services.external.ApiMoviesIOService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MoviesWatchedServiceImpl implements MoviesService {
    private final ApiMoviesIOService apiMoviesIOService;
    private final MoviesWatchedRepository moviesWatchedRepository;
    private final ProfileRepository profileRepository;
    private final GenreRepository genreRepository;
    private final WatchedMapper watchedMapper;

    public MoviesWatchedServiceImpl(ApiMoviesIOService apiMoviesIOService, MoviesWatchedRepository moviesWatchedRepository, ProfileRepository profileRepository, GenreRepository genreRepository, WatchedMapper watchedMapper) {
        this.apiMoviesIOService = apiMoviesIOService;
        this.moviesWatchedRepository = moviesWatchedRepository;
        this.profileRepository = profileRepository;
        this.genreRepository = genreRepository;
        this.watchedMapper = watchedMapper;
    }

    public List<MovieInfos> getBestMovies(int pid) {
        ProfileEntity profileEntity = profileRepository.findById(pid).orElseThrow();
        Movies movies = getMoviesByProfilFilters(profileEntity, 1);
        List<Integer> moviesWatchedIds = getAllMoviesWatchedIds(profileEntity);

        do {
            addMoviesToList(movies, profileEntity);
            removeMoviesByWatched(movies, moviesWatchedIds);
            movies.setPage(movies.getPage() + 1);

        } while (movies.getMoviesList().size() < 10 && movies.getPage() <= movies.getTotalPage());


        if (movies.getMoviesList().size() < 10) {
            return movies.getMoviesList();
        }

        return movies.getMoviesList().subList(0, 10);
    }


    public void addMoviesToList(Movies movies, ProfileEntity profileEntity) {
        List<MovieInfos> moviesTmp = new ArrayList<>();
        moviesTmp.addAll(movies.getMoviesList());
        moviesTmp.addAll(getMoviesByProfilFilters(profileEntity, movies.getPage()).getMoviesList());
        movies.setMoviesList(moviesTmp);
    }


    public Movies getMoviesByProfilFilters(ProfileEntity profileEntity, int page) {
        return apiMoviesIOService.getMoviesByProfileOrYear(getFiltersUriParameters(profileEntity) + getGenresUriParameters(profileEntity) + "&page=" + page);
    }


    public void removeMoviesByWatched(Movies movies, List<Integer> moviesWatchedIds) {
        movies.getMoviesList().removeAll(movies.getMoviesList().stream().filter(movie -> moviesWatchedIds.contains(movie.id())).toList());
    }


    public List<Integer> getAllMoviesWatchedIds(ProfileEntity profileEntity) {
        return moviesWatchedRepository.findById_Pid(profileEntity).stream().map(MoviesWatchedEntity::getId).map(MoviesWatchedEntityId::getMid).toList();
    }


    public String getFiltersUriParameters(ProfileEntity profileEntity) {

        return String.format("&include_adult=%b&with_original_language=%s", profileEntity.getAdult(), profileEntity.getLang());
    }

    public String getGenresUriParameters(ProfileEntity profileEntity) {
        List<String> genres = genreRepository.findGenreEntitiesById_Pid(profileEntity).stream().map(genre ->
                genre.getId().getGid().toString()
        ).toList();

        return String.format("&with_genres=%s", String.join(",", genres));
    }


    public MoviesWatched addWatchedMovie(WatchedDTO watchedDTO) throws ConflictException {
        ProfileEntity profileEntity = profileRepository.findById(watchedDTO.getPid()).orElseThrow();

        MoviesWatchedEntityId moviesWatchedEntityId = new MoviesWatchedEntityId();
        moviesWatchedEntityId.setPid(profileEntity);
        moviesWatchedEntityId.setMid(watchedDTO.getMid());

        MoviesWatchedEntity moviesWatchedEntity = new MoviesWatchedEntity();
        moviesWatchedEntity.setId(moviesWatchedEntityId);

        if (moviesWatchedRepository.existsById(moviesWatchedEntityId)) {
            throw new ConflictException();
        }

        return watchedMapper.toWatched(moviesWatchedRepository.save(moviesWatchedEntity));
    }


    public Movies getBestMoviesPerYear(int year) {
        return apiMoviesIOService.getMoviesByProfileOrYear(String.format("&include_adult=true&include_video=false&page=1&year=%d", year));
    }
}
