package com.example.taskapp.ui.gallery;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;

public class GalleryViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    public GalleryViewHolder(@NonNull View itemView) {
        super(itemView);
        textView=itemView.findViewById(R.id.textTitle);

    }
}
