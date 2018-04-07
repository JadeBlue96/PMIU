package com.example.user.lab10_asynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FirstActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button button;
    private TextView status;
    private Integer time=2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final LinearLayout ll = (LinearLayout)findViewById(R.id.linear);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_first);
        button=findViewById(R.id.start);
        status=findViewById(R.id.status);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ASyncTaskRunner runner = new ASyncTaskRunner();
                String sleepTime = time.toString();
                runner.execute(sleepTime);
            }
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


    public class ASyncTaskRunner extends AsyncTask<String,String,String> {
        private String resp;
        ProgressDialog progressDialog;
        @Override
        protected String doInBackground(String... strings) {
            publishProgress("Sleeping..."); // Calls onProgressUpdate()
            try {
                int time = Integer.parseInt(strings[0])*1000;

                Thread.sleep(time);
                resp = "Status: Finished";
            } catch (InterruptedException e) {
                e.printStackTrace();
                resp = e.getMessage();
            } catch (Exception e) {
                e.printStackTrace();
                resp = e.getMessage();
            }
            return resp;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
            status.setText(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(FirstActivity.this,
                    "ProgressDialog",
                    "Wait for "+time.toString()+ " seconds");
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            status.setText(values[0]);
        }
    }


}
