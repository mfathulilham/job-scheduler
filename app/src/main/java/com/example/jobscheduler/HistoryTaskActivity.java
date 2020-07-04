package com.example.jobscheduler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HistoryTaskActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<MyTask> tList;
    private HistoryAdapter historyAdapter;

    private String dateString;
    private SimpleDateFormat formatter;
    private SimpleDateFormat dateformatter;
    private DatabaseReference mdatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_task);

        recyclerView = findViewById(R.id.rvHistory);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String uid = firebaseUser.getUid();
        mdatabase = database.getReference("MyTask").child(uid);
        getDateTime();
//        Log.i("Tanggal",dateString);
        showRecyclerList();
    }

    private void showRecyclerList() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tList = new ArrayList<>();
        mdatabase.orderByChild("date").endAt(dateString).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //set code to retrieve data and replace Layout
                tList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    MyTask myTask = dataSnapshot1.getValue(MyTask.class);
//                    String tanggal = myTask.getDate();
//                    if ((tanggal > dateString)){
//
//                    }
                    tList.add(myTask);
                }
                historyAdapter = new HistoryAdapter(tList, HistoryTaskActivity.this);
                recyclerView.setAdapter(historyAdapter);
                historyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Oops.. No data", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getDateTime() {
        Calendar calender = Calendar.getInstance();

//        formatter = new SimpleDateFormat("HH:mm", Locale.getDefault());
//        timeString = formatter.format(new Date(calender.getTimeInMillis()));

        dateformatter = new SimpleDateFormat("d MMM yyyy",Locale.getDefault());
        dateString = dateformatter.format(new Date(calender.getTimeInMillis()));
    }
}
