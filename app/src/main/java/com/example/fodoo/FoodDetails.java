package com.example.fodoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.fodoo.Common.Common;
import com.example.fodoo.Model.Food;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class FoodDetails extends AppCompatActivity {

    TextView food_name_detail,food_price,food_description;
    ElegantNumberButton elegantNumberButton;
    CollapsingToolbarLayout collapsingToolbarLayout;
    FloatingActionButton btn_cart;

    ImageView imageView;
    String foodDetail = "";

    FirebaseDatabase database;
    DatabaseReference foods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        //initialize views
        imageView = findViewById(R.id.scrollable);
        food_name_detail = findViewById(R.id.food_name_detail);
        food_price = findViewById(R.id.food_price);
        food_description = findViewById(R.id.food_description);
        elegantNumberButton = findViewById(R.id.number_button);
        btn_cart = findViewById(R.id.btn_cart);
        collapsingToolbarLayout = findViewById(R.id.collapsing);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);


        //database
        database = FirebaseDatabase.getInstance();
        foods = database.getReference("Food");


        //getIntent

        if(getIntent()!=null)
        {
            foodDetail = getIntent().getStringExtra("FoodId");
            assert foodDetail != null;
            if(!foodDetail.isEmpty()){
                populateFoodDetail(foodDetail);
            }

        }
    }

    private void populateFoodDetail(String foodDetail) {
        foods.child(foodDetail).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Food food = dataSnapshot.getValue(Food.class);
                assert food != null;
                Picasso.get().load(food.getImage()).into(imageView);
                food_name_detail.setText(food.getName());
                collapsingToolbarLayout.setTitle(food.getName());
                food_price.setText(food.getPrice());
                food_description.setText(food.getDescription());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
