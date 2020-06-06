package com.example.taskapp.ui.firestore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.taskapp.MainActivity;
import com.example.taskapp.R;


import com.example.taskapp.models.User;
import com.example.taskapp.ui.gallery.GalleryAdapter;
import com.example.taskapp.ui.home.TaskAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class FireStoreFragment extends Fragment implements FireStoreListener {
    private Adapter adapter;
    private ArrayList<User> list = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fire_store, container, false);    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        RecyclerView recyclerView=view.findViewById(R.id.fire_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter= new Adapter(list);
        recyclerView.setAdapter(adapter);
            FirebaseFirestore.getInstance().collection( "users" ).get()
                    .addOnSuccessListener( new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot snapshots) {
                            list.addAll( snapshots.toObjects( User.class ) );
                            adapter.notifyDataSetChanged();
                        }
                    } );

    }



    @Override
    public void onItemClick(int pos) {

    }
}

