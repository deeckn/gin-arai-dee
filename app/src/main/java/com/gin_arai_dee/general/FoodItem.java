package com.gin_arai_dee.general;

public class FoodItem {
    private int id;
    private final String food_item;
    private final String description;
    private final String dish_type;
    private final String nationality;
    private final int kcal;
    private final String image_url;
    private boolean selected;
    private final int isFavorite;

    public FoodItem(int id, String food_item,
                    String description, String dish_type,
                    String nationality, int kcal,
                    String image_url, int isFavorite) {
        this.id = id;
        this.food_item = food_item;
        this.description = description;
        this.dish_type = dish_type;
        this.nationality = nationality;
        this.kcal = kcal;
        this.image_url = image_url;
        this.selected = false;
        this.isFavorite = isFavorite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFood_item() {
        return food_item;
    }

    public String getDescription() {
        return description;
    }

    public String getDish_type() {
        return dish_type;
    }

    public String getNationality() {
        return nationality;
    }

    public int getKcal() {
        return kcal;
    }

    public String getImage_url() {
        return image_url;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getIsFavorite() {
        return isFavorite;
    }
}
