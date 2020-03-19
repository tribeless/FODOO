package com.example.fodoo.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import  com.example.fodoo.Interface.itemClickListener;
import com.example.fodoo.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView txtMenuName;
        public ImageView imageView;

        private itemClickListener itemClickListener;

         public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        txtMenuName = itemView.findViewById(R.id.menu_name);
        imageView = itemView.findViewById(R.id.menu_image);

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
