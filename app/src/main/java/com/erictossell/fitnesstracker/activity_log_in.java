package com.erictossell.fitnesstracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.erictossell.fitnesstracker.Database.AppDatabase;
import com.erictossell.fitnesstracker.Database.User;

public class activity_log_in extends AppCompatActivity {
    private AppDatabase database;
    private EditText emailEditText;
    private EditText passwordEditText;

    private String email;
    private String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        database = AppDatabase.getDatabase(getApplicationContext());
        emailEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);

        Button loginButton = (Button) findViewById(R.id.logInButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                confirmLogIn(email, password);
            }
        });
    }
    public void confirmLogIn(String email, String password) {
        User user = database.userDao().getUser(email);
        if(user == null){
            Intent intent = new Intent(getBaseContext(), signUp.class);
            startActivity(intent);
        }
        else if(user.getPassword().equals(password)){
            Intent intent = new Intent(getBaseContext(), createProfile.class);
            intent.putExtra("username", email);
            startActivity(intent);
        }
        else {
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

    }
}
