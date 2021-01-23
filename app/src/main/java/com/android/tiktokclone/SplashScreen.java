package com.android.tiktokclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

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
                Intent onBoarding = new Intent(SplashScreen.this, OnBoardingActivity.class);
                startActivity(onBoarding);
            }
        }, 2000);
    }
}