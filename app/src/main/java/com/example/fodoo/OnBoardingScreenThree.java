package com.example.fodoo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OnBoardingScreenThree extends AppCompatActivity {

    Button nextPage3;
    TextView skipAction3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen_three);

        nextPage3 = findViewById(R.id.next_page3);
        skipAction3 = findViewById(R.id.skip_two);

       /* nextPage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OnBoardingScreenThree.this,PopUp.class);
                startActivity(intent);
            }
        });*/

        /*
        skipAction3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OnboardingScreenTwo.this,HomeScreen.class);
                startActivity(i);
            }
        });*/
    }
}
