package com.example.gamingsmlogin;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import com.example.gamingsmlogin.classes.PermissionClass;
import com.example.gamingsmlogin.management.UsersManagement;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;


public class LoginActivity extends AppCompatActivity {

    Toolbar toolbarLogin;
    TextInputLayout passTIL,mailTIL;
    TextInputEditText mailTextInput,passTextInput;
    Button buttonLogin;
    TextView forgottenPassText;
    private UsersManagement um;
    PermissionClass pc=new PermissionClass(this,LoginActivity.this);
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        toolbarLogin = findViewById(R.id.toolbarLogin);
        passTIL = findViewById(R.id.passTIL);
        mailTIL = findViewById(R.id.mailTIL);
        mailTextInput = findViewById(R.id.mailTextInput);
        passTextInput = findViewById(R.id.passTextInput);
        buttonLogin = findViewById(R.id.buttonLogin);
        forgottenPassText = findViewById(R.id.forgottenPassText);
        forgottenPassText.setClickable(true);
        //pc.getStoragePermission();
        forgottenPassText.setOnClickListener(view -> {
            startActivity(new Intent(LoginActivity.this, GameSelectActivity.class));
            finish();
        });

        //setSupportActionBar(toolbarLogin);
        toolbarLogin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, LogSignInActivity.class));

                finish();
            }
        });
        buttonLogin.setOnClickListener(view -> {
            String email = String.valueOf(mailTextInput.getText());
            String password = String.valueOf(passTextInput.getText());
            if (!email.equals("") && !password.equals("")){

                pc.getStoragePermission();// broadcaste services ile uyguoama açıldığında tetiklenecek // canlıda hata verir //allowdeny kısmına bak
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                    um = new UsersManagement(context);
                    if (um.isValidEmail(email)){
                       um.getForLogin(email,password,view,passTextInput);

                    }else{
                        Snackbar.make(view,"Lütfen e-posta formatına uygun bir e-posta girin.",Snackbar.LENGTH_LONG)
                                .setAction("Tekrar Dene", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        mailTextInput.setText("");
                                        mailTextInput.requestFocus();
                                    }
                                }).show();
                    }
                }
            }else{
                Snackbar.make(view,"E-posta ve şifre kısmı boş bırakılamaz",Snackbar.LENGTH_LONG)
                        .setAction("anladım", view1 -> mailTextInput.requestFocus()).show();
            }

            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);     //if you click the button it will close keyboard.
            if(getCurrentFocus() != null) {
                im.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        pc.requestPermissionResult(requestCode,grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        pc.activityResult(requestCode);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        pc.postResume();
    }
}