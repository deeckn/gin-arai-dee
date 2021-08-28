package com.gin_arai_dee;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserFactory {

    private static FirebaseDatabase database;
    private static DatabaseReference reference;

    public static User getUser(int id) {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(String.valueOf(id));
        // return
    }

    public static User getDietaryUser(int id) {
        database = FirebaseDatabase.getInstance();
        reference = database.getReference(String.valueOf(id));
        // return
    }
}
