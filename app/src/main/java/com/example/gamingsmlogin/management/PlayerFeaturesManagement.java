package com.example.gamingsmlogin.management;

import android.app.Activity;
import android.content.Context;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.gamingsmlogin.classes.ControlReturn;
import com.example.gamingsmlogin.classes.CsGO;
import com.example.gamingsmlogin.classes.Dota2;

import com.example.gamingsmlogin.classes.GameOptionController;
import com.example.gamingsmlogin.classes.Lol;

import com.example.gamingsmlogin.classes.Valorant;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.interfaces.IServices;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.stepstone.stepper.StepperLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PlayerFeaturesManagement extends BaseManagement{

    //Oyuncu özellikleri ile ilgili servisleri yönetir.

    IServices services = ApiUtils.getIServices();
    public boolean check = true;

    public PlayerFeaturesManagement(Context context) {
        super(context);
    }

    //rv den hangi oyunun seçildiğini öğreniyor ve ona göre opsiyonları çağırıyor.

    public void getGameObj(int gameID, AutoCompleteTextView textBox1, AutoCompleteTextView textBox2, TextInputLayout til1,TextInputLayout til2){

        if(gameID == 1){
            GameOptionController mLol = new Lol(textBox1,textBox2,context,til1,til2);
            Lol lol = (Lol) mLol;
            lol.getOptions();

        }else if(gameID == 2){
            GameOptionController mCsGo = new CsGO(textBox1,textBox2,context,til1,til2);
            CsGO csGO = (CsGO) mCsGo;
            csGO.getOptions();

        }else if(gameID == 3){
            GameOptionController mDota2 = new Dota2(textBox1,textBox2,context,til1,til2);
            Dota2 dota2 = (Dota2) mDota2;
            dota2.getOptions();

        }else if(gameID == 6){
            GameOptionController mValorant = new Valorant(textBox1,textBox2,context,til1,til2);
            Valorant valorant = (Valorant) mValorant;
            valorant.getOptions();
        }
    }

    //girilen oyuncu adına veya ip ye göre oyuncunun statslarını getiriyor

    public void getGameUser(int gameID,String required){
        if(gameID == 1){
            GameOptionController mLol = new Lol(required);
            Lol lol = (Lol) mLol;
            lol.getStats();
        }else if(gameID == 2){
            GameOptionController mCsGo = new CsGO(null);
            CsGO csGO = (CsGO) mCsGo;
            csGO.getStats();

        }else if(gameID == 3){
            GameOptionController mDota2 = new Dota2(null);
            Dota2 dota2 = (Dota2) mDota2;
            dota2.getStats();

        }else if(gameID == 6){
            GameOptionController mValorant = new Valorant(null);
            Valorant valorant = (Valorant) mValorant;
            valorant.getStats();
        }
    }

    //Oyuncu özelliklerini kontrol eder birden fazla aynı kullanıcı adı olmaması için

    public void playerFeaturesControl(int gameID, String required, View view, TextInputEditText textBox1, StepperLayout.OnNextClickedCallback callback){
        services.playerFeaturesControl("playerFeaturesControl",gameID,required).enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                Log.e("playerFeaturesControl",response.body().getControl());
                if(response.body().getControl().equals("0")){
                    Snackbar.make(view,"Bu Kullanıcı Adı daha önce bu oyun için alınmış. Eğer siz değilseniz ",Snackbar.LENGTH_LONG)
                            .setAction("tamam", view1 -> {
                                textBox1.setText("");
                                textBox1.requestFocus();
                            })
                            .show();
                }else{
                    if (check){
                        callback.goToNextStep();
                    }
                    check = false;
                }
            }

            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("PlayerFeatures", "connection error playerFeaturesControl()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //Veritabanına oyuncu özelliklerini işler.

    public void insertPlayerFeatures(String option1,String option2,String about,int gameID, String required,String source1, String source2){
        services.insertPlayerFeatures("insertPlayerFeatures",option1,option2,about,gameID,required,source1,source2).enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                Log.e("insertPlayerFeatures",response.body().getControl());
            }
            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("PlayerFeatures", "connection error insertPlayerFeatures()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //enter tuşuna basıldığı zaman klavye kapanması için.

    public void keyListener(TextInputEditText text, Activity activity){
        text.setOnKeyListener((view, i, keyEvent) -> {
            if(keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER){
                InputMethodManager im = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                if(activity.getCurrentFocus() != null) {
                    im.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return true;
            }
            return false;
        });
    }

}
