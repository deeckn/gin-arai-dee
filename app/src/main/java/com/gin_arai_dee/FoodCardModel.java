package com.gin_arai_dee;

public class FoodCardModel {
    private String title;
    private String description;
    private String calories;
    private String image_url;

    public FoodCardModel(String title, String description, String calories, String image_url) {
        this.title = title;
        this.description = description;
        this.calories = calories;
        this.image_url = image_url;
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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
