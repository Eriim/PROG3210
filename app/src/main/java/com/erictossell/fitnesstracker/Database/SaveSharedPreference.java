package com.erictossell.fitnesstracker.Database;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.SharedPreferences.Editor;

/**
 * Created by etossell8259 on 12/12/2017.
 */

public class SaveSharedPreference {
    static final String PREF_USER_NAME = "username";

    private static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }
    public static void setUserName(Context ctx, String userName){
        Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }
    public static String getUserName(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    static final String DAILY_PROTEIN = "protein";

    public static void setDailyProtein(Context ctc, int protein) {
        Editor editor = getSharedPreferences(ctc).edit();
        editor.putInt(DAILY_PROTEIN, protein);
        editor.commit();
    }
    public static int getProtein(Context ctx){
        return getSharedPreferences(ctx).getInt(DAILY_PROTEIN, 0);
    }
    static final String DAILY_FAT = "fat";

    public static void setDailyFat(Context ctc, int fat) {
        Editor editor = getSharedPreferences(ctc).edit();
        editor.putInt(DAILY_FAT, fat);
        editor.commit();
    }
    public static int getFat(Context ctx){
        return getSharedPreferences(ctx).getInt(DAILY_FAT, 0);
    }
    static final String DAILY_CARB = "carbs";

    public static void setDailyCarb(Context ctc, int carb) {
        Editor editor = getSharedPreferences(ctc).edit();
        editor.putInt(DAILY_CARB, carb);
        editor.commit();
    }
    public static int getCarb(Context ctx){
        return getSharedPreferences(ctx).getInt(DAILY_CARB, 0);
    }
    static final String DAILY_CALORIE = "calories";

    public static void setDailyCalorie(Context ctc, int calories) {
        Editor editor = getSharedPreferences(ctc).edit();
        editor.putInt(DAILY_CALORIE, calories);
        editor.commit();
    }
    public static int getCalories(Context ctx){
        return getSharedPreferences(ctx).getInt(DAILY_CALORIE, 0);
    }
    static final String LAST_RESET_DATE = "last reset date";

    public static void setLastResetDate(Context ctc, String date) {
        Editor editor = getSharedPreferences(ctc).edit();
        editor.putString(LAST_RESET_DATE, date);
        editor.commit();
    }
    public static String getLastResetDate(Context ctx){
        return getSharedPreferences(ctx).getString(LAST_RESET_DATE, " " );
    }


}
