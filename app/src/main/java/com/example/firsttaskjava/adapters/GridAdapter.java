package com.example.firsttaskjava.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.firsttaskjava.R;
import com.example.firsttaskjava.util.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GridAdapter extends BaseAdapter {

    private List<Photo> photos;
    private LayoutInflater inflater;
    private int resource;

    public GridAdapter( List<Photo> photoList, LayoutInflater inflater, int resource){
        this.photos=photoList;
        this.inflater=inflater;
        this.resource=resource;
    }
    @Override
    public int getCount() {
        return photos.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view = inflater.inflate(resource, viewGroup, false);
        }
        ImageView imageView=view.findViewById(R.id.iv_photo);
        Photo photo=photos.get(i);
        Picasso.get().load(photo.getUrl()).into(imageView);
        return view;
    }

}
