package com.example.jobscheduler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView nav=findViewById(R.id.navigation);

        nav.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(savedInstanceState==null){
            nav.setSelectedItemId(R.id.task);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseUser != null)
            firebaseUser = mAuth.getCurrentUser();
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
                        case R.id.task:
                            fragment = new TasksFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container_layout,fragment,fragment.getClass().getSimpleName())
                                    .commit();
                            return true;
                        case R.id.profile:
                            fragment = new ProfileFragment();
                            getSupportFragmentManager().beginTransaction()
                                    .replace(R.id.container_layout,fragment,fragment.getClass().getSimpleName())
                                    .commit();
                            return true;
                    }
                    return false;
                }
            };
}
