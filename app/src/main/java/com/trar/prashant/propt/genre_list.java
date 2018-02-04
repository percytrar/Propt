package com.trar.prashant.propt;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import cz.msebera.android.httpclient.Header;

public class genre_list extends AppCompatActivity {
    static final String baseUrl = "https://api.themoviedb.org/3/";
    final String apiKey = "fc6fc13a18c3f583beab6917c8cda885";
    static final String imageUrl = "https://image.tmdb.org/t/p/";
    static final String logoSize = "w154";
    static final String posterSize = "w300";
    int genre_id;
    static ImageButton i1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_list);
        i1 = (ImageButton)findViewById(R.id.m1);
        Intent mIntent = getIntent();
        genre_id = mIntent.getIntExtra("genreId",0);
        if(genre_id==0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Oops!");
            builder.setIcon(R.drawable.alert);
            builder.setPositiveButton("Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
        }
        else{

            RequestParams params = new RequestParams();
            params.put("genre_id",genre_id);
            params.put("api_key",apiKey);
            letsDoSomeNetworking(params);
        }
    }
    public void  letsDoSomeNetworking(RequestParams params){

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(baseUrl,params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                Log.d("Movie", "onSuccess: Json"+response.toString());
                JSONObject object = new JSONObject();
                try {
                    object.getJSONObject(getString(R.string.res));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MovieItem movieItem = MovieItem.fromJson(response);
                try {
                    updateUI(movieItem);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers,Throwable e, JSONObject response){
                Log.e("Movie","OnFail:"+e.toString() );
                Log.d("Movie", "statusCode"+statusCode);
                Toast.makeText(genre_list.this,"Server Request Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public static void updateUI(MovieItem movieItem) throws IOException {

        URL url = new URL(imageUrl + logoSize + movieItem.getPoster());
        Log.d("Movie", "updateUI: Url "+url);
        Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        i1.setImageBitmap(bmp);
    }
}
