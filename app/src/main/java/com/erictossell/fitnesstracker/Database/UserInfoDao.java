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
public interface UserInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMacro(UserInfo userInfo);

    @Query("select * from userInfo")
    public List<UserInfo> getAllWeight();

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateMacro(UserInfo userInfo);
}
