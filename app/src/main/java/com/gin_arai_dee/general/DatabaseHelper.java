package com.gin_arai_dee.general;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME              = "gin_arai_dee.sqlite";
    public static final String FOOD_ITEMS_TABLE     = "FOOD_ITEMS_TABLE";
    public static final String COLUMN_ID            = "ID";
    public static final String COLUMN_FOOD_ITEM     = "FOOD_ITEM";
    public static final String COLUMN_DESCRIPTION   = "DESCRIPTION";
    public static final String COLUMN_DISH_TYPE     = "DISH_TYPE";
    public static final String COLUMN_NATIONALITY   = "NATIONALITY";
    public static final String COLUMN_KCAL          = "KCAL";
    public static final String COLUMN_IMAGE_URL     = "IMAGE_URL";
    public static final String COLUMN_FAV_STATUS    = "FAV_STATUS";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement =
                "CREATE TABLE " + FOOD_ITEMS_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FOOD_ITEM + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_DISH_TYPE + " TEXT, " +
                COLUMN_NATIONALITY + " TEXT, " +
                COLUMN_KCAL + " INTEGER, " +
                COLUMN_IMAGE_URL + " TEXT, " +
                COLUMN_FAV_STATUS + " INTEGER)";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

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
        }
        catch (Exception ex) {
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
        }
        catch (Exception ex) {
            System.out.println("Database Error");
        }
        return favorites;
    }

    public void updateFavoriteStatus(int id, int status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FAV_STATUS, status);
        db.update(FOOD_ITEMS_TABLE, cv, COLUMN_ID + "=?", new String[] {String.valueOf(id)});
    }

    public void clearDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FOOD_ITEMS_TABLE, null, null);
    }
}
