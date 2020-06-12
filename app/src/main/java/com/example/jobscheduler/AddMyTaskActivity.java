package com.example.jobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class AddMyTaskActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtTitle, edtDeskripsi, edtDue;
    Button btnAdd, btnCancel;
    String title, desc, due;

    private FirebaseAuth mAuth;
    private DatabaseReference mdatabase;
    private FirebaseDatabase database;
    Integer numRandom = new Random().nextInt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_my_task);
        database = FirebaseDatabase.getInstance();
        mdatabase = database.getReference("MyTask");
        mAuth = FirebaseAuth.getInstance();

        edtTitle = findViewById(R.id.edtTitle);
        edtDeskripsi = findViewById(R.id.edtDeskripsi);
        edtDue = findViewById(R.id.edtDue);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAdd: {
                title = edtTitle.getText().toString().trim();
                desc = edtDeskripsi.getText().toString().trim();
                due = edtDue.getText().toString().trim();
                MyTask myTask = new MyTask(title, desc, due);
                mdatabase.child(mAuth.getCurrentUser().getUid()).child("MyTask" + numRandom).setValue(myTask);
                finish();
            }
            case R.id.btnCancel: {
                finish();
            }

        }
    }
}