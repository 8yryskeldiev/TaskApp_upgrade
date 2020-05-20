package com.example.taskapp.logın;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.taskapp.MainActivity;
import com.example.taskapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class PhoneActivity extends AppCompatActivity {
    private TextView registration;
    private ImageView ricon;
    private ImageView aicon;
    private Button rbutton;
    private Button abutton;
    private  EditText reditText;
    private EditText aeditText;
private ProgressBar progressBar;
    private   String verificatoinId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        registration=findViewById(R.id.registration);
        ricon=findViewById(R.id.registration_icon);
        aicon=findViewById(R.id.approval_icon);
        rbutton=findViewById(R.id.registration_button);
        abutton=findViewById(R.id.approval_button);
        reditText=findViewById(R.id.masked_edit_text);
        aeditText=findViewById(R.id.edit_text);
      aicon.setVisibility(View.GONE);
      abutton.setVisibility(View.GONE);
      progressBar=findViewById(R.id.progressbar);
      aeditText.setVisibility(View.GONE);

callbacks= new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
    @Override
    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
        Log.e("phone","onVerificationCompleted");
        String code=phoneAuthCredential.getSmsCode();
        if(code!=null){
            progressBar.setVisibility(View.GONE);
        }
    }
    @Override
    public void onVerificationFailed(@NonNull FirebaseException e) {
        Log.e("phone","onVerificationFailed"+ e.getMessage());
    }
    @Override
    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
        super.onCodeSent(s, forceResendingToken);
        verificatoinId=s;
        Log.e("phone","onCodeSent");
    }
};
    }

    public void onContinueCkick(View view) {
       registration.setText("Approval");
       aicon.setVisibility(View.VISIBLE);
       abutton.setVisibility(View.VISIBLE);
       aeditText.setVisibility(View.VISIBLE);
       ricon.setVisibility(View.GONE);
       rbutton.setVisibility(View.GONE);
       reditText.setVisibility(View.GONE);
       progressBar.setVisibility(View.VISIBLE);
       String phoneNumber=reditText.getText().toString().trim();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNumber,60, TimeUnit.SECONDS,
                this, callbacks);

    }

    public void onApproveClick(View view) {
String code= aeditText.getText().toString().trim();
if(code.isEmpty()){
    aeditText.setError("Enter CODE...");
    aeditText.requestFocus();
    return;
}
verifyCode(code);
    }
    private void verifyCode(String code){
PhoneAuthCredential credential= PhoneAuthProvider.getCredential(verificatoinId,code);
signIn(credential);
    }

    private void signIn(PhoneAuthCredential credential) {
        FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener
                (new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(PhoneActivity.this, MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(PhoneActivity.this,"ERROR",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


}
