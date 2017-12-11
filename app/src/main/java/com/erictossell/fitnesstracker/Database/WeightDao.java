package com.erictossell.fitnesstracker.Database;

import android.arch.persistence.room.*;
import java.util.List;
/**
 * Created by erict on 12/10/2017.
 */
@Dao
public interface WeightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addWeight(Weight weight);

    @Query("select * from weight")
    public List<Weight> getAllWeight();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMeal(Meal meal);
}
