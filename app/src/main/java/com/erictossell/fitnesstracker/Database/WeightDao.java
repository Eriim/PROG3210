package com.erictossell.fitnesstracker.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface WeightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addWeight(Weight weight);

    @Query("select * from weight")
    public List<Weight> getAllWeight();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMeal(Meal meal);
}
