package com.example.gamingsmlogin;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.gamingsmlogin.fragments.mainFragments.UserProfilFragment;

public class ViewUserForSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user_for_search);

        String userID = getIntent().getStringExtra("userIDForProf");
        loadFragment(new UserProfilFragment(1,userID));

    }
    private void loadFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.userProfileFrame,fragment);
        ft.commit();
    }
}