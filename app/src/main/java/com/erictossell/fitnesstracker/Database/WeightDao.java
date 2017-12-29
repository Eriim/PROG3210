package com.erictossell.fitnesstracker.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Dao
public interface WeightDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addWeight(Weight weight);

    @Query("select * from weight")
    public List<Weight> getAllWeight();

    @Query("select weight from weight where userId = :id")
    public List<Integer> getIntWeight(long id);

    @Query("select logged_date from weight where userId = :id")
    public List<Date> getDateWeight(long id);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateWeight(Weight weight);
}
