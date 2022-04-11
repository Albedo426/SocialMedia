package com.example.gamingsmlogin;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.gamingsmlogin.adapters.CrtAccountStepperAdapter;
import com.example.gamingsmlogin.classes.StepperClass;
import com.example.gamingsmlogin.fragments.crtAccountFragments.CrtAccountFragment1;
import com.example.gamingsmlogin.fragments.crtAccountFragments.CrtAccountFragment2;
import com.example.gamingsmlogin.fragments.crtAccountFragments.CrtAccountFragment3;
import com.example.gamingsmlogin.fragments.crtAccountFragments.CrtAccountFragment4;
import com.example.gamingsmlogin.fragments.crtAccountFragments.CrtAccountFragment5;
import com.stepstone.stepper.StepperLayout;

import java.util.ArrayList;
import java.util.List;

public class CrtAccountActivity extends AppCompatActivity{

   StepperLayout stepperLayoutCreateAcc;
   Toolbar toolbarCreateAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crt_account);

        List<StepperClass> list = new ArrayList<>();
        list.add(new CrtAccountFragment1());
        list.add(new CrtAccountFragment2());
        list.add(new CrtAccountFragment3());
        list.add(new CrtAccountFragment4());
        list.add(new CrtAccountFragment5(this));
        FragmentManager fm = getSupportFragmentManager();

        stepperLayoutCreateAcc = findViewById(R.id.stepperLayoutCreateAcc);
        toolbarCreateAcc = findViewById(R.id.toolbarCreateAcc);
        stepperLayoutCreateAcc.setAdapter(new CrtAccountStepperAdapter(fm, this,list));


        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.frameLayoutCreateAcc,new CrtAccountFragment1()).commit();

        toolbarCreateAcc.setNavigationOnClickListener(view -> {
            startActivity(new Intent(CrtAccountActivity.this, LogSignInActivity.class));
            finish();
        });

    }



}