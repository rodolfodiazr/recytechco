package com.example.recytechco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannedString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.recytechco.models.User;
import com.example.recytechco.util.Config;
import com.example.recytechco.util.DatabaseQuery;

public class LoginActivity extends AppCompatActivity {

    private static final String tag = LoginActivity.class.getSimpleName();

    private EditText mUsernameEditText, mPasswordEditText;
    private TextView mSignUpTextView, mMessageTextView;
    private Button mLoginButton;

    private DatabaseQuery mDatabaseQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mDatabaseQuery = new DatabaseQuery(this);

        mUsernameEditText = findViewById(R.id.usernameEditText);
        mPasswordEditText = findViewById(R.id.passwordEditText);
        mMessageTextView = findViewById(R.id.errorMessageTextView);
        mSignUpTextView = findViewById(R.id.signUpTextView);
        SpannableString spannableString = new SpannableString(getString(R.string.lbl_sign_up));
        spannableString.setSpan(new UnderlineSpan(), 0, spannableString.length(), 0);
        mSignUpTextView.setText(spannableString);
        mSignUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
        mLoginButton = findViewById(R.id.loginButton);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUsernameEditText.getText().toString().trim().equals("") ||
                        mPasswordEditText.getText().toString().trim().equals("")) {
                    mMessageTextView.setText(R.string.message_type_credentials);
                    return;
                }
                login();
            }
        });

    }

    private void login() {
        User user = mDatabaseQuery.getUserBy(mUsernameEditText.getText().toString(),
                mPasswordEditText.getText().toString());
        if (user == null) {
            mMessageTextView.setText(R.string.message_wrong_credentials);
            return;
        }
        mMessageTextView.setText("");
        Config.setUserId(this, user.getId());
        Config.setFullName(this, user.getFullName());
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mUsernameEditText.requestFocus();
    }
}
