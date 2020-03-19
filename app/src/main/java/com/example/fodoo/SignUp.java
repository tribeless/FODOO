package com.example.fodoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fodoo.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

    EditText phone_num,usr_name,usr_pass;
    Button sign_up;
    ProgressBar new_Progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        phone_num = (MaterialEditText)findViewById(R.id.phone_number);
        usr_name = (MaterialEditText)findViewById(R.id.name);
        usr_pass = (MaterialEditText)findViewById(R.id.pass_word_sign_up);

        sign_up = findViewById(R.id.sign_up_btn);
        new_Progress = findViewById(R.id.new_progressBar);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference("user");

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progress bar
                new_Progress.setVisibility(View.VISIBLE);
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //check to see if input num exists or not
                        if(dataSnapshot.child(phone_num.getText().toString()).exists()){

                            Toast.makeText(SignUp.this,"Phone number already registered",Toast.LENGTH_SHORT).show();
                            new_Progress.setVisibility(View.INVISIBLE);

                            //to be done if phone num is not yet reqistered
                        }else{
                            //adding the user name and password to the db
                            User user = new User(usr_name.getText().toString(),usr_pass.getText().toString());

                            //setting those values into the User class
                            databaseReference.child(phone_num.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this,"Sign up successful",Toast.LENGTH_SHORT).show();
                            new_Progress.setVisibility(View.INVISIBLE);
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

       /* FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = firebaseDatabase.getReference();

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        //check if number already exists

                        if(dataSnapshot.child(phone_num.getText().toString()).exists()){

                            Toast.makeText(SignUp.this,"User already exists",Toast.LENGTH_LONG).show();
                        }else{

                            User user = new User(usr_name.getText().toString(),usr_pass.getText().toString());
                            databaseReference.child(phone_num.getText().toString()).setValue(user);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });*/
    }
}
