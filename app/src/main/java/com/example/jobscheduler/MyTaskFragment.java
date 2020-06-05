package com.example.jobscheduler;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyTaskFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    List<MyTask> tList;
    MyTaskAdapter myTaskAdapter;

    public MyTaskFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_task, container, false);

        recyclerView = view.findViewById(R.id.rvMyTask);
        myTaskAdapter = new MyTaskAdapter(tList,getContext());
        recyclerView.setAdapter(myTaskAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tList = new ArrayList<>();
        tList.add(new MyTask("Tandatangan Berkas", "Deskripsi : Tandatangan berkas di bagian...","Due Sat 17.30 PM"));
        tList.add(new MyTask("Pengambilan Form", "Deskripsi : Pengambilan form di bagian...","Due Thu 20.30 PM"));
        tList.add(new MyTask("Pengurusan KRS", "Deskripsi : Pengurusan KRS di bagian...","Due Fri 18.30 PM"));
        tList.add(new MyTask("Pengembalian Berkas", "Deskripsi : Pengembalian berkas di bagian...","Due Tue 09.00 AM"));
        tList.add(new MyTask("Mengajar", "Deskripsi : Mengajar di kelas...","Due Mon 11.00 AM"));
    }
}
