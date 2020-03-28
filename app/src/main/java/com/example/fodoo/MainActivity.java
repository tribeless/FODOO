package com.example.fodoo;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import com.example.fodoo.SignIn.*;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import android.graphics.Typeface;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fodoo.Common.Common;
import com.example.fodoo.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import io.paperdb.Paper;


public class MainActivity  extends AppCompatActivity  {



    private static int SPLASH_SCREEN = 1000;
    Animation top_animation,bottom_animation;
    ImageView img_View;
    TextView txt_View;
    Button sign,log;



   // Button btn_1,btn_2,btn;
   /* LocationManager locationManager;
    LocationListener locationListener;*/






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        Paper.init(this);



        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        img_View = findViewById(R.id.imageView);
        txt_View = findViewById(R.id.textView);
       /* btn_1 = findViewById(R.id.pop_btn_1);
        btn_2 = findViewById(R.id.pop_btn_2);*/

        img_View.setAnimation(top_animation);
        txt_View.setAnimation(bottom_animation);
        sign = findViewById(R.id.sign_up);
        log = findViewById(R.id.log_in);
        sign.setAnimation(bottom_animation);
        log.setAnimation(bottom_animation);


        Typeface typeface = Typeface.createFromAsset(getAssets(),"fonts/thebomb.ttf");
        txt_View.setTypeface(typeface);



        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this,SignUp.class);
                startActivity(intent);
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,SignIn.class);
                startActivity(i);
            }
        });


        String userKey = Paper.book().read(Common.USER_KEY);
        String userPwd = Paper.book().read(Common.USER_PWD);

        if(userKey!=null && userPwd!=null){

            if(!TextUtils.isEmpty(userKey) && !TextUtils.isEmpty(userPwd)){

                logUserIn(userKey,userPwd);
            }
        }


    }

    private void logUserIn(final String phone_num, final String pass_word) {


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("user");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                //Get user information
                //check user exists

                if (dataSnapshot.child(phone_num).exists()){
                    //if phone num exists, do the following

                    User user = dataSnapshot.child(phone_num).getValue(User.class);
                    assert user != null;

                    //check if password is correct
                    if(user.getPassword().equals(pass_word)){

                        Intent homeIntent = new Intent(MainActivity.this,Home.class);
                        Common.currentUser = user;
                        startActivity(homeIntent);
                        //Toast.makeText(SignIn.this,"Sign in successful",Toast.LENGTH_SHORT).show();
                        //progress.setVisibility(View.INVISIBLE);


                    }else{
                        Toast.makeText(MainActivity.this,"Wrong password",Toast.LENGTH_SHORT).show();
                       // progress.setVisibility(View.INVISIBLE);
                    }}
                //if phone num doesn't exist
                else{Toast.makeText(MainActivity.this,"User does not exist",Toast.LENGTH_SHORT).show(); //progress.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }

}

