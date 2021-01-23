package com.android.tiktokclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.tiktokclone.utils.Constants;
import com.android.tiktokclone.utils.SharedPref;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    Button verifyBT;
    TextInputEditText phoneNumberTIET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneNumberTIET = findViewById(R.id.phoneNumberTIET);
        verifyBT = findViewById(R.id.verifyBT);
        verifyBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phNumber = phoneNumberTIET.getText().toString();
                if (phNumber.length() == 10 && phNumber != null) {
                    SharedPref.putString(getApplicationContext(), Constants.phoneNumber, phNumber);
                    Intent otp = new Intent(LoginActivity.this, OtpVerificationActivity.class);
                    startActivity(otp);
                } else {
                    phoneNumberTIET.setError("Please enter valid phone number");
                }
            }
        });
    }
}