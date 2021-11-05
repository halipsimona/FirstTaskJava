package com.example.firsttaskjava.util;

public class Photo {
    private int Id;
    private int albumId;
    private String title;
    private String url;
    private String thumbnailUrl;

    public Photo(){}

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

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
