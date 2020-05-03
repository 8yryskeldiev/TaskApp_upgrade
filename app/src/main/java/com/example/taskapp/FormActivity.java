package com.example.taskapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.taskapp.models.Task;

public class FormActivity extends AppCompatActivity {
private EditText editTitle;
private EditText editDisc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
       if(getSupportActionBar()!= null){
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
           getSupportActionBar().setDisplayShowHomeEnabled(true);
           getSupportActionBar().setTitle("Новая задача");
       }
        editTitle=findViewById(R.id.editTitle);
        editDisc=findViewById(R.id.editDisc);
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
        String desc=editDisc.getText().toString().trim();
        Task task= new Task(title,desc);
        Intent intent=new Intent();
        intent.putExtra("task",task);
        setResult(RESULT_OK, intent);
        finish();
    }
}
