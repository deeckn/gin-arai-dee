package com.gin_arai_dee;

public class FoodCardModel {
    private final String title;
    private final String description;
    private final String calories;
    private final String image_url;

    public FoodCardModel(String title, String description, String calories, String image_url) {
        this.title = title;
        this.description = description;
        this.calories = calories;
        this.image_url = image_url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCalories() {
        return calories;
    }

    public String getImage_url() {
        return image_url;
    }
}
