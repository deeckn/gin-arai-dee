package com.gin_arai_dee.diet_page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gin_arai_dee.R;
import com.gin_arai_dee.general.FoodItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CardDietAdapter extends RecyclerView.Adapter<CardDietAdapter.CardDietHolder> {

    Context context;
    ArrayList<CardDietModel> models;

    public CardDietAdapter(Context context, ArrayList<CardDietModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public CardDietHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        @SuppressLint("InflateParams") View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_diet, null);
        return new CardDietHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDietHolder holder, int position) {
        // set time
        @SuppressLint("SimpleDateFormat") SimpleDateFormat f24Hour = new SimpleDateFormat("HH:mm");
        try {
            Date date = f24Hour.parse(models.get(position).getTime());
            assert date != null;
            holder.dietTime.setText(f24Hour.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ArrayList<FoodItem> foodItems = models.get(position).getFoodItemsLists();
        DetailAdapter detailAdapter = new DetailAdapter(foodItems);
        holder.cardRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.cardRecyclerView.setAdapter(detailAdapter);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class CardDietHolder extends RecyclerView.ViewHolder {

        TextView dietTime;
        RecyclerView cardRecyclerView;

        public CardDietHolder(@NonNull View itemView) {
            super(itemView);
            dietTime = itemView.findViewById(R.id.card_time);
            cardRecyclerView = itemView.findViewById(R.id.card_itemList);

        }
    }
}
