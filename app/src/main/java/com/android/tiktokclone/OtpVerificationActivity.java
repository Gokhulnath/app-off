package com.android.tiktokclone;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.tiktokclone.utils.Constants;
import com.android.tiktokclone.utils.SharedPref;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class OtpVerificationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private PhoneAuthProvider phoneAuth;
    private String verificationId;
    PhoneAuthOptions options;

    Button otpVerifiedBT;
    Button sendOtpBT;
    TextView numberTV;
    TextInputEditText otpTIET;
    String phNumber;
    String authId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verification);


        mAuth = FirebaseAuth.getInstance();

        phNumber = SharedPref.getString(getApplicationContext(), Constants.phoneNumber);
        otpVerifiedBT = findViewById(R.id.otpVerifiedBT);
        numberTV = findViewById(R.id.numberTV);
        numberTV.setText("OTP sent to " + phNumber);
        otpTIET = findViewById(R.id.otpTIET);
        sendOtpBT = findViewById(R.id.sendOtpBT);
        otpVerifiedBT.setEnabled(false);
        sendOtpBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpVerifiedBT.setEnabled(true);
                sendOtpBT.setEnabled(false);
                new CountDownTimer(10000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        sendOtpBT.setText("RESEND OTP IN " + millisUntilFinished / 1000 + "sec");
                    }

                    public void onFinish() {
                        sendOtpBT.setText("RESEND OTP");
                    }
                }.start();
                sendOtpBT.setText("RESEND OTP IN 5sec");
                Timer sendOtpTimer = new Timer();
                sendOtpTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                sendOtpBT.setEnabled(true);
                            }
                        });
                    }
                }, 10000);
                String phoneNumber = "+91" + phNumber;


                options =
                        PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber(phoneNumber)
                                .setTimeout(60L, TimeUnit.SECONDS)
                                .setActivity(OtpVerificationActivity.this)
                                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        Toast.makeText(OtpVerificationActivity.this, "Auto verification called", Toast.LENGTH_SHORT).show();
                                        otpTIET.setText(phoneAuthCredential.getSmsCode());
                                        verifyUser(phoneAuthCredential);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        Toast.makeText(OtpVerificationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        Toast.makeText(OtpVerificationActivity.this, "Code sent!", Toast.LENGTH_SHORT).show();
                                        verificationId = s;
                                    }
                                })
                                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);
            }
        });

        otpVerifiedBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (otpTIET.getEditableText().toString().matches("")) {
                    otpTIET.setError("Invalid otp");
                } else {
                    String otp = otpTIET.getEditableText().toString();
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);
                    verifyUser(credential);
                }
            }
        });
    }


    void verifyUser(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(OtpVerificationActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    authId = FirebaseAuth.getInstance().getUid();
                    SharedPref.putString(getApplicationContext(), Constants.authId, authId);
                    SharedPref.putInt(getApplicationContext(), Constants.loginStatus, 1);
                    Intent main = new Intent(OtpVerificationActivity.this,MainActivity.class);
                    startActivity(main);
                } else {
                    Toast.makeText(OtpVerificationActivity.this, "Login Failure", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}