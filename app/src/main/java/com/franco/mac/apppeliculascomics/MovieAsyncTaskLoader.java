package com.franco.mac.apppeliculascomics;

import android.content.Context;
import androidx.loader.content.AsyncTaskLoader;

import com.franco.mac.apppeliculascomics.JsonUtils.JsonParsingMovie;
import com.franco.mac.apppeliculascomics.Model.Movie;

import java.util.ArrayList;

public class MovieAsyncTaskLoader extends AsyncTaskLoader<ArrayList<Movie>> {
    private String mUrl;

    public MovieAsyncTaskLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
    }

    @Override
    public ArrayList<Movie> loadInBackground() {
        if (mUrl == null) {
            return null;
        }
        ArrayList<Movie> listMovie = null;
        listMovie = JsonParsingMovie.fetchMovieAppData(mUrl);
        return listMovie;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

}
