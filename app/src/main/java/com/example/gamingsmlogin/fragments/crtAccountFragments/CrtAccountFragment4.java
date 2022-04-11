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

public class CrtAccountFragment4 extends StepperClass {

    //kullanıcı oluştururuken kullanılan stepperlarda görüntülenecek fragmentlerden biri

    private TextInputEditText emailTextInput, phoneTextInput;
    private UsersManagement um = new UsersManagement();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_crt_account_4,container,false);

        emailTextInput = v.findViewById(R.id.emailTextInput);
        phoneTextInput = v.findViewById(R.id.phoneTextInput);

        //klavyede enter basıldığı zaman klavyeyi kapatacak

        um.keyListener(phoneTextInput,getActivity());
        return v;
    }

    //ileri tuşuna basıldığı zaman çalışacak.

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {

        String email = String.valueOf(emailTextInput.getText());
        String phone = String.valueOf(phoneTextInput.getText());

        if(um.isValidEmail(email)){
            if(um.isValidPhone(phone) && (phoneTextInput.getText().length() == 10)){
                getUserData(new User());

                //usersManager içindeki check true yapıldı.

                um.check = true;
                um.isDuplicate("email",email,emailTextInput,callback);
            }else{
                Snackbar.make(requireView(),"Lütfen geçerli bir numara girin",Snackbar.LENGTH_LONG)
                        .setAction("Tekrar dene", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                phoneTextInput.setText("");
                                phoneTextInput.requestFocus();
                            }
                        })
                        .show();
            }
        }else{
            Snackbar.make(requireView(),"Lütfen geçerli bir e-posta girin",Snackbar.LENGTH_LONG)
                    .setAction("Tekrar dene", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            emailTextInput.setText("");
                            emailTextInput.requestFocus();
                        }
                    })
                    .show();
        }
    }


    @Override
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        super.onBackClicked(callback);
    }

    //CreateAccStepperAdapter içindeki globalUseru doldurmak için StepperClassdaki getUserData fonksiyonunu çağırıyoruz.

    @Override
    public User getUserData(User u) {
        u.setEmail(String.valueOf(emailTextInput.getText()));
        u.setPhone(String.valueOf(phoneTextInput.getText()));
        return super.getUserData(u);
    }
}
