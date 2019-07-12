package com.example.texgram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignupActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText email;
    private EditText name;
    private Button signup;
    private final String TAG = "SignUpActivity";  // For logcat

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Set up views
        username = (EditText) findViewById(R.id.username_et);
        password = (EditText) findViewById(R.id.password_et);
        email = (EditText) findViewById(R.id.email_et);
        name = (EditText) findViewById(R.id.name_et);
        signup = (Button) findViewById(R.id.signup_btn);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the ParseUser
                ParseUser user = new ParseUser();

                // Set core properties
                String handle = username.getText().toString();
                user.setUsername(handle);

                String pw = password.getText().toString();
                user.setPassword(pw);

                String emailAddress = email.getText().toString();
                user.setEmail(emailAddress);

                String namePerson = name.getText().toString();
                user.put("name", namePerson);


                // Invoke signUpInBackground
                user.signUpInBackground(new SignUpCallback() {
                    public void done(ParseException e) {
                        // Login Unsuccessful
                        if(e != null){
                            Toast.makeText(SignupActivity.this, "Error, please try again.", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "SignUp Failure");
                            e.printStackTrace();
                            return;
                        }
                        // Sign-up successful - pass intent to home page
                        Toast.makeText(SignupActivity.this, "Sign up Successful!", Toast.LENGTH_LONG).show();
                        Log.d(TAG,"Login Successful");
                        final Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        });
    }



}
