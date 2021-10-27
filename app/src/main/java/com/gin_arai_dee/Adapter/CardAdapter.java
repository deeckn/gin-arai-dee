package com.gin_arai_dee.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gin_arai_dee.Holder.CardHolder;
import com.gin_arai_dee.FoodCardModel;
import com.gin_arai_dee.R;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardHolder> {

    Context context;
    ArrayList<FoodCardModel> models;

    public CardAdapter(Context context, ArrayList<FoodCardModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_card, null);
        return new CardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        holder.title.setText(models.get(position).getTitle());
        holder.description.setText(models.get(position).getDescription());
        holder.imageView.setImageResource(models.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
