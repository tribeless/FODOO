package com.example.fodoo;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN = 1000;
    Animation top_animation,bottom_animation;
    ImageView img_View;
    TextView txt_View;


   // Button btn_1,btn_2,btn;
    LocationManager locationManager;
    LocationListener locationListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);




        top_animation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_animation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        img_View = findViewById(R.id.imageView);
        txt_View = findViewById(R.id.textView);
       /* btn_1 = findViewById(R.id.pop_btn_1);
        btn_2 = findViewById(R.id.pop_btn_2);*/

        img_View.setAnimation(top_animation);
        txt_View.setAnimation(bottom_animation);


      /*  btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                displayDialog();
               *//* myDialog.setContentView(R.layout.custom_pop_up);
                myDialog.show();*//*
            }
        });*/

displayDialog();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            }

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void displayDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LinearLayout layout  = new LinearLayout(this);
        final Button btn_a = new Button(this);
        final Button btn_b = new Button(this);
        TextView txt_a = new TextView(this);
        txt_a.setText(R.string.pop_up);
        txt_a.setTextSize(50f);
        btn_a.setText(R.string.pop_btn_name);
        btn_b.setText(R.string.pop_btn_name2);

        builder.setView(layout);
        builder.show();
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.addView(txt_a);
        layout.addView(btn_a);
        layout.addView(btn_b);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_a.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED) {
                            locationManager = (LocationManager) MainActivity.this.getSystemService(Context.LOCATION_SERVICE);
                            locationListener = new LocationListener() {
                                @Override
                                public void onLocationChanged(Location location) {
                                    Log.e("Location", location.toString());
                                }

                                @Override
                                public void onStatusChanged(String provider, int status, Bundle extras) {

                                }

                                @Override
                                public void onProviderEnabled(String provider) {


                                }

                                @Override
                                public void onProviderDisabled(String provider) {

                                }
                            };
                            if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
                            }
                            else{
                                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                            }
                        }
                        else if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
                            Intent i = new Intent(MainActivity.this,OnBoardingScreenOne.class);
                            startActivity(i);
                        }else{

                            Intent i = new Intent(MainActivity.this,OnBoardingScreenOne.class);
                            startActivity(i);
                        }



                    }


                });
                btn_b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MainActivity.this,OnBoardingScreenOne.class);
                        startActivity(intent);
                        finish();

                    }
                });

            }
        },SPLASH_SCREEN);



    }

   /* public void onButtonTapped(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,OnBoardingScreenOne.class);
                startActivity(intent);
                finish();
            }
        },5500);

    }*/




    /*new Handler().postDelayed(new Runnable() {
@Override
public void run() {

        displayDialog();

        //Objects.requireNonNull(myDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        btn_1.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {

        locationManager = (LocationManager) MainActivity.this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
@Override
public void onLocationChanged(Location location) {
        Log.e("Location",location.toString());
        }

@Override
public void onStatusChanged(String provider, int status, Bundle extras) {

        }

@Override
public void onProviderEnabled(String provider) {

        }

@Override
public void onProviderDisabled(String provider) {

        }
        };

        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        else{
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        }

        }

        });
        btn_2.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,OnBoardingScreenOne.class);
        startActivity(intent);
        finish();
        }
        });
        }
        },SPLASH_SCREEN);
*/
}

