package com.phamminhtri.hd_wallpaper.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbuminCate {
        @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("view")
    @Expose
    private Integer view;
    @SerializedName("love")
    @Expose
    private Integer love;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("categoryid")
    @Expose
    private String categoryid;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    public Integer getLove() {
        return love;
    }

    public void setLove(Integer love) {
        this.love = love;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

}
