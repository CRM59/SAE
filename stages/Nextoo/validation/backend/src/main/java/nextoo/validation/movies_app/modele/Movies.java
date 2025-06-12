package nextoo.validation.movies_app.modele;

import java.util.ArrayList;
import java.util.List;

public class Movies {
    private int page;
    private List<MovieInfos> moviesList;
    private int totalPage;

    public Movies(int page, List<MovieInfos> moviesList, int totalPage) {
        this.page = page;
        this.moviesList = moviesList;
        this.totalPage = totalPage;
    }

    public Movies(){
        this(1,new ArrayList<>(), 1);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieInfos> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<MovieInfos> moviesList) {
        this.moviesList = moviesList;
    }

    public int getTotalPage() {
        return totalPage;
    }
}
