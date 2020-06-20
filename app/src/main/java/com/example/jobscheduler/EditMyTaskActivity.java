package com.example.jobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class EditMyTaskActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtTitle, edtDeskripsi, edtDue;
    Button btnAdd, btnCancel;
    private DatabaseReference mdatabase;
    FirebaseUser firebaseUser;
    private String keyMyTask;

    String title, desc, due;

    public EditMyTaskActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_task);
        edtTitle = findViewById(R.id.edtTitle);
        edtDeskripsi = findViewById(R.id.edtDeskripsi);
        edtDue = findViewById(R.id.edtDue);
        btnAdd = findViewById(R.id.btnEdit);
        btnCancel = findViewById(R.id.btnCancel);

        edtTitle.setText(getIntent().getStringExtra("titleMyTask"));
        edtDeskripsi.setText(getIntent().getStringExtra("DescMyTask"));
        edtDue.setText(getIntent().getStringExtra("DueMyTask"));
        keyMyTask = getIntent().getStringExtra("keyMyTask");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //    String keyMyTask = Integer.toString(numRandom);
        String uid = firebaseUser.getUid();
        mdatabase = database.getReference("MyTask").child(uid).child("MyTask" + keyMyTask);

        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnEdit: {
                title = edtTitle.getText().toString().trim();
                desc = edtDeskripsi.getText().toString().trim();
                due = edtDue.getText().toString().trim();
                MyTask myTask = new MyTask(title, desc, due, keyMyTask);
//                mdatabase.child(uid).child("MyTask" + numRandom).setValue(myTask);
                mdatabase.setValue(myTask);
                finish();
            }
            case R.id.btnCancel: {
                finish();
            }
        }
    }
}
