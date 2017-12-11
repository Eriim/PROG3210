package com.erictossell.fitnesstracker.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by etossell8259 on 12/11/2017.
 */
@Entity (foreignKeys = {
        @ForeignKey(
                entity = User.class,
                parentColumns = "userId",
                childColumns = "userId"
        )
})
public class UserInfo {
    @PrimaryKey(autoGenerate = true)
    long id;
    @ColumnInfo(name="userId")
    private long userId;
    private int protein;
    private int fat;
    private int carb;
    private int calories;
    private int height;

    public UserInfo(long userId, int protein, int fat, int carb, int calories, int height){
        this.userId = userId;
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
        this.calories = calories;
        this.height = height;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public int getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public int getCarb() {
        return carb;
    }

    public void setCarb(int carb) {
        this.carb = carb;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
