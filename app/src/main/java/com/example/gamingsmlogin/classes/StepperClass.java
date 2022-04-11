package com.example.gamingsmlogin.classes;

import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

public class StepperClass extends Fragment implements Step, BlockingStep{

    //Burada stepperAdapterlerın düzenlediği stepler için özellikler bulunuyor ayrıca globalUser ve globalPf burada.
    int i = 0;
    public User globalUser = new User();
    public PlayerFeature globalpf = new PlayerFeature();

    public StepperClass() {
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {
    }

    // hızlı bir şekilde ileriye basıldığı zaman direkt olarak geçme sorununu çözmek için bir kontrol.

    public boolean check = true;

    //herhangi bir stepte ileri butonuna basıldığında neler olacağını ayarlıyor.

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //çalıştı ve check = false oldu ta ki biz çalıştığı yerde super.check = true gönderene kadar.

                if (check){
                    callback.goToNextStep();
                }
                check = false;
                Log.e("check", String.valueOf(check));
            }
        },200);
    }

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
    }

    //stepperda geri tuşuna basıldığı zaman ne olacağına karar veriyor.

    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.goToPrevStep();
            }
        },200);
    }

    //createAccStepperAdapter ve PlayerFeaturesStepperAdapter içindeki globalUser ve globalPfleri döndüren fonksiyonlar bunlar.

    public User getUserData(User user){
        globalUser = user;
        return globalUser;
    }

    public PlayerFeature getPFData(PlayerFeature pf){
        globalpf = pf;
        return globalpf;
    }


}
