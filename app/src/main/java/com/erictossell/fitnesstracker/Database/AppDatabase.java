package com.erictossell.fitnesstracker.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;


@Database(entities={User.class, Meal.class, Weight.class, MacroPlan.class},
version = 26, exportSchema = false)
@TypeConverters({DateConverter.class})
public abstract class AppDatabase extends RoomDatabase{

    //Variable declaration
    private static AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract MealDao mealDao();
    public abstract WeightDao weightDao();
    public abstract MacroPlanDao macroPlanDao();

    // Database constructor
    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "fitnesssdatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }

    // Destroys current instance
    public static void destroyInstance() {
        INSTANCE = null;
    }
}
