package com.example.taskapp.ui.onboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.chabbal.slidingdotsplash.SlidingSplashView;
import com.example.taskapp.MainActivity;
import com.example.taskapp.R;
import com.example.taskapp.ui.home.HomeFragment;
import com.google.android.material.tabs.TabLayout;

public class OnBoardActivity extends AppCompatActivity  {
int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);
        ViewPager viewPager=findViewById(R.id.viewPager);
        viewPager.setAdapter( new SectionsPagerAdapter(getSupportFragmentManager()));
        TabLayout tabLayout = findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);

    }

    public void onClick(View view) {
saveIsShown();
        Intent intent = new Intent(OnBoardActivity.this, MainActivity.class);
        startActivity(intent);
        finish();

    }
    private void saveIsShown() {
        SharedPreferences preferences= getSharedPreferences("setting",MODE_PRIVATE);
        preferences.edit().putBoolean("isShown",true).apply();}


    public  class SectionsPagerAdapter extends FragmentPagerAdapter{

        public SectionsPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            pos=position;
            Bundle bundle= new Bundle();
            bundle.putInt("pos",pos);
            Boardragment fragment= new Boardragment();
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

}
