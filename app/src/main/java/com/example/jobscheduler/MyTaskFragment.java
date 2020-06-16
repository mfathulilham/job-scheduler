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
import com.google.firebase.auth.FirebaseUser;
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
    List<MyTask> tList;
    MyTaskAdapter myTaskAdapter;

    private DatabaseReference mdatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    private String uid;

    Integer numRandom = new Random().nextInt();

    public MyTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_task, container, false);


        recyclerView = view.findViewById(R.id.rvMyTask);
        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        uid= firebaseUser.getUid();
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
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseUser = mAuth.getCurrentUser();
    }

    private void showRecyclerList() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tList = new ArrayList<>();

        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //set code to retrieve data and replace Layout
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    MyTask myTask = dataSnapshot1.getValue(MyTask.class);
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
