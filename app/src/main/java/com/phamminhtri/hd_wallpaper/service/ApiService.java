package com.phamminhtri.hd_wallpaper.service;


public class ApiService {

    public static final String Base_Url = "http://192.168.198.102:3000/";

    public static DataClient getData() {
        return RetrofitClient.getClient(Base_Url).create(DataClient.class);
    }
}
