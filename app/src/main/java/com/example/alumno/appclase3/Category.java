package com.example.alumno.appclase3;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alumno on 08/09/2016.
 */
public class Category{

    private String title;
    private String description;
    private Boolean is_fav;
    private String imageSrc;



    public Category(String title, String description, Boolean fav, String imageSrc){
        this.title = title;
        this.description = description;
        this.is_fav = fav;
        this.imageSrc = imageSrc;
    }
    public String getTitle(){
         return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String desc){
        this.description = desc;
    }

    public Boolean getIsfav() {
        return is_fav;
    }

    public void setIsfav(Boolean is_fav) {
        this.is_fav = is_fav;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

}
