package com.gin_arai_dee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FoodHub extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_hub);

        bottomNavigationView = findViewById(R.id.dock_navigation);
        bottomNavigationView.setSelectedItemId(R.id.food_hub);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int currentItem = item.getItemId();
            if (currentItem == R.id.home_page) {
                startActivity(new Intent(getApplicationContext(), HomePage.class));
                overridePendingTransition(0, 0);
                return true;
            }
            else if (currentItem == R.id.food_hub) {
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
        ImageButton foodButton = findViewById(R.id.FoodButton);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //openFoodPage();
                Toast.makeText(FoodHub.this, "Disable", Toast.LENGTH_SHORT).show();
            }
        });
        ImageButton dietaryButton = findViewById(R.id.DietaryButton);
        dietaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDietPage();
            }
        });
        ImageButton recipeButton = findViewById(R.id.RecipeButton);
        recipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FoodHub.this, "Disable", Toast.LENGTH_SHORT).show();
            }
        });
        ImageButton randomButton = findViewById(R.id.RandomButton);
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FoodHub.this, "Disable", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void openFoodPage() {
        startActivity(new Intent(this, FoodPage.class));
    }
    private void openDietPage() {
        startActivity(new Intent(this, DietPage.class));
    }
}