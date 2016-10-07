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

    public byte[] httpGet(String urlString)throws IOException{
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();

        int response = conn.getResponseCode();

        if(response == 200){
            InputStream is = conn.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int length = 0;
            while ((length = is.read(buffer)) != -1){
                baos.write(buffer,0,length);
            }

            is.close();
            return baos.toByteArray();
        }else
            return new byte[1];
    }
}
