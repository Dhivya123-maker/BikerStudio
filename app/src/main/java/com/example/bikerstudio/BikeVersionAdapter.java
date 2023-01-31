package com.example.bikerstudio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BikeVersionAdapter extends BaseAdapter{

    Context context;
    List<BikeVersionModel> bikeVersion;


    public BikeVersionAdapter(Context context, List<BikeVersionModel> bikeVersion) {
        this.context = context;
        this.bikeVersion= bikeVersion;
    }

    @Override
    public int getCount() {
        return bikeVersion.size();
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

        view = LayoutInflater.from(context).inflate(R.layout.bike_version_spinner,viewGroup,false);



        return view;
    }
}
