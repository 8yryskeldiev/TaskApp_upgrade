package com.example.taskapp.ui.onboard;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.taskapp.MainActivity;
import com.example.taskapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Boardragment extends Fragment {


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Boardragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_boardragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView textView=view.findViewById(R.id.fragmentText);
        Button button=view.findViewById(R.id.getStarted);
        LottieAnimationView lottieAnimationView=view.findViewById(R.id.animation_view);
        int pos = getArguments().getInt("pos");
        switch (pos){
            case 0:
                button.setVisibility(View.INVISIBLE);
                textView.setText("ПРИВЕТ");
                lottieAnimationView.setAnimation(R.raw.history);
                break;
            case 1:
                button.setVisibility(View.INVISIBLE);
                textView.setText("КАК ДЕЛА?");
                lottieAnimationView.setAnimation(R.raw.swinging);
                break;
            case 2:
                button.setVisibility(View.VISIBLE);
                textView.setText("ЧТО ДЕЛАШЬ?");

                lottieAnimationView.setAnimation(R.raw.startupanimations);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveIsShown();
                                Intent intent = new Intent(getContext(), MainActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                    }

                });

                break;

        }
    }
    private void saveIsShown() {
        SharedPreferences preferences= getActivity().getSharedPreferences("setting", Context.MODE_PRIVATE);
        preferences.edit().putBoolean("isShown",true).apply();
    }
}
