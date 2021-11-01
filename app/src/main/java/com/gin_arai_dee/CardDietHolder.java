package com.gin_arai_dee;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CardDietHolder extends RecyclerView.ViewHolder {

    TextView dietTime;

    public CardDietHolder(@NonNull View itemView) {
        super(itemView);
        dietTime = itemView.findViewById(R.id.card_time);
    }
}
