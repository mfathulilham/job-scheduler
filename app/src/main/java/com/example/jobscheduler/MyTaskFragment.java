package com.example.jobscheduler;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyTaskFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    ArrayList<MyTask> tList;
    MyTaskAdapter myTaskAdapter;
    DatabaseReference mdatabase;
    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    FloatingActionButton floatingActionButton;
    Integer numRandom = new Random().nextInt();

    public MyTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_task, container, false);

        database = FirebaseDatabase.getInstance();
        mdatabase = database.getReference("MyTask");
        mAuth = FirebaseAuth.getInstance();

        recyclerView = view.findViewById(R.id.rvMyTask);
        floatingActionButton = view.findViewById(R.id.fab);

//        myTaskAdapter = new MyTaskAdapter(tList,getContext());
//        recyclerView.setAdapter(myTaskAdapter);

//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        showRecyclerList();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AddMyTaskActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void showRecyclerList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tList = new ArrayList<MyTask>();

        //Retrieve  Data from Firebase
//        mdatabase = FirebaseDatabase.getInstance().getReference().child("MyTask");
        mdatabase.child(mAuth.getCurrentUser().getUid());
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //set code to retrieve data and replace Layout
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    MyTask myTask = dataSnapshot1.getValue(MyTask.class);
//                    String uid= mAuth.getCurrentUser().getUid();
//                    MyTask myTask = dataSnapshot1.child(uid).getValue(MyTask.class);
                    tList.add(myTask);
                }
                myTaskAdapter = new MyTaskAdapter(tList, getContext());
                recyclerView.setAdapter(myTaskAdapter);
                myTaskAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Oops.. No data", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
