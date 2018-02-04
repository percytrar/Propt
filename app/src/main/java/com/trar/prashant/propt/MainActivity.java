package com.trar.prashant.propt;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    int genreId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton bAction = (ImageButton)findViewById(R.id.ibtn_action);
        ImageButton bHorror = (ImageButton)findViewById(R.id.ibtn_horror);
        ImageButton bDrama = (ImageButton)findViewById(R.id.ibtn_drama);
        ImageButton bRomance = (ImageButton)findViewById(R.id.ibtn_romance);
        ImageButton bSci_fi = (ImageButton)findViewById(R.id.ibtn_sci);
        ImageButton bCrime = (ImageButton)findViewById(R.id.ibtn_crime);


    }
        public void mAction(View view){
            genreId = 28;
            assignId();
        }
        public void mHorror(View view){
            genreId = 27;
            assignId();
        }
        public void mDrama(View view){
            genreId = 18;
            assignId();
        }
        public void mRomance(View view){
            genreId = 10749;
            assignId();
        }
        public void mSci_fi(View view){
            genreId = 878;
            assignId();
        }
        public void mCrime(View view){
            genreId = 80;
            assignId();
        }

        //To send respective genre Id to the next screen
        public void assignId(){
            Intent intent = new Intent(this,genre_list.class);
            intent.putExtra("genreId",genreId);
            startActivity(intent);
        }

    }

