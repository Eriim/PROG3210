package com.erictossell.fitnesstracker.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by etossell8259 on 12/11/2017.
 */
@Dao
public interface MacroPlanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMacro(MacroPlan macroPlan);

    @Query("select * from macroPlan")
    public List<MacroPlan> getAllWeight();

    @Query("select * from macroPlan where userId = :input")
    public MacroPlan getMacroPlan(long input);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMacro(MacroPlan macroPlan);
}
