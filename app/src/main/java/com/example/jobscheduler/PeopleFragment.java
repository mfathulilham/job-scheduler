package com.example.jobscheduler;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {

    EditText search;
    private ProgressBar progressBar;
    private TextView tvName;
    private RecyclerView recyclerView;
    private List<People> pList;
    //    private List<PeopleFilter> fList;
    private PeopleAdapter peopleAdapter;
    private String uid;
    ArrayList<String> names;

    private DatabaseReference mdatabase;
    FirebaseDatabase database;
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
        recyclerView = view.findViewById(R.id.rvPeople);
        progressBar = view.findViewById(R.id.progressBar);
        search = view.findViewById(R.id.edtSearch);
//        FloatingActionButton floatingActionButton = view.findViewById(R.id.fab);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        uid = firebaseUser.getUid();

//        showName();
        showRecyclerList();
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), AddPeopleActivity.class);
//                startActivity(intent);
//            }
//        });
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
//                Query query = database.getReference("Users").equalTo(editable.toString());
                database.getReference("Users").orderByChild("username").equalTo(editable.toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){
                            pList.clear();
                            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                                People people = postSnapshot.getValue(People.class);
                                pList.add(people);
                            }
                        }
                        peopleAdapter = new PeopleAdapter(pList, getContext());
                        recyclerView.setAdapter(peopleAdapter);
                        peopleAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getContext(), "No Username Found!",
                                Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
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
        pList = new ArrayList<>();
//        String key = database.getReference("Users").child(uid).child("People").orderByValue("true").getKey();
//        final String TAG = "ADAKAHH";
//        Log.i(TAG, "KEY : " + key);
        database.getReference("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot1) {
                for (DataSnapshot dataSnapshot2 : dataSnapshot1.getChildren()) {
                    String key = dataSnapshot2.getKey();
//                            final String TAG = "ADAKAHH";
//        Log.i(TAG, "KEY : " + key);
                    if (!key.equals(uid)){
                        People people = dataSnapshot2.getValue(People.class);
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

            }
        });
    }
}