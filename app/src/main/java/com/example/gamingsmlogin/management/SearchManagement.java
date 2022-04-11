package com.example.gamingsmlogin.management;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.SearchForDetailedActivity;
import com.example.gamingsmlogin.adapters.DetailedSearchRVAdapter;
import com.example.gamingsmlogin.classes.CsGO;
import com.example.gamingsmlogin.classes.Dota2;
import com.example.gamingsmlogin.classes.Game;
import com.example.gamingsmlogin.classes.GameOptionController;
import com.example.gamingsmlogin.classes.GamesReturn;
import com.example.gamingsmlogin.classes.Lol;
import com.example.gamingsmlogin.classes.User;
import com.example.gamingsmlogin.classes.UsersReturn;
import com.example.gamingsmlogin.classes.Valorant;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.interfaces.IServices;
import com.google.android.material.textfield.TextInputLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchManagement extends BaseManagement{

    IServices services = ApiUtils.getIServices();
    DetailedSearchRVAdapter detailedSearchRVAdapter;
    RecyclerView recyclerView;
    Activity activity;

    public SearchManagement(Context context,Activity activity) {
        super(context);
        this.activity = activity;
    }

    public SearchManagement(Context context, RecyclerView recyclerView) {
        super(context);
        this.recyclerView = recyclerView;
    }

    public void detailedSearch(String value, String whichMethod){
        services.detailedSearch("detailedSearch",value,whichMethod).enqueue(new Callback<UsersReturn>() {
            @Override
            public void onResponse(Call<UsersReturn> call, Response<UsersReturn> response) {
                List<User> list= response.body().getUsers();
                detailedSearchRVAdapter = new DetailedSearchRVAdapter(list,context);
                recyclerView.setAdapter(detailedSearchRVAdapter);
            }

            @Override
            public void onFailure(Call<UsersReturn> call, Throwable t) {
                Log.e("detailedSearch", "connection error detailedSearch()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getFilteredUser(String option1,String option2, String source1,String source2){
        services.getFilteredUser("getFilteredUser",option1,option2,source1,source2).enqueue(new Callback<UsersReturn>() {
            @Override
            public void onResponse(Call<UsersReturn> call, Response<UsersReturn> response) {
                List<User> list = response.body().getUsers();
                if(!list.get(0).getUserID().equals(0)){
                    context.startActivity(new Intent(activity, SearchForDetailedActivity.class).putExtra("getFilteredUserList", (Serializable) list));
                }else{
                    Toast.makeText(context,"İstenen kriterlerde oyuncu bulunamadı.",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<UsersReturn> call, Throwable t) {
                Log.e("detailedSearch", "connection error getFilteredUser()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getGamesForSearch(AutoCompleteTextView sGameText, AutoCompleteTextView sOption1Text, TextInputLayout sOption1TIL, AutoCompleteTextView sOption2Text, TextInputLayout sOption2TIL, String[] ranks, AutoCompleteTextView sLolStatsText){
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

                sGameText.setAdapter(gameAdapter);
                sLolStatsText.setAdapter(rankAdapter);

                Log.e("geldim ben","4000");
                sGameText.setOnItemClickListener((adapterView, view, i, l) -> {
                    sOption1Text.setText("");
                    sOption1TIL.clearFocus();
                    sOption2Text.setText("");
                    sOption2TIL.clearFocus();
                    if(gameNames.get(i).equals("League of Legends")){
                        GameOptionController mLol = new Lol(sOption1Text,sOption2Text, context, sOption1TIL,sOption2TIL);
                        Lol lol = (Lol) mLol;
                        lol.getOptions();
                        activity.findViewById(R.id.includeLol).setVisibility(View.VISIBLE);
                    }else if(gameNames.get(i).equals("Counter-Strike: Global Offensive")){
                        GameOptionController mCsGo = new CsGO(sOption1Text,sOption2Text, context, sOption1TIL,sOption2TIL);
                        CsGO csGO = (CsGO) mCsGo;
                        csGO.getOptions();
                        activity.findViewById(R.id.includeLol).setVisibility(View.INVISIBLE);
                    }else if(gameNames.get(i).equals("Dota 2")){
                        GameOptionController mDota2 = new Dota2(sOption1Text,sOption2Text, context, sOption1TIL,sOption2TIL);
                        Dota2 dota2 = (Dota2) mDota2;
                        dota2.getOptions();
                        activity.findViewById(R.id.includeLol).setVisibility(View.INVISIBLE);

                    }else if(gameNames.get(i).equals("Valorant")){
                        GameOptionController mValorant = new Valorant(sOption1Text,sOption2Text, context, sOption1TIL,sOption2TIL);
                        Valorant valorant = (Valorant) mValorant;
                        valorant.getOptions();
                        activity.findViewById(R.id.includeLol).setVisibility(View.INVISIBLE);

                    }
                });
                sGameText.setThreshold(1);
            }

            @Override
            public void onFailure(Call<GamesReturn> call, Throwable t) {
                Log.e("detailedSearch", "connection error getGamesForSearch()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
