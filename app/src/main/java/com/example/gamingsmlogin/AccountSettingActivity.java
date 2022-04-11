package com.example.gamingsmlogin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamingsmlogin.classes.CircleTransform;
import com.example.gamingsmlogin.classes.File;
import com.example.gamingsmlogin.classes.FilesReturn;
import com.example.gamingsmlogin.classes.ImgUpload;
import com.example.gamingsmlogin.classes.User;
import com.example.gamingsmlogin.dbconnection.ApiUtils;
import com.example.gamingsmlogin.interfaces.IServices;
import com.example.gamingsmlogin.management.UsersManagement;
import com.example.gamingsmlogin.sqlstorage.DbHelper;
import com.example.gamingsmlogin.sqlstorage.UserDAO;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountSettingActivity extends AppCompatActivity {

    /**
     * verilerin değiştirildiği inputlar düzgün formata konucak
     * resim değiştirme kısmı eklenecek hepsi User u değişkeni üzerinde yapılıp o gönderilicek
     * toolbar eklenecek tik işareti olucak basılınca güncelleme işlemi yapılıcak
     * **/
    private static final String TAG ="AccountSettingActivity";
    TextInputEditText name,email,phone,password,userName,lastName;
    Button saveButton;
    ImageView prfImg;
    String myPhotoUrl="";
    IServices services = ApiUtils.getIServices();
    UsersManagement um= new UsersManagement(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        name=findViewById(R.id.NameTextInput);
        email=findViewById(R.id.userEmailTextInput);
        phone=findViewById(R.id.userPhoneTextInput);
        saveButton=findViewById(R.id.saveButton);
        password=findViewById(R.id.userPasswordTextInput);
        userName=findViewById(R.id.userNameTextInput);
        lastName=findViewById(R.id.lastNameTextInput);
        prfImg=findViewById(R.id.imageViewProfile);
        UserDAO dao = new UserDAO();
        DbHelper db = new DbHelper(this);
        User u=dao.getSQLUser(db).get(0);
        init(u);
    }
    public void init(User u){
        setData(u);

       saveButton.setOnClickListener(v -> {
        Log.e(TAG, myPhotoUrl );
            User newUser = new User(u.getUserID(),name.getText().toString(),lastName.getText().toString(),
                    email.getText().toString(),userName.getText().toString(),password.getText().toString(),phone.getText().toString(),myPhotoUrl);
            um.updateUser(newUser);

        });

        prfImg.setOnClickListener(v -> {
            getfile(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        });
    }
    private void getfile(Uri uri) {
        Intent i  = new Intent(Intent.ACTION_PICK,uri);
        startActivityForResult(i,0);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // When an Image is picked
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && null != data) {
            selectedImagePath(data);
        }
    } // When an Video is picked
    public void selectedImagePath(Intent data){
        ImgUpload im = new ImgUpload(this);
        uploadFile(data, im.selectimgPath(data));
    }
    private void uploadFile(Intent filedata,String mediaPath) {
        // Map is used to multipart the file using okhttp3.RequestBody
        final java.io.File file = new java.io.File(mediaPath);
        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);

        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file",  file.getName(), requestBody);
        // create RequestBody instance from file
        RequestBody fileName;
        String typeName;
            fileName = RequestBody.create(MediaType.parse("text/plain"), file.getName());
            typeName="fotoğraf";

        final ProgressDialog progressDialog = new ProgressDialog(this, R.style.Theme_AppCompat_DayNight_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(typeName+" yüklenyor lütfen bekleyiniz...");
        progressDialog.show();
        //dbDIF.addMessageForImage resim için
        services.addImages("addImages",fileToUpload,fileName,"0").enqueue(new Callback<FilesReturn>() {
            @Override
            public void onResponse(Call<FilesReturn> call, Response<FilesReturn> response) {
                /*List<File> serverResponse = response.body().getFiles(); //url değeri için
                serverResponse.get(0).setStorageUrl(filedata.getData().toString());
                list.add(  serverResponse.get(0));*/
                File file = response.body().getFiles().get(0); //url değeri için
                file.setStorageUrl(filedata.getData().toString());
                Log.e("onResponse", "urlimg: "+file.getUrl() );
                Picasso.with(getApplicationContext()).load(ApiUtils.BASE_URL+"images/"+ file.getUrl() ).transform(new CircleTransform()).into(prfImg);
                myPhotoUrl=file.getUrl() ;
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call<FilesReturn> call, Throwable t) {
                Log.e("PostShareManagement", "connection error uploadFile()",t );
                Toast.makeText(getApplicationContext(), "bir hata meydana geldi", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    void setData(User u){
        name.setText(u.getName());
        email.setText(u.getEmail());
        phone.setText(u.getPhone());
        password.setText(u.getPassword());
        userName.setText(u.getUserName());
        lastName.setText(u.getLastName());
        if(u.getProfilePhoto().equals("") ||u.getProfilePhoto().equals(" ")) {
            Picasso.with(this).load(R.drawable.def_profile).transform(new CircleTransform()).into(prfImg);
            myPhotoUrl="";
        }else{
            Picasso.with(this).load(ApiUtils.BASE_URL+"images/"+ u.getProfilePhoto()).transform(new CircleTransform()).into(prfImg);
            myPhotoUrl=u.getProfilePhoto();
        }

    }
}