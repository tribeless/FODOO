package com.example.fodoo;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.fodoo.Common.Common;
import com.example.fodoo.Interface.itemClickListener;
import com.example.fodoo.Model.Category;
import com.example.fodoo.ViewHolder.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity
{

    private AppBarConfiguration mAppBarConfiguration;

    FirebaseDatabase database;
    DatabaseReference category;
    TextView txt_fullName;

   RecyclerView recyclerView;
   RecyclerView.LayoutManager layoutManager;
    FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter;
    FirebaseRecyclerOptions<Category> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Menu");
        setSupportActionBar(toolbar);

        //init database
        database = FirebaseDatabase.getInstance();
        category = database.getReference("Category");




        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_cart, R.id.nav_orders_orders,
                R.id.nav_log_out)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        //set full name of user
        View headerView = navigationView.getHeaderView(0);
        txt_fullName = headerView.findViewById(R.id.txt_full_name);
        txt_fullName.setText(Common.currentUser.getName());

        //load menu
        recyclerView = findViewById(R.id.recycler_View);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        loadMenu();



    }

    private void loadMenu() {

        options = new FirebaseRecyclerOptions.Builder<Category>().setQuery(category,Category.class).build();

         adapter = new FirebaseRecyclerAdapter<Category, MenuViewHolder>(options) {
             @NonNull
             @Override
             public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                 View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
                 return new MenuViewHolder(view);
             }

             @Override
             protected void onBindViewHolder(@NonNull MenuViewHolder holder, int position, @NonNull Category model) {

                 holder.txtMenuName.setText(model.getName());
                 Picasso.get().load(model.getImage()).into(holder.imageView);
                 //Glide.with(getBaseContext()).load(category.getImage()).into(menuViewHolder.imageView);
                 //final Category clickItem = category;

                 //item-click-listener class
                 holder.setItemClickListener(new itemClickListener() {
                     @Override
                     public void onClick(View view, int position, boolean isLongClick) {
                         Intent foodIntent = new Intent(Home.this,FoodList.class);
                         foodIntent.putExtra("CategoryId",adapter.getRef(position).getKey());
                         startActivity(foodIntent);

                     }
                 });
             }


        };
         adapter.startListening();
        recyclerView.setAdapter(adapter);

    }



    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }*/

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //handle navigation item clicks here
        int id = item.getItemId();
        if(id==R.id.nav_menu){

        }else if (id==R.id.nav_cart){

        }else if (id==R.id.nav_orders){

        }else if (id==R.id.nav_log_out){

        }

        return super.onOptionsItemSelected(item);
    }

}
