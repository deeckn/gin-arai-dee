package com.gin_arai_dee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String FOOD_ITEMS_TABLE = "FOOD_ITEMS_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FOOD_ITEM = "FOOD_ITEM";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_DISH_TYPE = "DISH_TYPE";
    public static final String COLUMN_NATIONALITY = "NATIONALITY";
    public static final String COLUMN_KCAL = "KCAL";
    public static final String COLUMN_IMAGE_NAME = "IMAGE_NAME";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "gin_arai_dee_sqlite.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + FOOD_ITEMS_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FOOD_ITEM + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_DISH_TYPE + " TEXT, " +
                COLUMN_NATIONALITY + " TEXT, " +
                COLUMN_KCAL + " INTEGER, " +
                COLUMN_IMAGE_NAME + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public boolean addFoodItem(FoodModel food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FOOD_ITEM, food.getFood_item());
        cv.put(COLUMN_DESCRIPTION, food.getDescription());
        cv.put(COLUMN_DISH_TYPE, food.getDish_type());
        cv.put(COLUMN_NATIONALITY, food.getNationality());
        cv.put(COLUMN_KCAL, food.getKcal());
        cv.put(COLUMN_IMAGE_NAME, food.getImage_name());

        long insert = db.insert(FOOD_ITEMS_TABLE, null, cv);
        return insert != -1;
    }

    public List<FoodModel> getAllFoodItems() {
        List<FoodModel> allFoodItems = new ArrayList<>();
        String query = "SELECT * FROM " + FOOD_ITEMS_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String dish_type = cursor.getString(3);
                String nationality = cursor.getString(4);
                int kcal = cursor.getInt(5);
                String image = cursor.getString(6);
                FoodModel foodItem =
                        new FoodModel(id, name, description, dish_type, nationality, kcal, image);
                allFoodItems.add(foodItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return allFoodItems;
    }
}
