package com.erictossell.fitnesstracker.Database;

import android.arch.persistence.room.*;
import java.util.List;

/**
 * Created by etossell8259 on 12/10/2017.
 */
@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(User user);

    @Query("select * from user")
    public List<User> getAllUser();

    @Query("select * from user where email = :input")
    public User getUser(String input);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateUser(User user);

    @Query("delete from user")
    void removeAllUsers();
}
