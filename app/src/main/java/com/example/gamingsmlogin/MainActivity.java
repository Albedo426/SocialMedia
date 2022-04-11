package com.example.gamingsmlogin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gamingsmlogin.fragments.mainFragments.HomePageFragment;
import com.example.gamingsmlogin.fragments.mainFragments.PostShareFragment;
import com.example.gamingsmlogin.fragments.mainFragments.UserProfilFragment;
import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    UserDAO dao = new UserDAO();
    DbHelper dbHelper = new DbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavMain);
        loadFragment(new HomePageFragment());
        if(!dao.usersCount(dbHelper)){
           startActivity(new Intent(this, LogSignInActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment;
            switch (item.getItemId()){
                case R.id.tab_home:
                    fragment = new HomePageFragment();
                    loadFragment(fragment);
                    break;
                case R.id.tab_post:
                    fragment = new PostShareFragment();
                    loadFragment(fragment);
                    break;
                case R.id.tab_user:
                    fragment = new UserProfilFragment(0,dao.getSQLUser(dbHelper).get(0).getUserID().toString());
                    loadFragment(fragment);
                    break;
            }
            return false;
        });
        //CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
        //layoutParams.setBehavior(new BottomNavigationBehavior());
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

}