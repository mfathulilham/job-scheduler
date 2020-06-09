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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private DatabaseReference mdatabase;
    private FirebaseDatabase database;
    private EditText editUser, editEmail, editPass, editPhone, editOccup, editAddress;
    private String username,email, password,phone,occup,address;
    private ProgressDialog progressDialog;
//    private static final String USER = "user";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize Firebase Auth
        database = FirebaseDatabase.getInstance();
        mdatabase = database.getReference("user");
        mAuth = FirebaseAuth.getInstance();

        editUser = findViewById(R.id.edtUsername);
        editEmail = findViewById(R.id.edtEmail);
        editPass = findViewById(R.id.edtPass);
        editPhone = findViewById(R.id.edtPhone);
        editOccup = findViewById(R.id.edtOccupation);
        editAddress = findViewById(R.id.edtAddress);

        TextView tvLogin = findViewById(R.id.tvLogin);
        Button register = findViewById(R.id.btnRegister);

        register.setOnClickListener(this);
        tvLogin.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null){

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister: {
                userRegister();
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

    private void userRegister() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Wait a second");
        progressDialog.show();

        email = editEmail.getText().toString().trim();
        password = editPass.getText().toString().trim();
        username = editUser.getText().toString().trim();
        phone = editPhone.getText().toString().trim();
        occup = editOccup.getText().toString().trim();
        address = editAddress.getText().toString().trim();
        if (email.isEmpty() || password.isEmpty() || username.isEmpty() || phone.isEmpty() || occup.isEmpty() || address.isEmpty()){
            if (email.isEmpty()){
                editEmail.setError("Insert email first");
            }
            if (password.isEmpty()){
                editPass.setError("Insert password first");
            }
            if (username.isEmpty()){
                editUser.setError("Insert username first");
            }
            if (phone.isEmpty()){
                editPhone.setError("Insert phone first");
            }
            if (occup.isEmpty()){
                editOccup.setError("Insert occup first");
            }
            if (address.isEmpty()){
                editAddress.setError("Insert address first");
            }
//            Toast.makeText(RegisterActivity.this, "Isi terlebih dahulu dengan lengkap",
//                    Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
        }
        else {
            userAuth();
        }
    }

    private void userAuth() {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = new User(username,email,password,phone,occup,address);
                            database.getReference("Users").child(mAuth.getCurrentUser().getUid()).setValue(user);
                            finish();
                            Toast.makeText(RegisterActivity.this, "Registration Success",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            if (!(isValid(email))){
                                Toast.makeText(RegisterActivity.this, "Your email isn't valid!",
                                        Toast.LENGTH_SHORT).show();
                            }
                            else if (password.length() <= 6) {
                                Toast.makeText(RegisterActivity.this, "Password should at least 6 character",
                                        Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(RegisterActivity.this, "Registration failed Please Check Your Connection",
                                        Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        // ...
                    }
                });
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
