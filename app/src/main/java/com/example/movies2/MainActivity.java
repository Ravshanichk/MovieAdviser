package com.example.movies2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movies2.data.FavouriteMovie;
import com.example.movies2.data.MainViewModel;
import com.example.movies2.data.Movie;
import com.example.movies2.data.MovieDatabase;
import com.example.movies2.utils.JSONUtils;
import com.example.movies2.utils.NetworkUtils;

import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private Switch aSwitch;
    private TextView textViewPopularity;
    private TextView textViewTopRated;
    private MainViewModel viewModel;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.itemMain:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.itemFavourite:
                Intent intent1 = new Intent(this, FavouriteActivity.class);
                startActivity(intent1);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.hide();
//        }
        aSwitch = findViewById(R.id.switchSortBy);
        recyclerView = findViewById(R.id.recyclerViewPoster);
        textViewPopularity = findViewById(R.id.textViewPopularity);
        textViewTopRated = findViewById(R.id.textViewTopRated);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        movieAdapter = new MovieAdapter();
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        recyclerView.setAdapter(movieAdapter);
        textViewPopularity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aSwitch.setChecked(false);
            }
        });
        textViewTopRated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aSwitch.setChecked(true);
            }
        });
        movieAdapter.setOnPosterClickListener(new MovieAdapter.OnPosterClickListener() {
            @Override
            public void onPosterClick(int position) {
                Movie movie = movieAdapter.getMovies().get(position);
                Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("id",movie.getId());
                startActivity(intent);
            }
        });
        movieAdapter.setOnReachEndListener(new MovieAdapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {

            }
        });
        aSwitch.setChecked(true);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setMethodOfSort(isChecked);
            }
        });
        aSwitch.setChecked(false);
       LiveData<List<Movie>> moviesFromLive = viewModel.getMovies();
       moviesFromLive.observe(this, new Observer<List<Movie>>() {
           @Override
           public void onChanged(List<Movie> movies) {
               movieAdapter.setMovies(movies);
           }
       });
    }

    public void setMethodOfSort(boolean isTopRated) {
        int methodOfSort;
        if(isTopRated) {
            methodOfSort = NetworkUtils.TOP_RATED;
            textViewTopRated.setTextColor(getResources().getColor(R.color.colorAccent));
            textViewPopularity.setTextColor(getResources().getColor(R.color.whiteColor));
        } else {
            methodOfSort = NetworkUtils.POPULARITY;
            textViewTopRated.setTextColor(getResources().getColor(R.color.whiteColor));
            textViewPopularity.setTextColor(getResources().getColor(R.color.colorAccent));
        }
        getData(methodOfSort,1);
    }

    public void getData(int method, int page) {
        JSONObject object = NetworkUtils.getJSONObject(method, page);
        List<Movie> movies = JSONUtils.getMovies(object);
        if(movies !=null && !movies.isEmpty()) {
            viewModel.deleteAllMovies();
            for(Movie movie: movies){
                viewModel.insertMovie(movie);
            }
        }
    }


}

