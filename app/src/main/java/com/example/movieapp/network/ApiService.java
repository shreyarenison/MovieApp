package com.example.movieapp.network;

import com.example.movieapp.model.Movie;
import com.example.movieapp.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("/")
    Call<MovieResponse> getMovies(
            @Query("s") String title,
            @Query("apikey") String apiKey
    );

    @GET("/")
    Call<Movie> getMovieDetails(
            @Query("i") String imdbID,
            @Query("apikey") String apiKey
    );
}
