package com.example.movies2.utils;

import com.example.movies2.data.Movie;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtils {
    private static final String PARAMS_RESULTS = "results";
    private static final String PARAMS_ID = "id";
    private static final String PARAMS_VOTE_COUNT = "vote_count";
    private static final String PARAMS_VOTE_AVERAGE = "vote_average";
    private static final String PARAMS_POSTER_PATH = "poster_path";
    private static final String PARAMS_BACKDROP_PATH = "backdrop_path";
    private static final String PARAMS_ORIGINAL_TITLE = "original_title";
    private static final String PARAMS_TITLE = "title";
    private static final String PARAMS_OVERVIEW = "overview";
    private static final String PARAMS_RELEASE_DATE = "release_date";
    private static final String PARAMS_POPULARITY = "popularity";

    private static String POSTER_URL = "https://image.tmdb.org/t/p/";
    public static final String SMALL_POSTER_SIZE = "w185";
    public static final String BIG_POSTER_SIZE = "original";

    public static ArrayList<Movie> getMovies(JSONObject object) {
        ArrayList<Movie> result = new ArrayList<>();
        try {
            JSONArray jsonArray = object.getJSONArray(PARAMS_RESULTS);
            for (int i = 0; i< jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int id = jsonObject.getInt(PARAMS_ID);
                int voteCount = jsonObject.getInt(PARAMS_VOTE_COUNT);
                int voteAverage = jsonObject.getInt(PARAMS_VOTE_AVERAGE);
                String smallPosterPath = POSTER_URL + SMALL_POSTER_SIZE + jsonObject.getString(PARAMS_POSTER_PATH);
                String bigPosterPath = POSTER_URL + BIG_POSTER_SIZE + jsonObject.getString(PARAMS_POSTER_PATH);
                String backdropPath = jsonObject.getString(PARAMS_BACKDROP_PATH);
                String originalTitle = jsonObject.getString(PARAMS_ORIGINAL_TITLE);
                String title = jsonObject.getString(PARAMS_TITLE);
                String overView = jsonObject.getString(PARAMS_OVERVIEW);
                String releaseDate = jsonObject.getString(PARAMS_RELEASE_DATE);
                double popularity = jsonObject.getDouble(PARAMS_POPULARITY);
                Movie movie = new Movie(id,voteCount,voteAverage, smallPosterPath,bigPosterPath,backdropPath,originalTitle,title,overView,releaseDate,popularity);
                result.add(movie);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }


}
