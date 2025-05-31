package ru.mirea.kuzmina.employeedb;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "superheroes")
public class Superhero {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;
    public String alias;
    public String powers;
    public int strengthLevel;
    public String affiliation;
}