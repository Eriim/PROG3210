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
public class Macro {
    @PrimaryKey(autoGenerate = true)
    long id;
    @ColumnInfo(name="userId")
    private long userId;
    private Double protein;
    private Double fat;
    private Double carb;
    private Double calories;

    public Macro() {

    }
    public Macro(long userId, Double protein, Double fat, Double carb, Double calories){
        this.userId = userId;
        this.protein = protein;
        this.fat = fat;
        this.carb = carb;
        this.calories = calories;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getCarb() {
        return carb;
    }

    public void setCarb(Double carb) {
        this.carb = carb;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

}
