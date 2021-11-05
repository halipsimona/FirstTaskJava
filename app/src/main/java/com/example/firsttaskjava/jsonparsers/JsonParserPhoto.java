package com.example.firsttaskjava.jsonparsers;

import android.util.Log;

import com.example.firsttaskjava.util.Photo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonParserPhoto {
    private static final String ALBUM_ID="albumId";
    private static final String ID="id";
    private static final String TITLE="title";
    private static final String URL="url";
    private static final String THUMBNAIL_URL="thumbnailUrl";

    private static Photo readPhoto(JSONObject jsonObject) throws JSONException {
        int albumId= jsonObject.getInt(ALBUM_ID);
        int Id= jsonObject.getInt(ID);
        String title=jsonObject.getString(TITLE);
        String url=jsonObject.getString(URL);
        String thumbnailUrl=jsonObject.getString(THUMBNAIL_URL);

        return new Photo(Id,albumId,title,url,thumbnailUrl);
    }

    private static List<Photo> readPhotos(JSONArray array) throws JSONException {
        List<Photo> photos=new ArrayList<>();
        for(int i=0;i<array.length();i++){
            photos.add(readPhoto(array.getJSONObject(i)));
        }
        return photos;
    }

    public static List<Photo> fromJSON(String json)  {
        try{
            JSONArray jsonArray=new JSONArray(json);
            return readPhotos(jsonArray);
        }catch (JSONException ex){
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }
}
