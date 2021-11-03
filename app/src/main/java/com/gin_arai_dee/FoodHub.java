package com.gin_arai_dee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FoodHub extends AppCompatActivity {

    // Image Buttons
    ImageButton foodButton;
    ImageButton dietaryButton;
    ImageButton recipeButton;
    ImageButton randomButton;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_hub);
        instantiateObjects();

        bottomNavigationView.setSelectedItemId(R.id.food_hub);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int currentItem = item.getItemId();
            if (currentItem == R.id.home_page) {
                openHomePage();
                finish();
                overridePendingTransition(0, 0);
                return true;
            }
            else if (currentItem == R.id.food_hub) {
                return true;
            }
            else if (currentItem == R.id.billing_page) {
                openBillSplitterPage();
                finish();
                overridePendingTransition(0, 0);
                return true;
            }
            else {
                System.out.println("Not implemented");
                return false;
            }
        });

        foodButton.setOnClickListener(view -> openFoodPage());
        dietaryButton.setOnClickListener(view -> openDietPage());
        randomButton.setOnClickListener(view -> openRandomFoodPage());

        recipeButton.setOnClickListener(view -> Toast.makeText(
                FoodHub.this, "Disable", Toast.LENGTH_SHORT).show()
        );

    }

    private void instantiateObjects() {
        foodButton = findViewById(R.id.FoodButton);
        dietaryButton = findViewById(R.id.DietaryButton);
        recipeButton = findViewById(R.id.RecipeButton);
        randomButton = findViewById(R.id.RandomButton);
        bottomNavigationView = findViewById(R.id.dock_navigation);
    }

    // Opens the dietary page
    private void openDietPage() {
        startActivity(new Intent(this, DietPage.class));
    }

    // Opens the home page
    private void openHomePage() {
        startActivity(new Intent(this, HomePage.class));
    }

    // Opens the food page
    private void openFoodPage() {
        startActivity(new Intent(this, FoodPage.class));
    }

    // Opens the random food page
    private void openRandomFoodPage() {
        startActivity(new Intent(this, RandomFood.class));
    }

    // Opens the bill splitter page
    private void openBillSplitterPage() {
        startActivity(new Intent(this, BillSplitterPage.class));
    }
}