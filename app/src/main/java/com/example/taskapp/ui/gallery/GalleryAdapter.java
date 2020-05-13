package com.example.taskapp.ui.gallery;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;

import java.util.ArrayList;


public class GalleryAdapter extends RecyclerView.Adapter<GalleryViewHolder> {


    private  ArrayList<String>list;

    public GalleryAdapter(ArrayList<String> list) {
        this.list = list;
    }



    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view= layoutInflater.inflate(R.layout.list_task,parent,false);
        return new GalleryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
holder.textView.setText((position + 1) + ") " +list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
