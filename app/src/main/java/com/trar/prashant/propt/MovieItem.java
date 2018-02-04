package com.trar.prashant.propt;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Prashant on 03-02-2018.
 */

public class MovieItem {
    private int id ;
    private String title;
    private String overview;
    private String releaseDate;
    private String poster;

    public static MovieItem fromJson(JSONObject jsonObject){
        try{
            MovieItem movieItem = new MovieItem();
            movieItem.id = jsonObject.getJSONArray("results").getJSONObject(0).getInt("id");
            movieItem.title = jsonObject.getJSONArray("results").getJSONObject(0).getString("original_tile");
            movieItem.overview = jsonObject.getJSONArray("results").getJSONObject(0).getString("overview");
            movieItem.releaseDate = jsonObject.getJSONArray("results").getJSONObject(0).getString("release_date");
            movieItem.poster = jsonObject.getJSONArray("results").getJSONObject(0).getString("poster_path");
            return movieItem;
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getPoster() {
        return poster;
    }
}
