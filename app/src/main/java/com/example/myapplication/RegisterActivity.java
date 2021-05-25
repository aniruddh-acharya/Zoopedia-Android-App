package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity

{

    EditText musername, mpassword,mcpassword;
    Button mRegister;
    TextView mLoginRedirect;
    FirebaseAuth fAuth;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        musername = findViewById(R.id.username);
        mpassword = findViewById(R.id.password);
        mcpassword = findViewById(R.id.confirmpassword);
        mRegister = findViewById(R.id.RegisterButton);
        mLoginRedirect = findViewById(R.id.loginlink);

        fAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressbar);

        if(fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }

        mLoginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String username = musername.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                String confirmpassword = mcpassword.getText().toString().trim();

                if(TextUtils.isEmpty(username))
                {
                    musername.setError("Username is Required!");
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    musername.setError("Password is Required!");
                    return;
                }

                if(password.length()<6)
                {
                    mpassword.setError("Password must be greater than 6 characters!");
                    return;
                }

                if(!password.equals(confirmpassword))
                {
                    mcpassword.setError("Passwords Don't Match!");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);


                fAuth.createUserWithEmailAndPassword(username,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task)
                      {
                          if(task.isSuccessful())
                          {
                              Toast.makeText(RegisterActivity.this,"User Created.",Toast.LENGTH_SHORT).show();
                              startActivity(new Intent(getApplicationContext(),MainActivity.class));
                          }

                          else
                          {
                              Toast.makeText(RegisterActivity.this, "ERROR !"+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                              progressBar.setVisibility(View.GONE);
                          }
                      }
                  }
                );
            }
        });
    }
}