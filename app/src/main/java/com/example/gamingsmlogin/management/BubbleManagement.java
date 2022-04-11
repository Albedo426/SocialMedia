package com.example.gamingsmlogin.management;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.adapters.BubbleRVAdapter;
import com.example.gamingsmlogin.classes.Bubble;
import com.example.gamingsmlogin.classes.BubblesReturn;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.interfaces.IServices;
import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BubbleManagement extends BaseManagement {
    RecyclerView recyclerView;
    List<Bubble> list;
    BubbleRVAdapter recyclerAdapter;

    String Id;
    IServices services = ApiUtils.getIServices();
    public BubbleManagement(RecyclerView recyclerView, List<Bubble> list, Context context) {
        super(context);
        this.recyclerView = recyclerView;
        this.list = list;

        UserDAO dao = new UserDAO();
        DbHelper db = new DbHelper(context);
        Id =String.valueOf( dao.getSQLUser(db).get(0).getUserID()); // DÜZELT
        getBubble();
    }
    public void getBubble(){
        services.getBubbles("getBubbles",String.valueOf(Id)).enqueue(new Callback<BubblesReturn>() {
            @Override
            public void onResponse(Call<BubblesReturn> call, Response<BubblesReturn> response) {
                list= response.body().getBubbles();
                recyclerAdapter = new BubbleRVAdapter(list,context);
                recyclerView.setAdapter(recyclerAdapter);
            }
            @Override
            public void onFailure(Call<BubblesReturn> call, Throwable t) {
                Log.e("BubbleManagement", "connection error getBubble()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
