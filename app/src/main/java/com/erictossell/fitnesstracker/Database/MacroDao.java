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
public interface MacroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMacro(Macro macro);

    @Query("select * from macro")
    public List<Macro> getAllWeight();

    @Query("select * from macro where userId = :input")
    public Macro getMacro(long input);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMacro(Macro macro);
}
