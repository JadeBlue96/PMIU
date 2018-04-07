package com.example.user.lab10_asynctask;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    private TextView stat_text;
    private Button b_start,b_pause;
    private int interval = 1000;
    private int ctr=0;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        stat_text=findViewById(R.id.time_text);
        b_start=findViewById(R.id.start_timer);
        b_pause=findViewById(R.id.pause_timer);
        handler=new Handler();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        b_start.setOnClickListener(ButtonStartListener);
        b_pause.setOnClickListener(ButtonPauseListener);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private Runnable TimeTask=new Runnable() {
        @Override
        public void run() {

            stat_text.setText("Handler active for "+ctr+" seconds.");
            ctr++;
            handler.postDelayed(TimeTask, interval);
        }
    };
    View.OnClickListener ButtonPauseListener = v -> handler.removeCallbacks(TimeTask);
    View.OnClickListener ButtonStartListener = v -> {
        try {
            handler.removeCallbacks(TimeTask);
            handler.postDelayed(TimeTask, interval); // delay 1 second
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    };


}
