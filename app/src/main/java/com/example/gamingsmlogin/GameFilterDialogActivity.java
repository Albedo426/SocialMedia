package com.example.gamingsmlogin;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamingsmlogin.management.SearchManagement;
import com.google.android.material.textfield.TextInputLayout;

//yapılıyor
public class GameFilterDialogActivity extends AppCompatActivity {


    AutoCompleteTextView sGameText, sOption1Text,sOption2Text, sLolStatsText;
    TextInputLayout sGameTIL, sOption1TIL,sOption2TIL;
    Button btnDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_filter_dialog);

        sGameText = findViewById(R.id.sGameText);
        sGameTIL = findViewById(R.id.sGameTIL);
        sOption1Text = findViewById(R.id.sOption1Text);
        sOption2Text = findViewById(R.id.sOption2Text);
        sOption1TIL = findViewById(R.id.sOption1TIL);
        sOption2TIL = findViewById(R.id.sOption2TIL);
        sLolStatsText = findViewById(R.id.sLolStatsText);
        btnDetail = findViewById(R.id.btnDetail);

        SearchManagement sm = new SearchManagement(this,GameFilterDialogActivity.this);

        String[] ranks =  {"IRON I","IRON II","IRON III","IRON IV","BRONZE I","BRONZE II","BRONZE III","BRONZE IV",
        "SILVER I","SILVER II","SILVER III","SILVER IV","GOLD I","GOLD II","GOLD III","GOLD IV","PLATINUM I","PLATINUM II","PLATINUM III","PLATINUM IV",
        "DIAMOND I","DIAMOND II","DIAMOND III","DIAMOND IV","MASTER","GRANDMASTER","CHALLENGER"};

        sm.getGamesForSearch(sGameText,sOption1Text,sOption1TIL,sOption2Text,sOption2TIL,ranks,sLolStatsText);
        btnDetail.setOnClickListener(view -> {
            if(sGameText.getText().toString().equals("League of Legends")){
                sm.getFilteredUser(sOption1Text.getText().toString().trim() ,sOption2Text.getText().toString().trim(), sLolStatsText.getText().toString().trim(),"");
            }else{
                sm.getFilteredUser(sOption1Text.getText().toString().trim() ,sOption2Text.getText().toString().trim(), "","");
            }
        });
    }
}