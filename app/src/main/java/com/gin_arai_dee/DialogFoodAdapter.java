package com.gin_arai_dee;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class DialogFoodAdapter extends ArrayAdapter<FoodItem> {

    private Context context;
    int resource;

    public DialogFoodAdapter(Context context, int resource, List<FoodItem> objects){
        super(context,resource,objects);
        this.context = context;
        this.resource = resource;
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor", "ViewHolder"})
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        String name = getItem(position).getFood_item();
        int kCal = getItem(position).getKcal();
        boolean selected = getItem(position).isSelected();

        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(resource,parent,false);

        TextView food_name = convertView.findViewById(R.id.dialog_title);
        TextView food_kcal  = convertView.findViewById(R.id.dialog_kCal);

        food_name.setText(name);
        food_kcal.setText(kCal + "");
        if(selected){
            food_name.setTextColor(R.color.maximum_yellow_red);
        }

        return convertView;
    }



}
