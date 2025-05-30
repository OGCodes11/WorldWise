package com.bignerdranch.android.worldwise.database;

import android.database.Cursor;
import android.database.CursorWrapper;

public class CountryCursorWrapper extends CursorWrapper {
    public CountryCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public String getCountryName() {
        return getString(getColumnIndex(CountryDbSchema.CountryTable.Cols.COUNTRY_NAME));
    }
}