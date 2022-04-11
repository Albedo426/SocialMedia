package com.example.gamingsmlogin.fragments.crtAccountFragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gamingsmlogin.GameSelectActivity;
import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.classes.StepperClass;
import com.example.gamingsmlogin.classes.User;
import com.example.gamingsmlogin.management.UsersManagement;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.stepstone.stepper.StepperLayout;

public class CrtAccountFragment5 extends StepperClass {

    //kullanıcı oluştururuken kullanılan stepperlarda görüntülenecek fragmentlerden biri

    private TextInputEditText passTextInput, aPassTextInput;
    private Context context;
    public CrtAccountFragment5(Context context){
        this.context = context;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_crt_account_5,container,false);

        passTextInput = v.findViewById(R.id.passTextInput);
        aPassTextInput = v.findViewById(R.id.aPassTextInput);
        UsersManagement um = new UsersManagement();

        //klavyede enter basıldığı zaman klavyeyi kapatacak

        um.keyListener(aPassTextInput,getActivity());

        return v;
    }

    //tamamla tuşuna basıldığı zaman çalışacak.

    @Override
    public void onCompleteClicked(StepperLayout.OnCompleteClickedCallback callback) {
        String pass = String.valueOf(passTextInput.getText());
        String aPass = String.valueOf(aPassTextInput.getText());

        if(!pass.equals("")){
            if (pass.equals(aPass)){
                globalUser.setPassword(String.valueOf(passTextInput.getText()));

                //globalUser usersManager içindeki onCreateUser servisine gönderiliyor. Ayrıca bu servisle birlikte oyun seçim ekranına yönlendiriliyor.

                User localUser = globalUser;
                Intent i = new Intent(context, GameSelectActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("globalUser",localUser);
                context.startActivity(i);

            }else{
                Snackbar.make(requireView(),"Şifreler aynı olmalıdır.",Snackbar.LENGTH_LONG)
                        .setAction("Tekrar dene", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                aPassTextInput.setText("");
                                aPassTextInput.requestFocus();
                            }
                        })
                        .show();
            }
        }else{
            Snackbar.make(requireView(),"Şifre boş bırakılamaz.",Snackbar.LENGTH_LONG)
                    .setAction("tamam", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    })
                    .show();
        }
    }

    @Override
    public User getUserData(User u) {
        return super.getUserData(u);
    }
}
