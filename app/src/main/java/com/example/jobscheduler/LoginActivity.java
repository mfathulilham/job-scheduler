package com.example.jobscheduler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText edtEmail,edtPass;
    private AlertDialog alertDialog;
    private Button login;
    private String email, pass;
    private FirebaseAuth mAuth;
    private TextView tvRegis;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);

        mAuth = FirebaseAuth.getInstance();
        login = findViewById(R.id.btnLogin);

        tvRegis = findViewById(R.id.tvRegister);

        login.setOnClickListener(this);
        tvRegis.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin : {
                userLogin();
            }
            break;
            case R.id.tvRegister: {
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    private void userLogin() {
        email = edtEmail.getText().toString().trim();
        pass = edtPass.getText().toString().trim();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Wait a second");
        progressDialog.show();

        if (email.isEmpty() || pass.isEmpty()){
            if (email.isEmpty()){
                edtEmail.setError("Insert username first");
            }
            if (pass.isEmpty()){
                edtPass.setError("Insert password first");
            }
            progressDialog.dismiss();
        }
        else {

//            if (!(email.isEmpty() && pass.isEmpty())) {
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Login Success..", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Login failed, check again!",
                                        Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.dismiss();
                            // ...
                        }
                    });
        }
//        else {
//            edtEmail.setError("Insert username first");
//            edtPass.setError("Insert password first");
//            progressDialog.dismiss();
//        }
    }

}
