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

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCountalbum() {
        return countalbum;
    }

    public void setCountalbum(Integer countalbum) {
        this.countalbum = countalbum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
