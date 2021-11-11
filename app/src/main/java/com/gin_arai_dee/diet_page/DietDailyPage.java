package com.gin_arai_dee.diet_page;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gin_arai_dee.R;
import com.gin_arai_dee.general.FoodItem;

import java.util.ArrayList;

public class DietDailyPage extends AppCompatActivity implements DietDialog.OnInputListener {

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
        Intent intent = getIntent();
        String selected_Day = intent.getStringExtra(DietPage.EXTRA_TEXT);

        selectedDay = findViewById(R.id.selectedDay);
        selectedDay.setText(selected_Day);
        totalKcal = findViewById(R.id.total_kcal);
        recyclerView = findViewById(R.id.item_list);

        foodItemLists = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardAdapter = new CardDietAdapter(this, foodItemLists);
        recyclerView.setAdapter(cardAdapter);

        add_item_button = findViewById(R.id.add_item_button);
        add_item_button.setOnClickListener(e -> {
            DietDialog dialog = new DietDialog();
            dialog.show(getSupportFragmentManager(), "MyDialog");
        });
    }

    @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
    @Override
    public void sentInput(String time, ArrayList<FoodItem> lists) {
        CardDietModel dietModel = new CardDietModel(time);
        dietModel.setFoodItemsLists(lists);
        foodItemLists.add(dietModel);

        // update total kcal.
        int itemKCal = Integer.parseInt((String) totalKcal.getText());
        for(FoodItem item:lists){
            itemKCal += item.getKcal();
        }
        totalKcal.setText(itemKCal + "");

        cardAdapter.notifyDataSetChanged();



    }
}
