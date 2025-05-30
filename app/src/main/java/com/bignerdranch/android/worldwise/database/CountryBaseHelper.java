package com.bignerdranch.android.worldwise.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class CountryBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "countryBase.db";

    public CountryBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + CountryDbSchema.CountryTable.NAME + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CountryDbSchema.CountryTable.Cols.COUNTRY_NAME + " TEXT UNIQUE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CountryDbSchema.CountryTable.NAME);
        onCreate(db);
    }
}