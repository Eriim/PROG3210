package com.erictossell.fitnesstracker.Database;

import android.arch.persistence.room.*;
import java.util.List;

/**
 * Created by etossell8259 on 12/10/2017.
 */
@Dao
public interface MealDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMeal(Meal meal);

    @Query("select * from meal")
    public List<Meal> getAllMeal();

    @Query("select name from meal")
    public List<String> getAllMeals();

    @Query("select * from meal where name = :mealName")
    public Meal getMeal(String mealName);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMeal(Meal meal);

    @Query("delete from meal where id = :mealId")
    void removeAllMeal(int mealId);
}