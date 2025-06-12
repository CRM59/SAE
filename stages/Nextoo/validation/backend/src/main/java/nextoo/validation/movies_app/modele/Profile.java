package nextoo.validation.movies_app.modele;

import java.util.List;

public record Profile(
        int pid,
        String name,
        String tag,
        String lang,
        boolean adult,
        List<Genre> genres,
        List<MoviesWatched> moviesWatched
){}
