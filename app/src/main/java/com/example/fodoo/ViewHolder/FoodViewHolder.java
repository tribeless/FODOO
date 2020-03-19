package com.example.fodoo.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fodoo.Interface.itemClickListener;
import com.example.fodoo.R;

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

 public TextView foodTextView;
 public ImageView foodImageView;

private itemClickListener itemClickListener;
    public FoodViewHolder(@NonNull View itemView) {


        super(itemView);
        foodImageView = itemView.findViewById(R.id.food_image);
        foodTextView =  itemView.findViewById(R.id.food_name);

        itemView.setOnClickListener(this);

    }

    public void setItemClickListener(com.example.fodoo.Interface.itemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {

        itemClickListener.onClick(v,getAdapterPosition(),false);

    }
}
