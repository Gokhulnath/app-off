package com.android.tiktokclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.android.tiktokclone.utils.Constants;
import com.android.tiktokclone.utils.SharedPref;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class SplashScreen extends AppCompatActivity {
    ImageView logoIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logoIV = findViewById(R.id.logoIV);
        YoYo.with(Techniques.Shake)
                .duration(900)
                .repeat(3)
                .playOn(findViewById(R.id.logoIV));

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                int loginStatus = SharedPref.getInt(getApplicationContext(), Constants.loginStatus);
                if (loginStatus == 1) {
                    Intent main = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(main);
                    finish();
                } else {
                    Intent onBoarding = new Intent(SplashScreen.this, OnBoardingActivity.class);
                    startActivity(onBoarding);
                }


            }
        }, 2000);
    }
}