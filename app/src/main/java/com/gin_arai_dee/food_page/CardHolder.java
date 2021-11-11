package com.gin_arai_dee.food_page;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gin_arai_dee.R;

public class CardHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView title;
    TextView calorie;
    TextView description;

    public CardHolder(@NonNull View itemView) {
        super(itemView);
        this.imageView = itemView.findViewById(R.id.cardImage);
        this.title = itemView.findViewById(R.id.cardTitle);
        this.calorie = itemView.findViewById(R.id.cardCalorie);
        this.description = itemView.findViewById(R.id.cardDescription);
    }
}
