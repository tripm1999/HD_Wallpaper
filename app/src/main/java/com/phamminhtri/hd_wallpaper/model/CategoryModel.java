package com.phamminhtri.hd_wallpaper.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryModel {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("countalbum")
    @Expose
    private Integer countalbum;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;

    public String getImage() {
        return image;
    }

    public Integer getCountalbum() {
        return countalbum;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
