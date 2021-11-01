package com.gin_arai_dee;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DietPage extends AppCompatActivity {

    Button button_monday;
    Button button_tuesday;
    Button button_wednesday;
    Button button_thursday;
    Button button_friday;
    Button button_saturday;
    Button button_sunday;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_page_main);

        button_monday = findViewById(R.id.monday_edit);
        button_tuesday = findViewById(R.id.tuesday_edit);
        button_wednesday = findViewById(R.id.wednesday_edit);
        button_thursday = findViewById(R.id.thusday_edit);
        button_friday = findViewById(R.id.friday_edit);
        button_saturday = findViewById(R.id.saturday_edit);
        button_sunday = findViewById(R.id.sunday_edit);

    }
}
