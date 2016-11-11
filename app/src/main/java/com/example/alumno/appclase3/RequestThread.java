package com.example.alumno.appclase3;


import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by alumno on 06/10/2016.
 */
public class RequestThread implements Runnable {
    private Handler handler;
    private ArrayList<Category> categories;
    private String requestMethodName;
    private String apiKey;

    public RequestThread(Handler handler, String requestMethodName){
        this.handler = handler;
        this.categories = new ArrayList<>();
        this.requestMethodName = requestMethodName;
    }

    public void setApiKey(String apiKey){
        this.apiKey = apiKey;
    }
    @Override
    public void run() {

        HttpManager manager = new HttpManager();
        Message msg = new Message();
        String infoString = "";
        String result;
        try {
            Uri.Builder params = new Uri.Builder();
            switch (this.requestMethodName){
                case "register":
                    throw new IOException();
                case "login":
                    params.appendQueryParameter("email", "franco@gmail.com");
                    params.appendQueryParameter("password", "franco123");
                    Object loginResponse = manager.httpLogin("http://lkdml.myq-see.com/login", params);
                    msg.arg1 = 1;
                    msg.obj = loginResponse;
                    break;
                case "getList":
                    params.appendQueryParameter("email", "franco@gmail.com");
                    params.appendQueryParameter("password", "franco123");
                    result = manager.httpGetCategories("http://lkdml.myq-see.com/categorias", "5cd87a6c8bd6c2fbaea3919c7671fbb2");
                    msg.arg1 = 1;
                    msg.obj = result;
                    //infoString = manager.httpRegister()
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

      /*  Message msg = new Message();
        categories.add(new Category("Deportes",infoString, true,"https://s-media-cache-ak0.pinimg.com/originals/09/7c/7c/097c7c15103d99cb550b364ea5fdb4bc.jpg"));
        categories.add(new Category("Musica", "Descripcion mas corta", true, ""));
        categories.add(new Category("Videos", "Los mejores videos los podes encontrar en esta categoria", false , ""));
        categories.add(new Category("Juegos", "Los juegos mas entretenidos", false , ""));
        categories.add(new Category("Comida", "La mejor comida para que disfrutes", false , ""));
        categories.add(new Category("Baile", "Encontra la infromacion sobre los mejores eventos de baile", false, ""));
        msg.arg1 = 1;
        msg.obj = categories;*/

        handler.sendMessage(msg);
    }
}
