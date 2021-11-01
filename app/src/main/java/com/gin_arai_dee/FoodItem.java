package com.gin_arai_dee;

public class FoodItem {
    private int id;
    private String food_item;
    private String description;
    private String dish_type;
    private String nationality;
    private int kcal;
    private String image_url;

    public FoodItem(int id, String food_item,
                    String description, String dish_type,
                    String nationality, int kcal,
                    String image_url) {
        this.id = id;
        this.food_item = food_item;
        this.description = description;
        this.dish_type = dish_type;
        this.nationality = nationality;
        this.kcal = kcal;
        this.image_url = image_url;
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

    public void setFood_item(String food_item) {
        this.food_item = food_item;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDish_type() {
        return dish_type;
    }

    public void setDish_type(String dish_type) {
        this.dish_type = dish_type;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
