package com.example.jobscheduler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class AddPeopleActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtEmail;

    private DatabaseReference mdatabase;
    private FirebaseUser firebaseUser;
    FirebaseDatabase database;
    private String uid, email, username, occup, address, phone, key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_people);
        edtEmail = findViewById(R.id.edtEmail);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnCancel = findViewById(R.id.btnCancel);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        uid= firebaseUser.getUid();

        btnAdd.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnAdd : {
                email = edtEmail.getText().toString().trim();
                database.getReference("Users").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            key = dataSnapshot.getKey();
                            addData();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Sorry, The user hasn't available yet", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), "oopss...unexpected error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            case R.id.btnCancel : {
                finish();
            }
        }
    }

    public void addData(){
//        email = edtEmail.getText().toString().trim();
//        database.getReference("Users").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
        database.getReference("Users").child(key).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                username = dataSnapshot1.child("username").getValue(String.class);
                occup = dataSnapshot1.child("occup").getValue(String.class);
                address = dataSnapshot1.child("address").getValue(String.class);
                phone = dataSnapshot1.child("phone").getValue(String.class);

                People people = new People(username, occup, email, address, phone);
                database.getReference("People").child(uid).push().setValue(people);
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
