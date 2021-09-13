package com.gin_arai_dee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
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
    ImageButton categoryDropdownArrow;
    boolean categoryBoxState = true;
    boolean categoryLoaded = false;
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

    // Nationality Selector Box
    GridLayout nationalitySelectorBox;
    ImageButton nationalityDropdownArrow;
    boolean nationalityBoxState = true;
    boolean nationalityLoaded = false;
    ArrayList<TextView> nationalityTextViews;
    TextView thai_text;
    TextView japanese_text;
    TextView italian_text;
    TextView chinese_text;
    TextView korean_text;
    ArrayList<CheckBox> nationalityCheckBoxes;
    CheckBox thai_checkbox;
    CheckBox japanese_checkbox;
    CheckBox italian_checkbox;
    CheckBox chinese_checkbox;
    CheckBox korean_checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);

        // Initializing page elements and objects
        parentGridLayout = findViewById(R.id.main_grid_layout);
        bottomNavigationView = findViewById(R.id.dock_navigation);

        categorySelectorBox = findViewById(R.id.category_selector_box);
        categoryDropdownArrow = findViewById(R.id.category_arrow);
        categoryTextViews = new ArrayList<>();
        categoryCheckBoxes = new ArrayList<>();

        nationalitySelectorBox = findViewById(R.id.nationality_selector_box);
        nationalityDropdownArrow = findViewById(R.id.nationality_arrow);
        nationalityTextViews = new ArrayList<>();
        nationalityCheckBoxes = new ArrayList<>();

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

    private int getValueInDp(int value) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    // Expand and minimize category selector box
    public void toggleCategoryBox(View view) {
        if (!categoryLoaded) {
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
                c.setGravity(Gravity.END);
                c.setPadding(getValueInDp(20), 0, 0, 0);
            }

            categoryTextViews.get(categoryTextViews.size()-1)
                    .setPadding(getValueInDp(20), 0, 0, getValueInDp(8));
            categoryCheckBoxes.get(categoryCheckBoxes.size()-1)
                    .setPadding(0, 0, getValueInDp(5), getValueInDp(8));

            categoryLoaded = true;
        }

        // Performing tasks based on CategoryBoxState boolean
        if (categoryBoxState) {
            categoryDropdownArrow.animate().rotation(90).setDuration(500);
            for (int i = 0; i < categoryTextViews.size(); i++) {
                categorySelectorBox.addView(categoryTextViews.get(i));
                categorySelectorBox.addView(categoryCheckBoxes.get(i));
            }
        }
        else {
            categoryDropdownArrow.animate().rotation(0).setDuration(500);
            for (int i = 0; i < categoryTextViews.size() * 2; i++)
                categorySelectorBox.removeViewAt(2);
        }
        categoryBoxState = !categoryBoxState;
    }

    public void toggleNationalityBox(View view) {
        if (!nationalityLoaded) {
            // New TextView and Checkbox Creation
            thai_text = new TextView(this);
            japanese_text = new TextView(this);
            italian_text = new TextView(this);
            chinese_text = new TextView(this);
            korean_text = new TextView(this);

            thai_checkbox = new CheckBox(this);
            japanese_checkbox = new CheckBox(this);
            italian_checkbox = new CheckBox(this);
            chinese_checkbox = new CheckBox(this);
            korean_checkbox = new CheckBox(this);

            // TextViews for Nationality Titles
            nationalityTextViews.add(thai_text);
            nationalityTextViews.add(japanese_text);
            nationalityTextViews.add(italian_text);
            nationalityTextViews.add(chinese_text);
            nationalityTextViews.add(korean_text);

            // Checkboxes for each nationality titles
            nationalityCheckBoxes.add(thai_checkbox);
            nationalityCheckBoxes.add(japanese_checkbox);
            nationalityCheckBoxes.add(italian_checkbox);
            nationalityCheckBoxes.add(chinese_checkbox);
            nationalityCheckBoxes.add(korean_checkbox);

            // Category TextView Setup
            thai_text.setText(R.string.thai);
            japanese_text.setText(R.string.japanese);
            italian_text.setText(R.string.italian);
            chinese_text.setText(R.string.chinese);
            korean_text.setText(R.string.korean);

            Typeface rubik_regular = ResourcesCompat.getFont(this, R.font.rubik);

            for (TextView t : nationalityTextViews) {
                t.setTextSize(18);
                t.setPadding(getValueInDp(20), 0, 0, 0);
                t.setTypeface(rubik_regular);
            }

            for (CheckBox c : nationalityCheckBoxes) {
                c.setGravity(Gravity.END);
                c.setPadding(getValueInDp(20), 0, 0, 0);
            }

            nationalityTextViews.get(nationalityTextViews.size()-1)
                    .setPadding(getValueInDp(20), 0, 0, getValueInDp(8));
            nationalityCheckBoxes.get(nationalityCheckBoxes.size()-1)
                    .setPadding(0, 0, getValueInDp(5), getValueInDp(8));

            nationalityLoaded = true;
        }

        // Performing tasks based on CategoryBoxState boolean
        if (nationalityBoxState) {
            nationalityDropdownArrow.animate().rotation(90).setDuration(500);
            for (int i = 0; i < nationalityTextViews.size(); i++) {
                nationalitySelectorBox.addView(nationalityTextViews.get(i));
                nationalitySelectorBox.addView(nationalityCheckBoxes.get(i));
            }
        }
        else {
            nationalityDropdownArrow.animate().rotation(0).setDuration(500);
            for (int i = 0; i < nationalityTextViews.size() * 2; i++)
                nationalitySelectorBox.removeViewAt(2);
        }
        nationalityBoxState = !nationalityBoxState;
    }

    private void clearFoodList() {}
    private void buildFoodList() {}
}