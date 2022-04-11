package com.example.gamingsmlogin.sqlstorage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {


    public DbHelper(Context context ){

        super(context, "db", null, 1);

    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user (" +
                "uId INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                "userID INTEGER,"+
                "name TEXT," +
                "lastName TEXT," +
                "email TEXT," +
                "userName TEXT," +
                "password TEXT," +
                "phone TEXT," +
                "profilePhoto TEXT," +
                "loginDate TEXT," +
                "birthDate TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        onCreate(db);
    }
}
