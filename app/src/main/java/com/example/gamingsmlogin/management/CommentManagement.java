package com.example.gamingsmlogin.management;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.adapters.CommentRVAdapter;
import com.example.gamingsmlogin.classes.Comment;
import com.example.gamingsmlogin.classes.CommentReturn;
import com.example.gamingsmlogin.classes.ControlReturn;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.interfaces.IServices;
import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentManagement extends BaseManagement {
    RecyclerView recyclerView;
    List<Comment> list;
    CommentRVAdapter commentAdapter;
    String Id;
    IServices services = ApiUtils.getIServices();
    String connectId ;
    int sub;
    int limit;
    public CommentManagement(RecyclerView recyclerView, List<Comment> list, Context context, String connectId , int sub, int  limit) {
        super(context);
        this.recyclerView = recyclerView;
        this.list = list;
        this.connectId =connectId;
        this.sub = sub;
        this.limit = limit;

        UserDAO dao = new UserDAO();
        DbHelper db = new DbHelper(context);
        Id =String.valueOf( dao.getSQLUser(db).get(0).getUserID());
    }
    public void addComment(String text){
        services.addComment("addComment",connectId, Id,String.valueOf(sub),text).enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                init();
            }
            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("CommentManagement", "connection error addComment()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void init() {
        String method="getCommentForPostId";
        if(sub==1){
            method="getCommentForUserId";
        }
        Log.e("TAG", "onCreate: "+method );
        services.getComment(method,connectId,String.valueOf(limit)).enqueue(new Callback<CommentReturn>() {
            @Override
            public void onResponse(Call<CommentReturn> call, Response<CommentReturn> response) {
                list= response.body().getComments();
                commentAdapter = new CommentRVAdapter(list,context);
                recyclerView.setAdapter(commentAdapter);
            }
            @Override
            public void onFailure(Call<CommentReturn> call, Throwable t) {
                Log.e("CommentManagement", "connection error init()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
