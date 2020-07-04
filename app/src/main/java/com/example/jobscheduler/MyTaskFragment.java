package com.example.jobscheduler;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
//import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyTaskFragment extends Fragment {


    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private List<MyTask> tList;
    private MyTaskAdapter myTaskAdapter;

    private DatabaseReference mdatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    private String dateString;
    private SimpleDateFormat dateformatter;
//    Integer numRandom = new Random().nextInt();

    public MyTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_task, container, false);

        progressBar = view.findViewById(R.id.progressBar);
        Button btnShow = view.findViewById(R.id.btnShow);
        recyclerView = view.findViewById(R.id.rvMyTask);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String uid = firebaseUser.getUid();
        mdatabase = database.getReference("MyTask").child(uid);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab);

        showRecyclerList();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddMyTaskActivity.class);
                startActivity(intent);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HistoryTaskActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (firebaseUser != null)
            firebaseUser = mAuth.getCurrentUser();
    }

    private void showRecyclerList() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tList = new ArrayList<>();
        getDateTime();
        mdatabase.orderByChild("date").startAt(dateString).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //set code to retrieve data and replace Layout
                tList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    MyTask myTask = dataSnapshot1.getValue(MyTask.class);
                    tList.add(myTask);
                }
                myTaskAdapter = new MyTaskAdapter(tList, getContext());
                recyclerView.setAdapter(myTaskAdapter);
                myTaskAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Oops.. No data", Toast.LENGTH_SHORT).show();
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
