package com.erictossell.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_log_in extends AppCompatActivity {
    private EditText emailEditText;

    private String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        emailEditText = (EditText) findViewById(R.id.usernameEditText);

        Button loginButton = (Button) findViewById(R.id.logInButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                email = emailEditText.getText().toString();
                confirmLogIn(email);
            }
        });
    }
    public void confirmLogIn(String email) {
        Intent intent = new Intent(getBaseContext(), createProfile.class);
        intent.putExtra("username", email);
        startActivity(intent);
    }
}
