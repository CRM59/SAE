package nextoo.validation.movies_app.data.entities.mappers;

import nextoo.validation.movies_app.data.entities.ProfileEntity;
import nextoo.validation.movies_app.modele.Genre;
import nextoo.validation.movies_app.modele.Profile;
import nextoo.validation.movies_app.modele.MoviesWatched;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfileMapper {
    private final GenreMapper genreMapper;
    private final WatchedMapper watchedMapper;

    public ProfileMapper(GenreMapper genreMapper, WatchedMapper watchedMapper) {
        this.genreMapper = genreMapper;
        this.watchedMapper = watchedMapper;
    }

    public Profile toProfile(ProfileEntity profileEntity) {
        int pid = profileEntity.getId();
        String name = profileEntity.getName();
        String tag = profileEntity.getTag();
        String lang = profileEntity.getLang();
        boolean adult = profileEntity.getAdult();

        List<Genre> genres = profileEntity.getGenres()
                .stream()
                .map(genreMapper::toGenre)
                .toList();

        List<MoviesWatched> moviesWatched = profileEntity.getWatcheds()
                .stream()
                .map(watchedMapper::toWatched)
                .toList();

        return new Profile(pid, name, tag, lang, adult, genres, moviesWatched);
    }
}
