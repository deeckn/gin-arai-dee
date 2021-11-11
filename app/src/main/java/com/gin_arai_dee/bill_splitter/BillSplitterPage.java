package com.gin_arai_dee.bill_splitter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.gin_arai_dee.HomePage;
import com.gin_arai_dee.R;
import com.gin_arai_dee.food_page.FoodHub;
import com.gin_arai_dee.food_page.UserPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class BillSplitterPage extends AppCompatActivity implements BillDialog.onDoneListener{
    // Font
    Typeface rubik_bold;

    // Overall Info
    TextView numPeople;
    TextView total;
    int newTotal = 0;

    // Tab Widget Layout
    ScrollView scrollView;
    TabHost tabHost;
    TabHost.TabSpec spec;

    // Food Item
    GridLayout foodList;
    String listInput;
    EditText list_add_bar;
    Button addList;
    Button clearList;
    TextView foodName;
    TextView foodPrice;
    TextView foodPerPerson;
    ArrayList<TextView> listTextView = new ArrayList<>();

    // Payer
    GridLayout nameList;
    String nameInput;
    EditText name_add_bar;
    Button addName;
    Button clearName;
    TextView personName;
    TextView personPayment;
    ArrayList<TextView> peopleTextView = new ArrayList<>();

    // Payer Data
    Person person;
    ArrayList<Person> people = new ArrayList<>();

    // Bottom Navigation View
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_splitter_page);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        // Bottom Navigation Settings
        bottomNavigationView = findViewById(R.id.dock_navigation);
        bottomNavigationView.setSelectedItemId(R.id.billing_page);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int currentItem = item.getItemId();
            if (currentItem == R.id.home_page) {
                openHomePage();
                finish();
                overridePendingTransition(0, 0);
                return true;
            }
            else if (currentItem == R.id.food_hub) {
                openFoodHub();
                finish();
                overridePendingTransition(0, 0);
                return true;
            }
            else if (currentItem == R.id.billing_page) {
                return true;
            }
            else if (currentItem == R.id.user_page){
                openFavoritesPage();
                finish();
                overridePendingTransition(0,0);
                return true;
            }
            else {
                System.out.println("Not implemented");
                return false;
            }
        });

        // Initialize bill page elements
        scrollView = findViewById(R.id.bill_scroll_area);
        numPeople  = findViewById(R.id.num_people);
        total      = findViewById(R.id.total);

        // Set TabWidget name
        tabHost = findViewById(R.id.tabhost);
        tabHost.setup();

        spec = tabHost.newTabSpec("List Tab");
        spec.setContent(R.id.list_tab);
        spec.setIndicator("☰ List");
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("Payer Tab");
        spec.setContent(R.id.payer_tab);
        spec.setIndicator("웃 Payer");
        tabHost.addTab(spec);

        // Set TabWidget text style
        final TabWidget tw = tabHost.findViewById(android.R.id.tabs);
        rubik_bold = ResourcesCompat.getFont(this, R.font.rubik_bold);
        for (int i = 0; i < tw.getChildCount(); ++i)
        {
            final View tabView = tw.getChildTabViewAt(i);
            final TextView tv  = tabView.findViewById(android.R.id.title);
            tv.setTextSize(20);
            tv.setAllCaps(false);
            tv.setTypeface(rubik_bold);
        }

        // Initialize food list
        foodList     = findViewById(R.id.food_list);
        list_add_bar = findViewById(R.id.list_add_bar);
        addList      = findViewById(R.id.add_list_button);
        clearList    = findViewById(R.id.clear_list_button);
        foodList.setColumnCount(3);

        // Add food item to List's tab and show food dialog
        addList.setOnClickListener(v -> {
            listInput = list_add_bar.getText().toString();
            if (TextUtils.isEmpty(listInput)) System.out.println("empty");
            else { showFoodDialog(); }
        });

        // Clear all food in list
        clearList.setOnClickListener(view -> {
            for (int i = 0; i < listTextView.size(); i++) { foodList.removeViewAt(0); }
            listTextView.clear();
            total.setText("0");
            for (Person p: people) { p.setPayment(0); }
            newTotal = 0;
            for (int i = 1; i < peopleTextView.size(); i+=2) {
                peopleTextView.get(i).setText(String.valueOf(newTotal));
            }
        });

        // Initialize person list
        nameList     = findViewById(R.id.name_list);
        name_add_bar = findViewById(R.id.name_add_bar);
        addName      = findViewById(R.id.add_name_button);
        clearName    = findViewById(R.id.clear_name_button);
        nameList.setColumnCount(2);

        // Add name to Payer's tab and add to people list
        addName.setOnClickListener(v -> {
            nameInput = name_add_bar.getText().toString();
            if (TextUtils.isEmpty(nameInput)) System.out.println("empty");
            else {
                person = new Person(nameInput);
                people.add(person);

                personName = new TextView(getApplicationContext());
                personName.setText(name_add_bar.getText());
                personName.setTypeface(getResources().getFont(R.font.rubik));
                personName.setTextSize(20);
                personName.setTextColor(ContextCompat.getColor(BillSplitterPage.this, R.color.ghost_white));
                personName.setMinWidth(getResources().getDisplayMetrics().widthPixels/2+170);
                peopleTextView.add(personName);

                personPayment = new TextView(getApplicationContext());
                personPayment.setText(String.valueOf(person.getPayment()));
                personPayment.setTypeface(getResources().getFont(R.font.rubik));
                personPayment.setTextSize(20);
                personPayment.setTextColor(ContextCompat.getColor(BillSplitterPage.this, R.color.ghost_white));
                peopleTextView.add(personPayment);

                name_add_bar.getText().clear();
                nameList.addView(personName);
                nameList.addView(personPayment);
                numPeople.setText(String.valueOf(people.size()));
            }
        });

        // Clear all people in list
        clearName.setOnClickListener(view -> {
            for (int i = 0; i < peopleTextView.size(); i++) { nameList.removeViewAt(0); }
            peopleTextView.clear();
            people.clear();
            numPeople.setText(String.valueOf(people.size()));
        });
    }

    // Opens the bill splitter page
    private void openHomePage() {
        startActivity(new Intent(this, HomePage.class));
    }

    // Open the Food Hub
    private void openFoodHub() {
        startActivity(new Intent(this, FoodHub.class));
    }

    // Open the favorites page
    private void openFavoritesPage() {
        startActivity(new Intent(this, UserPage.class));
    }

    // Open food dialog
    private void showFoodDialog() {
        FragmentManager fm = getSupportFragmentManager();
        BillDialog billDialog = BillDialog.newInstance("Bill Dialog");
        billDialog.show(fm, "Bill Dialog");
        list_add_bar.getText().clear();
    }

    // Set added food back to main page and update person payment
    public String getFoodName() { return listInput; }
    public ArrayList<Person> getPeople() { return people; }

    @Override
    public void sendFood(ListItem food) {
        foodName = new TextView(getApplicationContext());
        foodName.setText(food.getName());
        foodName.setTypeface(getResources().getFont(R.font.rubik));
        foodName.setTextSize(20);
        foodName.setTextColor(ContextCompat.getColor(BillSplitterPage.this, R.color.ghost_white));
        foodName.setMinWidth(getResources().getDisplayMetrics().widthPixels/3+50);
        listTextView.add(foodName);

        foodPrice = new TextView(getApplicationContext());
        foodPrice.setText(String.valueOf(food.getPrice()));
        foodPrice.setTypeface(getResources().getFont(R.font.rubik));
        foodPrice.setTextSize(20);
        foodPrice.setTextColor(ContextCompat.getColor(BillSplitterPage.this, R.color.ghost_white));
        foodPrice.setMinWidth(getResources().getDisplayMetrics().widthPixels/3);
        listTextView.add(foodPrice);

        foodPerPerson = new TextView(getApplicationContext());
        foodPerPerson.setText(String.valueOf(food.getPerPerson()));
        foodPerPerson.setTypeface(getResources().getFont(R.font.rubik));
        foodPerPerson.setTextSize(20);
        foodPerPerson.setTextColor(ContextCompat.getColor(BillSplitterPage.this, R.color.ghost_white));
        listTextView.add(foodPerPerson);

        foodList.addView(foodName);
        foodList.addView(foodPrice);
        foodList.addView(foodPerPerson);

        int i = 1;
        for (Person p: people) {
            peopleTextView.get(i).setText(String.valueOf(p.getPayment()));
            i += 2;
        }
    }

    // Set new total
    @Override
    public void sendResult(int result) {
        newTotal += result;
        total.setText(String.valueOf(newTotal));
    }
}