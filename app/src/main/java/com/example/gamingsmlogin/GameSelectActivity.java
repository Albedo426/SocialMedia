package com.example.gamingsmlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.gamingsmlogin.adapters.GameRVAdapter;
import com.example.gamingsmlogin.classes.User;
import com.example.gamingsmlogin.management.GameManagement;

public class GameSelectActivity extends AppCompatActivity {

    Toolbar gameToolbar;
    RecyclerView gameRV;
    GameRVAdapter adapter;
    GameManagement gm;
    static User localUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_select);
        gameToolbar = findViewById(R.id.gameToolbar);
        gameRV = findViewById(R.id.gameRV);
        localUser = (User) getIntent().getSerializableExtra("globalUser");
        gameToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GameSelectActivity.this, LogSignInActivity.class));
            }
        });
        gm = new GameManagement(this,adapter);
        gameRV.setHasFixedSize(true);
        gameRV.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));

        gm.getAllGames(gameRV,GameSelectActivity.this);

    }
    public static User getLocalUser(User user){
        user = localUser;
        return user;
    }
}