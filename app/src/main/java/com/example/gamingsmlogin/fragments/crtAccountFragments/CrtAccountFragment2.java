package com.example.gamingsmlogin.fragments.crtAccountFragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.gamingsmlogin.R;

import com.example.gamingsmlogin.classes.StepperClass;
import com.example.gamingsmlogin.classes.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import com.stepstone.stepper.StepperLayout;

import java.util.Calendar;

public class CrtAccountFragment2 extends StepperClass{

    //kullanıcı oluştururuken kullanılan stepperlarda görüntülenecek fragmentlerden biri

    private TextInputEditText dateTextInput;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_crt_account_2,container,false);

        //takvim oluşturduk.

        dateTextInput = v.findViewById(R.id.dateTextInput);
        dateTextInput.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int y = c.get(Calendar.YEAR);
                int m = c.get(Calendar.MONTH);
                int d = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePicker = new DatePickerDialog(getActivity(),R.style.datePickerTheme, new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        dateTextInput.setText(i2+"/"+(i1+1)+"/"+i);
                    }
                },y,m,d);
                datePicker.setTitle("Tarih Seçin");
                datePicker.setButton(DialogInterface.BUTTON_NEGATIVE,"iptal",datePicker);
                datePicker.setButton(DialogInterface.BUTTON_POSITIVE,"tamamla",datePicker);
                datePicker.show();
            }
        });
        return v;
    }

    //ileri tuşuna basıldığı zaman çalışacak.

    @Override
    public void onNextClicked(StepperLayout.OnNextClickedCallback callback) {
        String date = String.valueOf(dateTextInput.getText());

        if (!date.equals("")){
            getUserData(new User());

            //stepperClass içindeki check true yapıldı.

            super.check = true;
            super.onNextClicked(callback);
        }else{
            Snackbar.make(requireView(),"Doğum tarihi boş bırakılamaz.",Snackbar.LENGTH_LONG)
                    .setAction("tamam", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
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
        u.setBirthDate(String.valueOf(dateTextInput.getText()));
        return super.getUserData(u);
    }
}
