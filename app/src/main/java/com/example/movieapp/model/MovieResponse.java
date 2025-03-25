package com.example.movieapp.model;
import com.example.movieapp.model.Movie;
import java.util.List;


public class MovieResponse {
    private List<Movie> Search;

    public List<Movie> getSearch() {
        return Search;
    }
    public void setSearch(List<Movie> search) {
        Search = search;
    }

}
