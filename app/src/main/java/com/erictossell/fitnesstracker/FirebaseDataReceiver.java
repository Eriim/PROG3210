package com.erictossell.fitnesstracker;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

/**
 * Created by erict on 1/4/2018.
 */

public class FirebaseDataReceiver extends WakefulBroadcastReceiver {
    private final String TAG = "FirebaseDataReceiver";


    public void onReceive(Context context, Intent intent){
        if(intent.getExtras() != null){
            for(String key : intent.getExtras().keySet()){
                Object value = intent.getExtras().get(key);
            }
        }
    }
}
