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

    public static final String FOOD_DIET_TABLE = "FOOD_DIET_TABLE";
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
                "CREATE TABLE " + FOOD_DIET_TABLE + " (" +
                        COLUMN_DATE + " TEXT PRIMARY KEY, " +
                        COLUMN_TIME + " TEXT, " +
                        COLUMN_ID + " INTEGER)";

        sqLiteDatabase.execSQL(createDietTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
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
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + FOOD_ITEMS_TABLE + " WHERE " + COLUMN_ID + "=" + iD;
        FoodItem foodItem = null;
        try {
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String dish_type = cursor.getString(3);
                String nationality = cursor.getString(4);
                int kcal = cursor.getInt(5);
                String image = cursor.getString(6);
                int isFavorite = cursor.getInt(7);
                foodItem = new FoodItem(id, name, description, dish_type, nationality, kcal, image, isFavorite);
            }
            cursor.close();
            db.close();
        } catch (Exception ex) {
            System.out.println("Database Error");
        }
        return foodItem;
    }

    public List<DietBuffer> getAtDate(String selectedDate) {
        SQLiteDatabase db = this.getReadableDatabase();
        List<DietBuffer> bufferList = new ArrayList<>();
        String query = "SELECT * FROM " + FOOD_DIET_TABLE + " WHERE " + COLUMN_DATE + " = " + selectedDate;
        try {
            Cursor cursor = db.rawQuery(query, null);

            if (cursor.moveToFirst()) {
                String time = cursor.getString(0);
                String date = cursor.getString(1);
                int iD = cursor.getInt(2);
                DietBuffer buffer = new DietBuffer(time, iD);
                bufferList.add(buffer);
            }
            cursor.close();
            db.close();
        } catch (Exception ex) {
            Log.e("DataBase", ex.getMessage());
        }
        return bufferList;
    }

    public void addDietItem(String date, String time, List<FoodItem> list) {
        if (list == null) return;
        SQLiteDatabase db = this.getWritableDatabase();
        for (FoodItem item : list) {
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_DATE, date);
            cv.put(COLUMN_TIME, time);
            cv.put(COLUMN_ID, item.getId());
            db.insert(FOOD_DIET_TABLE, null, cv);
        }
    }

    @SuppressLint("Recycle")
    public void deleteDietItem(String date, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + FOOD_DIET_TABLE + " WHERE " + COLUMN_DATE + " = " + date + " AND " + COLUMN_TIME + " = " + time;
        Cursor cursor = db.rawQuery(query, null);
        cursor.close();
        db.close();

    }


}
