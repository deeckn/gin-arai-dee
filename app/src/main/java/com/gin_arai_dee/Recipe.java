package com.gin_arai_dee;

import java.util.ArrayList;

public class Recipe
{
    private String name;
    private String description;
    private User user;
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Comment> comments;
    public Recipe(User user,String name, String description){
        this.user = user;
        this.name = name;
        this.description = description;
        loadIngredients();
        loadComments();
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private void loadIngredients() {
        //Database
    }

    private void loadComments() {
        //Database
    }
}
