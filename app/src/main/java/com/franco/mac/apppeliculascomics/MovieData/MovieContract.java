package com.franco.mac.apppeliculascomics.MovieData;

import android.net.Uri;
import android.provider.BaseColumns;

public class MovieContract {
    public static final String AUTHORITY="com.example.mac.movieappbasic";

    public static final String PATH_MOVIE_DB="movieDataBase";

    public static final class MovieEntry implements BaseColumns {

        public static final Uri BASE_CONTENT_URI= Uri.parse("content://"+AUTHORITY);

        public static final Uri CONTENT_URI= BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE_DB).build();


        public static final String TABLE_NAME="movieDataBase";
        public static final String _ID="id";
        public static final String MOVIE_ID="id";
        public static final String MOVIE_IMAGE="movieImage";
        public static final String DESCRIPTION="description";
        public static final String MOVIE_NAME="name";
        public static final String RELEASE_DATE="release_date";
    }
}
