package com.example.baseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BaseActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        bottomNavigationView.findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavMethod);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new HomeFragment()).commit();

    }

    //click event to open comments
    //add explicit intent for comments activity
    public void openComments(View view) {
        Intent intent = new Intent(this, CommentsFragment.class);
        startActivity(intent);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavMethod =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    Fragment fragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.home_icon:
                            fragment = new HomeFragment();
                            break;
                        case R.id.add_icon:
                            fragment = new AddPost();
                            break;
                    }
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container, fragment)
                            .commit();
                    //when menu icon is selected in switch-case, it will store in fragment.
                    return true;
                }
            };



}
