package com.franco.mac.apppeliculascomics;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.franco.mac.apppeliculascomics.Model.Movie;
import com.squareup.picasso.Picasso;

public class MovieDetail extends AppCompatActivity {

    private static final String TAG = MovieDetail.class.getSimpleName();
    ImageView imageDetail;
    TextView titleDetail;
    TextView releaseDate;

    TextView overview;

    Movie selectedMovie=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_layout);

        Slide ejemplo = new Slide();
        Window window=getWindow();
        ejemplo.setSlideEdge(Gravity.RIGHT);

        window.setEnterTransition(ejemplo);
        Intent intent = getIntent();

        final Movie movie = getIntent().getExtras().getParcelable("MOVIE_OBJECT");
        selectedMovie=movie;


        String movieName = movie.getMovieName();
        Log.e(TAG, "This is the movie name:--------->");
        String poster = movie.getPoster_path();
        int id = movie.getMovie_ID();
        Log.e(TAG, "This is the movie ID:----------------------------->");
        String description = movie.getDescription();
        String releaseDate1 = movie.getReleaseDate();


        imageDetail = findViewById(R.id.imageMovie_detail_activity);

        Picasso.get().load(poster).into(imageDetail);

        titleDetail = findViewById(R.id.text_movie_name);
        titleDetail.setText(movieName);

        releaseDate = findViewById(R.id.text_movie_release_date);
        releaseDate.setText(releaseDate1);

        overview = findViewById(R.id.text_movie_overview);
        if(description!=null) {
            overview.setText(description);
        }else{
            overview.setText(getResources().getString(R.string.sin_descripcion));
        }

    }


}
