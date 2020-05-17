package com.example.taskapp.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.App;
import com.example.taskapp.FormActivity;
import com.example.taskapp.MainActivity;
import com.example.taskapp.R;
import com.example.taskapp.models.Task;
import com.example.taskapp.ui.OnItemClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment implements OnItemClickListener {


private  Task task;
    private TaskAdapter adapter;
    private ArrayList<Task> list = new ArrayList<>();




    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == RESULT_OK && requestCode==100&& data != null) {
//            task = (Task) data.getSerializableExtra("task");
//            Log.e("tag", "title=" + task.getTitle());
//            if (task!=null){
//                list.add(task);
//            }
//        }
//    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView=view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter= new TaskAdapter(list);
        adapter.listener =this;
        recyclerView.setAdapter(adapter);

        loadData();





    }

    private void loadData() {
        App.getInstance().getDataBase().taskDao().getAllLive().observe(getViewLifecycleOwner(), new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                list.clear();
                list.addAll(tasks);
                adapter.notifyDataSetChanged();
            }
        });
    }


    @Override
    public void onItemClick(int pos) {
        task=list.get(pos);

        Intent intent = new Intent(getContext(), FormActivity.class);
        intent.putExtra("change", task);
        getActivity().startActivity(intent);

    }

    @Override
    public void createOneButtonAlertDialog(final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Вы точно хотите удалить этот элемент из БД?");
        builder.setNegativeButton("НЕТ",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
            Toast.makeText(getContext(), "Вы нажали нет", Toast.LENGTH_SHORT).show();
                    }
                });
        builder.setPositiveButton("ДА",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        App.getInstance().getDataBase().taskDao().delete(list.get(pos));
                    }
                });
        builder.show();

    }

    public void sorting() {
        list.clear();
        list.addAll(App.getInstance().getDataBase().taskDao().sort());
        adapter.notifyDataSetChanged();
        }
    public void  back() {
        list.clear();
        list.addAll(App.getInstance().getDataBase().taskDao().getAll());
        adapter.notifyDataSetChanged();
    }}



