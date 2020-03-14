package com.example.fodoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OnboardingScreenTwo extends AppCompatActivity {

    Button nextPage2;
    TextView skipAction2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_screen_two);

        nextPage2 = findViewById(R.id.next_page2);
        skipAction2 = findViewById(R.id.skip_one);

        nextPage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OnboardingScreenTwo.this,OnBoardingScreenThree.class);
                startActivity(i);
                finish();
            }
        });
/*
        skipAction2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OnboardingScreenTwo.this,HomeScreen.class);
                startActivity(i);
            }
        });*/
    }
}
