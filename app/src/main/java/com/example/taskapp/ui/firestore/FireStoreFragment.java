package com.example.taskapp.ui.firestore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.taskapp.R;
import com.example.taskapp.ui.slideshow.SlideshowViewModel;


public class FireStoreFragment extends Fragment {

    private FireStoreModel firestoreViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        firestoreViewModel =
                ViewModelProviders.of(this).get(FireStoreModel.class);
        View root = inflater.inflate(R.layout.fragment_fire_store, container, false);
        final TextView textView = root.findViewById(R.id.text_firestore);
        firestoreViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
