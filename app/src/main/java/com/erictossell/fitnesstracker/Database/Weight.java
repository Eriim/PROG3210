package com.erictossell.fitnesstracker.Database;
import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;


@Entity
        (foreignKeys = {
        @ForeignKey (
        entity = User.class,
        parentColumns = "userId",
        childColumns = "userId"
        )
})
public class Weight {
    @PrimaryKey(autoGenerate = true)
    long id;
    @ColumnInfo(name="userId")
    private long userId;
    private Integer weight;
    @ColumnInfo(name = "logged_date")
    @TypeConverters({DateConverter.class})
    private Date date;
    private String units;

    public Weight(long userId, Integer weight, Date date, String units){
        this.userId = userId;
        this.weight = weight;
        this.date = date;
        this.units = units;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }
}
