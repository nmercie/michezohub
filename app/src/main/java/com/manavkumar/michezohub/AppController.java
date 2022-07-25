package com.manavkumar.michezohub;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AppController extends Application {

    private DatabaseReference database;
    private SharedPreferences preferences;

    @Override
    public void onCreate() {
        super.onCreate();

        database = FirebaseDatabase.getInstance().getReference();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    public DatabaseReference getDatabase() {
        return database;
    }

    public SharedPreferences getPreferences() {
        return preferences;
    }
}
