package com.example.bikerstudio;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class BikeModel_Adapter extends BaseAdapter{

    Context context;
    List<BikeModalModel> bike_model;


    public BikeModel_Adapter(Context context, List<BikeModalModel> bike_model) {
        this.context = context;
        this.bike_model= bike_model;
    }

    @Override
    public int getCount() {
        return bike_model.size();
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

        view = LayoutInflater.from(context).inflate(R.layout.bike_model_spinner,viewGroup,false);







        return view;
    }
}
