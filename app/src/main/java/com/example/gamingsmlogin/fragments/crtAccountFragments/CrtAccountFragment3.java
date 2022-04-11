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

public class CrtAccountFragment3 extends StepperClass {

    //kullanıcı oluştururuken kullanılan stepperlarda görüntülenecek fragmentlerden biri

    private TextInputEditText userNameTextInput;
    private UsersManagement um = new UsersManagement();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_crt_account_3,container,false);
        userNameTextInput = v.findViewById(R.id.userNameTextInput);

        //klavyede enter basıldığı zaman klavyeyi kapatacak

        um.keyListener(userNameTextInput,getActivity());
        return v;
    }

    //ileri tuşuna basıldığı zaman çalışacak.

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        String userName = String.valueOf(userNameTextInput.getText());

        if (!userName.equals("")){
            getUserData(new User());

            //usersManager içindeki check true yapıldı.

            um.check = true;
            um.isDuplicate("userName",userName,userNameTextInput,callback);
        }else{
            Snackbar.make(requireView(),"Kullanıcı adı boş bırakılamaz.",Snackbar.LENGTH_LONG)
                    .setAction("tamam", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    })
                    .show();
        }
    }

    //CreateAccStepperAdapter içindeki globalUseru doldurmak için StepperClassdaki getUserData fonksiyonunu çağırıyoruz.

    @Override
    public User getUserData(User u) {
        u.setUserName(String.valueOf(userNameTextInput.getText()));
        return super.getUserData(u);
    }
}
