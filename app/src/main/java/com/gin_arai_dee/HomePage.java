package com.gin_arai_dee;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class HomePage extends AppCompatActivity {

    DatabaseHelper db;
    BottomNavigationView bottomNavigationView;

    ImageButton highlightsButton;
    ImageButton recipesButton;
    ImageButton todaySMealButton;

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
                openFoodHub();
                finish();
                overridePendingTransition(0, 0);
                return true;
            }
            else if (currentItem == R.id.billing_page) {
                openBillSplitterPage();
                finish();
                overridePendingTransition(0, 0);
                return true;
            }
            else if (currentItem == R.id.user_page) {
                openFavoritesPage();
                finish();
                overridePendingTransition(0, 0);
                return true;
            }
            else {
                System.out.println("Not implemented");
                return false;
            }
        });

        highlightsButton = findViewById(R.id.foodHighlightsButton);
        recipesButton = findViewById(R.id.recipesButton);
        todaySMealButton = findViewById(R.id.todaysMealButton);

        highlightsButton.setOnClickListener(view ->
                Toast.makeText(HomePage.this, "Coming Soon!", Toast.LENGTH_SHORT).show());
        recipesButton.setOnClickListener(view ->
                Toast.makeText(HomePage.this,"Coming Soon!",Toast.LENGTH_SHORT).show());
        todaySMealButton.setOnClickListener(view ->
                Toast.makeText(HomePage.this,"Coming Soon!",Toast.LENGTH_SHORT).show());
    }

    // Opens the bill splitter page
    private void openBillSplitterPage() {
        startActivity(new Intent(this, BillSplitterPage.class));
    }

    // Open the Food Hub
    private void openFoodHub() {
        startActivity(new Intent(this, FoodHub.class));
    }

    // Open the favorites page
    private void openFavoritesPage() {
        startActivity(new Intent(this, UserPage.class));
    }

    /***
     * Development Utility for SQLite
     * Adding data when the user opens app for the first time
     */

    private void initializeDatabase() {
        db = new DatabaseHelper(this);
        File dbFile = getDatabasePath(DatabaseHelper.DB_NAME);
        if (!dbFile.exists()) {
            db.clearDatabase();
            importFromCSV();
        }
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
                        tokens[4], Integer.parseInt(tokens[5]), tokens[6], Integer.parseInt(tokens[7])
                );
                db.addFoodItem(food);
            }
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}