package com.example.gamingsmlogin.management;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.adapters.GameRVAdapter;
import com.example.gamingsmlogin.classes.Game;
import com.example.gamingsmlogin.classes.GamesReturn;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.interfaces.IServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameManagement extends BaseManagement{

    //Oyun servislerini yönetir.
    private GameRVAdapter adapter;
    IServices services = ApiUtils.getIServices();
    public GameManagement(Context context, GameRVAdapter adapter) {
        super(context);
        this.adapter = adapter;
    }

    public GameManagement(Context context) {
        super(context);
    }

    //bütün oyunları getirir.

    public void getAllGames(RecyclerView rv, Activity activity){
        services.getAllGames("getAllGames").enqueue(new Callback<GamesReturn>() {
            @Override
            public void onResponse(Call<GamesReturn> call, Response<GamesReturn> response) {
                List<Game> games = response.body().getGames();

                //oyunları rv ye yerleştiriyor.

                adapter = new GameRVAdapter(context,games,activity);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GamesReturn> call, Throwable t) {
                Log.e("GameManagement", "connection error getAllGames()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //--- taşınacak burası

    /*public void getGamesForSearch(AutoCompleteTextView sGameText, AutoCompleteTextView sOption1Text,TextInputLayout sOption1TIL, AutoCompleteTextView sOption2Text,TextInputLayout sOption2TIL, Activity activity,String[] ranks,String[] kda,String[] hsp, AutoCompleteTextView sLolStatsText,AutoCompleteTextView sCsgoKDAText,AutoCompleteTextView sCsgoHSPText){
        services.getAllGames("getAllGames").enqueue(new Callback<GamesReturn>() {
            @Override
            public void onResponse(Call<GamesReturn> call, Response<GamesReturn> response) {
                List<Game> games = response.body().getGames();
                ArrayList<String> gameNames = new ArrayList<>();
                for(Game g: games){
                   gameNames.add(g.getGameName());
                }
                ArrayAdapter<String> gameAdapter = new ArrayAdapter<>(context, R.layout.support_simple_spinner_dropdown_item, gameNames);
                ArrayAdapter<String> rankAdapter = new ArrayAdapter<>(context,R.layout.support_simple_spinner_dropdown_item,ranks);
                ArrayAdapter<String> kdaAdapter = new ArrayAdapter<>(context,R.layout.support_simple_spinner_dropdown_item,kda);
                ArrayAdapter<String> hspAdapter = new ArrayAdapter<>(context,R.layout.support_simple_spinner_dropdown_item,hsp);


                sGameText.setAdapter(gameAdapter);
                sLolStatsText.setAdapter(rankAdapter);
                sCsgoKDAText.setAdapter(kdaAdapter);
                sCsgoHSPText.setAdapter(hspAdapter);

                Log.e("geldim ben","4000");
                sGameText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        sOption1Text.setText("");
                        sOption1TIL.clearFocus();
                        sOption2Text.setText("");
                        sOption2TIL.clearFocus();
                        if(gameNames.get(i).equals("League of Legends")){
                            GameOptionController mLol = new Lol(sOption1Text,sOption2Text, context, sOption1TIL,sOption2TIL);
                            Lol lol = (Lol) mLol;
                            lol.getOptions();
                            activity.findViewById(R.id.includeLol).setVisibility(View.VISIBLE);
                            activity.findViewById(R.id.includeCsgo).setVisibility(View.INVISIBLE);
                        }else if(gameNames.get(i).equals("Counter-Strike: Global Offensive")){
                            GameOptionController mCsGo = new CsGO(sOption1Text,sOption2Text, context, sOption1TIL,sOption2TIL);
                            CsGO csGO = (CsGO) mCsGo;
                            csGO.getOptions();
                            activity.findViewById(R.id.includeLol).setVisibility(View.INVISIBLE);
                            activity.findViewById(R.id.includeCsgo).setVisibility(View.VISIBLE);
                        }else if(gameNames.get(i).equals("Dota 2")){
                            GameOptionController mDota2 = new Dota2(sOption1Text,sOption2Text, context, sOption1TIL,sOption2TIL);
                            Dota2 dota2 = (Dota2) mDota2;
                            dota2.getOptions();
                            activity.findViewById(R.id.includeLol).setVisibility(View.INVISIBLE);
                            activity.findViewById(R.id.includeCsgo).setVisibility(View.INVISIBLE);

                        }else if(gameNames.get(i).equals("Valorant")){
                            GameOptionController mValorant = new Valorant(sOption1Text,sOption2Text, context, sOption1TIL,sOption2TIL);
                            Valorant valorant = (Valorant) mValorant;
                            valorant.getOptions();
                            activity.findViewById(R.id.includeLol).setVisibility(View.INVISIBLE);
                            activity.findViewById(R.id.includeCsgo).setVisibility(View.INVISIBLE);

                        }
                    }
                });
                sGameText.setThreshold(1);
            }

            @Override
            public void onFailure(Call<GamesReturn> call, Throwable t) {
                Log.e("getAllGames",t.getMessage());
            }
        });
    }*/



    //---

    /*sGameText.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            Log.e("geldim ben","4000");
            if(gameNames.get(i).equals("League of Legends")){
                GameOptionController mLol = new Lol(sOption1Text,sOption2Text, context, sOption1TIL,sOption2TIL);
                Lol lol = (Lol) mLol;
                lol.getOptions();
            }else if(gameNames.get(i).equals("Counter-Strike: Global Offensive")){
                GameOptionController mCsGo = new CsGO(sOption1Text,sOption2Text, context, sOption1TIL,sOption2TIL);
                CsGO csGO = (CsGO) mCsGo;
                csGO.getOptions();
            }else if(gameNames.get(i).equals("Dota 2")){
                GameOptionController mDota2 = new Dota2(sOption1Text,sOption2Text, context, sOption1TIL,sOption2TIL);
                Dota2 dota2 = (Dota2) mDota2;
                dota2.getOptions();
            }else if(gameNames.get(i).equals("Valorant")){
                GameOptionController mValorant = new Valorant(sOption1Text,sOption2Text, context, sOption1TIL,sOption2TIL);
                Valorant valorant = (Valorant) mValorant;
                valorant.getOptions();
            }

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    });*/
}
