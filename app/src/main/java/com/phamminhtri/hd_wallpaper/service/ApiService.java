package com.phamminhtri.hd_wallpaper.service;


public class ApiService {

    private static final String Base_Url = "http://192.168.1.5:3000/";
//    public static final String Base_Url = "http://192.168.198.102:3000/";

    public static DataClient getData() {
        return RetrofitClient.getClient(Base_Url).create(DataClient.class);
    }
}
