package com.example.alumno.appclase3;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by alumno on 06/10/2016.
 */
public class HttpManager {

    public String httpPost(String urlString, Uri.Builder params)throws IOException{
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        String query = params.build().getEncodedQuery();
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();

        conn.connect();

        int response = conn.getResponseCode();

        if(response == 200 || response == 201 || response == 202){
            InputStream is = conn.getInputStream();
            String respuesta = new String(readFully(is),"UTF-8");

            try {
                Gson gson = new Gson();
                RegisterResponse registerResponse = gson.fromJson(respuesta, RegisterResponse.class);
                if(registerResponse.error)
                    return registerResponse.message;
                else
                    return "ok";
            }catch (Exception e){
                e.printStackTrace();
            }
            return "ok";
        }else
            return "error";
    }

    public Object httpLogin(String urlString, Uri.Builder  params) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setDoOutput(true);
        String query = params.build().getEncodedQuery();
        OutputStream os = conn.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
        writer.write(query);
        writer.flush();
        writer.close();
        os.close();
        conn.connect();

        int response = conn.getResponseCode();

        if(response == 200 || response == 201 || response == 202){
            InputStream is = conn.getInputStream();
            String respuesta = new String(readFully(is),"UTF-8");
            Log.e("RESPONSE", respuesta);
            try {
                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(respuesta, LoginResponse.class);
                if(loginResponse.error)
                    return loginResponse;
                else
                    return loginResponse;
            }catch (Exception e){
                e.printStackTrace();
            }
            return "ok";
        }else
            return "error";
    }

    public String httpGetCategories(String urlString, String apiKey)throws IOException{
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("AUTHORIZATION", apiKey);
        conn.connect();
        int response = conn.getResponseCode();

        if(response == 200 || response == 201 || response == 202){
            InputStream is = conn.getInputStream();
            String respuesta = new String(readFully(is),"UTF-8");
            Log.e("RESPONSE", respuesta);
            try {
                Gson gson = new Gson();
                LoginResponse loginResponse = gson.fromJson(respuesta, LoginResponse.class);
                if(loginResponse.error)
                    return loginResponse.message;
                else
                    return loginResponse.toString();
            }catch (Exception e){
                e.printStackTrace();
            }
            return "ok";
        }else
            return "error";
    }

    private byte[] readFully(InputStream inputStream)throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        inputStream.close();
        return baos.toByteArray();
    }


}
