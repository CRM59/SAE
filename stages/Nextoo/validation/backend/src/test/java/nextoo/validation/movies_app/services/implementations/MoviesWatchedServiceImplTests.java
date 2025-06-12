package nextoo.validation.movies_app.services.implementations;

import nextoo.validation.movies_app.data.dto.WatchedDTO;
import nextoo.validation.movies_app.data.entities.*;
import nextoo.validation.movies_app.data.entities.mappers.WatchedMapper;
import nextoo.validation.movies_app.data.repositories.GenreRepository;
import nextoo.validation.movies_app.data.repositories.ProfileRepository;
import nextoo.validation.movies_app.data.repositories.MoviesWatchedRepository;
import nextoo.validation.movies_app.exceptions.ConflictException;
import nextoo.validation.movies_app.modele.MovieInfos;
import nextoo.validation.movies_app.modele.Movies;
import nextoo.validation.movies_app.services.external.ApiMoviesIOService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(MockitoExtension.class)
class MoviesWatchedServiceImplTests {

    @Autowired
    private MoviesWatchedRepository moviesWatchedRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private GenreRepository genreRepository;

    @MockitoBean
    private ApiMoviesIOService apiMoviesIOService;


    private WatchedMapper watchedMapper;

    @InjectMocks
    private MoviesWatchedServiceImpl moviesWatchedServiceImpl;


    @BeforeEach
    void setUp() {
        watchedMapper = new WatchedMapper();
        moviesWatchedServiceImpl = new MoviesWatchedServiceImpl(apiMoviesIOService, moviesWatchedRepository, profileRepository, genreRepository, watchedMapper);
    }

    public ProfileEntity addProfiles(String name, String lang, boolean adult) {
        ProfileEntity profileEntity = new ProfileEntity();

        profileEntity.setName(name);
        profileEntity.setLang(lang);
        profileEntity.setAdult(adult);


        return profileRepository.save(profileEntity);
    }


    public void initGenres(ProfileEntity profileEntity, List<Integer> genresIds) {

        for (Integer gid : genresIds) {
            GenreEntity genreEntity = new GenreEntity();
            GenreEntityId genreEntityId = new GenreEntityId();

            genreEntityId.setPid(profileEntity);
            genreEntityId.setGid(gid);


            genreEntity.setId(genreEntityId);


            genreRepository.save(genreEntity);
        }
    }


    public void initMoviesWatched(ProfileEntity profileEntity, List<Integer> moviesIds) {

        for (Integer mid : moviesIds) {
            MoviesWatchedEntity moviesWatchedEntity = new MoviesWatchedEntity();
            MoviesWatchedEntityId moviesWatchedEntityId = new MoviesWatchedEntityId();

            moviesWatchedEntityId.setPid(profileEntity);
            moviesWatchedEntityId.setMid(mid);


            moviesWatchedEntity.setId(moviesWatchedEntityId);


            moviesWatchedRepository.save(moviesWatchedEntity);
        }
    }



    public Movies generateMovies(){
        List<MovieInfos> moviesList = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            moviesList.add(new MovieInfos(i,"title" + i, "overview", "fr", List.of(12,28), true,  10, LocalDate.of(2020,1,1)));
        }

        return new Movies(1, moviesList, 1);
    }





    @Test
    void should_return_10_best_movies_for_profile_filters_without_genre(){
        ProfileEntity profileEntity = addProfiles("Leo", "fr", true);

        initMoviesWatched(profileEntity, new ArrayList<>());

        Mockito.when(
                apiMoviesIOService.getMoviesByProfileOrYear(
                        Mockito.anyString()
                )
        ).thenReturn(generateMovies());

        List<MovieInfos> movies = moviesWatchedServiceImpl.getBestMovies(profileEntity.getId());

        Assertions.assertEquals(10, movies.size());
    }


    @Test
    void should_return_10_best_movies_for_profile_filters_with_genre(){
        ProfileEntity profileEntity = addProfiles("Leo", "fr", true);

        initGenres(profileEntity, List.of(12,28));
        initMoviesWatched(profileEntity, new ArrayList<>());

        Mockito.when(
                apiMoviesIOService.getMoviesByProfileOrYear(
                        Mockito.anyString()
                )
        ).thenReturn(generateMovies());

        List<MovieInfos> movies = moviesWatchedServiceImpl.getBestMovies(profileEntity.getId());


        Assertions.assertEquals(10, movies.size());
        Assertions.assertTrue(movies.get(0).genres().contains(12));
        Assertions.assertTrue(movies.get(0).genres().contains(28));
    }



    @Test
    void should_not_return_any_movies_for_profile_filters_with_no_present_genre(){
        ProfileEntity profileEntity = addProfiles("Leo", "fr", true);

        initGenres(profileEntity, List.of(35));
        initMoviesWatched(profileEntity, new ArrayList<>());

        Mockito.when(
                apiMoviesIOService.getMoviesByProfileOrYear(
                        Mockito.anyString()
                )
        ).thenReturn(new Movies());

        List<MovieInfos> movies = moviesWatchedServiceImpl.getBestMovies(profileEntity.getId());

        Assertions.assertEquals(0, movies.size());
    }


    @Test
    void should_return_10_best_movies_for_profile_filters_with_movies_watched(){
        ProfileEntity profileEntity = addProfiles("Leo", "fr", true);


        initMoviesWatched(profileEntity, List.of(0,1,2,3,4,5,6,7,8,9));


        Mockito.when(apiMoviesIOService.getMoviesByProfileOrYear(Mockito.anyString())).thenReturn(generateMovies());

        List<MovieInfos> movies = moviesWatchedServiceImpl.getBestMovies(profileEntity.getId());


        Assertions.assertEquals(10, movies.size());
        Assertions.assertEquals(10, movies.get(0).id());
    }


    @Test
    void should_not_return_any_movies_for_all_movies_watched(){
        ProfileEntity profileEntity = addProfiles("Leo", "fr", true);

        initMoviesWatched(profileEntity, List.of(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30));


        Mockito.when(apiMoviesIOService.getMoviesByProfileOrYear(Mockito.anyString())).thenReturn(generateMovies());

        List<MovieInfos> movies = moviesWatchedServiceImpl.getBestMovies(profileEntity.getId());


        Assertions.assertEquals(0, movies.size());
    }



    @Test
    void should_return_10_best_movies_for_profile_filters_with_genres_and_movies_watched(){
        ProfileEntity profileEntity = addProfiles("Leo", "fr", true);

        initGenres(profileEntity, List.of(12,28));
        initMoviesWatched(profileEntity, List.of(0,1,2,3,4,5,6,7,8,9));


        Mockito.when(apiMoviesIOService.getMoviesByProfileOrYear(Mockito.anyString())).thenReturn(generateMovies());

        List<MovieInfos> movies = moviesWatchedServiceImpl.getBestMovies(profileEntity.getId());


        Assertions.assertEquals(10, movies.size());
        Assertions.assertEquals(10, movies.get(0).id());
        Assertions.assertTrue(movies.get(0).genres().contains(12));
        Assertions.assertTrue(movies.get(0).genres().contains(28));
    }


    @Test
    void should_add_new_watched_movie(){
        ProfileEntity profileEntity = addProfiles("Leo", "fr", true);


        moviesWatchedServiceImpl.addWatchedMovie(new WatchedDTO(profileEntity.getId(), 0));
        moviesWatchedServiceImpl.addWatchedMovie(new WatchedDTO(profileEntity.getId(), 12));


        List<MoviesWatchedEntity> movies = moviesWatchedRepository.findById_Pid(profileEntity);


        Assertions.assertEquals(2, movies.size());
        Assertions.assertEquals(0, movies.get(0).getId().getMid());
        Assertions.assertEquals(12, movies.get(1).getId().getMid());
    }



    @Test
    void should_throw_ConflictException_if_watched_movie_already_exists(){
        ProfileEntity profileEntity = addProfiles("Leo", "fr", true);


        moviesWatchedServiceImpl.addWatchedMovie(new WatchedDTO(profileEntity.getId(), 0));


        Assertions.assertThrows(ConflictException.class, () -> moviesWatchedServiceImpl.addWatchedMovie(new WatchedDTO(profileEntity.getId(), 0)));
    }
}
