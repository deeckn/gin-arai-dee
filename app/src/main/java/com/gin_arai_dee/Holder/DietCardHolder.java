package com.gin_arai_dee.Holder;

import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import com.gin_arai_dee.Domain.FoodItem;
import com.gin_arai_dee.R;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class DietCardHolder extends CardHolder{
    public TextView cardTimeText;
    public GridLayout cardGridLayout;

    public DietCardHolder(@NonNull View itemView) {
        super(itemView);
        this.cardTimeText = itemView.findViewById(R.id.card_time);
        this.cardGridLayout = itemView.findViewById(R.id.foodList_grid);
    }
}
