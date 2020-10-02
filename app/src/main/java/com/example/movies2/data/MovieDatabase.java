package com.example.movies2.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class, FavouriteMovie.class}, version = 2, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    private static MovieDatabase movieDatabase;
    private static final String DB_NAME = "movies.db";
    private static final Object LOCK = new Object();

    public static MovieDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (movieDatabase == null) {
                movieDatabase = Room.databaseBuilder(context, MovieDatabase.class, DB_NAME).fallbackToDestructiveMigration().build();
            }
            return movieDatabase;
        }
    }

    public abstract MovieDao movieDao();
}
