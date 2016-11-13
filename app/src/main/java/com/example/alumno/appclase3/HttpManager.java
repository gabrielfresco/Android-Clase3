package com.example.alumno.appclase3;

import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alumno on 06/10/2016.
 */
public class HttpManager {

    public RequestResponse httpPost(String urlString, Uri.Builder params)throws IOException{
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

        //if(response == 200 || response == 201 || response == 202){
            InputStream is = conn.getInputStream();
            String respuesta = new String(readFully(is),"UTF-8");
            RequestResponse requestResponse = new RequestResponse();
            try {
                Gson gson = new Gson();
                requestResponse = gson.fromJson(respuesta, RequestResponse.class);
                return requestResponse;

            }catch (Exception e){
                e.printStackTrace();
            }
            requestResponse.error = true;
            return requestResponse;
       // }
    }

    public RequestResponse httpLogin(String urlString, Uri.Builder  params) throws IOException {
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
        RequestResponse requestResponse = new RequestResponse();
        if(response == 200 || response == 201 || response == 202){
            InputStream is = conn.getInputStream();
            String respuesta = new String(readFully(is),"UTF-8");
            Log.e("RESPONSE", respuesta);
            try {
                Gson gson = new Gson();
                requestResponse = gson.fromJson(respuesta, RequestResponse.class);
                if(requestResponse.error)
                    return requestResponse;
                else
                    return requestResponse;
            }catch (Exception e){
                e.printStackTrace();
            }
            requestResponse.error = true;
            return requestResponse;
        }else
            requestResponse.error = true;
            return requestResponse;
    }

    public RequestResponse httpGetCategories(String urlString, String apiKey)throws IOException{
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("AUTHORIZATION", apiKey);
        conn.connect();
        int response = conn.getResponseCode();

        RequestResponse requestResponse = new RequestResponse();
        if(response == 200 || response == 201 || response == 202){
            InputStream is = conn.getInputStream();
            String respuesta = new String(readFully(is),"UTF-8");
            Log.e("RESPONSE", respuesta);
            try {
                Gson gson = new Gson();
                requestResponse = gson.fromJson(respuesta, RequestResponse.class);
                return requestResponse;

            }catch (Exception e){
                e.printStackTrace();
            }
            return requestResponse;
        }else{
            requestResponse.error = true;
            return requestResponse;
        }
    }

    public RequestResponse httpAddCategory(String urlString, Uri.Builder params, String apiKey)throws IOException{
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("AUTHORIZATION", apiKey);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
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
        RequestResponse requestResponse = new RequestResponse();

        if(response == 200 || response == 201 || response == 202){
        InputStream is = conn.getInputStream();
        String respuesta = new String(readFully(is),"UTF-8");
        try {
            Gson gson = new Gson();
            requestResponse = gson.fromJson(respuesta, RequestResponse.class);
            return requestResponse;

        }catch (Exception e){
            e.printStackTrace();
        }
        requestResponse.error = true;
        return requestResponse;
        }else{
            requestResponse.error = true;
            requestResponse.message = "404 not found";
            return  requestResponse;
        }
    }

    public RequestResponse httpModifyCategory(String urlString, Uri.Builder params, String apiKey)throws IOException{
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("AUTHORIZATION", apiKey);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
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
        RequestResponse requestResponse = new RequestResponse();

        if(response == 200 || response == 201 || response == 202){
            InputStream is = conn.getInputStream();
            String respuesta = new String(readFully(is),"UTF-8");
            try {
                Gson gson = new Gson();
                requestResponse = gson.fromJson(respuesta, RequestResponse.class);
                return requestResponse;

            }catch (Exception e){
                e.printStackTrace();
            }
            requestResponse.error = true;
            return requestResponse;
        }else{
            requestResponse.error = true;
            requestResponse.message = "404 not found";
            return  requestResponse;
        }
    }

    public RequestResponse httpDeleteCategory(String urlString, String apiKey)throws IOException{
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("DELETE");
        conn.setRequestProperty("AUTHORIZATION", apiKey);
        conn.connect();

        int response = conn.getResponseCode();
        RequestResponse requestResponse = new RequestResponse();

        if(response == 200 || response == 201 || response == 202){
            InputStream is = conn.getInputStream();
            String respuesta = new String(readFully(is),"UTF-8");
            try {
                Gson gson = new Gson();
                requestResponse = gson.fromJson(respuesta, RequestResponse.class);
                return requestResponse;

            }catch (Exception e){
                e.printStackTrace();
            }
            requestResponse.error = true;
            return requestResponse;
        }else{
            requestResponse.error = true;
            requestResponse.message = "404 not found";
            return  requestResponse;
        }
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
