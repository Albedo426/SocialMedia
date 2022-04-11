package com.example.gamingsmlogin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gamingsmlogin.adapters.PlayerFeaturesStepperAdapter;
import com.example.gamingsmlogin.classes.StepperClass;
import com.example.gamingsmlogin.fragments.playerFeaturesFragments.PlayerFeaturesFragment1;
import com.example.gamingsmlogin.fragments.playerFeaturesFragments.PlayerFeaturesFragment2;
import com.example.gamingsmlogin.fragments.playerFeaturesFragments.PlayerFeaturesFragment3;
import com.stepstone.stepper.StepperLayout;

import java.util.ArrayList;
import java.util.List;

public class PlayerFeaturesActivity extends AppCompatActivity {
    Toolbar toolbarPlayerFeatures;
    StepperLayout stepperLayoutPlayerFeatures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_features);

        Intent i = getIntent();
        int gameID = i.getIntExtra("gameID",0);
        Log.e("playerFeaturesAc",String.valueOf(gameID));

        List<StepperClass> list = new ArrayList<>();
        list.add(new PlayerFeaturesFragment1(gameID));
        list.add(new PlayerFeaturesFragment2(gameID));
        list.add(new PlayerFeaturesFragment3(gameID,this));
        FragmentManager fm = getSupportFragmentManager();

        toolbarPlayerFeatures = findViewById(R.id.toolbarPlayerFeatures);
        stepperLayoutPlayerFeatures = findViewById(R.id.stepperLayoutPlayerFeatures);
        stepperLayoutPlayerFeatures.setAdapter(new PlayerFeaturesStepperAdapter(fm,this,list));

        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frameLayoutPlayerFeatures,new PlayerFeaturesFragment1(gameID)).commit();

        toolbarPlayerFeatures.setNavigationOnClickListener(view -> {
            startActivity(new Intent(PlayerFeaturesActivity.this, LogSignInActivity.class));
            finish();
        });

    }
}