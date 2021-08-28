package com.gin_arai_dee;

public class User {
    private final int userId, age;
    private final float weight, height;
    private final String username, firstName, lastName, gender, email, password;
    private final String recipeString, favoritesString;
    private UserRecipes userRecipes;
    // private UserFavorites favoriteFoods;

    // Constructor for user data retrieval from database
    public User(int userId, int age, float weight, float height, String gender,
                String username, String firstName, String lastName, String email,
                String password, String recipeString, String favoritesString) {
        this.userId = userId;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.recipeString = recipeString;
        this.favoritesString = favoritesString;
    }

    public int getId() {
        return userId;
    }

    public int getAge() {
        return age;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public boolean isUsername(String username) {
        return this.username.equals(username);
    }

    private void retrieveUserHistory() {
        // Loads history from Database
    }

    private void retrieveFavoriteFoods() {
        // Loads favorite foods from Database
    }

    private void loadData() {
        retrieveUserHistory();
        retrieveFavoriteFoods();
    }

    private void saveData() {
        // Saves current data to Database
    }
}
