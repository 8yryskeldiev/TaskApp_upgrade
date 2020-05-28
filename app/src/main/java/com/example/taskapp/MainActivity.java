package com.example.taskapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.taskapp.logın.PhoneActivity;
import com.example.taskapp.models.Task;
import com.example.taskapp.ui.home.HomeFragment;
import com.example.taskapp.ui.onboard.OnBoardActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
private  boolean ten;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isShown()) {
            startActivity(new Intent(MainActivity.this, OnBoardActivity.class));
            finish();
            return;

        }
        if(FirebaseAuth.getInstance().getCurrentUser()==null){
            //Если мы не авторизованы
            startActivity(new Intent(MainActivity.this, PhoneActivity.class));
            finish();
            return;

        }

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this, FormActivity.class), 100);
            }
        });
        final DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        headerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toProfile= new Intent(MainActivity.this,ProfileActivity.class);
                startActivity(toProfile);
                finish();
            }
        });


        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,R.id.nav_fireStore)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }
    private  boolean isShown(){
        SharedPreferences preferences=getSharedPreferences("setting", Context.MODE_PRIVATE);
        return preferences.getBoolean("isShown",false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        {
            switch (item.getItemId()){
                case R.id.action_exit:
                    SharedPreferences preferences = getSharedPreferences("setting", Context.MODE_PRIVATE);
                    preferences.edit().putBoolean("isShown", false).apply();finish();
                    break;
                case R.id.action_sort:
                    if(ten){
                        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                        ((HomeFragment) fragment.getChildFragmentManager().getFragments().get(0)).sorting();
                        ten= false;
                    } else
                    {Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                        ((HomeFragment) fragment.getChildFragmentManager().getFragments().get(0)).back();
                        ten=true;
                    }
                break;
                case R.id.action_logout:
                    FirebaseAuth.getInstance().signOut();
                    Intent toPhone= new Intent(MainActivity.this,PhoneActivity.class);
                    startActivity(toPhone);
                    finish();
            }
        }
        return super.onOptionsItemSelected(item);

    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode == 100 && data != null) {
//            task = (Task) data.getSerializableExtra("task");
//

//            Fragment homeFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
//            homeFragment.getChildFragmentManager().getFragments().get(0).onActivityResult(requestCode,resultCode,data);
//
//
//        }
//    }

}




