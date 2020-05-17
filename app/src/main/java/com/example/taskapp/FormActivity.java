package com.example.taskapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.taskapp.models.Task;

public class FormActivity extends AppCompatActivity {
private EditText editTitle;
private EditText editDisc;
Task task2;
    private int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Новая задача");

        }
        editTitle = findViewById(R.id.editTitle);
        editDisc = findViewById(R.id.editDisc);


        Intent intent = getIntent();
        task2 = (Task) intent.getSerializableExtra("change");
        if (task2!= null) {
                editTitle.setText(task2.title);
                editDisc.setText(task2.desc);
                position=intent.getIntExtra("position",0);
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       int id= item.getItemId();
       if(id==android.R.id.home){
           finish();
       }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        String title= editTitle.getText().toString().trim();
        if(title.isEmpty()){
            editTitle.setError("Пустое поле");
                    return;
        }
        String desc=editDisc.getText().toString().trim();
        if(desc.isEmpty()){
            editDisc.setError("Пустое поле");
            return;}
        Task task= new Task(title,desc);
        if ( task2!= null){
            task.setId(task2.getId());
            App.getInstance().getDataBase().taskDao().update(task);
        } else {
            App.getInstance().getDataBase().taskDao().insert(task);
        }
        finish();
    }


}
