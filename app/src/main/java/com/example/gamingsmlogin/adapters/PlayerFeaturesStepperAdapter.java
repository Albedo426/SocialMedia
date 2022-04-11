package com.example.gamingsmlogin.adapters;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.classes.PlayerFeature;
import com.example.gamingsmlogin.classes.StepperClass;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.List;

public class PlayerFeaturesStepperAdapter extends AbstractFragmentStepAdapter {

    //Oyuncu özellikleri ile ilgili stepperları düzenliyor.

    List<StepperClass> stepperList;

    // constructor

    public PlayerFeaturesStepperAdapter(FragmentManager fm, Context context, List<StepperClass> stepperList) {
        super(fm, context);
        this.stepperList = stepperList;
    }

    // stepleri oluşturduk.

    @Override
    public Step createStep(int position) {
        StepperClass step = stepperList.get(position);
        Bundle b = new Bundle();
        b.putInt("CURRENT_STEP_POSITION_KEY", position);
        step.setArguments(b);
        return step;
    }

    //step sayısını tutuyor.

    @Override
    public int getCount() {
        return stepperList.size();
    }

    //stepi görüntüleyen kısım ayrıca başlığı vs özellikler değiştirilebiliyor.

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        return new StepViewModel.Builder(context)
                .setTitle(R.string.app_name) //can be a CharSequence instead
                .create();
    }

    //Oyuncu özelliklerini step step taşıyor.

    PlayerFeature tempPF = new PlayerFeature();
    @Override
    public Step findStep(int position) {
        if (position != 0){
            switch (position){
                case 1:
                    tempPF.setgUserName(stepperList.get(position-1).globalpf.getgUserName());
                    break;
                case 2:
                    tempPF.setOpsiyon1(stepperList.get(position-1).globalpf.getOpsiyon1());
                    tempPF.setOpsiyon2(stepperList.get(position-1).globalpf.getOpsiyon2());
                    break;
            }
            stepperList.get(position).globalpf = tempPF;
        }
        return super.findStep(position);
    }
}
