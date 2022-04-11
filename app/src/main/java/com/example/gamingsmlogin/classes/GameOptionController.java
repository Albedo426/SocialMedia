package com.example.gamingsmlogin.classes;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.interfaces.IOptions;
import com.example.gamingsmlogin.interfaces.IServices;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameOptionController implements IOptions {

    // Oyunları kontrol eden kısım burada oyun opsiyonları ve oyuncuların statsları yer alıyor.

    protected IServices services = ApiUtils.getIServices();
    private String gameOption1, gameOption2, required;
    private ArrayAdapter<String> arrayAdapter1,arrayAdapter2;
    private Context mContext;
    public static String source1, source2;

    //constructorlar

    public GameOptionController(String required){
        this.required = required;
    }

    public GameOptionController(String gameOption1, String gameOption2, Context mContext) {
        this.gameOption1 = gameOption1;
        this.gameOption2 = gameOption2;
        this.mContext = mContext;
    }

    //opsiyonların çekildiği kısım.
  
    @Override
    public void getOptions(String method, AutoCompleteTextView textBox1, AutoCompleteTextView textBox2, TextInputLayout til1, TextInputLayout til2) {
        //texboxların hintlerini ayarlıyorum.

        til1.setHint(gameOption1);
        til2.setHint(gameOption2);

        services.options(method).enqueue(new Callback<OptionsReturn>() {
            @Override
            public void onResponse(Call<OptionsReturn> call, Response<OptionsReturn> response) {
                List<String> option1 = response.body().getOptions().get(0).getOption1();
                List<String> option2 = response.body().getOptions().get(0).getOption2();

                //autocomplatetext için gerekli orada gösterilecek liste için adapter.

                arrayAdapter1 = new ArrayAdapter<>(mContext,R.layout.support_simple_spinner_dropdown_item,option1);
                arrayAdapter2 = new ArrayAdapter<>(mContext,R.layout.support_simple_spinner_dropdown_item,option2);

                textBox1.setAdapter(arrayAdapter1);
                textBox2.setAdapter(arrayAdapter2);

                textBox1.setThreshold(1);
                textBox2.setThreshold(1);
            }

            @Override
            public void onFailure(Call<OptionsReturn> call, Throwable t) {
                Log.e("GameOptionController", "connection error getOptions()",t );
                Toast.makeText(mContext, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //oyuncuların statlarını alıyorum burada.

    @Override
    public void getStats(String method) {
        services.getStats(method,required).enqueue(new Callback<SourceReturn>() {
            @Override
            public void onResponse(Call<SourceReturn> call, Response<SourceReturn> response) {
                List<Source> list = response.body().getSources();
                Log.e(method,list.get(0).getType());
                Log.e(method,list.get(0).getSource());
                source1 = list.get(0).getSource();
                if(list.size() == 2){
                    Log.e("burası çalıştı","1");
                    source2 = list.get(1).getSource();
                }else{
                    Log.e("burası çalıştı","2");
                    source2 = "";
                }
            }

            @Override
            public void onFailure(Call<SourceReturn> call, Throwable t) {
                Log.e(method,t.getMessage());
            }
        });
    }

    public static String getSource1(){
        return source1;
    }

    public static String getSource2(){
        return source2;
    }

}
