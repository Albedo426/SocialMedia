package com.example.gamingsmlogin.sqlstorage;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.gamingsmlogin.classes.User;

import java.util.ArrayList;

public class UserDAO {

    //GETUSER user;
    public void insertUser(DbHelper db, User u){//kayıt giriş
        SQLiteDatabase dbx= db.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put("userID",u.getUserID());
        cv.put("name",u.getName());
        cv.put("lastName",u.getLastName());
        cv.put("email",u.getEmail());
        cv.put("userName",u.getUserName());
        cv.put("password",u.getPassword());
        cv.put("phone",u.getPhone());
        cv.put("profilePhoto",u.getProfilePhoto());
        cv.put("loginDate",u.getLoginDate());
        cv.put("birthDate",u.getBirthDate());
        dbx.insertOrThrow("user",null,cv);
        dbx.close();
    }

    public static void deleteEntry(DbHelper db)
    {
        SQLiteDatabase dbx=db.getWritableDatabase();
        int numberOFEntriesDeleted=  dbx.delete("user", null, null);
    }

    public ArrayList<User> getSQLUser(DbHelper db){
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase dbx = db.getWritableDatabase();
        Cursor c = dbx.rawQuery("SELECT * FROM user",null);
        while(c.moveToNext()){
            User user = new User(c.getInt(c.getColumnIndex("userID")),
                    c.getString(c.getColumnIndex("name")),
                    c.getString(c.getColumnIndex("lastName")),
                    c.getString(c.getColumnIndex("email")),
                    c.getString(c.getColumnIndex("userName")),
                    c.getString(c.getColumnIndex("password")),
                    c.getString(c.getColumnIndex("phone")),
                    c.getString(c.getColumnIndex("profilePhoto")),
                    c.getString(c.getColumnIndex("loginDate")),
                    c.getString(c.getColumnIndex("birthDate")));
            users.add(user);
        }
        return users;
    }

    public boolean usersCount(DbHelper db){
        SQLiteDatabase dbx=db.getWritableDatabase();

        Cursor c= dbx.rawQuery("select * from user ",null);

        ArrayList<User> count = new ArrayList<>();
        while(c.moveToNext()){
            count.add(new User(c.getInt(c.getColumnIndex("userID")),
                    c.getString(c.getColumnIndex("name")),
                    c.getString(c.getColumnIndex("lastName")),
                    c.getString(c.getColumnIndex("email")),
                    c.getString(c.getColumnIndex("userName")),
                    c.getString(c.getColumnIndex("password")),
                    c.getString(c.getColumnIndex("phone")),
                    c.getString(c.getColumnIndex("profilePhoto")),
                    c.getString(c.getColumnIndex("loginDate")),
                    c.getString(c.getColumnIndex("birthDate"))));
        }
        return count.size() == 1; // == 0 sadece userManangement için denedikten sonra düzelt ayrıca 0 yaptığın için 0 true dönecek şifre istemeden giriş için false döndüğü zaman şifresiz giriş yapacaksın.
    }

}
