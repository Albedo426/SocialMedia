package com.example.gamingsmlogin.fragments.mainFragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.adapters.PostShareRVAdapter;
import com.example.gamingsmlogin.classes.File;
import com.example.gamingsmlogin.classes.User;
import com.example.gamingsmlogin.management.PostShareManagement;
import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;

import java.util.ArrayList;
import java.util.List;

//post paylaşma kısmı ###sadece fotoğraf şu anlık //düzenlendi
public class PostShareFragment extends Fragment {


    LinearLayout  videoAdd,photoAdd;
    TextView userName;
    EditText val;
    Button sendPostBtn;
    RecyclerView recyclerView;
    PostShareManagement psm;
    List<File> list;
    PostShareRVAdapter postShareRVAdapter;
    String imgVideo="0";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View holder =inflater.inflate(R.layout.fragment_post_share, container, false);
        videoAdd=holder.findViewById(R.id.videoAdd);
        photoAdd=holder.findViewById(R.id.photoAdd);
        UserDAO dao = new UserDAO();
        DbHelper db = new DbHelper(getContext());
        userName=holder.findViewById(R.id.userName);
        val=holder.findViewById(R.id.postText);
        sendPostBtn =holder.findViewById(R.id.sendButton);
        recyclerView = holder.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        list= new ArrayList<>();

        postShareRVAdapter = new PostShareRVAdapter(list,getContext());
        recyclerView.setAdapter(postShareRVAdapter);
        recyclerView.scrollToPosition(list.size() - 1);


        User name =dao.getSQLUser(db).get(0);
        userName.setText(String.format("%s %s", name.getName(), name.getLastName()));

        sendPostBtn.setOnClickListener(v -> sendPost());

        videoAdd.setOnClickListener(v -> {
            if(list.size()<9){
                imgVideo="1";
                getfile(android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            }else{
                Toast.makeText(getContext(), "max 9 dosya yükleyebilirsiniz", Toast.LENGTH_SHORT).show();
            }
        });
        photoAdd.setOnClickListener(v -> {
            if(list.size()<9){
                getfile(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                imgVideo="0";
            }else{
                Toast.makeText(getContext(), "max 9 dosya yükleyebilirsiniz", Toast.LENGTH_SHORT).show();
            }
        });
        return holder;
    }

    private void sendPost() {
        if(psm==null){
            psm= new PostShareManagement(recyclerView, list,  getContext());
        }
        psm.sendPost(val);
    }
    private void getfile(Uri uri) {
        Intent i  = new Intent(Intent.ACTION_PICK,uri);
        startActivityForResult(i,0);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When an Image is picked
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && null != data) {
            psm= new PostShareManagement(recyclerView, list, getContext());
            psm.selectedImagePath(data,imgVideo);
        }
    } // When an Video is picked
}