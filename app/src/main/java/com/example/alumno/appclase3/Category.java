package com.example.alumno.appclase3;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alumno on 08/09/2016.
 */
public class Category{

    private String title;
    private String description;
    private String celular;

    public Category(String title, String description, String cel){
        this.title = title;
        this.description = description;
        this.celular = cel;
    }

    public String getTitle(){
         return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getCelular(){
        return this.celular;
    }

    public void setCelular(String cel){
        this.celular = cel;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String desc){
        this.description = desc;
    }


}
