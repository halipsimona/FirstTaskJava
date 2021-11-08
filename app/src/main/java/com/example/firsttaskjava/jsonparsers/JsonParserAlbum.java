package com.example.firsttaskjava.jsonparsers;

import com.example.firsttaskjava.util.Album;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParserAlbum {
    private static final String ID="id";
    private static final String USER_ID="userId";
    private static final String TITLE="title";

    private static Album readAlbum(JSONObject jsonObject) throws JSONException{
        int userId= jsonObject.getInt(USER_ID);
        int Id= jsonObject.getInt(ID);
        String title=jsonObject.getString(TITLE);

        return new Album(userId,Id,title);
    }

    private static List<Album> readAlbums(JSONArray array) throws JSONException {
        List<Album> albums=new ArrayList<>();
        for(int i=0;i<array.length();i++){
            albums.add(readAlbum(array.getJSONObject(i)));
        }
        return albums;
    }

    public static List<Album> fromJSON(String json)  {
        try{
            JSONArray jsonArray=new JSONArray(json);
            return readAlbums(jsonArray);
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }
}
