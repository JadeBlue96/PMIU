package com.example.user.lab2_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import entity.PhoneBookEntry;

public class SecActivity extends AppCompatActivity implements Serializable {
TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_sec);
        Intent intent=this.getIntent();
        Bundle bundle=intent.getExtras();
        PhoneBookEntry entry=(PhoneBookEntry) bundle.getSerializable("entry");
        tv1=(TextView)findViewById(R.id.m_txt_sec);
        tv1.setText(entry.toString());

    }


}
