package com.gin_arai_dee.Helper;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.gin_arai_dee.Domain.FoodItem;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DB_NAME              = "gin_arai_dee.sqlite";
    @SuppressLint("SdCardPath")
    public static final String DB_LOCATION          = "/data/data/com.gin_arai_dee/databases/";
    public static final String FOOD_ITEMS_TABLE     = "FOOD_ITEMS_TABLE";
//    public static final String COLUMN_ID            = "ID";
    public static final String COLUMN_FOOD_ITEM     = "FOOD_ITEM";
    public static final String COLUMN_DESCRIPTION   = "DESCRIPTION";
    public static final String COLUMN_DISH_TYPE     = "DISH_TYPE";
    public static final String COLUMN_NATIONALITY   = "NATIONALITY";
    public static final String COLUMN_KCAL          = "KCAL";
    public static final String COLUMN_IMAGE_NAME    = "IMAGE_NAME";

    @SuppressLint("StaticFieldLeak")
    private static DatabaseHelper databaseHelper;
    private final Context context;
    private SQLiteDatabase db;

    private DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    public static DatabaseHelper getInstance(Context context) {
        if (databaseHelper == null) databaseHelper = new DatabaseHelper(context);
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /* Used only to create new table in SQLite
        String createTableStatement = "CREATE TABLE " + FOOD_ITEMS_TABLE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FOOD_ITEM + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_DISH_TYPE + " TEXT, " +
                COLUMN_NATIONALITY + " TEXT, " +
                COLUMN_KCAL + " INTEGER, " +
                COLUMN_IMAGE_NAME + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {}

    public void openDatabase() {
        String path = context.getDatabasePath(DB_NAME).getPath();
        if (db != null && db.isOpen()) return;
        db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase() {
        if (db != null) db.close();
    }

    public void addFoodItem(FoodItem food) {
        openDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_FOOD_ITEM, food.getFood_item());
        cv.put(COLUMN_DESCRIPTION, food.getDescription());
        cv.put(COLUMN_DISH_TYPE, food.getDish_type());
        cv.put(COLUMN_NATIONALITY, food.getNationality());
        cv.put(COLUMN_KCAL, food.getKcal());
        cv.put(COLUMN_IMAGE_NAME, food.getImage_name());

        long insert = db.insert(FOOD_ITEMS_TABLE, null, cv);
        closeDatabase();
    }

    public List<FoodItem> getAllFoodItems() {
        openDatabase();
        List<FoodItem> allFoodItems = new ArrayList<>();
        String query = "SELECT * FROM " + FOOD_ITEMS_TABLE;
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
                FoodItem foodItem =
                        new FoodItem(id, name, description, dish_type, nationality, kcal, image);
                allFoodItems.add(foodItem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        closeDatabase();
        return allFoodItems;
    }
}
