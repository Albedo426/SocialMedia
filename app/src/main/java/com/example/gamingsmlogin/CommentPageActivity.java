package com.example.gamingsmlogin;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinuscxj.refresh.RecyclerRefreshLayout;
import com.example.gamingsmlogin.classes.Comment;
import com.example.gamingsmlogin.management.CommentManagement;

import java.util.ArrayList;
import java.util.List;


//Yorumların Gözüktüğü alan /düzeltildi
public class CommentPageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Comment> list;
    String connectId;
    int sub;
    int limit=5;
    CommentManagement cManagement;
    Button buttonSend;
    RecyclerRefreshLayout recyclerRefreshLayout;
    EditText sendText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_page);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerRefreshLayout = findViewById(R.id.RecyclerRefreshlayout);

        buttonSend = findViewById(R.id.button);
        sendText = findViewById(R.id.editTextText);


        recyclerView.setLayoutManager( new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        list= new ArrayList<>();

        connectId = getIntent().getStringExtra("connectId");
        sub = getIntent().getIntExtra("sub",0);

        Log.e("TAG", "ifSubComment(Def=0): "+sub );

        cManagement= new CommentManagement(recyclerView,list,this,connectId,sub,limit);
        init();
        buttonSend.setOnClickListener(v -> {
            cManagement.addComment(sendText.getText().toString().trim());
            sendText.setText("");
        });
        recyclerRefreshLayout.setOnRefreshListener(() -> {
            init();
            recyclerRefreshLayout.setRefreshing(false);
        });
    }

    private void init() {
        cManagement.init();
    }
}