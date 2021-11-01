package com.gin_arai_dee;

import java.util.ArrayList;

public class CardDietModel {
    private String time;
    private ArrayList<FoodItem> foodItemsLists;

    public CardDietModel(String time){
        this.time = time;
        foodItemsLists = new ArrayList<>();
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setFoodItemsLists(ArrayList<FoodItem> foodItemsLists) {
        this.foodItemsLists = foodItemsLists;
    }

    public ArrayList<FoodItem> getFoodItemsLists() {
        return foodItemsLists;
    }
}
