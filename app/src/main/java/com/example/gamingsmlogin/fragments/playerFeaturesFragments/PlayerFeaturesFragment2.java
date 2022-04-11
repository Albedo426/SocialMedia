package com.example.gamingsmlogin.fragments.playerFeaturesFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.classes.PlayerFeature;
import com.example.gamingsmlogin.classes.StepperClass;
import com.example.gamingsmlogin.management.PlayerFeaturesManagement;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.stepstone.stepper.StepperLayout;

public class PlayerFeaturesFragment2 extends StepperClass {

    //oyun özellikleri seçerken görüntülenecek fragment

    private AutoCompleteTextView pf2ChampText, pf2RoleText;
    TextInputLayout til1,til2;
    int gameID;

    public PlayerFeaturesFragment2(int gameID){
        this.gameID = gameID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player_features_2,container,false);

        til1 = v.findViewById(R.id.pf2ChampTIL);
        til2 = v.findViewById(R.id.pf2RoleTIL);
        pf2ChampText = v.findViewById(R.id.pf2ChampText);
        pf2RoleText = v.findViewById(R.id.pf2RoleText);

        PlayerFeaturesManagement pfm = new PlayerFeaturesManagement(getContext());

        //playerFeaturesManagerdan hangi oyunun seçildiğini ve opsiyonlarını aldık.

        pfm.getGameObj(gameID,pf2ChampText,pf2RoleText,til1,til2);

        return v;
    }

    //ileri tuşuna basıldığı zaman çalışacak

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

        String option1Text = String.valueOf(pf2ChampText.getText());
        String option2Text = String.valueOf(pf2RoleText.getText());

        if(!option1Text.equals("") && !option2Text.equals("")){
            getPFData(new PlayerFeature());

            //stepperClass içindeki check true yapıldı.

            super.check = true;
            super.onNextClicked(callback);
        }else{
            Snackbar.make(requireView(),"Hop hemşerim nereye.",Snackbar.LENGTH_LONG)
                    .setAction("tamam", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    })
                    .show();
        }
    }

    //PlayerFeaturesStepperAdapter içindeki globalPf doldurmak için StepperClassdaki getPFData fonksiyonunu çağırıyoruz.

    @Override
    public PlayerFeature getPFData(PlayerFeature pf) {
        pf.setOpsiyon1(String.valueOf(pf2ChampText.getText()));
        pf.setOpsiyon2(String.valueOf(pf2RoleText.getText()));
        return super.getPFData(pf);
    }
}
