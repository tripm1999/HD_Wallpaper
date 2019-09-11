package com.phamminhtri.hd_wallpaper.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitClient {
    private static Retrofit retrofit;

    public static Retrofit getClient(String baseUrl) {
        OkHttpClient builder = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)//thời gian chờ kết nối vs sever, quá là hủy
                .readTimeout(5000, TimeUnit.MILLISECONDS)//thời gian chờ dữ liệu
                .retryOnConnectionFailure(true)//nếu cái sever có vấn đề j nó sẽ khôi phục lại thử lại cái kết nối đó.
                .build();
      Gson gson = new GsonBuilder().setLenient().create();// khởi tạo cái này truyền vào ConverterFactory để tránh trường hợp bị lỗi khi chuyển json về java
        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)// chỉ định phần đầu Api là gì
                .client(builder)
                .addConverterFactory(GsonConverterFactory.create(gson))//lấy dữ liệu json về chuyển về biến của java thông qua libery gson
                .build();
        return retrofit;
    }
}
