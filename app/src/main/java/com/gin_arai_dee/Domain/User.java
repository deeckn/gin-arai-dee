package com.gin_arai_dee.Domain;

import com.gin_arai_dee.Domain.FoodItem;
import com.gin_arai_dee.Domain.Recipe;

import java.util.ArrayList;

public class User {
    private int id, age;
    private float weight, height;
    private String username, firstName, lastName, gender, email, password, recipes, favorites;
    private ArrayList<Recipe> userRecipes;
    private ArrayList<FoodItem> favoriteFoods;

    public User() {
        this.id = 9999;
        this.age = 0;
        this.weight = 0;
        this.height = 0;
        this.gender = "N";
        this.username = "";
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.password = "";
        this.recipes = "";
        this.favorites = "";
    }

    public User(int id, int age, float weight, float height, String username,
                String firstName, String lastName, String gender, String email,
                String password, String recipes, String favorites) {
        this.id = id;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.password = password;
        this.recipes = recipes;
        this.favorites = favorites;
        loadData();
    }

    private void loadData() {
        loadRecipes();
        loadFavorites();
    }

    private void loadRecipes() {

    }

    private void loadFavorites() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRecipes() {
        return recipes;
    }

    public void setRecipes(String recipes) {
        this.recipes = recipes;
    }

    public String getFavorites() {
        return favorites;
    }

    public void setFavorites(String favorites) {
        this.favorites = favorites;
    }
}
