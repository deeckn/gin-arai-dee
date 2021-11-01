package com.gin_arai_dee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

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
        holder.calorie.setText(models.get(position).getCalories());
        Picasso.get().load(models.get(position).getImage_url()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
