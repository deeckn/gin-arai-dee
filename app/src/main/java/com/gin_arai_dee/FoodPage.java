package com.gin_arai_dee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;

public class FoodPage extends AppCompatActivity {

    // Main Grid Layout
    GridLayout parentGridLayout;

    // Navigation Bar
    BottomNavigationView bottomNavigationView;

    // Category Selector Box
    GridLayout categorySelectorBox;
    ImageButton dropdownTriangleArrow;
    boolean categoryBoxState = true;
    ArrayList<TextView> categoryTextViews;
    TextView main_dish_text;
    TextView appetizer_text;
    TextView desserts_text;
    TextView snacks_text;
    TextView beverages_text;
    ArrayList<CheckBox> categoryCheckBoxes;
    CheckBox main_dish_checkbox;
    CheckBox appetizer_checkbox;
    CheckBox desserts_checkbox;
    CheckBox snacks_checkbox;
    CheckBox beverages_checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);

        // Initializing page elements and objects
        parentGridLayout = findViewById(R.id.main_grid_layout);
        bottomNavigationView = findViewById(R.id.dock_navigation);
        categorySelectorBox = findViewById(R.id.category_selector_box);
        dropdownTriangleArrow = findViewById(R.id.category_arrow);
        categoryTextViews = new ArrayList<>();
        categoryCheckBoxes = new ArrayList<>();

        // Navigation Bar Settings
        bottomNavigationView.setSelectedItemId(R.id.food_page);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int currentItem = item.getItemId();
            if (currentItem == R.id.home_page) {
                startActivity(new Intent(getApplicationContext(), HomePage.class));
                overridePendingTransition(0, 0);
                return true;
            }
            else if (currentItem == R.id.food_page) {
                return true;
            }
            else {
                System.out.println("Not implemented");
                return false;
            }
        });
    }

    public int getValueInDp(int value) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    // Expand and minimize category selector box
    public void toggleCategoryBox(View view) {
        // New Elements Creation
        main_dish_text = new TextView(this);
        appetizer_text = new TextView(this);
        desserts_text = new TextView(this);
        snacks_text = new TextView(this);
        beverages_text = new TextView(this);
        main_dish_checkbox = new CheckBox(this);
        appetizer_checkbox = new CheckBox(this);
        desserts_checkbox = new CheckBox(this);
        snacks_checkbox = new CheckBox(this);
        beverages_checkbox = new CheckBox(this);

        // TextViews for Category Titles
        categoryTextViews.clear();
        categoryTextViews.add(main_dish_text);
        categoryTextViews.add(appetizer_text);
        categoryTextViews.add(desserts_text);
        categoryTextViews.add(snacks_text);
        categoryTextViews.add(beverages_text);

        // Checkboxes for each category titles
        categoryCheckBoxes.clear();
        categoryCheckBoxes.add(main_dish_checkbox);
        categoryCheckBoxes.add(appetizer_checkbox);
        categoryCheckBoxes.add(desserts_checkbox);
        categoryCheckBoxes.add(snacks_checkbox);
        categoryCheckBoxes.add(beverages_checkbox);

        // Category TextView Setup
        main_dish_text.setText(R.string.main_dish);
        appetizer_text.setText(R.string.beverages);
        desserts_text.setText(R.string.desserts);
        snacks_text.setText(R.string.snacks);
        beverages_text.setText(R.string.beverages);
        
        Typeface rubik_regular = ResourcesCompat.getFont(this, R.font.rubik);

        for (TextView t : categoryTextViews) {
            t.setTextSize(18);
            t.setPadding(getValueInDp(20), 0, 0, 0);
            t.setTypeface(rubik_regular);
        }

        for (CheckBox c : categoryCheckBoxes) {
            c.setPadding(getValueInDp(20), 0, 0, 0);
        }

        categoryTextViews.get(categoryTextViews.size()-1)
                .setPadding(getValueInDp(20), 0, 0, getValueInDp(8));
        categoryCheckBoxes.get(categoryCheckBoxes.size()-1)
                .setPadding(0, 0, getValueInDp(5), getValueInDp(8));

        // Performing tasks based on CategoryBoxState boolean
        if (categoryBoxState) {
            dropdownTriangleArrow.animate().rotation(90).setDuration(500);
            for (int i = 0; i < categoryTextViews.size(); i++) {
                categorySelectorBox.addView(categoryTextViews.get(i));
                categorySelectorBox.addView(categoryCheckBoxes.get(i));
            }
        }
        else {
            dropdownTriangleArrow.animate().rotation(0).setDuration(500);
            for (int i = 0; i < categoryTextViews.size() * 2; i++)
                categorySelectorBox.removeViewAt(2);
        }
        categoryBoxState = !categoryBoxState;
    }

    public void toggleNationalityBox(View view) {

    }
}