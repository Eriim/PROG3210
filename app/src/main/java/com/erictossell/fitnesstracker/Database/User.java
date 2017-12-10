package com.erictossell.fitnesstracker.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by erict on 9/28/2017.
 */
@Entity
public class User {
    @PrimaryKey
    public final int id;
    private String email;
    private String password;

    public User(int id, String email, String password){
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
