package com.example.gamingsmlogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsMain extends AppCompatActivity {

    LinearLayout gameSettings,userSettings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_main);
        gameSettings=findViewById(R.id.gameSettings);
        userSettings=findViewById(R.id.userSettings);

        gameSettings.setOnClickListener((view)->{
            //eklenecek
        });
        userSettings.setOnClickListener((view)->{
            startActivity(new Intent(this,AccountSettingActivity.class));
        });
    }
}