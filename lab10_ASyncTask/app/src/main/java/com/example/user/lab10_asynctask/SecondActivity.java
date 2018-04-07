package com.example.user.lab10_asynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText username;
    EditText password;
    TextView val_res;
    Button validate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        val_res=findViewById(R.id.val_result);
        validate=findViewById(R.id.validate);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        validate.setOnClickListener(v -> {
            hideSoftKeyboard(this);
            SecondActivity.LoginTask runner = new SecondActivity.LoginTask();
            runner.execute();
        });




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
    public static void hideSoftKeyboard(SecondActivity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        SecondActivity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }

    public class LoginTask extends AsyncTask<String,String,String> implements Runnable {

        private String user;
        private String pass;
        private String result;
        private ProgressDialog progressDialog;
        @Override
        protected String doInBackground(String... strings) {
            user=username.getText().toString();
            pass=password.getText().toString();
            try {
                runOnUiThread(() -> {
                    if(user.trim().length() == 0)
                    {
                        result="Username field is empty.";
                        val_res.setText(result);
                    }
                    else if( pass.trim().length() == 0) {
                        result="Password field is empty.";
                        val_res.setText(result);
                    }
                    else {
                        result="Validation success.";
                        val_res.setText(result);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(SecondActivity.this,
                    "ProgressDialog",
                    "Logging in..");
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
        }


        @Override
        public void run() {

        }
    }





}
