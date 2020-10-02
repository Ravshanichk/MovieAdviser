package com.example.movies2.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "movies")
public class Movie {
    @PrimaryKey (autoGenerate = true)
    private int id;
    private int voteCount;
    private int voteAverage;
    private String smallPosterPath;
    private String bigPosterPath;
    private String backdropPath;
    private String originalTitle;
    private String title;
    private String overview;
    private String releaseDate;
    private double popularity;

    public Movie(int id, int voteCount, int voteAverage, String smallPosterPath, String bigPosterPath, String backdropPath, String originalTitle, String title, String overview, String releaseDate, double popularity) {
        this.id = id;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
        this.smallPosterPath = smallPosterPath;
        this.bigPosterPath = bigPosterPath;
        this.backdropPath = backdropPath;
        this.originalTitle = originalTitle;
        this.title = title;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(int voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getSmallPosterPath() {
        return smallPosterPath;
    }

    public void setSmallPosterPath(String smallPosterPath) {
        this.smallPosterPath = smallPosterPath;
    }

    public String getBigPosterPath() {
        return bigPosterPath;
    }

    public void setBigPosterPath(String bigPosterPath) {
        this.bigPosterPath = bigPosterPath;
    }


    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
