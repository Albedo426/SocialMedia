package com.example.gamingsmlogin.management;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.adapters.ChatRVAdapter;
import com.example.gamingsmlogin.classes.ControlReturn;
import com.example.gamingsmlogin.classes.ImgUpload;
import com.example.gamingsmlogin.classes.Message;
import com.example.gamingsmlogin.classes.MessageReturn;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.interfaces.IServices;
import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatSingleManagement extends BaseManagement {
    RecyclerView recyclerView;
    List<Message> list;
    ChatRVAdapter chatAdapter;
    String receiver  ;// i.getStringExtra("userId");
    String Id;
    int defGetData=10;
    IServices services = ApiUtils.getIServices();
    public ChatSingleManagement(RecyclerView recyclerView, List<Message> list, String receiver, Context context) {
        super(context);
        this.recyclerView = recyclerView;
        this.list = list;
        this.receiver = receiver;

        UserDAO dao = new UserDAO();
        DbHelper db = new DbHelper(context);
        Id =String.valueOf( dao.getSQLUser(db).get(0).getUserID());
        getSingleChat(0);
    }
    public void sendMessage(String s) {
        messageControlSend(s,"addmessage");
    }
    public void newSendMessage(String s) {
        messageControlSend(s,"newaddmessage");
    }

    public void controlSendMessage(String s){
        if(list.size()!=0){
            Log.e("TAG", "onClick: "+"1" +list.size()+"/");
            sendMessage(s) ;
        }else{
            Log.e("TAG", "onClick: "+"2" +list.size()+"/");
            newSendMessage(s);
        }
    }

    public void messageControlSend(String s,String Method){
        services.addMessage(Method, Id,receiver,s,"1","0").enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {getSingleChat(0 );}
            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("ChatSingleManagement", "connection error messageControlSend()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void newDataRecy(){
        defGetData+=10;
        getSingleChat(1);
    }
    public void selectedimagepath(Intent data,String type){
        ImgUpload im = new ImgUpload(context);
        //postimg.setImageBitmap(BitmapFactory.decodeFile(mediaPath));//seçilen datanın stringi yolu;
        uploadFile(type,im.selectimgPath(data));
    }
    private void uploadFile(String type ,String mediaPath) {
        // Map is used to multipart the file using okhttp3.RequestBody
        File f = new File(mediaPath);
        // Parsing any Media type file

        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), f);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file",  f.getName(), requestBody);
        RequestBody fileName;
        String typeName;
        if(type.equals("3")){
            fileName = RequestBody.create(MediaType.parse("video/*"), f);
            typeName="video";
        }else{
            fileName = RequestBody.create(MediaType.parse("text/plain"), f.getName());
            typeName="fotograf";
        }

        final ProgressDialog progressDialog = new ProgressDialog(context, R.style.MSDefaultStepperLayoutTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("yüklenyor lütfen bekleyiniz...");
        progressDialog.show();
        //dbDIF.addMessageForImage resim için
        services.sendMessageFile("sendMessageFile",fileToUpload,fileName, Id,receiver,typeName+"dosyası yüklendi",type,"0").enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                ControlReturn s = response.body();
                if (s.getControl().equals("0")) {
                    Toast.makeText(context, typeName+" yüklendi", Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("başarısız", s.toString());
                    Toast.makeText(context, typeName+"t yüklenemedi", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
                getSingleChat(0);
            }

            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("ChatSingleManagement", "connection error uploadFile()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
    public void getSingleChat(int HowStart){
        services.getChat("getChat", Id,receiver,String.valueOf(defGetData)).enqueue(new Callback<MessageReturn>() {
            @Override
            public void onResponse(Call<MessageReturn> call, Response<MessageReturn> response) {
                list= response.body().getMessage();
                chatAdapter = new ChatRVAdapter(list,context);
                recyclerView.setAdapter(chatAdapter);
                if(HowStart==0){
                    recyclerView.scrollToPosition(list.size() - 1);
                }
            }
            @Override
            public void onFailure(Call<MessageReturn> call, Throwable t) {
                Log.e("ChatSingleManagement", "connection error getSingleChat()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
