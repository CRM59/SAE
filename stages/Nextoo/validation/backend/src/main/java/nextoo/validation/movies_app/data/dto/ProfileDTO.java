package nextoo.validation.movies_app.data.dto;

import java.util.List;
import java.util.Optional;

public class ProfileDTO {
    private String name;
    private String tag;
    private Optional<String> lang;
    private Optional<Boolean> adult;
    private Optional<List<Integer>> genres;

    public ProfileDTO(String name, String tag, Optional<String> lang, Optional<Boolean> adult, Optional<List<Integer>> genres) {
        this.name = name;
        this.tag = tag;
        this.lang = lang;
        this.adult = adult;
        this.genres = genres;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public Optional<String> getLang() {
        return lang;
    }

    public Optional<Boolean> getAdult() {
        return adult;
    }

    public Optional<List<Integer>> getGenres() {
        return genres;
    }
}
