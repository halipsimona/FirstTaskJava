package com.example.firsttaskjava.util;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "albums")
public class Album {
    @ColumnInfo(name="user_id")
    private int userId;
    @PrimaryKey
    @ColumnInfo(name="id")
    private int id;
    @ColumnInfo(name = "title")
    private String title;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Album{" +
                "userId=" + userId +
                ", id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public Album(int userId, int id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }
    public Album(){}
}
