package com.example.alumno.appclase3;


import android.net.Uri;
import android.os.Handler;
import android.os.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by alumno on 06/10/2016.
 */
public class RequestThread implements Runnable {
    private Handler handler;
    private ArrayList<Category> categories;
    private String requestMethodName;
    private String apiKey;
    private TreeMap<String,String> methodParams;


    public RequestThread(Handler handler, String requestMethodName, TreeMap<String,String> params){
        this.handler = handler;
        this.categories = new ArrayList<>();
        this.requestMethodName = requestMethodName;
        this.methodParams = params;
    }

    public void setApiKey(String apiKey){
        this.apiKey = apiKey;
    }
    @Override
    public void run() {

        HttpManager manager = new HttpManager();
        Message msg = new Message();
        RequestResponse result;
        try {
            Uri.Builder params = new Uri.Builder();
            switch (this.requestMethodName){
                case "register":
                    params.appendQueryParameter("nombre", methodParams.get("nombre"));
                    params.appendQueryParameter("apellido",methodParams.get("apellido"));
                    params.appendQueryParameter("usuario",methodParams.get("usuario"));
                    params.appendQueryParameter("email", methodParams.get("email"));
                    params.appendQueryParameter("password", methodParams.get("password"));
                    result =  manager.httpPost("http://lkdml.myq-see.com/register", params);
                    msg.arg1 = 1;
                    msg.obj = result;
                case "login":
                    params.appendQueryParameter("email", methodParams.get("email"));
                    params.appendQueryParameter("password", methodParams.get("password"));
                    result = manager.httpLogin("http://lkdml.myq-see.com/login", params);
                    msg.arg1 = 1;
                    msg.obj = result;
                    break;
                case "getList":
                    params.appendQueryParameter("email", methodParams.get("email"));
                    params.appendQueryParameter("password", methodParams.get("password"));
                    result =  manager.httpGetCategories("http://lkdml.myq-see.com/categorias", this.apiKey);
                    msg.arg1 = 1;
                    msg.obj = result;
                    break;
                case "addCategory":
                    params.appendQueryParameter("titulo", methodParams.get("titulo"));
                    params.appendQueryParameter("descripcion", methodParams.get("descripcion"));
                    result = manager.httpAddCategory("http://lkdml.myq-see.com/categorias",params, this.apiKey);
                    msg.arg1 = 1;
                    msg.obj = result;
                    break;
                case "modifyCategory":
                    params.appendQueryParameter("titulo", methodParams.get("titulo"));
                    params.appendQueryParameter("descripcion", methodParams.get("descripcion"));
                    params.appendQueryParameter("categoria_id", methodParams.get("id"));
                    result = manager.httpModifyCategory("http://lkdml.myq-see.com/categorias",params, this.apiKey);
                    msg.arg1 = 1;
                    msg.obj = result;
                    break;
                case "deleteCategory":
                    result = manager.httpDeleteCategory("http://lkdml.myq-see.com/categorias/"+methodParams.get("category_id"),this.apiKey);
                    msg.arg1 = 2;
                    msg.arg2 = Integer.parseInt(methodParams.get("category_id"));
                    msg.obj = result;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        handler.sendMessage(msg);
    }

    public String getRequestMethodName() {
        return requestMethodName;
    }

    public void setRequestMethodName(String requestMethodName) {
        this.requestMethodName = requestMethodName;
    }

    public void setMethodParams(TreeMap<String, String> methodParams) {
        this.methodParams = methodParams;
    }
}
