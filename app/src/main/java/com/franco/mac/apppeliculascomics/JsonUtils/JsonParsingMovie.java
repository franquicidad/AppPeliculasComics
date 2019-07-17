package com.franco.mac.apppeliculascomics.JsonUtils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import com.franco.mac.apppeliculascomics.BuildConfig;
import com.franco.mac.apppeliculascomics.Model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class JsonParsingMovie {

//    thumb_url: "https://comicvine.gamespot.com/api/image/scale_avatar/868526-superman.jpg",
// https://comicvine.gamespot.com/api/movies/?api_key=743721a4d8aaa387527120b32cfce75b5f327833&format=json


    private static final String LOG_TAG = JsonParsingMovie.class.getSimpleName();
    private static final String IMAGE_URL = "https://comicvine.gamespot.com/api/image/scale_avatar/";
    private static final String SIZE_PARAM = "scale_avatar";
    private static final String MOVIEDB_BASE_URL="https://comicvine.gamespot.com/api/movies/?";

    private static final String PARAM_API_KEY="api_key";
    private static final String FORMAT="&format=json";

    private static final String API_KEY= "743721a4d8aaa387527120b32cfce75b5f327833";

    String movieName = null;
    String voteAverage = null;
    String overview = null;
    String releaseDate = null;
    String poster_path = null;


    public JsonParsingMovie() throws JSONException {

    }
    public static URL generalUrl(){
        Uri builtUri= Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_API_KEY,API_KEY)
                .appendPath(FORMAT)
                .build();
        return  makeUrl(builtUri.toString());
    }

    public static  URL buildReviewUrl(int id){
        Uri reviewUrl= Uri.parse(MOVIEDB_BASE_URL).buildUpon()
                .appendPath(Integer.toString(id))
                .appendQueryParameter(PARAM_API_KEY,API_KEY)
                .build();

        return makeUrl(reviewUrl.toString());

    }
//
//    public static boolean hasInternetAccess(Context context){
//        ConnectivityManager cm =
//                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        boolean isConnected = activeNetwork != null &&
//                activeNetwork.isConnectedOrConnecting();
//
//        return isConnected;
//    }

    public static URL makeUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Throw an Exception in makeUrl", e);
        }
        return url;
    }

    public static URL buildImageUrl(String poster_path) {
        Uri builtUri = Uri.parse(IMAGE_URL).buildUpon()
                .appendEncodedPath(SIZE_PARAM)
                .appendEncodedPath(poster_path)
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Throw an Exception in buildUrl", e);
        }
        return url;
    }


    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }


    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    public static ArrayList<Movie> parseMovie(String json) {


        ArrayList<Movie> listMovie = new ArrayList<>();


        try {
            JSONObject root = new JSONObject(json);
            JSONArray jsonArray = root.getJSONArray("results");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String movieName = jsonObject.getString("name");
                int movie_ID=jsonObject.getInt("id");
                String description = jsonObject.getString("description");
                String releaseDate = jsonObject.getString("release_date");
                JSONObject image = jsonObject.getJSONObject("image");
                String poster_path=image.getString("thumb_url");


                Movie movie = new Movie(movieName, movie_ID,poster_path, description, releaseDate);
                listMovie.add(movie);



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listMovie;


    }

    public static ArrayList<Movie> fetchMovieAppData(String requestUrl) {
        URL url = makeUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);

            Log.e(LOG_TAG,"result"+jsonResponse);

        } catch (IOException e) {
            Log.e(LOG_TAG, "Throw an Exception in fetchMovieAppData", e);
        }
        return parseMovie(jsonResponse);
    }


}
