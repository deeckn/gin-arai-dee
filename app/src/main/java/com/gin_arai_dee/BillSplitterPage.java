package com.gin_arai_dee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

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
    ScrollView scrollView;
    EditText list_input;
    TabHost tabHost;

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
    }
}