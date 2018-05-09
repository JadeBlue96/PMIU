package com.example.user.lab_11_12_sqlite;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.user.lab_11_12_sqlite.db.FootballerOperations;
import com.example.user.lab_11_12_sqlite.model.Footballer;

import java.util.List;

public class ViewAllFootballers extends ListActivity {
    private FootballerOperations footballerOperations;
    List<Footballer> footballers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_employees);
        footballerOperations = new FootballerOperations(this);
        footballerOperations.open();
        footballers = footballerOperations.getAllFootballers();
        footballerOperations.close();
        ArrayAdapter<Footballer> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, footballers);
        setListAdapter(adapter);
    }
}
