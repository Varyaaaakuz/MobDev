package ru.mirea.kuzmina.employeedb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SuperheroDao {

    @Query("SELECT * FROM superheroes")
    List<Superhero> getAll();

    @Query("SELECT * FROM superheroes WHERE id = :id")
    Superhero getById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Superhero superhero);

    @Update
    void update(Superhero superhero);

    @Delete
    void delete(Superhero superhero);

    @Query("SELECT * FROM superheroes WHERE affiliation = :team")
    List<Superhero> getByTeam(String team);

}