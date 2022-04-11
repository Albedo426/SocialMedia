package com.example.gamingsmlogin.fragments.crtAccountFragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gamingsmlogin.R;

import com.example.gamingsmlogin.classes.StepperClass;
import com.example.gamingsmlogin.classes.User;
import com.example.gamingsmlogin.management.UsersManagement;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.stepstone.stepper.StepperLayout;

public class CrtAccountFragment1 extends StepperClass{

    //kullanıcı oluştururuken kullanılan stepperlarda görüntülenecek fragmentlerden biri

    private TextInputEditText urNameTextInput, urLastNameTextInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_crt_account_1,container,false);
        urNameTextInput = v.findViewById(R.id.lastNameTextInput);
        urLastNameTextInput = v.findViewById(R.id.urLastNameTextInput);

        //klavyede enter basıldığı zaman klavyeyi kapatacak
        UsersManagement um = new UsersManagement();
        um.keyListener(urLastNameTextInput,getActivity());
        return v;
    }

    //ileri tuşuna basıldığı zaman çalışacak.

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

        String name = String.valueOf(urNameTextInput.getText());
        String surname = String.valueOf(urLastNameTextInput.getText());


        if(!name.equals("") && !surname.equals("")){
            getUserData(new User());

            //stepperClass içindeki check true yapıldı.

            super.check = true;
            super.onNextClicked(callback);
        }else{
            Snackbar.make(requireView(),"Ad ve Soyad boş bırakılamaz.",Snackbar.LENGTH_LONG)
                    .setAction("tamam", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    })
                    .show();
        }
    }

    //CreateAccStepperAdapter içindeki globalUser doldurmak için StepperClassdaki getUserData fonksiyonunu çağırıyoruz.

    @Override
    public User getUserData(User u) {
        u.setName(String.valueOf(urNameTextInput.getText()));
        u.setLastName(String.valueOf(urLastNameTextInput.getText()));
        return super.getUserData(u);
    }
}
