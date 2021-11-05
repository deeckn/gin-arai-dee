package com.gin_arai_dee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_diet, null);
        return new CardDietHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardDietHolder holder, int position) {
        holder.dietTime.setText(models.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class CardDietHolder extends RecyclerView.ViewHolder {

        TextView dietTime;
        ListView listView;

        public CardDietHolder(@NonNull View itemView) {
            super(itemView);
            dietTime = itemView.findViewById(R.id.card_time);
            listView = itemView.findViewById(R.id.card_itemList);
        }
    }
}
