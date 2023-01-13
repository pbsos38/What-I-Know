package com.example.prince;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class SliderBean {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("imageName")
    @Expose
    private String imageName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
