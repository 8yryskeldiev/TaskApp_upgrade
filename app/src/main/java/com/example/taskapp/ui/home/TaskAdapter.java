package com.example.taskapp.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.models.Task;

import java.util.ArrayList;

public class TaskAdapter extends  RecyclerView.Adapter<TaskAdapter.ViewHolder> {

  private  ArrayList<Task>list;

    public TaskAdapter(ArrayList<Task> list) {
        this.list = list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_task,parent,false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.bind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class  ViewHolder extends  RecyclerView.ViewHolder{
private TextView textTitle;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        textTitle=itemView.findViewById(R.id.textTitle);
    }

        public void bind(Task task) {
        textTitle.setText(task.getTitle());
        }
    }

    }

