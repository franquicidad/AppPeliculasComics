package com.franco.mac.apppeliculascomics;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.GridView;

import com.franco.mac.apppeliculascomics.Adapters.MovieAdapter;
import com.franco.mac.apppeliculascomics.Model.Movie;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Movie>> {
    public static final String API_KEY="743721a4d8aaa387527120b32cfce75b5f327833";
    //https://comicvine.gamespot.com/api/movies/?api_key=743721a4d8aaa387527120b32cfce75b5f327833&format=json

    private static final String BASE_URL = "https://comicvine.gamespot.com/api/movies/?api_key=";
    private static final String SECOND_PART = "&format=json";
    private static final String MERGED_MOVIE = (BASE_URL +API_KEY+SECOND_PART);
    private static final int NUM_LIST_ITEMS = 100;
    private static final int MOVIE_LOADER_ID = 1;
    GridLayoutManager gridLayoutManager;
    ArrayList<Movie> adapterArrayList;
    GridView gridView;
    String sortMode = "popular";
    private Context context;
    private MovieAdapter mMovieAdapter;
    private RecyclerView mMovieList;


    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieList = findViewById(R.id.rv_movies);
        gridView = findViewById(R.id.gridview_layout);

        gridLayoutManager = new GridLayoutManager(this, 2);
        mMovieList.setLayoutManager(gridLayoutManager);
        mMovieList.setHasFixedSize(true);
        mMovieAdapter = new MovieAdapter(this, NUM_LIST_ITEMS, new MovieAdapter.GridItemClickListener() {

            @Override
            public void onGridItemClick(Movie movie) {

                Intent intentDetailActivity = new Intent(getBaseContext(), MovieDetail.class);

                intentDetailActivity.putExtra("MOVIE_OBJECT", movie);

                Slide explode=new Slide();
                explode.setDuration(500);
                explode.setSlideEdge(Gravity.TOP);
                getWindow().setEnterTransition(explode);
                getWindow().setReenterTransition(explode);

                startActivity(intentDetailActivity,
                        ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());


            }
        });

            mMovieList.setAdapter(mMovieAdapter);
            adapterArrayList = new ArrayList<>();
            mMovieAdapter.addAll(adapterArrayList);



        LoaderManager loaderManager = getSupportLoaderManager();

        loaderManager.initLoader(MOVIE_LOADER_ID, null, MainActivity.this);



    }

    @Override
    protected void onResume(){
        super.onResume();
        Explode explode=new Explode();
        explode.setDuration(500);
        getWindow().setEnterTransition(explode);
        getWindow().setReenterTransition(explode);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Explode explode=new Explode();
        explode.setDuration(500);
        getWindow().setEnterTransition(explode);
        getWindow().setReenterTransition(explode);
    }

    @Override
    protected void onStart(){
        super.onStart();
        Explode explode=new Explode();
        explode.setDuration(500);
        getWindow().setEnterTransition(explode);
        getWindow().setReenterTransition(explode);

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Explode explode=new Explode();
        explode.setDuration(500);
        getWindow().setEnterTransition(explode);
        getWindow().setReenterTransition(explode);
    }


    @Override
    public Loader<ArrayList<Movie>> onCreateLoader(int id, Bundle args) {



                return new MovieAsyncTaskLoader(this,MERGED_MOVIE);
        }


    @Override
    public void onLoadFinished(Loader<ArrayList<Movie>> loader, ArrayList<Movie> data) {
            mMovieAdapter.addAll(data);
    }



    @Override
    public void onLoaderReset(Loader<ArrayList<Movie>> loader) {
        Explode explode=new Explode();
        explode.setDuration(500);
        getWindow().setEnterTransition(explode);
        getWindow().setReenterTransition(explode);
    }

}
