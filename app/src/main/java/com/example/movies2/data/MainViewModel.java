package com.example.movies2.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {
    private static MovieDatabase database;
    private LiveData<List<Movie>> movies;
    private LiveData<List<FavouriteMovie>> favouriteMovies;

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

    public LiveData<List<FavouriteMovie>> getFavouriteMovies() {
        return favouriteMovies;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = MovieDatabase.getInstance(getApplication());
        movies = database.movieDao().getAllMovies();
        favouriteMovies = database.movieDao().getAllFavouriteMovies();
    }

    public static MovieDatabase getDatabase() {
        return database;
    }

    public Movie getMovieById (int id) {
        try {
            return new GetMovieTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteAllMovies() {
        new DeleteAllMoviesTask().execute();
    }

    public void deleteMovie(Movie movie) {
        new DeleteMovieTask().execute(movie);
    }

    public void insertMovie(Movie movie) {
        new InsertMovieTask().execute(movie);
    }

    private static class InsertMovieTask extends AsyncTask<Movie,Void,Void> {

        @Override
        protected Void doInBackground(Movie... movies) {
            if(movies!=null && movies.length>0) {
                database.movieDao().insertMovie(movies[0]);
            }
            return null;
        }
    }

    private static class DeleteAllMoviesTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            database.movieDao().deleteAllMovies();
            return null;
        }

    }

    private static class GetMovieTask extends AsyncTask<Integer, Void,Movie> {

        @Override
        protected Movie doInBackground(Integer... integers) {
            if(integers!=null && integers.length>0) {
                return database.movieDao().getMovieById(integers[0]);
            }
            return null;
         }
    }
    private static class DeleteMovieTask extends AsyncTask<Movie, Void, Void> {

        @Override
        protected Void doInBackground(Movie... movies) {
            if(movies!=null && movies.length>0) {
                database.movieDao().deleteMovie(movies[0]);
            }
            return null;
        }
    }

    //---------------------------FAVOURITE MOVIE-----------------------------------------------
    public FavouriteMovie getFavouriteMovieById (int id){
        try {
            return new GetFavouriteMovieByIdTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteFavouriteMovie(FavouriteMovie favouriteMovie) {
        new DeleteFavouriteMovieTask().execute(favouriteMovie);
    }

    public void insertFavouriteMovie(FavouriteMovie favouriteMovie) {
        new InsertFavouriteMovieTask().execute(favouriteMovie);
    }

    private static class GetFavouriteMovieByIdTask extends AsyncTask<Integer, Void, FavouriteMovie> {

        @Override
        protected FavouriteMovie doInBackground(Integer... integers) {
            if(integers!=null && integers.length>0) {
                return database.movieDao().getFavouriteMovieById(integers[0]);
            }
            return null;
        }
    }

    private static class DeleteFavouriteMovieTask extends AsyncTask<FavouriteMovie, Void, Void> {


        @Override
        protected Void doInBackground(FavouriteMovie... favouriteMovies) {
            if (favouriteMovies !=null && favouriteMovies.length>0) {
                database.movieDao().deleteFavouriteMovie(favouriteMovies[0]);
            }
            return null;
        }
    }

    private static class InsertFavouriteMovieTask extends AsyncTask<FavouriteMovie,Void,Void> {

        @Override
        protected Void doInBackground(FavouriteMovie... favouriteMovies) {
            if(favouriteMovies!=null && favouriteMovies.length>0) {
                database.movieDao().insertFavouriteMovie(favouriteMovies[0]);
            }
            return null;
        }
    }

}
