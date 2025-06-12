package nextoo.validation.movies_app.modele;

import java.time.LocalDate;
import java.util.List;

public record MovieInfos(
        int id,
        String title,
        String overview,
        String language,
        List<Integer> genres,
        boolean forAdult,
        int rating,
        LocalDate releaseDate
        ) {
}
