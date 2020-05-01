package com.example.jobscheduler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment fragment;
                    switch (menuItem.getItemId()) {
                        case R.id.people:
                            fragment = new PeopleFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container_layout,fragment,fragment.getClass().getSimpleName())
                                    .commit();
                            return true;
//                        case R.id.tasks:
//                            fragment = new TasksFragment();
//                            getSupportFragmentManager().beginTransaction()
//                                    .replace(R.id.container_layout,fragment,fragment.getClass().getSimpleName())
//                                    .commit();
//                            return true;
                    }
                    return false;
                }
            };
}
