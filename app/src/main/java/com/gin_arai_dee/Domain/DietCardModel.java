package com.gin_arai_dee.Domain;

import com.gin_arai_dee.Activity.FoodPage;

import java.util.ArrayList;
import java.util.List;

public class DietCardModel {
    private String time;
    private List<FoodItem> foodItemList;

    public DietCardModel(String time){
        this.time = time;
        foodItemList = new ArrayList<>();
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setFoodItemList(List<FoodItem> foodItemList) {
        this.foodItemList = foodItemList;
    }

    public List<FoodItem> getFoodItemList() {
        return foodItemList;
    }
}
