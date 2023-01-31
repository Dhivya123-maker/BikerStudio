package com.example.bikerstudio;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class BikeName_Adapter  extends BaseAdapter{

    Context context;
    List<BikeNamemodel> bike_name;


    public BikeName_Adapter(Context context, List<BikeNamemodel> bike_name) {
        this.context = context;
        this.bike_name= bike_name;
    }


    @Override
    public int getCount() {
        return bike_name.size();
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

        view = LayoutInflater.from(context).inflate(R.layout.bike_name_spinner,viewGroup,false);



        return view;
    }


}
