package com.example.movieapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.movieapp.model.MovieResponse;
import com.squareup.picasso.Picasso;
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

        // Initializing the views
        titleTextView = findViewById(R.id.titleTextView);
        yearTextView = findViewById(R.id.yearTextView);
        plotTextView = findViewById(R.id.plotTextView);
        ratingTextView = findViewById(R.id.ratingTextView);
        posterImageView = findViewById(R.id.posterImageView);

        // Get the movie data passed from MainActivity
        Intent intent = getIntent();
        MovieResponse.Movie movie = (MovieResponse.Movie) intent.getSerializableExtra("MOVIE");

        // Display movie details
        if (movie != null) {
            titleTextView.setText(movie.getTitle());
            yearTextView.setText(movie.getYear());
            plotTextView.setText(movie.getPlot());
            ratingTextView.setText("Rating: " + movie.getImdbRating());
            Picasso.get().load(movie.getPoster()).into(posterImageView);
        } else {
            Toast.makeText(this, "No movie data available", Toast.LENGTH_SHORT).show();
        }
    }
}

