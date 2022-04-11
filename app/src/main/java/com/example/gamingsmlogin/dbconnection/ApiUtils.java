package com.example.gamingsmlogin.dbconnection;

import com.example.gamingsmlogin.interfaces.IServices;

public class ApiUtils {

    public static final String BASE_URL = "http://192.168.1.28:8080/GamingSM/";

    public static IServices getIServices(){
        return RetrofitClient.getClient(BASE_URL).create(IServices.class);
    }

}
