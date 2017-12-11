package com.erictossell.fitnesstracker.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by erict on 9/28/2017.
 */
@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    long id;
    private String email;
    private String password;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setPassword(String password) {this.password = password; }
    public String getPassword(){
        return password;
    }
}
