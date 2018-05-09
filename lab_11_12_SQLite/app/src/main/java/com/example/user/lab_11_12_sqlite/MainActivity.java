package com.example.user.lab_11_12_sqlite;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.lab_11_12_sqlite.db.FootballerOperations;
import com.example.user.lab_11_12_sqlite.model.Footballer;

public class MainActivity extends AppCompatActivity {
    private Button addFootballerButton;
    private Button editFootballerButton;
    private Button deleteFootballerButton;
    private Button viewAllFootballersButton;
    private FootballerOperations footballerOperations;
    private static final String EXTRA_EMP_ID = "footId";
    private static final String EXTRA_ADD_UPDATE = "foot_add_update";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addFootballerButton=findViewById(R.id.button_add_footballer);
        editFootballerButton=findViewById(R.id.button_edit_footballer);
        deleteFootballerButton=findViewById(R.id.button_delete_footballer);
        viewAllFootballersButton=findViewById(R.id.button_view_footballers);

        addFootballerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,Add_Update_Footballer.class);
                i.putExtra(EXTRA_ADD_UPDATE, "Add");
                startActivity(i);
            }
        });
        editFootballerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateFootballer();
            }
        });
        deleteFootballerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFootballer();
            }
        });
        viewAllFootballersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,ViewAllFootballers.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.foot_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.menu_item_settings)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void deleteFootballer() {
        LayoutInflater li=LayoutInflater.from(this);
        View getFootIdView=li.inflate(R.layout.dialog_get_foot_id,null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(getFootIdView);

        final EditText userInput = (EditText) getFootIdView.findViewById(R.id.editTextDialogUserInput);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        footballerOperations = new FootballerOperations(MainActivity.this);
                        footballerOperations.deleteFootballer(footballerOperations.getFootballer(Long.parseLong(userInput.getText().toString())));
                        Toast t = Toast.makeText(MainActivity.this,"Footballer removed successfully!",Toast.LENGTH_SHORT);
                        t.show();
                    }
                }).create()
                .show();
    }

    private void updateFootballer() {
        LayoutInflater li=LayoutInflater.from(this);
        View getFootIdView=li.inflate(R.layout.dialog_get_foot_id,null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(getFootIdView);

        final EditText userInput = (EditText) getFootIdView.findViewById(R.id.editTextDialogUserInput);

        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        Intent i = new Intent(MainActivity.this,Add_Update_Footballer.class);
                        i.putExtra(EXTRA_ADD_UPDATE, "Update");
                        i.putExtra(EXTRA_EMP_ID, Long.parseLong(userInput.getText().toString()));
                        startActivity(i);
                    }
                }).create()
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //footballerOperations.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
       // footballerOperations.close();
    }
}
