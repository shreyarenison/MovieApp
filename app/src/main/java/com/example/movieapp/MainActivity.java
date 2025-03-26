package com.example.movieapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        Log.d("MainActivity", "onCreate started");

        // initialize UI elements
        searchEditText = findViewById(R.id.searchEditText);
        Button searchButton = findViewById(R.id.searchButton);
        movieRecyclerView = findViewById(R.id.movieRecyclerView);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (searchButton != null) {
            Log.d("MainActivity", "Search Button is found and ready");
        } else {
            Log.e("MainActivity", "Search Button is not found!");
        }


        assert searchButton != null;
        searchButton.setOnClickListener(v -> searchMovies(searchEditText.getText().toString()));
    }

    private void searchMovies(String title) {
        if (title.isEmpty()) {
            Toast.makeText(this, "Please enter a movie name!", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.d("MainActivity", "Searching for movie: " + title);

        String apiKey = "59c74b7b";
        ApiService apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        apiService.getMovies(title, apiKey).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.body() != null) {
                    movieAdapter = new MovieAdapter(MainActivity.this, response.body().getSearch());
                    movieRecyclerView.setAdapter(movieAdapter);
                    Log.d("MainActivity", "Movies loaded successfully");
                } else {
                    Log.e("MainActivity", "No movies found");
                    Toast.makeText(MainActivity.this, "No movies found!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                Log.e("MainActivity", "Error fetching data: " + t.getMessage(), t);
                Toast.makeText(MainActivity.this, "Failed to fetch movie data. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
