package com.gin_arai_dee.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gin_arai_dee.Domain.DietCardModel;
import com.gin_arai_dee.Holder.DietCardHolder;
import com.gin_arai_dee.R;

import java.util.ArrayList;

public class DietCardAdapter extends RecyclerView.Adapter<DietCardHolder> {

    Context context;
    ArrayList<DietCardModel> models;

    public DietCardAdapter(Context context,ArrayList<DietCardModel> models){
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public DietCardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_diet,null);
        return new DietCardHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DietCardHolder holder, int position) {
        holder.cardTimeText.setText(models.get(position).getTime());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
}
