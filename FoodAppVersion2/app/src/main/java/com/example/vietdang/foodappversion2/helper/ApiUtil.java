package com.example.vietdang.foodappversion2.helper;


import com.example.vietdang.foodappversion2.util.Client;

/**
 * Created by PaulDuong on 16/09/2017.
 */

public class ApiUtil {
    public  static  final  String BASE_URL="http://kltotnghiepv2.azurewebsites.net/";
    public static APIService getAPIService(){
        return Client.getRetrofit(BASE_URL).create(APIService.class);
    }
}