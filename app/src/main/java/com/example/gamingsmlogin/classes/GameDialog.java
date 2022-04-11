package com.example.gamingsmlogin.classes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.gamingsmlogin.PlayerFeaturesActivity;
import com.example.gamingsmlogin.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GameDialog {

    // oyun seçtikten sonra çıkan dialog.

    Context context;

    //constructor

    public GameDialog(Context mContext) {
        this.context = mContext;
    }

    // Dialog açıldığı zaman neler olacak ayarlıyorum ve gösteriyorum.
    @SuppressLint("SetTextI18n")
    public void showDialog(Activity activity, Game g) {
        final Dialog DIALOG = new Dialog(activity);

        DIALOG.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DIALOG.setCancelable(false);
        DIALOG.setContentView(R.layout.custom_dialog);
        DIALOG.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        FloatingActionButton fabClose = DIALOG.findViewById(R.id.fabClose);
        FloatingActionButton fabOpen = DIALOG.findViewById(R.id.fabOpen);
        TextView typeText = DIALOG.findViewById(R.id.typeText);
        TextView linkText = DIALOG.findViewById(R.id.linkText);

        typeText.setText(g.getGameTypeID().toString());
        linkText.setText(g.getGameUrl());

        linkText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //dialog kırmızı butona basınca ne olacağıa karar veriyor.

        fabClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DIALOG.dismiss();
            }
        });

        //dialog yeşil butona basınca ne olacağıa karar veriyor.

        fabOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, PlayerFeaturesActivity.class);
                i.putExtra("gameID",g.getGameID());
                context.startActivity(i);

            }
        });

        DIALOG.show();
    }
}
