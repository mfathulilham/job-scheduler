package com.example.jobscheduler;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private TextView tvNama,tvOccup,tvAddrress,tvEmail,tvPhone,pass;

    private DatabaseReference mdatabase;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        tvNama = view.findViewById(R.id.nameProfile);
        tvOccup = view.findViewById(R.id.occupProfile);
        tvAddrress = view.findViewById(R.id.addressProfile);
        tvEmail = view.findViewById(R.id.emailProfile);
        tvPhone = view.findViewById(R.id.phoneProfile);
        pass = view.findViewById(R.id.passProfile);
        Button btnLogout = view.findViewById(R.id.btnLogout);

        mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String uid = firebaseUser.getUid();

        mdatabase = database.getReference("Users").child(uid);

        showProfile();
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                if (getActivity() != null){
                    getActivity().finish();
                }
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseUser = mAuth.getCurrentUser();
    }

    private void showProfile() {
        mdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //set code to retrieve data and replace Layout
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    if (dataSnapshot1.exists()){
                        tvNama.setText(dataSnapshot1.child("username").getValue(String.class));
                        tvOccup.setText(dataSnapshot1.child("occup").getValue(String.class));
                        tvEmail.setText(dataSnapshot1.child("email").getValue(String.class));
                        tvAddrress.setText(dataSnapshot1.child("address").getValue(String.class));
                        tvPhone.setText(dataSnapshot1.child("phone").getValue(String.class));
                        pass.setText(dataSnapshot1.child("pass").getValue(String.class));

//                        Profile profile = dataSnapshot1.getValue(Profile.class);
//                        tvNama.setText(profile.getUsername());
//                        tvOccup.setText(profile.getOccup());
//                        tvEmail.setText(profile.getEmail());
//                        tvAddrress.setText(profile.getAddress());
//                        tvPhone.setText(profile.getPhone());
//                        Toast.makeText(getContext(), "data showed", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Oops.. No data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
