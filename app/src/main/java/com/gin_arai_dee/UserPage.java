package com.gin_arai_dee;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class UserPage extends AppCompatActivity {

    DatabaseHelper db;
    BottomNavigationView bottomNavigationView;
    ArrayList<FoodItem> favorites;
    RecyclerView favoritesList;
    CardAdapter favoritesAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        // Bottom Navigation
        bottomNavigationView = findViewById(R.id.dock_navigation);
        bottomNavigationView.setSelectedItemId(R.id.user_page);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int currentItem = item.getItemId();
            if (currentItem == R.id.user_page) {
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
            else if (currentItem == R.id.home_page){
                openHomePage();
                finish();
                overridePendingTransition(0,0);
                return true;
            }
            else {
                System.out.println("Not implemented");
                return false;
            }
        });

        db = new DatabaseHelper(this);
        favorites = (ArrayList<FoodItem>) db.getAllFavorites();
        favoritesList = findViewById(R.id.favorites_recyclerview);
        favoritesAdapter = new CardAdapter(this, favorites);
        favoritesList.setAdapter(favoritesAdapter);
    }

    // Opens the Home Page
    private void openHomePage() {
        startActivity(new Intent(this, HomePage.class));
    }

    // Opens the BillSplitterPage
    private void openBillSplitterPage() {
        startActivity(new Intent(this, BillSplitterPage.class));
    }

    // Open the Food Hub
    private void openFoodHub() {
        startActivity(new Intent(this, FoodHub.class));
    }
}
