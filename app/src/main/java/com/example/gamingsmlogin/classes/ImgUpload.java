package com.example.gamingsmlogin.classes;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class ImgUpload {
    Context myContext;
    public ImgUpload(Context myContext) {
        this.myContext = myContext;
    }

    public String  selectimgPath(Intent data){
        // Get the Image from data
        String mediaPath;
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};

        Cursor cursor = myContext.getContentResolver().query(selectedImage, filePathColumn, null, null, null);
        assert cursor != null;
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        mediaPath = cursor.getString(columnIndex);
        // Set the Image in ImageView for Previewing the Media
        cursor.close();
        return mediaPath;
    }
    public void imguploadServer(){

    }
}
