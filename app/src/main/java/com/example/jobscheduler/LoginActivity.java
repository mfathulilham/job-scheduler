package com.example.jobscheduler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText edtEmail,edtPass;
    private Button btnLogin;
    private String email,pass;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtEmail = findViewById(R.id.edtEmail);
        edtPass = findViewById(R.id.edtPass);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin : {
                userLogin();
            }
            break;
        }
    }

    private void userLogin() {
        email = edtEmail.getText().toString().trim();
        pass = edtPass.getText().toString().trim();
        if (email.equals("123") && pass.equals("123")){
                Intent intent = new Intent(this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText(getApplicationContext(),"Login succesfully",Toast.LENGTH_SHORT).show();
                this.startActivity(intent);
        } else
            {
            edtEmail.setError("Username or Password false");
            alertDialog.dismiss();
        }
    }

}
