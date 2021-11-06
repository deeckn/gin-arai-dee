package com.gin_arai_dee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
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

public class BillSplitterPage extends AppCompatActivity implements BillDialog.onDoneListener {

    // Info
    TextView numPeople;
    TextView total;
    int newTotal = 0;

    // Tab Widget Layout
    ScrollView scrollView;
    TabHost tabHost;
    TabHost.TabSpec spec;

    // Food Item
    EditText list_add_bar;
    Button addList;
    Button clearList;
    String listInput;
    ArrayList<TextView> listTextView;
    GridLayout foodList;
    TextView foodName;
    TextView foodPrice;
    TextView foodPerPerson;

    // Payer
    GridLayout nameList;
    Button addName;
    Button clearName;
    EditText name_add_bar;
    TextView personName;
    TextView personPayment;
    Person person;
    ArrayList<Person> people;
    ArrayList<TextView> peopleTextView;
    String nameInput;

    // Font
    Typeface rubik_bold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_splitter_page);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        initializeInstances();

        // List Tab
        tabHost.setup();
        spec = tabHost.newTabSpec("List Tab");
        spec.setContent(R.id.list_tab);
        spec.setIndicator("☰ List");
        tabHost.addTab(spec);

        // Payer Tab
        spec = tabHost.newTabSpec("Payer Tab");
        spec.setContent(R.id.payer_tab);
        spec.setIndicator("웃 Payer");
        tabHost.addTab(spec);

        // Set TabWidget Text Style
        final TabWidget tw = tabHost.findViewById(android.R.id.tabs);
        rubik_bold = ResourcesCompat.getFont(this, R.font.rubik_bold);
        for (int i = 0; i < tw.getChildCount(); ++i) {
            final View tabView = tw.getChildTabViewAt(i);
            final TextView tv = tabView.findViewById(android.R.id.title);
            tv.setTextSize(20);
            tv.setAllCaps(false);
            tv.setTypeface(rubik_bold);
        }

        foodList.setColumnCount(3);
//        food_dialog = new DialogFragment(R.layout.activity_bill_food_dialog);
//        food_dialog.setContentView(R.layout.activity_bill_food_dialog);
//        food_dialog.getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        food_dialog.setCancelable(false);

        // Add food item and show dialog
        addList.setOnClickListener(v -> {
            listInput = list_add_bar.getText().toString();
            if (TextUtils.isEmpty(listInput)) System.out.println("empty");
            else showFoodDialog();
        });

//                    ListNameEvent listNameEvent = new ListNameEvent(list_input);
//                    EventBus.getDefault().post(listNameEvent);
//                    System.out.println(list_input);
//                    list = new FoodItem(list_input, );
//                    BillDialog.newInstance("")
//                    food_dialog.show
//                    food_name.setText(list_add_bar.getText());

        clearList.setOnClickListener(view -> {
            for (int i = 0; i < listTextView.size(); i++) foodList.removeViewAt(0);
            listTextView.clear();
            total.setText("0");
            for (Person p: people) p.setPayment(0);
            newTotal = 0;
            for (int i = 1; i < peopleTextView.size(); i+=2) {
                peopleTextView.get(i).setText(String.valueOf(newTotal));
            }
        });

        nameList.setColumnCount(2);

        // Add name to Payer's tab and add to people's list
        addName.setOnClickListener(v -> {
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
                personName.setTextColor(ContextCompat.getColor(this, R.color.ghost_white));
                personName.setMinWidth(getResources().getDisplayMetrics().widthPixels/2+170);
                peopleTextView.add(personName);

                personPayment = new TextView(getApplicationContext());
                personPayment.setText(String.valueOf(person.getPayment()));
                personPayment.setTypeface(getResources().getFont(R.font.rubik));
                personPayment.setTextSize(20);
                personPayment.setTextColor(ContextCompat.getColor(this, R.color.ghost_white));
                peopleTextView.add(personPayment);

                name_add_bar.getText().clear();
                nameList.addView(personName);
                nameList.addView(personPayment);
                person.setTextView(personName.getId(), personPayment.getId());
                numPeople.setText(String.valueOf(people.size()));
            }
        });

        clearName.setOnClickListener(view -> {
            for (int i = 0; i < peopleTextView.size(); i++) { nameList.removeViewAt(0); }
            peopleTextView.clear();
            people.clear();
            numPeople.setText(String.valueOf(people.size()));
        });
    }

    private void initializeInstances() {
        listTextView   = new ArrayList<>();
        people         = new ArrayList<>();
        peopleTextView = new ArrayList<>();

        scrollView   = findViewById(R.id.bill_scroll_area);
        list_add_bar = findViewById(R.id.list_add_bar);
        total        = findViewById(R.id.total);

        tabHost     = findViewById(R.id.tabhost);
        foodList    = findViewById(R.id.food_list);
        clearList   = findViewById(R.id.clear_list_button);

        addList = findViewById(R.id.add_list_button);

        // Payer tab's view
        addName      = findViewById(R.id.add_name_button);
        name_add_bar = findViewById(R.id.name_add_bar);
        nameList     = findViewById(R.id.name_list);
        numPeople    = findViewById(R.id.num_people);
        clearName    = findViewById(R.id.clear_name_button);
    }

    @Override
    public void sendFood(ListItem food) {
        foodName = new TextView(getApplicationContext());
        foodName.setText(food.getName());
        foodName.setTypeface(getResources().getFont(R.font.rubik));
        foodName.setTextSize(20);
        foodName.setTextColor(ContextCompat.getColor(this, R.color.ghost_white));
        foodName.setMinWidth(getResources().getDisplayMetrics().widthPixels/3+50);
        listTextView.add(foodName);

        foodPrice = new TextView(getApplicationContext());
        foodPrice.setText(String.valueOf(food.getPrice()));
        foodPrice.setTypeface(getResources().getFont(R.font.rubik));
        foodPrice.setTextSize(20);
        foodPrice.setTextColor(ContextCompat.getColor(this, R.color.ghost_white));
        foodPrice.setMinWidth(getResources().getDisplayMetrics().widthPixels/3);
        listTextView.add(foodPrice);

        foodPerPerson = new TextView(getApplicationContext());
        foodPerPerson.setText(String.valueOf(food.getPerPerson()));
        foodPerPerson.setTypeface(getResources().getFont(R.font.rubik));
        foodPerPerson.setTextSize(20);
        foodPerPerson.setTextColor(ContextCompat.getColor(this, R.color.ghost_white));
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

    private void showFoodDialog() {
        FragmentManager fm = getSupportFragmentManager();
        BillDialog billDialog = BillDialog.newInstance("Bill Dialog");
        billDialog.show(fm, "Bill Dialog");
        list_add_bar.getText().clear();

//        Objects.requireNonNull(billDialog.getDialog()).getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//        dialog = (BillDialog)
//        dialog.getFood();
    }

    public String getFoodName() {
        return listInput;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    @Override
    public void sendResult(int result) {
        newTotal += result;
        total.setText(String.valueOf(newTotal));
    }
}