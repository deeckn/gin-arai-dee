package com.gin_arai_dee.Activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gin_arai_dee.Adapter.DietCardAdapter;
import com.gin_arai_dee.Domain.DietCardModel;
import com.gin_arai_dee.R;

import java.util.ArrayList;

public class DietDayPage extends AppCompatActivity {

    ArrayList<DietCardModel> models;
    TextView selectedDay;
    TextView kCalOnDay;
    Button addItemButton;
    RecyclerView recyclerView;
    DietCardAdapter cardAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_edit_page);

        selectedDay = findViewById(R.id.selectedDay);
        kCalOnDay = findViewById(R.id.total_cal);
        addItemButton = findViewById(R.id.add_item_button);
        recyclerView = findViewById(R.id.item_list);

        models = new ArrayList<>();
        models.add(new DietCardModel("10.50"));
        models.add(new DietCardModel("11.50"));
        models.add(new DietCardModel("12.50"));
        models.add(new DietCardModel("10.50"));
        models.add(new DietCardModel("11.50"));
        models.add(new DietCardModel("12.50"));
        models.add(new DietCardModel("10.50"));
        models.add(new DietCardModel("11.50"));
        models.add(new DietCardModel("12.50"));
        models.add(new DietCardModel("10.50"));
        models.add(new DietCardModel("11.50"));
        models.add(new DietCardModel("12.50"));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardAdapter = new DietCardAdapter(this,models);
        recyclerView.setAdapter(cardAdapter);
    }
}
