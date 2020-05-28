package com.example.taskapp;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskapp.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {
    private static final int ACTIVITY_SELECT_IMAGE = 1;
    ImageView avatar;
    ImageView imageView;
    Button save;
    EditText editName;
    EditText editNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );
        imageView=findViewById( R.id.imageView);
        save=findViewById( R.id.profile_save );
        editName=findViewById( R.id.profile_name );
        editNumber=findViewById( R.id.profile_number );

//        getData();
        getData2();
        avatar = findViewById( R.id.avatar );
        avatar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent( Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI );
                startActivityForResult( i, ACTIVITY_SELECT_IMAGE );
            }
        } );
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult( requestCode, resultCode, data );
        if (resultCode == RESULT_OK) {
            if (requestCode == ACTIVITY_SELECT_IMAGE) {
                Uri uri = data.getData();
                avatar.setImageURI( uri );



            }
        }
    }
    private void getData() {
        String uid = FirebaseAuth. getInstance().getUid();
        FirebaseFirestore.getInstance().collection( "users" )
                .document(uid).get().addOnCompleteListener( new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    User user=task.getResult().toObject( User.class );
                    editName.setText( user.getName() );
                    editNumber.setText( user.getPhoneNumber() );
                }
            }
        } );
    }


    private void getData2() {
        String uid = FirebaseAuth. getInstance().getUid();
        FirebaseFirestore.getInstance().collection( "users" ).document(uid).addSnapshotListener( new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(documentSnapshot.exists()){
                    User user=documentSnapshot.toObject( User.class );
                    editName.setText( user.getName() );
                    editNumber.setText( user.getPhoneNumber() );
                }
            }
        } );
    }

    public void OnBaseClick(View view) {
String uid = FirebaseAuth. getInstance().getUid();
String name =editName.getText().toString().trim();
        User user = new User( name,"+996559662236",null );
        FirebaseFirestore.getInstance().collection( "users" )
                .document(uid)
                .set( user )
                .addOnCompleteListener( new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText( ProfileActivity.this, "Успешно", Toast.LENGTH_SHORT ).show();
                        }else {
                            Toast.makeText( ProfileActivity.this, "Ошибка", Toast.LENGTH_SHORT ).show();
                        }
                    }
                } );

    }
}
