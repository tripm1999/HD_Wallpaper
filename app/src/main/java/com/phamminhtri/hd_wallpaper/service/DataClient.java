package com.phamminhtri.hd_wallpaper.service;

import com.phamminhtri.hd_wallpaper.model.AlbumModel;
import com.phamminhtri.hd_wallpaper.model.CategoryModel;
import com.phamminhtri.hd_wallpaper.model.ImageModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataClient {
    @GET("categoryjson")
    Call<List<CategoryModel>> listCategory();

     @GET("albumjson")
    Call<List<AlbumModel>> listAlbum();

     @GET("imagejson")
    Call<List<ImageModel>>  listImg(@Query(":pId")String idalbum);
}
