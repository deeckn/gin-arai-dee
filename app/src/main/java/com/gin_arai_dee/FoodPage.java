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
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class FoodPage extends AppCompatActivity {
    // Database
    DatabaseHelper db;

    // Food Data
    List<FoodModel> allFoodItems;
    Set<FoodModel> displayFoodItems;
    HashMap<String, List<FoodModel>> categoryFoodGroup;
    HashMap<String, List<FoodModel>> nationalityFoodGroup;
    List<String> categoryFilter;
    List<String> nationalityFilter;

    // Main Grid Layout
    GridLayout parentGridLayout;

    // Navigation Bar
    BottomNavigationView bottomNavigationView;

    // Category Selector Box
    GridLayout categorySelectorBox;
    TextView chooseCategoryTitle;
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
    TextView chooseNationalityTitle;
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

    // String Constants
    public static final String MAIN_DISH = "main_dish";
    public static final String APPETIZERS = "appetizers";
    public static final String SNACKS = "snacks";
    public static final String DESSERTS = "desserts";
    public static final String BEVERAGES = "beverages";
    public static final String THAI = "thai";
    public static final String ITALIAN = "italian";
    public static final String JAPANESE = "japanese";
    public static final String CHINESE = "chinese";
    public static final String KOREAN = "korean";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_page);
        initializeInstances();
        loadData();

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

        // Category Dropdown Settings
        chooseCategoryTitle.setOnClickListener(this::toggleCategoryBox);
        categoryDropdownArrow.setOnClickListener(this::toggleCategoryBox);

        // Nationality Dropdown Settings
        chooseNationalityTitle.setOnClickListener(this::toggleNationalityBox);
        nationalityDropdownArrow.setOnClickListener(this::toggleNationalityBox);

        // Category Checkbox Settings
        main_dish_checkbox.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) categoryFilter.add(MAIN_DISH);
            else categoryFilter.remove(MAIN_DISH);
            updateDisplayFoodItems();
        });

        appetizer_checkbox.setOnCheckedChangeListener(((compoundButton, b) -> {
            if (b) categoryFilter.add(APPETIZERS);
            else categoryFilter.remove(APPETIZERS);
            updateDisplayFoodItems();
        }));

        snacks_checkbox.setOnCheckedChangeListener(((compoundButton, b) -> {
            if (b) categoryFilter.add(SNACKS);
            else categoryFilter.remove(SNACKS);
            updateDisplayFoodItems();
        }));

        desserts_checkbox.setOnCheckedChangeListener(((compoundButton, b) -> {
            if (b) categoryFilter.add(DESSERTS);
            else categoryFilter.remove(DESSERTS);
            updateDisplayFoodItems();
        }));

        beverages_checkbox.setOnCheckedChangeListener(((compoundButton, b) -> {
            if (b) categoryFilter.add(BEVERAGES);
            else categoryFilter.remove(BEVERAGES);
            updateDisplayFoodItems();
        }));

        // Nationality Checkbox Settings
        thai_checkbox.setOnCheckedChangeListener(((compoundButton, b) -> {
            if (b) nationalityFilter.add(THAI);
            else nationalityFilter.remove(THAI);
            updateDisplayFoodItems();
        }));

        italian_checkbox.setOnCheckedChangeListener(((compoundButton, b) -> {
            if (b) nationalityFilter.add(ITALIAN);
            else nationalityFilter.remove(ITALIAN);
            updateDisplayFoodItems();
        }));

        japanese_checkbox.setOnCheckedChangeListener(((compoundButton, b) -> {
            if (b) nationalityFilter.add(JAPANESE);
            else nationalityFilter.remove(JAPANESE);
            updateDisplayFoodItems();
        }));

        chinese_checkbox.setOnCheckedChangeListener(((compoundButton, b) -> {
            if (b) nationalityFilter.add(CHINESE);
            else nationalityFilter.remove(CHINESE);
            updateDisplayFoodItems();
        }));

        korean_checkbox.setOnCheckedChangeListener(((compoundButton, b) -> {
            if (b) nationalityFilter.add(KOREAN);
            else nationalityFilter.remove(KOREAN);
            updateDisplayFoodItems();
        }));
    }

    private void initializeInstances() {
        // Database
        db = new DatabaseHelper(this);

        // Food Data
        displayFoodItems = new HashSet<>();
        categoryFoodGroup = new HashMap<>();
        categoryFoodGroup.put(MAIN_DISH, new ArrayList<>());
        categoryFoodGroup.put(APPETIZERS, new ArrayList<>());
        categoryFoodGroup.put(SNACKS, new ArrayList<>());
        categoryFoodGroup.put(DESSERTS, new ArrayList<>());
        categoryFoodGroup.put(BEVERAGES, new ArrayList<>());

        nationalityFoodGroup = new HashMap<>();
        nationalityFoodGroup.put(THAI, new ArrayList<>());
        nationalityFoodGroup.put(ITALIAN, new ArrayList<>());
        nationalityFoodGroup.put(JAPANESE, new ArrayList<>());
        nationalityFoodGroup.put(CHINESE, new ArrayList<>());
        nationalityFoodGroup.put(KOREAN, new ArrayList<>());

        categoryFilter = new ArrayList<>();
        nationalityFilter = new ArrayList<>();

        // Initializing page elements and objects
        parentGridLayout = findViewById(R.id.main_grid_layout);
        bottomNavigationView = findViewById(R.id.dock_navigation);

        chooseCategoryTitle = findViewById(R.id.choose_category_title);
        categorySelectorBox = findViewById(R.id.category_selector_box);
        categoryDropdownArrow = findViewById(R.id.category_arrow);

        chooseNationalityTitle = findViewById(R.id.choose_nationality_title);
        nationalitySelectorBox = findViewById(R.id.nationality_selector_box);
        nationalityDropdownArrow = findViewById(R.id.nationality_arrow);

        // Category Dropdown Box Elements
        categoryTextViews = new ArrayList<>();
        main_dish_text = new TextView(this);
        appetizer_text = new TextView(this);
        desserts_text = new TextView(this);
        snacks_text = new TextView(this);
        beverages_text = new TextView(this);

        categoryCheckBoxes = new ArrayList<>();
        main_dish_checkbox = new CheckBox(this);
        appetizer_checkbox = new CheckBox(this);
        desserts_checkbox = new CheckBox(this);
        snacks_checkbox = new CheckBox(this);
        beverages_checkbox = new CheckBox(this);

        // Nationality Dropdown Box Elements
        nationalityTextViews = new ArrayList<>();
        thai_text = new TextView(this);
        japanese_text = new TextView(this);
        italian_text = new TextView(this);
        chinese_text = new TextView(this);
        korean_text = new TextView(this);

        nationalityCheckBoxes = new ArrayList<>();
        thai_checkbox = new CheckBox(this);
        japanese_checkbox = new CheckBox(this);
        italian_checkbox = new CheckBox(this);
        chinese_checkbox = new CheckBox(this);
        korean_checkbox = new CheckBox(this);
    }

    private void loadData() {
        loadFoodItems();
        groupFoodItems();
    }

    private int getValueInDp(int value) {
        float scale = getResources().getDisplayMetrics().density;
        return (int) (value * scale + 0.5f);
    }

    // Expand and minimize category selector box
    public void toggleCategoryBox(View view) {
        if (!categoryLoaded) {
            // Loads TextViews into ArrayList
            categoryTextViews.add(main_dish_text);
            categoryTextViews.add(appetizer_text);
            categoryTextViews.add(desserts_text);
            categoryTextViews.add(snacks_text);
            categoryTextViews.add(beverages_text);

            // Loads Checkboxes to ArrayList
            categoryCheckBoxes.add(main_dish_checkbox);
            categoryCheckBoxes.add(appetizer_checkbox);
            categoryCheckBoxes.add(desserts_checkbox);
            categoryCheckBoxes.add(snacks_checkbox);
            categoryCheckBoxes.add(beverages_checkbox);

            // Category UI Elements Setup
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
            categoryDropdownArrow.animate().rotation(90).setDuration(300);
            for (int i = 0; i < categoryTextViews.size(); i++) {
                categorySelectorBox.addView(categoryTextViews.get(i));
                categorySelectorBox.addView(categoryCheckBoxes.get(i));
            }
        }
        else {
            categoryDropdownArrow.animate().rotation(0).setDuration(300);
            for (int i = 0; i < categoryTextViews.size() * 2; i++)
                categorySelectorBox.removeViewAt(2);
        }
        categoryBoxState = !categoryBoxState;
    }

    public void toggleNationalityBox(View view) {
        if (!nationalityLoaded) {
            // Loads TextViews into ArrayList
            nationalityTextViews.add(thai_text);
            nationalityTextViews.add(japanese_text);
            nationalityTextViews.add(italian_text);
            nationalityTextViews.add(chinese_text);
            nationalityTextViews.add(korean_text);

            // Loads Checkboxes to ArrayList
            nationalityCheckBoxes.add(thai_checkbox);
            nationalityCheckBoxes.add(japanese_checkbox);
            nationalityCheckBoxes.add(italian_checkbox);
            nationalityCheckBoxes.add(chinese_checkbox);
            nationalityCheckBoxes.add(korean_checkbox);

            // Category UI Elements Setup
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
            nationalityDropdownArrow.animate().rotation(90).setDuration(300);
            for (int i = 0; i < nationalityTextViews.size(); i++) {
                nationalitySelectorBox.addView(nationalityTextViews.get(i));
                nationalitySelectorBox.addView(nationalityCheckBoxes.get(i));
            }
        }
        else {
            nationalityDropdownArrow.animate().rotation(0).setDuration(300);
            for (int i = 0; i < nationalityTextViews.size() * 2; i++)
                nationalitySelectorBox.removeViewAt(2);
        }
        nationalityBoxState = !nationalityBoxState;
    }

    // Add Food Item to Database
    private void addFoodItemToDatabase(String name, String description, String dishType,
                                       String nationality, int kcal, String imageName) {

        FoodModel food = new FoodModel(0, name, description, dishType, nationality, kcal, imageName);
        boolean success = db.addFoodItem(food);

        // Debug Output (Delete later)
        if (success) Toast.makeText(this, "SUCCESS SQLITE", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "UNSUCCESSFUL SQLITE", Toast.LENGTH_SHORT).show();
    }

    // Loads all food items from the database
    private void loadFoodItems() {
        allFoodItems = db.getAllFoodItems();
    }

    // Filters Food Items into Hashmaps
    private void groupFoodItems() {
        for (FoodModel food : allFoodItems) {
            if (food == null) continue;
            else {
                String dishType = food.getDish_type();
                switch (dishType) {
                    case MAIN_DISH:
                        Objects.requireNonNull(categoryFoodGroup.get(MAIN_DISH)).add(food);
                        break;
                    case APPETIZERS:
                        Objects.requireNonNull(categoryFoodGroup.get(APPETIZERS)).add(food);
                        break;
                    case SNACKS:
                        Objects.requireNonNull(categoryFoodGroup.get(SNACKS)).add(food);
                        break;
                    case DESSERTS:
                        Objects.requireNonNull(categoryFoodGroup.get(DESSERTS)).add(food);
                        break;
                    case BEVERAGES:
                        Objects.requireNonNull(categoryFoodGroup.get(BEVERAGES)).add(food);
                        break;
                    default:
                        System.out.println("No category");
                }
            }

            String nationality = food.getNationality();
            switch (nationality) {
                case THAI:
                    Objects.requireNonNull(nationalityFoodGroup.get(THAI)).add(food);
                case ITALIAN:
                    Objects.requireNonNull(nationalityFoodGroup.get(ITALIAN)).add(food);
                case JAPANESE:
                    Objects.requireNonNull(nationalityFoodGroup.get(JAPANESE)).add(food);
                case CHINESE:
                    Objects.requireNonNull(nationalityFoodGroup.get(CHINESE)).add(food);
                case KOREAN:
                    Objects.requireNonNull(nationalityFoodGroup.get(KOREAN)).add(food);
                default:
                    System.out.println("No nationality");
            }
        }
    }

    private void updateDisplayFoodItems() {
        displayFoodItems.clear();

        if (categoryFilter.isEmpty() && nationalityFilter.isEmpty()) {
            displayFoodItems.addAll(allFoodItems);
        }

        if (!categoryFilter.isEmpty()) {
            for (String type : categoryFilter) {
                List<FoodModel> temp = categoryFoodGroup.get(type);
                if (temp == null || temp.isEmpty()) break;
                displayFoodItems.addAll(temp);
            }
        }

        if (!nationalityFilter.isEmpty()) {
            for (String type : nationalityFilter) {
                List<FoodModel> temp = nationalityFoodGroup.get(type);
                if (temp == null || temp.isEmpty()) break;
                displayFoodItems.addAll(temp);
            }
        }
    }
}