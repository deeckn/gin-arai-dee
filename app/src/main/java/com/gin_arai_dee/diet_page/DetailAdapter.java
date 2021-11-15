package com.gin_arai_dee.diet_page;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gin_arai_dee.R;
import com.gin_arai_dee.general.FoodItem;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.DetailHolder> {

    ArrayList<FoodItem> foodItems;

    public DetailAdapter(ArrayList<FoodItem> foodItems){
        this.foodItems = foodItems;
    }

    @NonNull
    @Override
    public DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_namelist,parent,false);
        return new DetailHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {
        holder.food_name.setText(foodItems.get(position).getFood_item());
        holder.food_kcal.setText(foodItems.get(position).getKcal() + " kcal");
    }

    @Override
    public int getItemCount() {
        return foodItems.size();
    }

    public static class DetailHolder extends RecyclerView.ViewHolder{
        TextView food_name;
        TextView food_kcal;

        public DetailHolder(@NonNull View itemView) {
            super(itemView);
            food_name = itemView.findViewById(R.id.name_text);
            food_kcal = itemView.findViewById(R.id.kcal_text);
        }
    }
}
