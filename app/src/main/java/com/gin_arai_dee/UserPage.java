package com.gin_arai_dee;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

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

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeCallback);
        itemTouchHelper.attachToRecyclerView(favoritesList);
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

    ItemTouchHelper.SimpleCallback swipeCallback = new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView,
                              @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int id = favorites.get(viewHolder.getBindingAdapterPosition()).getId();
            System.out.println(id);
            if (direction == ItemTouchHelper.LEFT) {
                db.updateFavoriteStatus(id, 0);
                favorites.remove(viewHolder.getBindingAdapterPosition());
                Toast.makeText(UserPage.this, "Removed from favorites", Toast.LENGTH_SHORT).show();
            }
            favoritesAdapter.notifyDataSetChanged();
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(UserPage.this, R.color.wine))
                    .addSwipeLeftActionIcon(R.drawable.ic_baseline_delete_24)
                    .create()
                    .decorate();

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
}
