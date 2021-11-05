package com.example.firsttaskjava.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.firsttaskjava.util.Album;

import java.util.List;

@Dao
public interface AlbumDao {
    @Query("SELECT * from albums order by title asc")
    List<Album> getAlbumsSorted();

    @Query("DELETE from albums")
    void deleteAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAlbums(List<Album> albums);
}
