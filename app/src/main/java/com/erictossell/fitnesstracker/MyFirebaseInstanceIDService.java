package com.erictossell.fitnesstracker;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by erict on 1/4/2018.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService{
    private static final String TAG = "MyFirebaseIIDService";
    @Override
    public void onTokenRefresh(){
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        sendRegistrationToServer(refreshedToken);
    }
    private void sendRegistrationToServer(String token){

    }
}
