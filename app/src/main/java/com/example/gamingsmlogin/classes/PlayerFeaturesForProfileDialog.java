package com.example.gamingsmlogin.classes;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.TextView;

import com.example.gamingsmlogin.R;

public class PlayerFeaturesForProfileDialog {
    Context context;
    public PlayerFeaturesForProfileDialog(Context context){
        this.context = context;
    }

    public void showDialog(Activity activity, PlayerFeaturesForProfile pfu) {
        final Dialog DIALOG = new Dialog(activity);

        DIALOG.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DIALOG.setCancelable(true);
        DIALOG.setContentView(R.layout.custom_player_features_dialog);
        DIALOG.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        TextView option1TextView = DIALOG.findViewById(R.id.option1TextView);
        TextView option2TextView = DIALOG.findViewById(R.id.option2TextView);
        TextView aboutfTextView = DIALOG.findViewById(R.id.aboutfTextView);

        option1TextView.setText(pfu.getOpsiyon1());
        option2TextView.setText(pfu.getOpsiyon2());
        aboutfTextView.setText(pfu.getAbout());

        DIALOG.show();
    }

}
