package com.example.gamingsmlogin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.classes.Bubble;
import com.example.gamingsmlogin.management.BubbleManagement;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

//main mesajlaşma yeri //düzenlendi
public class MainMessageActivity extends AppCompatActivity {
    Thread th;
    RecyclerView recyclerView;
    FloatingActionButton fab;
    List<Bubble> list;
    Toolbar toolbar;
    BubbleManagement bm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_message);


        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("smchat");
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.floatingActionButton);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        bm = new BubbleManagement(recyclerView,  list,this);

        getBubble();
        fab.setOnClickListener(v -> {
            th.interrupt();
            startActivity(new Intent(getApplicationContext(), CrtMessageActivity.class)); //normalde çalışacak
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_chat,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_search) {
            startActivity(new Intent(this, CrtMessageActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    boolean whileRunnable=true;
    @Override
    protected void onStart() {
        whileRunnable=true;
        th= new Thread(() -> {
            while (whileRunnable) {
                if (staticHolder.BoobleChatHolder) {
                    staticHolder.BoobleChatHolder = false;
                    getBubble();
                }
            }
        });
        th.start();
        super.onStart();
    }

    @Override
    protected void onStop() {
        whileRunnable=false;
        th.interrupt();
        super.onStop();
    }

    public void getBubble(){
        bm.getBubble();
    }
}