package com.example.fodoo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fodoo.Interface.itemClickListener;
import com.example.fodoo.Model.Category;
import com.example.fodoo.Model.Food;
import com.example.fodoo.ViewHolder.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {

    RecyclerView foodRecyclerView;
    RecyclerView.LayoutManager foodlayoutManager;

    FirebaseDatabase firebaseDatabase ;
    DatabaseReference food;
    FirebaseRecyclerAdapter<Food,FoodViewHolder> foodAdapter;
    FirebaseRecyclerOptions<Food>options;

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

        options = new FirebaseRecyclerOptions.Builder<Food>().setQuery(food.orderByChild("MenuId").equalTo(categoryId),Food.class).build();

        foodAdapter =  new FirebaseRecyclerAdapter<Food, FoodViewHolder>(options)
                //this selects * from where menuId==Category id

        {
            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
                return new FoodViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder holder, int position, @NonNull Food model) {

                holder.foodTextView.setText(model.getName());
                Picasso.get().load(model.getImage()).into(holder.foodImageView);
                // Glide.with(getBaseContext()).load(food.getImage()).into(foodViewHolder.foodImageView);


                holder.setItemClickListener(new itemClickListener() {
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {
                        Intent FoodDetail = new Intent(FoodList.this,FoodDetails.class);
                        FoodDetail.putExtra("FoodId",foodAdapter.getRef(position).getKey());
                        startActivity(FoodDetail);

                    }
                });

            }


        };
        foodAdapter.startListening();
     foodRecyclerView.setAdapter(foodAdapter);
    }
}
