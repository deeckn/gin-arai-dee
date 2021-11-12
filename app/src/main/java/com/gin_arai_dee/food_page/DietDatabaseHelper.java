package com.gin_arai_dee.food_page;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DietDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_DIET = "";

    public DietDatabaseHelper(Context context) {
        super(context, DB_DIET, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
