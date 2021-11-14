package com.gin_arai_dee.general;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gin_arai_dee.diet_page.DietBuffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "gin_arai_dee.sqlite";
    public static final String FOOD_ITEMS_TABLE = "FOOD_ITEMS_TABLE";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_FOOD_ITEM = "FOOD_ITEM";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_DISH_TYPE = "DISH_TYPE";
    public static final String COLUMN_NATIONALITY = "NATIONALITY";
    public static final String COLUMN_KCAL = "KCAL";
    public static final String COLUMN_IMAGE_URL = "IMAGE_URL";
    public static final String COLUMN_FAV_STATUS = "FAV_STATUS";

    public static final String DIET_ID = "DIET_ID";
    public static final String DIET_TABLE = "DIET_ITEM_TABLE";
    public static final String COLUMN_DATE = "COLUMN_DATE";
    public static final String COLUMN_TIME = "COLUMN_TIME";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createFoodTableStatement =
                "CREATE TABLE " + FOOD_ITEMS_TABLE + " (" +
                        COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_FOOD_ITEM + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_DISH_TYPE + " TEXT, " +
                        COLUMN_NATIONALITY + " TEXT, " +
                        COLUMN_KCAL + " INTEGER, " +
                        COLUMN_IMAGE_URL + " TEXT, " +
                        COLUMN_FAV_STATUS + " INTEGER)";
        sqLiteDatabase.execSQL(createFoodTableStatement);

        String createDietTable =
                "CREATE TABLE " + DIET_TABLE + " (" +
                        DIET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_TIME + " TEXT, " +
                        COLUMN_ID + " INTEGER) ";

        sqLiteDatabase.execSQL(createDietTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + FOOD_ITEMS_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DIET_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addFoodItem(FoodItem food) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FOOD_ITEM, food.getFood_item());
        cv.put(COLUMN_DESCRIPTION, food.getDescription());
        cv.put(COLUMN_DISH_TYPE, food.getDish_type());
        cv.put(COLUMN_NATIONALITY, food.getNationality());
        cv.put(COLUMN_KCAL, food.getKcal());
        cv.put(COLUMN_IMAGE_URL, food.getImage_url());
        cv.put(COLUMN_FAV_STATUS, food.getIsFavorite());
        db.insert(FOOD_ITEMS_TABLE, null, cv);
        db.close();
    }

    public List<FoodItem> getAllFoodItems() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<FoodItem> allFoodItems = new ArrayList<>();
        String query = "SELECT * FROM " + FOOD_ITEMS_TABLE;

        try {
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
                    int isFavorite = cursor.getInt(7);
                    FoodItem foodItem =
                            new FoodItem(id, name,
                                    description, dish_type,
                                    nationality, kcal,
                                    image, isFavorite);
                    allFoodItems.add(foodItem);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        } catch (Exception ex) {
            System.out.println("Database Error");
        }
        return allFoodItems;
    }

    public List<FoodItem> getAllFavorites() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<FoodItem> favorites = new ArrayList<>();
        String query = "SELECT * FROM " + FOOD_ITEMS_TABLE + " WHERE " + COLUMN_FAV_STATUS + "=1";

        try {
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
                    int isFavorite = cursor.getInt(7);
                    FoodItem foodItem =
                            new FoodItem(id, name,
                                    description, dish_type,
                                    nationality, kcal,
                                    image, isFavorite);
                    favorites.add(foodItem);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
        } catch (Exception ex) {
            System.out.println("Database Error");
        }
        return favorites;
    }

    public void updateFavoriteStatus(int id, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FAV_STATUS, status);
        db.update(FOOD_ITEMS_TABLE, cv, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
    }

    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FOOD_ITEMS_TABLE, null, null);
    }

    public FoodItem findFoodByID(int iD) {
        ArrayList<FoodItem> foodItemList = (ArrayList<FoodItem>) getAllFoodItems();
        for (FoodItem item : foodItemList) {
            if (item.getId() == iD) {
                return item;
            }
        }
        return null;
    }

    public void insertDietItem(String date, String time, FoodItem foodItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_TIME, time);
        cv.put(COLUMN_ID, foodItem.getId());
        db.insert(DIET_TABLE, null, cv);
        db.close();
    }

    public void addAllDietItem(String date, String time, List<FoodItem> foodItems) {
        for (FoodItem item : foodItems) {
            insertDietItem(date, time, item);
        }
    }

    @SuppressLint("Recycle")
    public List<DietBuffer> getAllDietItem(String date) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<DietBuffer> dietBuffers = new ArrayList<>();
        String query = "SELECT * FROM " + DIET_TABLE + " WHERE " + COLUMN_DATE + " =" + '"' + date + '"';
        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {

                    String myTime = cursor.getString(2);
                    int myID = cursor.getInt(3);
                    DietBuffer buffer = new DietBuffer(myTime, myID);
                    dietBuffers.add(buffer);
                } while (cursor.moveToNext());
            }
            cursor.close();
        } catch (Exception ex) {
            Log.e("LOAD ITEM", "ERROR");
        }
        db.close();
        return dietBuffers;
    }

    @SuppressLint("Recycle")
    public void deleteDietItem(String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + DIET_TABLE + " WHERE " + COLUMN_DATE + " = " + '"' + date + '"'  + " AND " + COLUMN_TIME + " = " + '"'  + time + '"' ;
        db.execSQL(query);
        db.close();
    }

}
