package com.example.movieapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.util.Log;
import android.widget.Toast;

import com.example.movieapp.adapter.MovieAdapter;
import com.example.movieapp.model.MovieResponse;
import com.example.movieapp.network.ApiService;
import com.example.movieapp.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private EditText searchEditText;
    private RecyclerView movieRecyclerView;
    private MovieAdapter movieAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing UI elements
        searchEditText = findViewById(R.id.searchEditText);
        movieRecyclerView = findViewById(R.id.movieRecyclerView);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) Button searchButton = findViewById(R.id.searchButton);

        // set onClickListener for the search button
        searchButton.setOnClickListener(v -> searchMovies(searchEditText.getText().toString()));
    }

    // method to search movies by title
    private void searchMovies(String title) {
        String apiKey = "59c74b7b";
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // making an API call to get movies based on the search title
        apiService.getMovies(title, apiKey).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                // On successful response
                if (response.body() != null) {
                    movieAdapter = new MovieAdapter(MainActivity.this, response.body().getSearch());
                    movieRecyclerView.setAdapter(movieAdapter);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                // handle failure

                Toast.makeText(MainActivity.this, "Failed to fetch movie data. Please try again.", Toast.LENGTH_SHORT).show();

                Log.e("MovieSearch", "Error fetching data: " + t.getMessage(), t);
            }
        });
    }
}