package com.example.firsttaskjava;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.GridView;

import com.example.firsttaskjava.jsonparsers.JsonParserPhoto;
import com.example.firsttaskjava.typyapi.TypiApi;
import com.example.firsttaskjava.adapters.GridAdapter;
import com.example.firsttaskjava.util.Photo;

import java.util.ArrayList;
import java.util.List;

public class PhotosActivity extends AppCompatActivity {

    private GridView gvPhotos;
    private List<Photo> photoList=new ArrayList<>();
    public static final String ALBUM_ID_KEY="album_id_key";
    Intent intent;
    private int albumId;
    private static final String STATE_SCROLL_POSITION = "scrollPosition";
    private int savedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        GridAdapter gridAdapter=new GridAdapter(photoList,getLayoutInflater(),R.layout.photo_layout);
        gvPhotos=findViewById(R.id.gv_photos);
        gvPhotos.setAdapter(gridAdapter);
        if(getIntent()!=null&&getIntent().hasExtra(ALBUM_ID_KEY)) {
            intent=getIntent();
            albumId=intent.getIntExtra(ALBUM_ID_KEY,-1);
        }
        if(albumId!=-1){
            loadPhotos(albumId);
        }else{
            showAlertDialog();
        }
    }

    private void loadPhotos(int albumId) {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(MainActivity.link).append("/").append(albumId).append("/photos");
        String photosLink=stringBuilder.toString();

        Thread thread = new Thread() {
            @Override
            public void run() {
                final String result = new TypiApi().receiveFromNetwork(photosLink);
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        List<Photo> photosFromJson = JsonParserPhoto.fromJSON(result);
                        photoList.addAll(photosFromJson);
                        notifyAdapter();
                    }
                });
            }
        };
        thread.start();

    }

    private void showAlertDialog() {
        new AlertDialog.Builder(getApplicationContext())
                .setTitle("Error")
                .setMessage("The album does not exist.")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void notifyAdapter(){
        GridAdapter gridAdapter= (GridAdapter) gvPhotos.getAdapter();
        gridAdapter.notifyDataSetChanged();
        gvPhotos.setSelection(savedPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        int currentPosition = gvPhotos.getFirstVisiblePosition();
        savedInstanceState.putInt(STATE_SCROLL_POSITION, currentPosition);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedPosition = savedInstanceState.getInt(STATE_SCROLL_POSITION);
        gvPhotos.setSelection(savedPosition);
    }
}