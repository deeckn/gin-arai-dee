package com.gin_arai_dee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;

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

import java.util.ArrayList;

public class BillSplitterPage extends AppCompatActivity implements BillDialog.onDoneListener{
    private void showFoodDialog() {
        FragmentManager fm = getSupportFragmentManager();
        BillDialog billDialog = BillDialog.newInstance("Bill Dialog");
//        Objects.requireNonNull(billDialog.getDialog()).getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        billDialog.show(fm, "Bill Dialog");
//        dialog = (BillDialog)
//        dialog.getFood();
        list_add_bar.getText().clear();
    }

    public String getFoodName() { return listInput; }
    public ArrayList<Person> getPeople() { return people; }
//    private BillDialog dialog;

    // Info
    TextView numPeople;
    TextView total;

    // Tab Widget Layout
    ScrollView scrollView;
    TabHost tabHost;
    TabHost.TabSpec spec;
    // Food Item
    EditText list_add_bar;
    Button addList;
    String listInput;
    ArrayList<FoodItem> list = new ArrayList<>();
    GridLayout foodList;
    TextView foodName;
    TextView foodPrice;
    TextView foodPerPerson;
//    DialogFragment food_dialog;
    // Payer
    GridLayout nameList;
    Button addName;
    EditText name_add_bar;
    TextView personName;
    TextView personPayment;
    Person person;
    ArrayList<Person> people = new ArrayList<>();
    String nameInput;
    // Font
    Typeface rubik_bold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_splitter_page);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        scrollView = findViewById(R.id.bill_scroll_area);
        list_add_bar = findViewById(R.id.list_add_bar);
        total = findViewById(R.id.total);

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
            final TextView tv = tabView.findViewById(android.R.id.title);
            tv.setTextSize(20);
            tv.setAllCaps(false);
            tv.setTypeface(rubik_bold);
        }

//        food_dialog = new DialogFragment(R.layout.activity_bill_food_dialog);
//        food_dialog.setContentView(R.layout.activity_bill_food_dialog);
//        food_dialog.getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        food_dialog.setCancelable(false);

        foodList = findViewById(R.id.food_list);
        foodList.setColumnCount(3);

        // Add food item and show dialog
        addList = findViewById(R.id.add_list_button);
        addList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listInput = list_add_bar.getText().toString();
//                ListNameEvent listNameEvent = new ListNameEvent(list_input);
//                EventBus.getDefault().post(listNameEvent);
                if (TextUtils.isEmpty(listInput)) System.out.println("empty");
                else {
//                    System.out.println(list_input);
//                    list = new FoodItem(list_input, );
                    showFoodDialog();
//                    BillDialog.newInstance("")
//                    food_dialog.show
//                    food_name.setText(list_add_bar.getText());
                }
            }
        });

        // Payer tab's view
        addName = findViewById(R.id.add_name_button);
        name_add_bar = findViewById(R.id.name_add_bar);
        nameList = findViewById(R.id.name_list);
        numPeople = findViewById(R.id.num_people);
        nameList.setColumnCount(2);

        // Add name to Payer's tab and add to people's list
        addName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nameInput = name_add_bar.getText().toString();
                if (TextUtils.isEmpty(nameInput)) System.out.println("empty");
                else {
//                    System.out.println(name_input);
                    person = new Person(nameInput);
                    people.add(person);

                    personName = new TextView(getApplicationContext());
                    personName.setText(name_add_bar.getText());
                    personName.setTypeface(getResources().getFont(R.font.rubik));
                    personName.setTextSize(20);
                    personName.setTextColor(getResources().getColor(R.color.ghost_white));
                    personName.setMinWidth(getResources().getDisplayMetrics().widthPixels/2+170);

                    personPayment = new TextView(getApplicationContext());
                    personPayment.setText(String.valueOf(person.getPayment()));
                    personPayment.setTypeface(getResources().getFont(R.font.rubik));
                    personPayment.setTextSize(20);
                    personPayment.setTextColor(getResources().getColor(R.color.ghost_white));

                    name_add_bar.getText().clear();
                    nameList.addView(personName);
                    nameList.addView(personPayment);
                    person.setTextView(personName.getId(), personPayment.getId());
                    numPeople.setText(String.valueOf(people.size()));
                }
            }
        });
    }

    @Override
    public void sendFood(ListItem food) {
        foodName = new TextView(getApplicationContext());
        foodName.setText(food.getName());
        foodName.setTypeface(getResources().getFont(R.font.rubik));
        foodName.setTextSize(20);
        foodName.setTextColor(getResources().getColor(R.color.ghost_white));
        foodName.setMinWidth(getResources().getDisplayMetrics().widthPixels/3+50);

        foodPrice = new TextView(getApplicationContext());
        foodPrice.setText(String.valueOf(food.getPrice()));
        foodPrice.setTypeface(getResources().getFont(R.font.rubik));
        foodPrice.setTextSize(20);
        foodPrice.setTextColor(getResources().getColor(R.color.ghost_white));
        foodPrice.setMinWidth(getResources().getDisplayMetrics().widthPixels/3);

        foodPerPerson = new TextView(getApplicationContext());
        foodPerPerson.setText(String.valueOf(food.getPerPerson()));
        foodPerPerson.setTypeface(getResources().getFont(R.font.rubik));
        foodPerPerson.setTextSize(20);
        foodPerPerson.setTextColor(getResources().getColor(R.color.ghost_white));

        foodList.addView(foodName);
        foodList.addView(foodPrice);
        foodList.addView(foodPerPerson);
    }
}