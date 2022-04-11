package com.example.gamingsmlogin.fragments.playerFeaturesFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gamingsmlogin.GameSelectActivity;
import com.example.gamingsmlogin.LoginActivity;
import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.classes.GameOptionController;
import com.example.gamingsmlogin.classes.StepperClass;
import com.example.gamingsmlogin.classes.User;
import com.example.gamingsmlogin.management.PlayerFeaturesManagement;
import com.example.gamingsmlogin.management.UsersManagement;
import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.stepstone.stepper.StepperLayout;

public class PlayerFeaturesFragment3 extends StepperClass {

    //oyun özellikleri seçerken görüntülenecek fragment

    private TextInputEditText pf3AboutTextInput;
    int gameID;
    Context context;

    PlayerFeaturesManagement pfm = new PlayerFeaturesManagement(getContext());
    UsersManagement um = new UsersManagement(getContext());


    public PlayerFeaturesFragment3(int gameID,Context context){
        this.gameID = gameID;
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player_features_3,container,false);
        pf3AboutTextInput = v.findViewById(R.id.pf3AboutTextInput);

        //klavyede enter basıldığı zaman klavyeyi kapatacak

        pfm.keyListener(pf3AboutTextInput, getActivity());
        return v;
    }

    //tamamla tuşuna basıldığı zaman çalışacak.

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        DbHelper dbHelper = new DbHelper(context);
        UserDAO dao = new UserDAO();
        String about = String.valueOf(pf3AboutTextInput.getText());
        if(!about.equals("")){
            globalpf.setAbout(about);
            super.onCompleteClicked(callback);

            //oyuncu özellikleri veritabanına gönderildi.
            if(!dao.usersCount(dbHelper)){
                um.onCreateUser(GameSelectActivity.getLocalUser(new User()));
            }
            pfm.insertPlayerFeatures(globalpf.getOpsiyon1(),globalpf.getOpsiyon2(),globalpf.getAbout(),gameID,globalpf.getgUserName(), GameOptionController.getSource1(),GameOptionController.getSource2());

            context.startActivity(new Intent(context, LoginActivity.class));
        }else{
            Snackbar.make(requireView(),"Hop Hemşerim nereye.",Snackbar.LENGTH_LONG)
                    .setAction("tamam", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    })
                    .show();
        }

    }


}
