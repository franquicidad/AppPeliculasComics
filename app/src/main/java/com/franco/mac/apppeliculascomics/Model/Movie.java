package com.franco.mac.apppeliculascomics.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String movieName;
    private int movie_ID;
    private String poster_path;
    private String description;
    private String releaseDate;

    public Movie(String movieName, int movie_ID, String poster_path, String description, String releaseDate) {
        this.movieName = movieName;
        this.movie_ID=movie_ID;
        this.poster_path = poster_path;
        this.description = description;
        this.releaseDate = releaseDate;

    }

    protected Movie(Parcel in) {
        movieName = in.readString();
        movie_ID = in.readInt();
        poster_path = in.readString();
        description = in.readString();
        releaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getMovie_ID() {
        return movie_ID;
    }

    public void setMovie_ID(int movie_ID) {
        this.movie_ID = movie_ID;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(movieName);
        dest.writeInt(movie_ID);
        dest.writeString(poster_path);
        dest.writeString(description);
        dest.writeString(releaseDate);
    }
}
