package nextoo.validation.movies_app.services.external.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.LocalDate;
import java.util.List;

public record MovieInfosDTO (
        boolean adult,
        @JsonAlias("genre_ids")
        List<Integer> genres,
        int id,
        @JsonAlias("original_language")
        String lang,
        String overview,
        String title,
        @JsonAlias("vote_average")
        int rating,
        @JsonAlias("release_date")
        LocalDate releaseDate
){}