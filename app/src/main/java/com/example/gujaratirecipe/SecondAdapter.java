package com.example.gujaratirecipe;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SecondAdapter extends BaseAdapter {
    Activity activity;
    List<SecondModel> secondModelList;
    List<Model> modelList;

    public SecondAdapter(SecondActivity secondActivity, List<SecondModel> secondModelList, List<Model> modelList) {
        activity = secondActivity;
        this.secondModelList = secondModelList;
        this.modelList = modelList;

    }

    @Override
    public int getCount() {
        return secondModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(activity).inflate(R.layout.secondview,viewGroup,false);
        ImageView imageView;
        TextView textView;

        imageView = view.findViewById(R.id.pic2);
        textView = view.findViewById(R.id.title);

        textView.setText(modelList.get(i).getName());
        Glide.with(activity).load(secondModelList.get(i).getPic2()).into(imageView);

        return view;
    }
}
