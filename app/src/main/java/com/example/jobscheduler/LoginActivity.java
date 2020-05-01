package com.example.jobscheduler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
        if (email.isEmpty()) {
            edtEmail.setError("Insert username first");
            alertDialog.dismiss();
        }
        if (pass.isEmpty()) {
            edtPass.setError("Insert password first");
            alertDialog.dismiss();
        }
        if (!(email.isEmpty() && pass.isEmpty())) {
            if (email.equals("123456") && pass.equals("123456")){
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
            }
        }
    }

}
