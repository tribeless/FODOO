package com.example.fodoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fodoo.Interface.itemClickListener;
import com.example.fodoo.Model.Category;
import com.example.fodoo.Model.Food;
import com.example.fodoo.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {

    RecyclerView foodRecyclerView;
    RecyclerView.LayoutManager foodlayoutManager;

    FirebaseDatabase firebaseDatabase ;
    DatabaseReference food;
    FirebaseRecyclerAdapter<Food,FoodViewHolder> foodAdapter;

String CategoryId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        firebaseDatabase = FirebaseDatabase.getInstance();
        food = firebaseDatabase.getReference("Food");


        //load menu
        foodRecyclerView = findViewById(R.id.new_recycler);
        foodRecyclerView.setHasFixedSize(true);
        foodlayoutManager = new LinearLayoutManager(this);
        foodRecyclerView.setLayoutManager(foodlayoutManager);

        //get the intent here
        if(getIntent()!=null){
            CategoryId = getIntent().getStringExtra("CategoryId");
            assert CategoryId != null;
            if(!CategoryId.isEmpty()){
                loadListFood(CategoryId);
            }
        }




    }

    private void loadListFood(String categoryId) {

        foodAdapter =  new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class,
                R.layout.food_item,FoodViewHolder.class,
                food.orderByChild("MenuId").equalTo(categoryId))//this selects * from where menuId==Category id

        {
            @Override
            protected void populateViewHolder(FoodViewHolder foodViewHolder, Food food, int i) {


                foodViewHolder.foodTextView.setText(food.getName());
               Picasso.get().load(food.getImage()).into(foodViewHolder.foodImageView);
               // Glide.with(getBaseContext()).load(food.getImage()).into(foodViewHolder.foodImageView);

                final Food foodClicked = food;
                foodViewHolder.setItemClickListener(new itemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Toast.makeText(FoodList.this,foodClicked.getName(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };
     foodRecyclerView.setAdapter(foodAdapter);
    }
}
