package com.gin_arai_dee;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HomePage extends AppCompatActivity {

    DatabaseHelper db;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initializeDatabase();

        // Navigation Settings
        bottomNavigationView = findViewById(R.id.dock_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home_page);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int currentItem = item.getItemId();
            if (currentItem == R.id.home_page) {
                return true;
            }
            else if (currentItem == R.id.food_hub) {
                startActivity(new Intent(getApplicationContext(), FoodHub.class));
                overridePendingTransition(0, 0);
                return true;
            }
            else if (currentItem == R.id.billing_page) {
                startActivity(new Intent(getApplicationContext(), BillSplitterPage.class));
                overridePendingTransition(0, 0);
                return true;
            }
            else {
                System.out.println("Not implemented");
                return false;
            }
        });

        // Open your pages here
        // openFoodPage();
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

    // Open the Food Hub
    private void openFoodHub() {
        startActivity(new Intent(this, FoodHub.class));
    }

    /***
     * Development Utility for SQLite
     * Adding data when the user opens app for the first time
     */

    private void initializeDatabase() {
        db = new DatabaseHelper(this);
        db.clearDatabase();
        importFromCSV();
    }

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