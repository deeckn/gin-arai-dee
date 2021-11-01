package com.gin_arai_dee;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DietDailyPage extends AppCompatActivity {

    TextView selectedDay;
    TextView totalKcal;
    Button add_item_button;
    RecyclerView recyclerView;
    CardDietAdapter cardAdapter;
    ArrayList<CardDietModel> foodItemLists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_page_daily);

        foodItemLists = new ArrayList<>();

        selectedDay = findViewById(R.id.selectedDay);
        totalKcal = findViewById(R.id.total_kcal);
        recyclerView = findViewById(R.id.item_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardAdapter = new CardDietAdapter(this,foodItemLists);

        add_item_button.setOnClickListener(e -> {
            openDialog();
        });
    }

    private void openDialog(){

    }
}
