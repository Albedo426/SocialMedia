package com.example.gamingsmlogin.adapters;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.classes.StepperClass;
import com.example.gamingsmlogin.classes.User;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.List;


public class CrtAccountStepperAdapter extends AbstractFragmentStepAdapter {

    //hesap oluşturma kısmındaki stepperı düzenlemek için.

    List<StepperClass> stepperLists;

    //constructor
    public CrtAccountStepperAdapter(FragmentManager fm, Context context, List<StepperClass> stepperLists) {
        super(fm, context);
        this.stepperLists = stepperLists;
    }

    //stepleri oluşturan kısım.
    @Override
    public Step createStep(int position) {
        StepperClass step = stepperLists.get(position);
        Bundle b = new Bundle();
        b.putInt("CURRENT_STEP_POSITION_KEY", position);
        step.setArguments(b);
        return step;
    }
    //step sayısını alıyor.
    @Override
    public int getCount() {
        return stepperLists.size();
    }

    //step ayarları başlık vs ayrıca gösteren kısım.
    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
        //Override this method to set Step title for the Tabs, not necessary for other stepper types
        return new StepViewModel.Builder(context)
                .setTitle(R.string.app_name) //can be a CharSequence instead
                .create();
    }


    //kullanıcı oluştururken her stepte alınan bilgiyi bir sonraki stepe taşıyor.

    User tempUser = new User();
    @Override
    public Step findStep(int position) {
        if (position != 0){
            switch (position){
                case 1:
                    tempUser.setName(stepperLists.get(position-1).globalUser.getName());
                    tempUser.setLastName(stepperLists.get(position-1).globalUser.getLastName());
                    break;
                case 2:
                    tempUser.setBirthDate(stepperLists.get(position-1).globalUser.getBirthDate());
                    break;
                case 3:
                    tempUser.setUserName(stepperLists.get(position-1).globalUser.getUserName());
                    break;
                case 4:
                    tempUser.setEmail(stepperLists.get(position-1).globalUser.getEmail());
                    tempUser.setPhone(stepperLists.get(position-1).globalUser.getPhone());
                    break;
            }
            stepperLists.get(position).globalUser = tempUser;
        }
        return super.findStep(position);
    }
}
