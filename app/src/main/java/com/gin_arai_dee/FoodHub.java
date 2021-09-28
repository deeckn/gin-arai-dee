package com.gin_arai_dee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

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
    }
}