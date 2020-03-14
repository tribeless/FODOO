package com.example.fodoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OnBoardingScreenOne extends AppCompatActivity {
    TextView skipAction;
    Button nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen_one);

        skipAction = findViewById(R.id.skip_one);
        nextPage = findViewById(R.id.next_page);

        nextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OnBoardingScreenOne.this,OnboardingScreenTwo.class);
                startActivity(i);
                finish();
            }
        });

        /*skipAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OnBoardingScreenOne.this,HomeScreen.class);
                startActivity(i);
            }
        });*/

    }
}
