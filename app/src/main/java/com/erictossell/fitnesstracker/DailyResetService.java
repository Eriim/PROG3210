package com.erictossell.fitnesstracker;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.erictossell.fitnesstracker.Database.SaveSharedPreference;
import com.erictossell.fitnesstracker.calorieTracker;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by etossell8259 on 1/3/2018.
 */

public class DailyResetService extends Service {

    private Timer timer;

    @Override
    public void onCreate() {
        Log.d("Reset Service", "Service Created");
        startTimer();
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onDestroy(){
        stopTimer();
    }
    private void startTimer(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                SaveSharedPreference.setDailyCalorie(getApplicationContext(), 0);
                SaveSharedPreference.setDailyCarb(getApplicationContext(), 0);
                SaveSharedPreference.setDailyFat(getApplicationContext(), 0);
                SaveSharedPreference.setDailyProtein(getApplicationContext(), 0);

                startActivity(new Intent(getApplicationContext(), calorieTracker.class));
            }
        };
        timer = new Timer(true);
        int delay = 1000 * 10;
        int interval = 1000* 10;
        timer.schedule(task, delay, interval);
    }
    private void stopTimer() {
        if (timer != null) {
            timer.cancel();
        }
    }

}
