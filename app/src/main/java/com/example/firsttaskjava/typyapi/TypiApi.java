package com.example.firsttaskjava.typyapi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TypiApi {
    private URL url;
    private HttpURLConnection con;
    private InputStream inputStream;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;

    public String receiveFromNetwork(String link){
        try{
            return getAlbumsFromApi(link);
        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            closeCon();
        }
        return null;
    }

    private String getAlbumsFromApi(String link) throws IOException {
        url=new URL(link);
        con= (HttpURLConnection) url.openConnection();
        inputStream=con.getInputStream();
        inputStreamReader=new InputStreamReader(inputStream);
        bufferedReader=new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder=new StringBuilder();
        String line;
        while((line=bufferedReader.readLine())!=null){
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    private void closeCon(){
        try{
            bufferedReader.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        try{
            inputStreamReader.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        try{
            inputStream.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }
        con.disconnect();
    }
}
