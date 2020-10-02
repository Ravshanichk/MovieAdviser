package com.example.movies2.data;

import androidx.room.Entity;

@Entity( tableName = "favourite_movies")
public class FavouriteMovie extends Movie {

    public FavouriteMovie(int id, int voteCount, int voteAverage, String smallPosterPath, String bigPosterPath, String backdropPath, String originalTitle, String title, String overview, String releaseDate, double popularity) {
        super(id, voteCount, voteAverage, smallPosterPath, bigPosterPath, backdropPath, originalTitle, title, overview, releaseDate, popularity);
    }

    public FavouriteMovie(Movie movie) {
        super(movie.getId(), movie.getVoteCount(), movie.getVoteAverage(), movie.getSmallPosterPath(), movie.getBigPosterPath(), movie.getBackdropPath(),movie.getOriginalTitle(),movie.getTitle(), movie.getOverview(),movie.getReleaseDate(),movie.getPopularity());
    }
}
