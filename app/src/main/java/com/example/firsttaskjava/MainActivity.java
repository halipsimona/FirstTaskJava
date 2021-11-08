package com.example.firsttaskjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.firsttaskjava.database.AlbumDao;
import com.example.firsttaskjava.database.DatabaseManager;
import com.example.firsttaskjava.jsonparsers.JsonParserAlbum;
import com.example.firsttaskjava.typyapi.TypiApi;
import com.example.firsttaskjava.util.Album;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvAlbums;
    private final List<Album> albums=new ArrayList<>();
    private AlbumDao albumDao;
    public final static String link="https://jsonplaceholder.typicode.com/albums";
    private static final String STATE_SCROLL_POSITION = "scrollPosition";
    private int savedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvAlbums=findViewById(R.id.lv_albums);
        ArrayAdapter<Album> adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,albums);
        lvAlbums.setAdapter(adapter);
        albumDao= DatabaseManager.getInstance(MainActivity.this).getAlbumDao();
        if (isInternetAvailable()) {
            loadAlbums();
        }else{
            showAlbumsSorted();
        }
        lvAlbums.setOnItemClickListener(albumClicked());

    }

    private AdapterView.OnItemClickListener albumClicked() {
        return (adapterView, view, i, l) -> {
            int id=albums.get(i).getId();
            Intent intent=new Intent(getApplicationContext(),PhotosActivity.class);
            intent.putExtra(PhotosActivity.ALBUM_ID_KEY,id);
            startActivity(intent);
        };
    }

    private void loadAlbums(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                final String result = new TypiApi().receiveFromNetwork(link);
                new Handler(Looper.getMainLooper()).post(() -> {
                    List<Album> albumsFromJson = JsonParserAlbum.fromJSON(result);
                    insertIntoDB(albumsFromJson);
                });
            }
        };
        thread.start();
    }
    private void insertIntoDB(List<Album> albumsToInsert) {
        Thread thread=new Thread(){
            @Override
            public void run() {
                albumDao.insertAlbums(albumsToInsert);
                new Handler(Looper.getMainLooper()).post(() -> showAlbumsSorted());
            }
        };
        thread.start();
    }

    private void showAlbumsSorted(){
        Thread thread1=new Thread(){
            @Override
            public void run() {
                final List<Album> sortedAlbums=albumDao.getAlbumsSorted();
                new Handler(getMainLooper()).post(() -> {
                    albums.clear();
                    albums.addAll(sortedAlbums);
                    notifyAdapter();
                });
            }
        };
        thread1.start();
    }

    private void notifyAdapter(){
        BaseAdapter adapter = (BaseAdapter) lvAlbums.getAdapter();
        adapter.notifyDataSetChanged();
        lvAlbums.setSelection(savedPosition);
    }
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddress= InetAddress.getByName("google.com");
            return !ipAddress.toString().equals("");

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        int currentPosition = lvAlbums.getFirstVisiblePosition();
        savedInstanceState.putInt(STATE_SCROLL_POSITION, currentPosition);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedPosition = savedInstanceState.getInt(STATE_SCROLL_POSITION);
        lvAlbums.setSelection(savedPosition);
    }

}