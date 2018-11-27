package com.example.recytechco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recytechco.models.User;
import com.example.recytechco.util.DatabaseQuery;

public class SignupActivity extends AppCompatActivity {

    private TextView mLoginTextView;
    private Button mSignUpButton;
    private EditText mFullNameEditText, mUsernameEditText, mPasswordEditText, mPasswordEditText2;

    private DatabaseQuery mDatabaseQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mDatabaseQuery = new DatabaseQuery(getApplicationContext());

        mFullNameEditText = findViewById(R.id.fullNameEditText);
        mUsernameEditText = findViewById(R.id.usernameEditText);
        mPasswordEditText = findViewById(R.id.passwordEditText);
        mPasswordEditText2 = findViewById(R.id.passwordEditText2);

        mLoginTextView = findViewById(R.id.loginTextView);
        mLoginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
        mSignUpButton = findViewById(R.id.signUpButton);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    private void signUp() {
        if (validateFields()) {
            User user = new User(mUsernameEditText.getText().toString(),
                    mFullNameEditText.getText().toString(), mPasswordEditText.getText().toString(),
                    0);
            mDatabaseQuery.add(user, new DatabaseQuery.DatabaseQueryListener() {
                @Override
                public void onSuccess() {
                    finish();
                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                }
            });
        }
    }

    private boolean validateFields() {
        if (mFullNameEditText.getText().toString().trim().equals("") ||
                mUsernameEditText.getText().toString().trim().equals("") ||
                mPasswordEditText.getText().toString().trim().equals("") ||
                mPasswordEditText2.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Type all fields.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (mDatabaseQuery.getUserBy(mUsernameEditText.getText().toString()) != null) {
            Toast.makeText(SignupActivity.this, "User was already registered. ",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!mPasswordEditText.getText().toString().equals(mPasswordEditText2.getText().toString())) {
            Toast.makeText(this, "The passwords don't match.", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }
        return true;
    }
}