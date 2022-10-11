package com.akhilesh.internshala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    FirebaseAuth auth;
    EditText EmailBox,PasswordBox,ConfirmPasswordBox;
    Button SignupBtn;
    TextView SigninBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        auth=FirebaseAuth.getInstance();

        EmailBox=findViewById(R.id.EmailBox);
        PasswordBox=findViewById(R.id.PasswordBox);
        ConfirmPasswordBox=findViewById(R.id.ConfirmPasswordBox);
        SigninBtn=findViewById(R.id.SigninBtn);
        SignupBtn=findViewById(R.id.SignupBtn);

        SignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,pass,confirm;
                email=EmailBox.getText().toString();
                pass=PasswordBox.getText().toString();
                confirm=ConfirmPasswordBox.getText().toString();

                auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "Account is created", Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(SignupActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        SigninBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this,MainActivity.class));
            }
        });
    }
}