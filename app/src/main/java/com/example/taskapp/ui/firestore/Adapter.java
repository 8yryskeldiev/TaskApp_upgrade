package com.example.taskapp.ui.firestore;

import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taskapp.R;

import com.example.taskapp.models.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<User> list;
    FireStoreListener listener;

    public Adapter(final ArrayList<User> list) {
        this.list = list;

    }

    public void addUser(User user) {
        list.add(user);
        notifyDataSetChanged();}


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.fire_task,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(position % 2==0) {
            holder.layout.setBackgroundColor( Color.LTGRAY);
        }else {
            holder.layout.setBackgroundColor(Color.WHITE);
        }
        holder.bind(list.get(position));



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class  ViewHolder extends  RecyclerView.ViewHolder {
        private TextView name;
        FireStoreListener listener;
        LinearLayout layout;
        private TextView number;
        ImageView avatar;
        TextView url;


        public ViewHolder(@NonNull View itemView) {
            super( itemView );
            name = itemView.findViewById( R.id.textTitle );
            avatar = itemView.findViewById( R.id.imageView_fire );
            number = itemView.findViewById( R.id.textDisc );
            layout = itemView.findViewById( R.id.layout );
            url=itemView.findViewById( R.id.url );
        }

        public void bind(User user) {

            name.setText(user.getName());
            number.setText((user.getPhoneNumber()));
            url.setText( user.getAvatar() );

        }
    }
}
