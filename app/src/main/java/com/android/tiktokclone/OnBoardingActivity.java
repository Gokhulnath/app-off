package com.android.tiktokclone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.tiktokclone.adapter.ViewPageAdapter;
import com.android.tiktokclone.fragment.Frag1;
import com.android.tiktokclone.fragment.Frag2;
import com.android.tiktokclone.fragment.Frag3;

public class OnBoardingActivity extends AppCompatActivity {

    Button loginBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        ViewPager viewPager = findViewById(R.id.viewPager);
        ViewPageAdapter adpater = new ViewPageAdapter(getSupportFragmentManager());

        adpater.addFragment(new Frag1());
        adpater.addFragment(new Frag2());
        adpater.addFragment(new Frag3());

        viewPager.setAdapter(adpater);

        loginBT = findViewById(R.id.loginBT);
        loginBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(OnBoardingActivity.this, LoginActivity.class);
                startActivity(login);
            }
        });
    }
}