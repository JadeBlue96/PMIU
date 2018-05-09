package com.example.user.lab_11_12_sqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.user.lab_11_12_sqlite.model.Footballer;

import java.util.ArrayList;
import java.util.List;

public class FootballerOperations {
    public static final String LOGTAG = "FOOT_MNGMNT_SYS";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            FootballerDBHandler.COLUMN_ID,
            FootballerDBHandler.COLUMN_FIRST_NAME,
            FootballerDBHandler.COLUMN_LAST_NAME,
            FootballerDBHandler.COLUMN_GENDER,
            FootballerDBHandler.COLUMN_TEAM_NAME,
            FootballerDBHandler.COLUMN_CAREER_GOALS

    };

    public FootballerOperations(Context context) {
        dbhandler=new FootballerDBHandler(context);
    }
    public void open(){
        Log.i(LOGTAG,"Database Opened");
        database = dbhandler.getWritableDatabase();


    }
    public void close(){
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();

    }

    public Footballer addFootballer(Footballer footballer){
        ContentValues values  = new ContentValues();
        values.put(FootballerDBHandler.COLUMN_FIRST_NAME,footballer.getFirst_name());
        values.put(FootballerDBHandler.COLUMN_LAST_NAME,footballer.getLast_name());
        values.put(FootballerDBHandler.COLUMN_GENDER,footballer.getGender());
        values.put(FootballerDBHandler.COLUMN_TEAM_NAME,footballer.getTeam_name());
        values.put(FootballerDBHandler.COLUMN_CAREER_GOALS,footballer.getCareer_goals());
        long insertid = database.insert(FootballerDBHandler.TABLE_FOOTBALLERS,null,values);
        footballer.setFoot_id(insertid);
        return footballer;
    }
    public Footballer getFootballer(long id) {

        open();
        Cursor cursor = database.query(FootballerDBHandler.TABLE_FOOTBALLERS,allColumns,FootballerDBHandler.COLUMN_ID + "=?"
                ,new String[]{String.valueOf(id)},null,null, null, null);
        Footballer f=new Footballer();
        if (cursor.getCount()>0) {
            cursor.moveToFirst();

            f = new Footballer(Long.parseLong(cursor.getString(0)), cursor.getString(1),
                    cursor.getString(2), cursor.getString(3), cursor.getString(4),
                    Integer.parseInt(cursor.getString(5)));
        }
            return f;

    }
    public List<Footballer> getAllFootballers() {

        Cursor cursor = database.query(FootballerDBHandler.TABLE_FOOTBALLERS,allColumns,
                null,null,null, null, null);

        List<Footballer> footballers = new ArrayList<>();
        if(cursor.getCount() > 0){
            while(cursor.moveToNext()){
                Footballer footballer = new Footballer();
                footballer.setFoot_id(cursor.getLong(cursor.getColumnIndex(FootballerDBHandler.COLUMN_ID)));
                footballer.setFirst_name(cursor.getString(cursor.getColumnIndex(FootballerDBHandler.COLUMN_FIRST_NAME)));
                footballer.setLast_name(cursor.getString(cursor.getColumnIndex(FootballerDBHandler.COLUMN_LAST_NAME)));
                footballer.setGender(cursor.getString(cursor.getColumnIndex(FootballerDBHandler.COLUMN_GENDER)));
                footballer.setTeam_name(cursor.getString(cursor.getColumnIndex(FootballerDBHandler.COLUMN_TEAM_NAME)));
                footballer.setCareer_goals(cursor.getInt(cursor.getColumnIndex(FootballerDBHandler.COLUMN_CAREER_GOALS)));
                footballers.add(footballer);
            }
        }
        // return All Employees
        return footballers;
    }
    public int updateFootballer(Footballer footballer) {

        ContentValues values = new ContentValues();
        values.put(FootballerDBHandler.COLUMN_FIRST_NAME, footballer.getFirst_name());
        values.put(FootballerDBHandler.COLUMN_LAST_NAME, footballer.getLast_name());
        values.put(FootballerDBHandler.COLUMN_GENDER, footballer.getGender());
        values.put(FootballerDBHandler.COLUMN_TEAM_NAME, footballer.getTeam_name());
        values.put(FootballerDBHandler.COLUMN_CAREER_GOALS, footballer.getCareer_goals());

        // updating row
        return database.update(FootballerDBHandler.TABLE_FOOTBALLERS, values,
                FootballerDBHandler.COLUMN_ID + "=?",new String[] { String.valueOf(footballer.getFoot_id())});
    }
    public void deleteFootballer(Footballer footballer) {

        database.delete(FootballerDBHandler.TABLE_FOOTBALLERS, FootballerDBHandler.COLUMN_ID + "=" + footballer.getFoot_id(), null);
    }



}
