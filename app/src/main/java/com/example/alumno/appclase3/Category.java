package com.example.alumno.appclase3;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alumno on 08/09/2016.
 */
public class Category{

    private String titulo;
    private String desc;
    private String url_foto;
    private Integer id;
    private String createdAt;



    public Category(String titulo, String desc, String url_foto, Integer id){
        this.titulo = titulo;
        this.desc = desc;
        this.url_foto = url_foto;
        this.id = id;
    }
    public String getTitulo(){
         return this.titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getDesc(){
        return this.desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getUrl_foto() {
        return url_foto;
    }

    public void setUrl_foto(String url_foto) {
        this.url_foto = url_foto;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
