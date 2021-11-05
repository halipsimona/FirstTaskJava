package com.example.firsttaskjava.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.firsttaskjava.util.Album;

@Database(exportSchema = false,version=3,entities = {Album.class})
public abstract class DatabaseManager  extends RoomDatabase {

    private static DatabaseManager databaseManager;
    private static final String ALBUMS_DB="albums_db";

    public static DatabaseManager getInstance(Context context){
        if(databaseManager==null){
            synchronized (DatabaseManager.class){
                if(databaseManager==null){
                    databaseManager= Room.databaseBuilder(context,DatabaseManager.class,ALBUMS_DB).fallbackToDestructiveMigration().build();
                }
            }
        }
        return databaseManager;
    }

    public  abstract AlbumDao getAlbumDao();
}
