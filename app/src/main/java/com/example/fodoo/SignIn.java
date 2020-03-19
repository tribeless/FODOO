package com.example.fodoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fodoo.Common.Common;
import com.example.fodoo.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import io.paperdb.Paper;

public class SignIn extends AppCompatActivity {

    public EditText phone_num,pass_word;
    ProgressBar progress;
    Button log_in ;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Paper.init(this);
        phone_num  = (MaterialEditText)findViewById(R.id.phone_num);
        pass_word = (MaterialEditText)findViewById(R.id.pass_word);
        log_in = findViewById(R.id.log_in_btn);
        progress = findViewById(R.id.progressBar);
        checkBox = findViewById(R.id.checkBox);


        //init database
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference("user");



        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Dialog
                progress.setVisibility(View.VISIBLE);
                //finish();

                if(checkBox.isChecked())
                {
                    Paper.book().write(phone_num.getText().toString(),phone_num);
                    Paper.book().write(Common.currentUser.getPassword(),pass_word);

                }


                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //Get user information
                        //check user exists

                        if (dataSnapshot.child(phone_num.getText().toString()).exists()){
                            //if phone num exists, do the following

                        User user = dataSnapshot.child(phone_num.getText().toString()).getValue(User.class);
                        assert user != null;

                        //check if password is correct
                        if(user.getPassword().equals(pass_word.getText().toString())){

                            Intent homeIntent = new Intent(SignIn.this,Home.class);
                            Common.currentUser = user;
                            startActivity(homeIntent);
                            //Toast.makeText(SignIn.this,"Sign in successful",Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.INVISIBLE);


                        }else{
                            Toast.makeText(SignIn.this,"Wrong password",Toast.LENGTH_SHORT).show();
                            progress.setVisibility(View.INVISIBLE);
                        }}
                        //if phone num doesn't exist
                        else{Toast.makeText(SignIn.this,"User does not exist",Toast.LENGTH_SHORT).show(); progress.setVisibility(View.INVISIBLE);}
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }

                });
            }



        });
 }


}
