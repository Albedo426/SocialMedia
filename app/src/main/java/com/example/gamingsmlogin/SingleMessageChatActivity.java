package com.example.gamingsmlogin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dinuscxj.refresh.RecyclerRefreshLayout;
import com.example.gamingsmlogin.classes.Message;
import com.example.gamingsmlogin.management.ChatSingleManagement;
import com.example.gamingsmlogin.management.UserProfileManagement;

import java.util.LinkedList;
import java.util.List;

import at.markushi.ui.CircleButton;


public class SingleMessageChatActivity extends AppCompatActivity {

    public static final String TAG ="logger";
    Button buttonSend;
    CircleButton btnImgUpload;
    EditText sendText;
    TextView userName;
    RecyclerView recyclerView;
    List<Message> list;
    RecyclerRefreshLayout refreshLayout;
    ChatSingleManagement CSManagement;
    ConstraintLayout chatUserCard;
    ImageView ReciverImageView;
    String receiver ;// i.getStringExtra("userId");
    Thread th;
    String pType;
    static boolean active = false;
    int cMessageIf;
    UserProfileManagement userProfileManagement= new UserProfileManagement(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_single_message);

        Intent intent = getIntent();
        receiver = intent.getStringExtra("reciver");
        cMessageIf = getIntent().getIntExtra("ifcrative",1);
        buttonSend=findViewById(R.id.button);
        sendText=findViewById(R.id.editTextText);
        ReciverImageView=findViewById(R.id.ReciverImageView);
        btnImgUpload=findViewById(R.id.fileTypeChouser);
        chatUserCard=findViewById(R.id.chatUserCard);
        recyclerView=findViewById(R.id.recyView);
        userName=findViewById(R.id.userName);
        refreshLayout=findViewById(R.id.RecyclerRefreshlayout);
        recyclerView.setLayoutManager( new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);

        list= new LinkedList<>();
        userProfileManagement.getUser(receiver,userName,ReciverImageView,this);

        CSManagement = new ChatSingleManagement( recyclerView,  list,  receiver, this);
        chatUserCard.setOnClickListener(v -> {
            Intent i = new Intent(this, ViewUserForSearchActivity.class).putExtra("userIDForProf",receiver);
            startActivity(i);
        });
        refreshLayout.setOnRefreshListener(() -> {
            CSManagement.newDataRecy();
            refreshLayout.setRefreshing(false);
        });

        buttonSend.setOnClickListener(v -> CSManagement.controlSendMessage(sendText.getText().toString().trim()));

        //düz buttona basılınca fotoğraf yükleme yeri açılıyor
        btnImgUpload.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            builder.setTitle("Video/Fotoğraf")
                    .setMessage("Fotoğraf mı video mu yüklemek istiyorsunuz")
                    .setPositiveButton("Fotoğraf", (dialog, which) -> {
                        pType ="2";
                        getFile(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    })
                    .setNegativeButton("Video", (dialog, which) -> {
                        pType ="3";
                        getFile(MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                    });
            builder.show();
        });
    }
    private void getFile(Uri uri) {
        Intent i  = new Intent(Intent.ACTION_PICK,uri);
        startActivityForResult(i,0);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When an Image is picked
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && null != data) {
            CSManagement.selectedimagepath(data, pType);//resim 2 video 3
        } // When an Video is picked
    }
    boolean whileRunnable =true;
    @Override
    protected void onStart() {
        whileRunnable =true;
        active = true;
        th= new Thread(() -> {
            while (whileRunnable) {
                if (staticHolder.SingleChatHolder) {
                    staticHolder.SingleChatHolder = false;
                    Log.e(TAG, "run: whileRunnable in onStart");
                    CSManagement.getSingleChat(0);
                }
            }
        });
        th.start();

        super.onStart();
    }

    public void threadRef(){
        whileRunnable =false;
        th.interrupt();
        staticHolder.SingleChatHolder = true;
    }
    @Override
    protected void onStop() {
        active = false;
        threadRef();
        super.onStop();
    }
    @Override
    public void onBackPressed() {
        threadRef();
        super.onBackPressed();
    }
}