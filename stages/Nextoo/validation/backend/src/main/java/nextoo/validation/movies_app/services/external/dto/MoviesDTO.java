package nextoo.validation.movies_app.services.external.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

public class MoviesDTO {
    private int page;
    private List<MovieInfosDTO> results;
    @JsonAlias(value = "total_pages")
    private int totalPages;

    public MoviesDTO(int page, List<MovieInfosDTO> results, int totalPages) {
        this.page = page;
        this.results = results;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public List<MovieInfosDTO> getResults() {
        return results;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
