package com.example.jobscheduler;

import androidx.annotation.NonNull;
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

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private EditText editEmail, editPass;
    private String email, password;
    private TextView tvLogin;
    private Button register;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        editEmail = findViewById(R.id.edtEmail);
        editPass = findViewById(R.id.edtPass);
        tvLogin = findViewById(R.id.tvLogin);
        register = findViewById(R.id.btnRegister);

        register.setOnClickListener(this);
        tvLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister: {
                userLogin();
            }
            break;
            case R.id.tvLogin: {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }

    }

    private void userLogin() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Wait a second");
        progressDialog.show();
        email = editEmail.getText().toString().trim();
        password = editPass.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()){
            if (email.isEmpty()){
                editEmail.setError("Insert username first");
            }
            if (password.isEmpty()){
                editPass.setError("Insert password first");
            }
            progressDialog.dismiss();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                finish();
                                Toast.makeText(RegisterActivity.this, "Registration Success",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails, display a message to the user.
                                if (!(isValid(email))){
                                    Toast.makeText(RegisterActivity.this, "Your email isn't valid!",
                                            Toast.LENGTH_SHORT).show();
                                }
                                else if (password.length() <= 6) {
                                    Toast.makeText(RegisterActivity.this, "Password should at least 6 character",
                                            Toast.LENGTH_SHORT).show();
                                } else Toast.makeText(RegisterActivity.this, "Registration failed Please Check Your Connection",
                                        Toast.LENGTH_SHORT).show();

                            }
                            progressDialog.dismiss();
                            // ...
                        }
                    });
        }

    }

    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
