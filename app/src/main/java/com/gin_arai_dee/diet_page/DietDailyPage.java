package com.gin_arai_dee.diet_page;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gin_arai_dee.R;
import com.gin_arai_dee.general.DatabaseHelper;
import com.gin_arai_dee.general.FoodItem;

import java.util.ArrayList;
import java.util.Collections;

public class DietDailyPage extends AppCompatActivity implements DietDialog.OnInputListener {

    private DatabaseHelper db;

    private TextView totalKcal;
    private String date;

    RecyclerView recyclerView;
    private CardDietAdapter cardAdapter;
    private ArrayList<CardDietModel> foodItemLists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_page_daily);
        db = new DatabaseHelper(getApplicationContext());
        Intent intent = getIntent();
        date = intent.getStringExtra(DietPage.EXTRA_TEXT);

        TextView selectedDay = findViewById(R.id.selectedDay);
        selectedDay.setText(date);
        totalKcal = findViewById(R.id.total_kcal);
        recyclerView = findViewById(R.id.item_list);

        foodItemLists = loadData(date);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardAdapter = new CardDietAdapter(this, foodItemLists);
        recyclerView.setAdapter(cardAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeCallBack);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        Button add_item_button = findViewById(R.id.add_item_button);
        add_item_button.setOnClickListener(e -> {
            DietDialog dialog = new DietDialog();
            dialog.show(getSupportFragmentManager(), "MyDialog");
        });
    }

    ItemTouchHelper.SimpleCallback swipeCallBack = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @SuppressLint("NotifyDataSetChanged")
        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder holder, int direction) {
            // Swiped Left to Delete
            db.deleteDietItem(date,foodItemLists.get(holder.getBindingAdapterPosition()).getTime());
            updateTotalKcal(foodItemLists.get(holder.getBindingAdapterPosition()).getFoodItemsLists(), 1);
            foodItemLists.remove(holder.getBindingAdapterPosition());

            cardAdapter.notifyDataSetChanged();
        }
    };

    @SuppressLint("SetTextI18n")
    private void updateTotalKcal(ArrayList<FoodItem> lists, int mode) {
        int itemKCal = Integer.parseInt((String) totalKcal.getText());
        for (FoodItem item : lists) {
            if (mode == 0) {
                itemKCal += item.getKcal();
            } else {
                itemKCal -= item.getKcal();
            }
        }
        totalKcal.setText(itemKCal + "");
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void sentInput(String time, ArrayList<FoodItem> lists) {
        CardDietModel dietModel = new CardDietModel(time);
        dietModel.setFoodItemsLists(lists);
        dietModel.getHourMinute();
        foodItemLists.add(dietModel);
        Collections.sort(foodItemLists);
        db.addAllDietItem(date, time, lists);
        // update total kcal.
        updateTotalKcal(lists, 0);
        cardAdapter.notifyDataSetChanged();
    }

    private ArrayList<CardDietModel> loadData(String date) {
        ArrayList<CardDietModel> models = new ArrayList<>();
        ArrayList<String> timeBuffers = new ArrayList<>();
        ArrayList<DietBuffer> buffers = (ArrayList<DietBuffer>) db.getAllDietItem(date);
        // filter time
        for (DietBuffer dBuffer : buffers) {
            Log.e("LIST",dBuffer.getTime() + "-->" + dBuffer.getID());
            if (!timeBuffers.contains(dBuffer.getTime())) {
                timeBuffers.add(dBuffer.getTime());
            }
        }

        for (String myTime : timeBuffers) {
            CardDietModel model = new CardDietModel(myTime);
            ArrayList<FoodItem> foodItems = new ArrayList<>();
            for (DietBuffer dBuffer : buffers) {
                if (dBuffer.getTime().equals(myTime)) {
                    foodItems.add(db.findFoodByID(dBuffer.getID()));
                }
                model.setFoodItemsLists(foodItems);
            }
            updateTotalKcal(foodItems, 0);
            models.add(model);
        }
        Collections.sort(models);
        return models;
    }
}
