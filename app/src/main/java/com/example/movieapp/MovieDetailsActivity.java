package com.example.movieapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.movieapp.model.Movie;
import com.example.movieapp.model.MovieResponse;
import com.example.movieapp.network.ApiService;
import com.example.movieapp.network.RetrofitClient;
import com.squareup.picasso.Picasso;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MovieDetailsActivity extends AppCompatActivity {

    private TextView titleTextView, yearTextView, plotTextView, ratingTextView;
    private ImageView posterImageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        // initialize views
        plotTextView = findViewById(R.id.plot);
        View moviePosterImageView = findViewById(R.id.moviePoster);
        View backButton = findViewById(R.id.backButton);

        // get the IMDb ID passed from MainActivity
        String imdbID = getIntent().getStringExtra("imdbID");

        // fetch movie details
        if (imdbID != null) {
            fetchMovieDetails(imdbID);
        } else {
            Toast.makeText(this, "No movie data available", Toast.LENGTH_SHORT).show();
        }

        backButton.setOnClickListener(v -> onBackPressed());
    }

    private void fetchMovieDetails(String imdbID) {
        // API key and service initialization
        String apiKey = "59c74b7b";
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // make API call to fetch movie details
        apiService.getMovieDetails(imdbID, apiKey).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.body() != null) {
                    // Update UI with movie details
                    Movie movie = response.body();
                    plotTextView.setText(movie.getPlot());
                    ImageView moviePosterImageView = null;
                    Picasso.get().load(movie.getPoster()).into(moviePosterImageView);
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                // handle API call failure
                Toast.makeText(MovieDetailsActivity.this, "Failed to load movie details", Toast.LENGTH_SHORT).show();
            }
        });
    }

}