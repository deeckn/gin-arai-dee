package com.gin_arai_dee;

public class User {
    private final int age;
    private final float weight, height;
    private final char gender;
    private final String username;

    private int id;
    private int passwordHash;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String encryptedPassword;
    private UserHistory userHistory;
    // private UserFavorites favoriteFoods;

    // Constructor for user data retrieval from database
    public User(int id, int age, float weight, float height, String firstName, String lastName,
                String email, String username, String password, char gender,
                String userHistory, String favoriteFoods) {
        this.id = id;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.gender = gender;
        encryptPassword();
        calculatePasswordHash();
    }

    // Constructor for Dietary Calculations
    public User(int age, float weight, float height, String username, char gender) {
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.username = username;
        this.gender = gender;
    }

    public int getId() { return id; }
    public int getAge() { return age; }
    public float getWeight() { return weight; }
    public float getHeight() { return height; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEncryptedPassword() { return encryptedPassword; }
    public int getPasswordHash() { return passwordHash; }
    public char getGender() { return gender; }

    public boolean isUsername(String username) {
        return this.username.equals(username);
    }

    private void encryptPassword() {
        StringBuilder result = new StringBuilder();
        for (int c : password.toCharArray()) result.append((char)((c % 83) + 33));
        encryptedPassword = result.toString();
    }

    private void calculatePasswordHash() {
        int sum = 0;
        for (int c : password.toCharArray()) sum += c;
        passwordHash = sum;
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
