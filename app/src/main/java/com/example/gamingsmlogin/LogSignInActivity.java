package com.example.gamingsmlogin;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;
import com.google.firebase.iid.FirebaseInstanceId;


public class LogSignInActivity extends AppCompatActivity {

    Button createAccButton;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_sign_in);
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.e("TAG", "my token: " + refreshedToken);
        createAccButton = findViewById(R.id.createAccButton);
        loginText = findViewById(R.id.loginText);

        //ÇIKIŞ YAPMA EKLENDİKTEN SONRA DÜZELT

        if(!getUserCount(this)){
            SpannableString ss = new SpannableString(loginText.getText());
            ClickableSpan cs = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View view) {
                    startActivity(new Intent(LogSignInActivity.this, LoginActivity.class));
                    finish();
                }
                @Override
                public void updateDrawState(@NonNull TextPaint ds) {
                    super.updateDrawState(ds);
                    ds.setUnderlineText(false);
                }
            };

            ss.setSpan(cs,26,36, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            loginText.setText(ss);
            loginText.setMovementMethod(LinkMovementMethod.getInstance());
            loginText.setHighlightColor(Color.TRANSPARENT);

            createAccButton.setOnClickListener(view -> startActivity(new Intent(LogSignInActivity.this, CrtAccountActivity.class)));
        }else{
            startActivity(new Intent(LogSignInActivity.this,MainActivity.class));
        }

    }

    public boolean getUserCount(Context context){
        UserDAO dao = new UserDAO();
        DbHelper dbHelper = new DbHelper(context);
        return dao.usersCount(dbHelper);
    }


}