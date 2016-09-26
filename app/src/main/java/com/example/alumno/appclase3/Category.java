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

    public Category(String title, String description, Boolean fav){
        this.title = title;
        this.description = description;
        this.is_fav = fav;
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

}
