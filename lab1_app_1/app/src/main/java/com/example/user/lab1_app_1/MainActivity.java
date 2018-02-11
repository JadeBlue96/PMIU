package com.example.user.lab1_app_1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    ImageView m_im;
    TextView m_txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        m_im=(ImageView)findViewById(R.id.r_image);
        m_txt=(TextView)findViewById(R.id.r_text);


    }

    @Override
    protected void onStart() {
        Picasso.with(this)
                .load("https://static.pexels.com/photos/20787/pexels-photo.jpg")
                .resize(600,600).noFade().into(m_im, new Callback() {
            @Override
            public void onSuccess() {
                m_txt.setText("Loaded successful!");
            }

            @Override
            public void onError() {
                m_txt.setText("Load failed! Check Internet Connection.");
            }
        });
        super.onStart();

    }
}
