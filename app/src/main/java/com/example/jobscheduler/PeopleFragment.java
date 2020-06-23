package com.example.jobscheduler;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {

    private ProgressBar progressBar;
    private TextView tvName;
    private RecyclerView recyclerView;
    private List<People> pList;
    private PeopleAdapter peopleAdapter;
//    private String uid;


    private DatabaseReference mdatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    public PeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_people, container, false);
//        tvName = view.findViewById(R.id.tvName);
        recyclerView = view.findViewById(R.id.rvPeople);
        progressBar = view.findViewById(R.id.progressBar);
//        peopleAdapter = new PeopleAdapter(pList,getContext());
//        recyclerView.setAdapter(peopleAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        uid = firebaseUser.getUid();
        mdatabase = database.getReference("Users");

//        showName();
        showRecyclerList();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (firebaseUser != null)
                firebaseUser = mAuth.getCurrentUser();
    }

    //    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
////        pList = new ArrayList<>();
////        pList.add(new People(R.drawable.ic_user_3, "Fathulzz"));
////        pList.add(new People(R.drawable.ic_user_1, "Lisa"));
////        pList.add(new People(R.drawable.ic_user_3, "Ali"));
////        pList.add(new People(R.drawable.ic_user_2, "Rose"));
////        pList.add(new People(R.drawable.ic_user_1, "Jennie"));
////        pList.add(new People(R.drawable.ic_user_2, "Soodam"));
////        pList.add(new People(R.drawable.ic_user_3, "G-dragon"));
////        pList.add(new People(R.drawable.ic_user_3, "Taeyang"));
//
//
//
//    }

    private void showRecyclerList() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        pList = new ArrayList<>();

        mdatabase.orderByChild("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //set code to retrieve data and replace Layout
                pList.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    if (dataSnapshot1.exists()){
//                        People people = dataSnapshot1.child(uid).getValue(People.class);
                        People people = dataSnapshot1.getValue(People.class);
                        pList.add(people);
                    }
                }
                peopleAdapter = new PeopleAdapter(pList, getContext());
                recyclerView.setAdapter(peopleAdapter);
                peopleAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Oops... No One Here", Toast.LENGTH_SHORT).show();
            }
        });

    }

//    private void showName() {
//        mdatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                //set code to retrieve data and replace Layout
//                tvName.setText(dataSnapshot.child("username").getValue(String.class));
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getContext(), "Oops.. No data", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
