package com.gin_arai_dee;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class FoodPage extends AppCompatActivity {

    // Navigation Bar
    BottomNavigationView bottomNavigationView;

    // Category Selector Box
    GridLayout categorySelectorBox;
    ImageButton dropdownTriangleArrow;
    boolean categoryBoxState = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);

        // Initializing page elements
        bottomNavigationView = findViewById(R.id.dock_navigation);
        categorySelectorBox = findViewById(R.id.category_selector_box);
        dropdownTriangleArrow = findViewById(R.id.category_arrow);

        // Navigation Bar Settings
        bottomNavigationView.setSelectedItemId(R.id.food_page);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home_page:
                    startActivity(new Intent(getApplicationContext(), HomePage.class));
                    overridePendingTransition(0, 0);
                    return true;
                case R.id.food_page:
                    break;
                default:
                    System.out.println("Not Implemented");
            }
            return false;
        });
    }

    // Expand and minimize category selector box
    public void toggleCategoryBox(View view) {
        GridLayout.LayoutParams boxDimensions = new GridLayout.LayoutParams();
        boxDimensions.width = categorySelectorBox.getWidth();
        boxDimensions.setGravity(Gravity.CENTER);
        int boxMargins = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 20, getResources()
                        .getDisplayMetrics());
        boxDimensions.setMargins(boxMargins, boxMargins, boxMargins, 0);

        if (categoryBoxState) {
            dropdownTriangleArrow.animate().rotation(90).setDuration(500);
            boxDimensions.height = 400;
        }
        else {
            dropdownTriangleArrow.animate().rotation(0).setDuration(500);
            boxDimensions.height = GridLayout.LayoutParams.WRAP_CONTENT;
        }

        LayoutTransition transition = categorySelectorBox.getLayoutTransition();
        transition.enableTransitionType(LayoutTransition.CHANGING);
        categorySelectorBox.setLayoutParams(boxDimensions);
        categoryBoxState = !categoryBoxState;
    }
}