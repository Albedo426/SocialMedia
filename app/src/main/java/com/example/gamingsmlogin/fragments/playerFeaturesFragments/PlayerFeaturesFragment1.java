package com.example.gamingsmlogin.fragments.playerFeaturesFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.classes.PlayerFeature;
import com.example.gamingsmlogin.classes.StepperClass;
import com.example.gamingsmlogin.management.PlayerFeaturesManagement;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.stepstone.stepper.StepperLayout;

public class PlayerFeaturesFragment1 extends StepperClass {

    //oyun özellikleri seçerken görüntülenecek fragment

    private TextInputEditText pf1UserNameTextInput;
    int gameID;
    PlayerFeaturesManagement pfm = new PlayerFeaturesManagement(getContext());

    public PlayerFeaturesFragment1(int gameID){
        this.gameID = gameID;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player_features_1,container,false);
        pf1UserNameTextInput = v.findViewById(R.id.pf1UserNameTextInput);

        //klavyede enter basıldığı zaman klavyeyi kapatacak
        pfm.keyListener(pf1UserNameTextInput,getActivity());

        return v;
    }

    //ileri tuşuna basıldığı zaman çalışacak

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

        String userName = String.valueOf(pf1UserNameTextInput.getText());
        if((!userName.equals(""))){
            getPFData(new PlayerFeature());
            //playerFeaturesManager içindeki check true yapıldı.

            pfm.check = true;
            pfm.playerFeaturesControl(gameID,userName,requireView(),pf1UserNameTextInput,callback);

            pfm.getGameUser(gameID,userName);
        }else{
            Snackbar.make(requireView(),"Hop hemşerim nereye.",Snackbar.LENGTH_LONG)
                    .setAction("tamam", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            pf1UserNameTextInput.setText("");
                            pf1UserNameTextInput.requestFocus();
                        }
                    })
                    .show();
        }
    }

    //PlayerFeaturesStepperAdapter içindeki globalPf doldurmak için StepperClassdaki getPFData fonksiyonunu çağırıyoruz.

    @Override
    public PlayerFeature getPFData(PlayerFeature pf) {
        pf.setgUserName(String.valueOf(pf1UserNameTextInput.getText()));
        return super.getPFData(pf);
    }
}
