package com.example.taskapp.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.FormActivity;
import com.example.taskapp.MainActivity;
import com.example.taskapp.R;
import com.example.taskapp.models.Task;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    private Task task;
    private TaskAdapter adapter;
    private ArrayList<Task> list = new ArrayList<>();


//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Bundle bundle = this.getArguments();
//        if (bundle != null) {
//            task = (Task) bundle.getSerializable("key");
//            Log.e("tag", "title=" + task.getTitle());
//            if (task!=null){
//                list.add(task);
//            }
//        }
//    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode==100&& data != null) {
            task = (Task) data.getSerializableExtra("task");
            Log.e("tag", "title=" + task.getTitle());
            if (task!=null){
                list.add(task);
            }
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter= new TaskAdapter(list);
        recyclerView.setAdapter(adapter);





    }


}


