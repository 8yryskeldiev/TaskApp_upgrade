package com.example.taskapp;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.taskapp.models.User;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;




public class ProfileActivity extends AppCompatActivity  {
    private static final int ACTIVITY_SELECT_IMAGE = 1;
    ImageView avatar;
    Button save;
    String name;
    String number;
    EditText editName;
    EditText editNumber;
    Uri downloadUrl;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );
        save=findViewById( R.id.profile_save );
        editName=findViewById( R.id.profile_name );
        editNumber=findViewById( R.id.profile_number );
        progressBar=findViewById( R.id.progress_circle );
        progressBar.setVisibility( View.GONE );
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
                Glide.with( this ).load( data.getData() ).circleCrop().into( avatar );
                upload(data.getData());



            }
        }
    }

    private void upload(Uri data) {
        progressBar.setVisibility( View.VISIBLE );
        String uid = FirebaseAuth. getInstance().getUid();
       final StorageReference reference= FirebaseStorage.getInstance().getReference().child( uid+".jpg" );
       UploadTask uploadTask= reference.putFile( data );
        uploadTask.continueWithTask( new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                return reference.getDownloadUrl();
            }
        } ).addOnCompleteListener( new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if(task.isSuccessful()){

                     downloadUrl=task.getResult();
                    Log.e( "Profile","downloadUr: "+ downloadUrl );
                    updateAvatarInfo(downloadUrl);
                }    else {
progressBar.setVisibility( View.GONE );
                    Toast.makeText( ProfileActivity.this, "Error", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }

    private void updateAvatarInfo(Uri downloadUrl) {
        String uid = FirebaseAuth. getInstance().getUid();
        FirebaseFirestore.getInstance().collection( "users" ).document(uid).update( "avatar",downloadUrl.toString() ).addOnCompleteListener( new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressBar.setVisibility( View.GONE );
                if(task.isSuccessful()){
                    Toast.makeText( ProfileActivity.this, "Successful", Toast.LENGTH_SHORT ).show();
                }    else {
                    Toast.makeText( ProfileActivity.this, "Error", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
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
                    showImage(user.getAvatar());
                }
            }
        } );
    }

    private void showImage(String avatars) {
        Glide.with( this ).load( avatars ).circleCrop().into( avatar );

    }

    public void OnBaseClick(View view) {
String uid = FirebaseAuth. getInstance().getUid();
 name =editName.getText().toString().trim();
 number=editNumber.getText().toString().trim();
        User user = new User( name,number,downloadUrl.toString() );
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
