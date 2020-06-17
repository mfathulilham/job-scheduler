package com.example.jobscheduler;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PeopleFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    List<People> pList;
    PeopleAdapter peopleAdapter;
    public PeopleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_people, container, false);
        recyclerView = view.findViewById(R.id.rvPeople);
        peopleAdapter = new PeopleAdapter(pList,getContext());
        recyclerView.setAdapter(peopleAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pList = new ArrayList<>();
        pList.add(new People(R.drawable.ic_user_3, "Fathulzz"));
        pList.add(new People(R.drawable.ic_user_1, "Lisa"));
        pList.add(new People(R.drawable.ic_user_3, "Ali"));
        pList.add(new People(R.drawable.ic_user_2, "Rose"));
        pList.add(new People(R.drawable.ic_user_1, "Jennie"));
        pList.add(new People(R.drawable.ic_user_2, "Soodam"));
        pList.add(new People(R.drawable.ic_user_3, "G-dragon"));
        pList.add(new People(R.drawable.ic_user_3, "Taeyang"));


    }
}
