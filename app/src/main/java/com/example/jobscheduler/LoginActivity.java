package com.example.jobscheduler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText edtEmail,edtPass;
//    private AlertDialog alertDialog;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
//    private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressBar = findViewById(R.id.progressBar);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        Button login = findViewById(R.id.btnLogin);
        TextView tvRegis = findViewById(R.id.tvRegister);
        login.setOnClickListener(this);
        tvRegis.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if (user != null){
//                    String UserId = firebaseUser.getUid();
//                    firebaseUser = user;
//                    Toast.makeText(LoginActivity.this,"User ID\n"+UserId ,Toast.LENGTH_SHORT).show();
//                }
//                else Toast.makeText(LoginActivity.this,"No Id Got",Toast.LENGTH_SHORT).show();
//            }
//        };
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        firebaseUser = mAuth.getCurrentUser();
//    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseUser != null)
        firebaseUser = mAuth.getCurrentUser();
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

        progressBar.setVisibility(View.VISIBLE);
        String email = edtEmail.getText().toString().trim();
        String pass = edtPass.getText().toString().trim();
//        progressBar = new ProgressBar(this);
//        progressBar.setMessage("Wait a second");
        if (email.isEmpty() || pass.isEmpty()){
            if (email.isEmpty()){
                edtEmail.setError("Insert username first");
            }
            if (pass.isEmpty()){
                edtPass.setError("Insert password first");
            }
            progressBar.setVisibility(View.GONE);
        }
        else {

//            if (!(email.isEmpty() && pass.isEmpty())) {
            mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
//                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Login Success..", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LoginActivity.this, "Login failed, check again!",
                                        Toast.LENGTH_SHORT).show();
                            }
                            progressBar.setVisibility(View.GONE);
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
