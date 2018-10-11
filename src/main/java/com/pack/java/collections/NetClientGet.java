package com.pack.java.collections;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.http.client.methods.HttpPost;

public class NetClientGet {

    // http://localhost:8080/RESTfulExample/json/product/get
    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(baseUrl + path);
            URLConnection urlConnection = setUsernamePassword(url);
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
 
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
