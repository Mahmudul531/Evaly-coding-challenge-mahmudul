package com.example.codingtaskfromevaly.Features.Categories;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public  class CategoriModel {

    @Expose
    @SerializedName("image_url")
    private String image_url;
    @Expose
    @SerializedName("slug")
    private String slug;
    @Expose
    @SerializedName("name")
    private String name;

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
