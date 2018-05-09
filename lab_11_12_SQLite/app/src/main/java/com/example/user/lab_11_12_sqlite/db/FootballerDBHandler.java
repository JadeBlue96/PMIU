package com.example.user.lab_11_12_sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FootballerDBHandler extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "footballers.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_FOOTBALLERS = "footballers";
    public static final String COLUMN_ID = "foot_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_TEAM_NAME= "team_name";
    public static final String COLUMN_CAREER_GOALS= "career_goals";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_FOOTBALLERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FIRST_NAME + " TEXT, " +
                    COLUMN_LAST_NAME + " TEXT, " +
                    COLUMN_GENDER + " TEXT, " +
                    COLUMN_TEAM_NAME + " TEXT, " +
                    COLUMN_CAREER_GOALS + " INTEGER " +
                    ")";

    public FootballerDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_FOOTBALLERS);
        db.execSQL(TABLE_CREATE);
    }
}
