package com.phamminhtri.hd_wallpaper.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class ImageModel {
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
    @SerializedName("albumid")
    @Expose
    private String albumid;

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

    public String getAlbumid() {
        return albumid;
    }

    public void setAlbumid(String albumid) {
        this.albumid = albumid;
    }

}