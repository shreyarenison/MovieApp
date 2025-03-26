package com.example.movieapp.model;

import java.util.List;

public class MovieResponse {
    private List<Movie> Search;


    public List<Movie> getSearch() {
        return Search;
    }

    public void setSearch(List<Movie> search) {
        Search = search;
    }


    public static class Movie {
        private String Title;
        private String Year;
        private String Plot;
        private String imdbRating;
        private String Poster;

        // Constructor
        public Movie(String Title, String Year, String Plot, String imdbRating, String Poster) {
            this.Title = Title;
            this.Year = Year;
            this.Plot = Plot;
            this.imdbRating = imdbRating;
            this.Poster = Poster;
        }


        public String getTitle() {
            return Title;
        }

        public String getYear() {
            return Year;
        }

        public String getPlot() {
            return Plot;
        }

        public String getImdbRating() {
            return imdbRating;
        }

        public String getPoster() {
            return Poster;
        }


        public void setTitle(String title) {
            Title = title;
        }

        public void setYear(String year) {
            Year = year;
        }

        public void setPlot(String plot) {
            Plot = plot;
        }

        public void setImdbRating(String imdbRating) {
            this.imdbRating = imdbRating;
        }

        public void setPoster(String poster) {
            Poster = poster;
        }
    }
}
