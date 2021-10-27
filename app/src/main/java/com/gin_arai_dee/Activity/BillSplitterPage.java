package com.gin_arai_dee.Activity;

import android.graphics.Typeface;
import android.os.Build;
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

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;

import com.gin_arai_dee.Dialog.BillDialog;
import com.gin_arai_dee.R;

import java.util.ArrayList;

public class BillSplitterPage extends AppCompatActivity {
    private class FoodItem {
        private String name;
        private int price;

        public FoodItem(String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getPrice() { return price; }
        public void setPrice(int price) { this.price = price; }
    }

    private class Person {
        private String name;
        private int payment;
        private TextView name_textView;
        private TextView payment_textView;

        public Person(String name) {
            this.name = name;
            this.payment = 0;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getPayment() { return payment; }
        public void setPayment(int payment) { this.payment = payment; }

        public void setTextView(int nameId, int paymentId) {
            name_textView = findViewById(nameId);
            payment_textView = findViewById(paymentId);
        }
    }

    private void showFoodDialog() {
        FragmentManager fm = getSupportFragmentManager();
        BillDialog billDialog = BillDialog.newInstance("Bill Dialog");
//        Objects.requireNonNull(billDialog.getDialog()).getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        billDialog.show(fm, "Bill Dialog");
    }

    // Tab Widget Layout
    ScrollView scrollView;
    TabHost tabHost;
    TabHost.TabSpec spec;
    // Food Item
    EditText list_add_bar;
    Button add_list;
    String list_input;
    ArrayList<FoodItem> list = new ArrayList<>();
    // Food Dialog
    TextView food_name;
//    DialogFragment food_dialog;
    // Payer
    GridLayout name_list;
    Button add_name;
    EditText name_add_bar;
    TextView person_name;
    TextView person_payment;
    TextView num_people;
    Person person;
    ArrayList<Person> people = new ArrayList<>();
    String name_input;
    // Font
    Typeface rubik_bold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_splitter_page);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        scrollView = findViewById(R.id.bill_scroll_area);
        list_add_bar = findViewById(R.id.list_add_bar);

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


        // Add food item and show dialog
        add_list = findViewById(R.id.add_list_button);
        food_name = findViewById(R.id.food_name);
        add_list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                list_input = list_add_bar.getText().toString();
//                ListNameEvent listNameEvent = new ListNameEvent();
//                listNameEvent.setList_name(list_input);
//                EventBus.getDefault().post(listNameEvent);
                if (TextUtils.isEmpty(list_input)) System.out.println("empty");
                else {
                    System.out.println(list_input);
//                    list = new FoodItem(list_input, );
                    showFoodDialog();
//                    BillDialog.newInstance("")
//                    food_dialog.show
//                    food_name.setText(list_add_bar.getText());
                }
            }
        });

        // Payer tab's view
        add_name = findViewById(R.id.add_name_button);
        name_add_bar = findViewById(R.id.name_add_bar);
        name_list = findViewById(R.id.name_list);
        num_people = findViewById(R.id.num_people);

        name_list.setColumnCount(2);

        // Add name to Payer's tab and add to people's list
        add_name.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            public void onClick(View v) {
                name_input = name_add_bar.getText().toString();
                if (TextUtils.isEmpty(name_input)) System.out.println("empty");
                else {
                    System.out.println(name_input);
                    person = new Person(name_input);
                    people.add(person);

                    person_name = new TextView(getApplicationContext());
                    person_name.setText(name_add_bar.getText());
                    person_name.setTypeface(getResources().getFont(R.font.rubik));
                    person_name.setTextSize(20);
                    person_name.setTextColor(getResources().getColor(R.color.ghost_white));
                    person_name.setMinWidth(getResources().getDisplayMetrics().widthPixels/2+170);

                    person_payment = new TextView(getApplicationContext());
                    person_payment.setText(String.valueOf(person.getPayment()));
                    person_payment.setTypeface(getResources().getFont(R.font.rubik));
                    person_payment.setTextSize(20);
                    person_payment.setTextColor(getResources().getColor(R.color.ghost_white));

                    name_add_bar.setText("");
                    name_list.addView(person_name);
                    name_list.addView(person_payment);
                    person.setTextView(person_name.getId(), person_payment.getId());
                    num_people.setText(String.valueOf(people.size()));
                }
            }
        });
    }
}