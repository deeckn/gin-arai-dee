package com.gin_arai_dee;

public class FoodCardModel {
    private String title;
    private String description;
    private String calories;
    private int image;

    public FoodCardModel(String title, String description, String calories, int image) {
        this.title = title;
        this.description = description;
        this.calories = calories;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
