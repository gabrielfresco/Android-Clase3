package com.example.alumno.appclase3;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alumno on 06/10/2016.
 */
public class HttpManager {

    public String httpRegister(String urlString, String nom, String ape, String usr, String email, String pass)throws IOException{
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("nombre", nom);
        conn.setRequestProperty("apellido", ape);
        conn.setRequestProperty("email", email);
        conn.setRequestProperty("usuario", usr);
        conn.setRequestProperty("password", pass);
        conn.connect();

        int response = conn.getResponseCode();

        if(response == 200){
            return "ok";
        }else
            return "error";
    }
}
