package com.example.user.lab_6_fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity implements ColorFragment.OnFragmentInteractionListener{

    TextView textView1;
    TextView textView2;
    FrameLayout frame1;
    FrameLayout frame2;
    Button bAdd,bRemove,bTransfer;
    private Integer color;
    Bundle bundle=new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=findViewById(R.id.text_1);
        textView2=findViewById(R.id.text_2);
        bAdd=findViewById(R.id.b_add);
        bRemove=findViewById(R.id.b_remove);
        bTransfer=findViewById(R.id.b_transfer);
        frame1=findViewById(R.id.frame_1);
        frame2=findViewById(R.id.frame_2);
        bAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Generate random color
                Random rnd = new Random();
                color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                ColorFragment newFragment = ColorFragment.newInstance(color);
                FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                ft.add(R.id.frame_1,newFragment,"fragment_1");
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        bTransfer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ColorFragment oldFragment = (ColorFragment) getSupportFragmentManager().findFragmentByTag("fragment_1");
                if(oldFragment!=null) {
                    ColorFragment newFragment = ColorFragment.newInstance(color);
                    FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                    ft.remove(oldFragment);
                    ft.add(R.id.frame_2,newFragment,"fragment_2");
                    ft.addToBackStack(null);
                    ft.commit();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "No fragment to transfer.",
                            Toast.LENGTH_LONG).show();
                }

            }
        });

        bRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ColorFragment oldFragment = (ColorFragment) getSupportFragmentManager().findFragmentByTag("fragment_2");
                if(oldFragment!=null) {
                    FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                    ft.remove(oldFragment);
                    ft.commit();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "No fragment to remove.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
