package com.gin_arai_dee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class BillSplitterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_splitter_page);

        // Set TabWidget name
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
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