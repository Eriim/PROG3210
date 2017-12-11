package com.erictossell.fitnesstracker.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "weight",
foreignKeys = {
        @ForeignKey (
        entity = User.class,
        parentColumns = "id",
        childColumns = "userId",
        onDelete = ForeignKey.CASCADE
        )},
indices = {@Index(value = "id")})
public class Weight {
    @PrimaryKey(autoGenerate = true)
    long id;
    public long userId;
    Integer weight;
    Date date;
    String units;

    public Weight(long userId, Integer weight, Date date, String units){
        this.userId = userId;
        this.weight = weight;
        this.date = date;
        this.units = units;
    }
}
