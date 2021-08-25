package com.gin_arai_dee;

public class User {
    private final int id, age, weight, height;
    private int passwordHash;
    private final String firstName, lastName, email, username, password;
    private String encryptedPassword;
    private final char gender;
    // private UserHistory userHistory;
    // private UserFavorites favoriteFoods;

    public User(int id, int age, int weight, int height, String firstName, String lastName,
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

    public int getId() { return id; }
    public int getAge() { return age; }
    public int getWeight() { return weight; }
    public int getHeight() { return height; }
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

//    private void retrieveUserHistory() {
//
//    }
//
//    private void retrieveFavoriteFoods() {
//
//    }
//
//    private void loadData() {
//        retrieveUserHistory();
//        retrieveFavoriteFoods();
//    }
}
