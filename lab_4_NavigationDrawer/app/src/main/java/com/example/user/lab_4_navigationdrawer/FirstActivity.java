package com.example.user.lab_4_navigationdrawer;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
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
import android.widget.LinearLayout;
import android.widget.Toast;

public class FirstActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final LinearLayout ll = (LinearLayout)findViewById(R.id.linear);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_first);
        fab.setOnClickListener((View view) -> {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT);

                final Button button=new Button(FirstActivity.this);
                button.setText("Button "+counter);
                button.setBackgroundColor(Color.rgb(70, 80, 90));
                button.setId(counter);
                ll.addView(button,params);

                button.setOnClickListener(view1 -> {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(FirstActivity.this);
                    builder1.setMessage("Button clicked = " + button.getId());
                    builder1.setCancelable(true);

                    builder1.setPositiveButton("Yes", (dialog, id) -> dialog.cancel());
                    AlertDialog alert1 = builder1.create();
                    alert1.show();
                    //Toast.makeText(view.getContext(),
                           // "Button clicked = " + button.getId(), Toast.LENGTH_SHORT)
                           // .show();
                });
                counter++;
            });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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


}
