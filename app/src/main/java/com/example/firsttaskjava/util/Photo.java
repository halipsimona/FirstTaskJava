package com.example.firsttaskjava.util;

import androidx.annotation.NonNull;

public class Photo {
    private int Id;
    private int albumId;
    private final String title;
    private final String url;
    private final String thumbnailUrl;


    public Photo(int id, int albumId, String title, String url, String thumbnailUrl) {
        Id = id;
        this.albumId = albumId;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }


    public String getUrl() {
        return url;
    }

    @NonNull
    @Override
    public String toString() {
        return "Photo{" +
                "Id=" + Id +
                ", albumId=" + albumId +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
