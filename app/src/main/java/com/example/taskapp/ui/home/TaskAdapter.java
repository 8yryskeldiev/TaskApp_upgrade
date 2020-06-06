package com.example.taskapp.ui.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.models.Task;
import com.example.taskapp.ui.OnItemClickListener;

import java.util.ArrayList;



public class TaskAdapter extends  RecyclerView.Adapter<TaskAdapter.ViewHolder> {

  private  ArrayList<Task>list;
     OnItemClickListener listener;

    public TaskAdapter(final ArrayList<Task> list) {
        this.list = list;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_task,parent,false);
        ViewHolder vh = new ViewHolder(view);
        vh.listener = listener;
        return vh;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
if(position % 2==0) {
   holder.layout.setBackgroundColor(Color.LTGRAY);
}else {
 holder.layout.setBackgroundColor(Color.WHITE);
}


        holder.bind(list.get(position));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class  ViewHolder extends  RecyclerView.ViewHolder{
                private TextView textTitle;
                OnItemClickListener listener;
                LinearLayout layout;
                private TextView desc;

                public ViewHolder(@NonNull View itemView) {
                    super(itemView);
                    textTitle=itemView.findViewById(R.id.textTitle);
                    desc=itemView.findViewById(R.id.textDisc);
                    layout=itemView.findViewById(R.id.layout);
                    itemView.setOnTouchListener(new View.OnTouchListener(){
                        long startTime;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startTime = System.currentTimeMillis();

                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        long totalTime = System.currentTimeMillis() - startTime;
                        long totalSecunds = totalTime / 1000;
                        if( totalSecunds >= 2 )
                        {  listener.createOneButtonAlertDialog(getAdapterPosition()); }
                        else{listener.onItemClick(getAdapterPosition());}
                        break;
                }
                return true;
            }
        });

    }

        public void bind(Task task) {
desc.setText(task.getDesc());
        textTitle.setText((task.getTitle()));
        }


    }

    }

