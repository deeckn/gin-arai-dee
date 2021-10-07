package com.gin_arai_dee;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db = DatabaseHelper.getInstance(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_food_dialog);
        loadData();

        /* Pages for Testing
        openHomePage();
        openFoodPage();
        openBillSplitterPage();
        */
    }

    // Opens the home page
    private void openHomePage() {
        startActivity(new Intent(this, HomePage.class));
    }

    // Opens the food page
    private void openFoodPage() {
        startActivity(new Intent(this, FoodPage.class));
    }

    // Opens the bill splitter page
    private void openBillSplitterPage() { 
      startActivity(new Intent(this, BillSplitterPage.class));
    }

    // Load Data from SQLite to Device
    private void loadData() {
        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DB_NAME);
        if (!database.exists()) {
            db.getReadableDatabase();
            if (copyDatabase(this))
                Log.d("FoodPage", "Database Copied Successfully");
            else
                Log.d("FoodPage", "Database Not Copied");
        }
    }

    // Copies the Application Database to User Device
    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DB_NAME);
            String outFileName = DatabaseHelper.DB_LOCATION + DatabaseHelper.DB_NAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0)
                outputStream.write(buffer, 0, length);
            outputStream.flush();
            outputStream.close();
            Log.w("FoodPage", "DB Copied");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***
     * Development Utility for SQLite
     * Use for uploading new data into SQLite using CSV file
     */
    private void importFromCSV() {
        InputStream inputStream = getResources().openRawResource(R.raw.food_list);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8)
        );

        String line;
        try {
            reader.readLine();
            while ( (line = reader.readLine()) != null ) {
                String[] tokens = line.split(";");
                FoodItem food = new FoodItem(
                        Integer.parseInt(tokens[0]), tokens[1], tokens[2], tokens[3],
                        tokens[4], Integer.parseInt(tokens[5]), tokens[6]
                );
                db.addFoodItem(food);
            }
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}