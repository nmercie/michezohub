package com.manavkumar.michezohub;

import android.app.Application;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AppController extends Application {

    private DatabaseReference database;

    @Override
    public void onCreate() {
        super.onCreate();

        database = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDatabase() {
        return database;
    }
}
