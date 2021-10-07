package com.gin_arai_dee;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DietPage extends AppCompatActivity {

    // 7Days textview
    TextView mondayCalorie;
    TextView tuesdayCalorie;
    TextView wednesdayCalorie;
    TextView thursdayCalorie;
    TextView fridayCalorie;
    TextView saturdayCalorie;
    TextView sundayCalorie;

    // 7Days button
    Button mondayButton;
    Button tuesdayButton;
    Button wednesdayButton;
    Button thursdayButton;
    Button fridayButton;
    Button saturdayButton;
    Button sundayButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_page);

        // Initializing calories textview
        mondayCalorie = findViewById(R.id.monday_kcal);
        tuesdayCalorie = findViewById(R.id.tuesday_kcal);
        wednesdayCalorie = findViewById(R.id.wednesday_kcal);
        thursdayCalorie = findViewById(R.id.thursday_kcal);
        fridayCalorie = findViewById(R.id.friday_kcal);
        saturdayCalorie = findViewById(R.id.saturday_kcal);
        sundayCalorie = findViewById(R.id.sunday_kcal);

        // Initializing button
        mondayButton = findViewById(R.id.monday_edit_button);
        tuesdayButton = findViewById(R.id.tuesday_edit_button);
        wednesdayButton = findViewById(R.id.wednesday_edit_button);
        thursdayButton = findViewById(R.id.thursday_edit_button);
        fridayButton = findViewById(R.id.friday_edit_button);
        saturdayButton = findViewById(R.id.saturday_edit_button);
        sundayButton = findViewById(R.id.sunday_edit_button);

    }
}
