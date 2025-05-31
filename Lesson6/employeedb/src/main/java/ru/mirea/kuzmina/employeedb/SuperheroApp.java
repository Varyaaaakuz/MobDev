package ru.mirea.kuzmina.employeedb;

import android.app.Application;

import androidx.room.Room;

public class SuperheroApp extends Application {
    private static SuperheroApp instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "superhero-db")
                .allowMainThreadQueries()
                .build();
    }

    public static SuperheroApp getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}