package com.gin_arai_dee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

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

        public Person(String name) { this.name = name; }
    }

    private void showFoodDialog() {
        FragmentManager fm = getSupportFragmentManager();
        BillDialog billDialog = BillDialog.newInstance("Bill Dialog");
        billDialog.show(fm, "fragment_edit_name");
    }

    ScrollView scrollView;
    EditText list_input;
    TabHost tabHost;
    Button add_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_splitter_page);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        scrollView = findViewById(R.id.bill_scroll_area);
        list_input = findViewById(R.id.list_add_bar);

        // Set TabWidget name
        tabHost =  findViewById(R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec spec = tabHost.newTabSpec("List Tab");
        spec.setContent(R.id.list_tab);
        spec.setIndicator("☰ List");
        tabHost.addTab(spec);
        spec = tabHost.newTabSpec("Payer Tab");
        spec.setContent(R.id.payer_tab);
        spec.setIndicator("웃 Payer");
        tabHost.addTab(spec);

        // Set TabWidget text style
        final TabWidget tw = tabHost.findViewById(android.R.id.tabs);
        Typeface rubik_bold = ResourcesCompat.getFont(this, R.font.rubik_bold);
        for (int i = 0; i < tw.getChildCount(); ++i)
        {
            final View tabView = tw.getChildTabViewAt(i);
            final TextView tv = tabView.findViewById(android.R.id.title);
            tv.setTextSize(20);
            tv.setAllCaps(false);
            tv.setTypeface(rubik_bold);
        }

        add_list = findViewById(R.id.add_list_button);
        add_list.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                showFoodDialog();
            }
        });
    }
}