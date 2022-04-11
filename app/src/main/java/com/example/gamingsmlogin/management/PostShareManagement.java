package com.example.gamingsmlogin.management;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gamingsmlogin.R;
import com.example.gamingsmlogin.adapters.PostShareRVAdapter;
import com.example.gamingsmlogin.classes.File;
import com.example.gamingsmlogin.classes.FilesReturn;
import com.example.gamingsmlogin.classes.ControlReturn;
import com.example.gamingsmlogin.classes.ImgUpload;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.interfaces.IServices;
import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
//düzeltilmedi büyük değişiklik geçirecek
public class PostShareManagement extends BaseManagement {
    RecyclerView recyclerView;
    List<File> list;
    String Id;
    PostShareRVAdapter postShareAdapter;
    IServices services = ApiUtils.getIServices();
    public PostShareManagement(RecyclerView recyclerView, List<File> list, Context context) {
        super(context);
        this.recyclerView = recyclerView;
        this.list = list;
        UserDAO dao = new UserDAO();
        DbHelper db = new DbHelper(context);
        Id =String.valueOf( dao.getSQLUser(db).get(0).getUserID());
    }
    public void selectedImagePath(Intent data, String type){
        ImgUpload im = new ImgUpload(context);
        uploadFile(type,data, im.selectimgPath(data));
    }
    private void uploadFile(String type,Intent filedata,String mediaPath) {
        // Map is used to multipart the file using okhttp3.RequestBody
        final java.io.File file = new java.io.File(mediaPath);
        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);

        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file",  file.getName(), requestBody);
        // create RequestBody instance from file
        RequestBody fileName;
        String typeName;

        if(type.equals("0")){
            fileName = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            typeName="fotoğraf";
        }else{
            fileName = RequestBody.create(MediaType.parse("video/*"), file);
            typeName="video";
            // MultipartBody.Part is used to send the actual file
        }
        final ProgressDialog progressDialog = new ProgressDialog(context, R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(typeName+" yüklenyor lütfen bekleyiniz...");
        progressDialog.show();
        //dbDIF.addMessageForImage resim için
        services.addImages("addImages",fileToUpload,fileName,type).enqueue(new Callback<FilesReturn>() {
            @Override
            public void onResponse(Call<FilesReturn> call, Response<FilesReturn> response) {
                /*List<File> serverResponse = response.body().getFiles(); //url değeri için
                serverResponse.get(0).setStorageUrl(filedata.getData().toString());
                list.add(  serverResponse.get(0));*/
                File file = response.body().getFiles().get(0); //url değeri için
                file.setStorageUrl(filedata.getData().toString());
                Log.e("onResponse", "urlimg: "+file.getFileId() );
                list.add(file);
                postShareAdapter = new PostShareRVAdapter(list,context);
                recyclerView.setAdapter(postShareAdapter);
                recyclerView.scrollToPosition(list.size() - 1);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<FilesReturn> call, Throwable t) {
                Log.e("PostShareManagement", "connection error uploadFile()",t );
                Toast.makeText(context, "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    public void sendPost(EditText val) {
        final ProgressDialog progressDialog = new ProgressDialog(context, R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("paylaşılıyor lütfen bekleyiniz...");
        progressDialog.show();
        //dbDIF.addMessageForImage resim için


        String dataIsNull = ((list.size() == 0) ? "1" : "0"); //if else
        ArrayList<String> filesId = new ArrayList<>();
        if(dataIsNull.equals("0")) {
            for (int i = 0; i < list.size(); i++) {
                filesId.add(list.get(i).getFileId());
            }
        }

        services.postSend("postSend", Id,val.getText().toString().trim(),dataIsNull,filesId).enqueue(new Callback<ControlReturn>() {
            @Override
            public void onResponse(Call<ControlReturn> call, Response<ControlReturn> response) {
                String serverResponse = response.body().getControl();
                Log.e("TAG", "sendPost: "+serverResponse);
                list= new ArrayList<>();
                val.setText("");
                postShareAdapter = new PostShareRVAdapter(list,context);
                recyclerView.setAdapter(postShareAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<ControlReturn> call, Throwable t) {
                Log.e("PostShareManagement", "connection error sendPost()",t );;
                Toast.makeText(context, "paylaşılırken bir hata oluştu", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }
}
