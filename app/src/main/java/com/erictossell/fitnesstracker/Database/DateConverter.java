package com.erictossell.fitnesstracker.Database;

import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

/**
 * Created by etossell8259 on 12/11/2017.
 */
//type converter for ROOM dates
public class DateConverter {
    @TypeConverter
    public Date toDate(Long value){
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public Long toLong(Date value){
        return value == null ? null : value.getTime();
    }
}
