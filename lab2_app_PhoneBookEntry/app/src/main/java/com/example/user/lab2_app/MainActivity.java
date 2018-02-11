package com.example.user.lab2_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import entity.PhoneBookEntry;

public class MainActivity extends AppCompatActivity {

    private EditText m_edit_n;
    private EditText m_edit_p;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m_edit_n=(EditText)findViewById(R.id.in_name);
        m_edit_p=(EditText)findViewById(R.id.in_phone);

    }

    public void onButtonClicked(View view) {
        if(m_edit_n.getText().toString().length()>3 && m_edit_p.getText().toString().length()>3 )
        {
            PhoneBookEntry entry=new PhoneBookEntry(m_edit_n.getText().toString(),m_edit_p.getText().toString());
            Intent intent=new Intent(this,SecActivity.class);
            Bundle bundle=new Bundle();
            bundle.putSerializable("entry", entry);
            intent.putExtras(bundle);
            startActivity(intent);
            return;
        }
        else
        {
            Toast.makeText(this,"An error occured!",Toast.LENGTH_LONG).show();
        }
    }
}
