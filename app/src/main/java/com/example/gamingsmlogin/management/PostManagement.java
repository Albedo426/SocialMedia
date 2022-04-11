package com.example.gamingsmlogin.management;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.adapters.PostViewerRVAdapter;
import com.example.gamingsmlogin.classes.Post;
import com.example.gamingsmlogin.classes.PostReturn;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.interfaces.IServices;
import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostManagement extends BaseManagement {
    RecyclerView recyclerView;
    List<Post> list;
    PostViewerRVAdapter postViewerAdapter;
    IServices services = ApiUtils.getIServices();
    String Id;

    public PostManagement(RecyclerView recyclerView, List<Post> list, Context context) {
        super(context);
        this.recyclerView = recyclerView;
        this.list = list;
        UserDAO dao = new UserDAO();
        DbHelper db = new DbHelper(context);
        Id =String.valueOf( dao.getSQLUser(db).get(0).getUserID()); // DÜZELT
    }

    public void getPost(int limit) {
        services.getPost("getpost", Id,String.valueOf(limit)).enqueue(new Callback<PostReturn>() {
            @Override
            public void onResponse(Call<PostReturn> call, Response<PostReturn> response) {
                list= response.body().getPosts();
                postViewerAdapter = new PostViewerRVAdapter(list,context);
                recyclerView.setAdapter(postViewerAdapter);
            }

            @Override
            public void onFailure(Call<PostReturn> call, Throwable t) {
                Log.e("PostManagement", "connection error getPost()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getPostForId(int limit,String Id) {
        services.getpost("getPostForId", Id,String.valueOf(limit)).enqueue(new Callback<PostReturn>() {
            @Override
            public void onResponse(Call<PostReturn> call, Response<PostReturn> response) {
                list= response.body().getPosts();
                postViewerAdapter = new PostViewerRVAdapter(list,context);
                recyclerView.setAdapter(postViewerAdapter);
            }

            @Override
            public void onFailure(Call<PostReturn> call, Throwable t) {
                Log.e("PostManagement", "connection error getPostForId()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
