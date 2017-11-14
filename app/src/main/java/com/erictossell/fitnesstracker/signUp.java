package com.erictossell.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class signUp extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    private String email = "";
    private String password = "";
    private String confirmPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordEditText);

        Button createAccountButton = (Button) findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
            email = emailEditText.getText().toString();
            password = passwordEditText.getText().toString();
            confirmPassword = confirmPasswordEditText.getText().toString();
                confirmRegistration(email, password, confirmPassword);
            }
        });
    }

    public void confirmRegistration(String email, String password, String confirmPassword) {
            if (password.equals(confirmPassword)){

                Intent intent = new Intent(getBaseContext(), createProfile.class);
                intent.putExtra("username", email);
                startActivity(intent);
            }
    }
}