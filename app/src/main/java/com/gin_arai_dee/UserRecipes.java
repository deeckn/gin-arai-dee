package com.gin_arai_dee;

import java.util.ArrayList;

public class UserRecipes {
    private final String tableName;
    private ArrayList<Recipe> recipeBlogs;

    UserRecipes(String tableName) {
        this.tableName = tableName;
    }

    private void loadData() {}
    private void saveData() {}
    private void deleteRecipe() {}
    private void createUserHistory() {}
}
