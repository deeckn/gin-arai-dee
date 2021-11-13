package com.gin_arai_dee.diet_page;

import android.util.Log;

import com.gin_arai_dee.general.FoodItem;

import java.util.ArrayList;
import java.util.Comparator;

public class CardDietModel implements Comparable<CardDietModel>{
    private String time;
    private ArrayList<FoodItem> foodItemsLists;


    public CardDietModel(String time){
        this.time = time;
        this.foodItemsLists = new ArrayList<>();
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setFoodItemsLists(ArrayList<FoodItem> items) {
        this.foodItemsLists = items;
    }

    public ArrayList<FoodItem> getFoodItemsLists() {
        return foodItemsLists;
    }

    public void updateList(FoodItem foodItem){
        foodItemsLists.add(foodItem);
    }

    public int[] getHourMinute(){
        String[] temp = getTime().split(":");
        int[] res = new int[temp.length];
        for(int i=0;i<temp.length;i++){
            res[i] = Integer.parseInt(temp[i]);
        }
        return res;
    };

    @Override
    public int compareTo(CardDietModel other) {
        if(getHourMinute()[0] == other.getHourMinute()[0]){
            return getHourMinute()[1] - other.getHourMinute()[1];
        }else{
            return getHourMinute()[0] - other.getHourMinute()[0];
        }
    }

}
