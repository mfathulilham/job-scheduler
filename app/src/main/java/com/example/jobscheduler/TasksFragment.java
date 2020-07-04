package com.example.jobscheduler;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class TasksFragment extends Fragment {


    public TasksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.fragment_tasks, container, false);
//        toolbar = view.findViewById(R.id.toolbar);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        //    private Toolbar toolbar;
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPagerAdapter.addFragment(new MyTaskFragment(), "My Task");
        viewPagerAdapter.addFragment(new AssignedTaskFragment(), "Assigned Task");

//        toolbar.inflateMenu(R.menu.tasks);
//        Menu menu = toolbar.getMenu();
//        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                Intent intent = new Intent(getActivity(), HistoryTaskActivity.class);
//                startActivity(intent);
//                return true;
//            }
//        });
//        ((AppCompatActivity)getActivity().)
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        return view;
    }

//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.tasks, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.abTasks:{
//                Intent intent = new Intent(getActivity(), AddMyTaskActivity.class);
//                startActivity(intent);
//                return true;
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
