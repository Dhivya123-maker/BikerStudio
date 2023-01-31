package com.example.bikerstudio;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class FragmentOrder extends Fragment {

    Animation animation,bottomanim;
    List<OrderModel> orderModelList;
    RecyclerView recyclerView;

    String service,es_date,id;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_order, container, false);
        orderModelList = new ArrayList<>();
        recyclerView =  root.findViewById(R.id.list_services1);

        SharedPreferences settings = getActivity().getSharedPreferences("YOUR_PREF_NAM", 0);
        id = settings.getString("id", "");


        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = " http://ns1.nodeserver.co.in:8001/v1/api/m_view_order?id="+id;


        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray order_address = response.getJSONArray("order_add");

                    for (int i=0; i<order_address.length();i++) {

                        JSONObject jsonObject = order_address.getJSONObject(i);

                        es_date = jsonObject.getString("es_date");
                        String o_id = jsonObject.getString("id");
                        service = jsonObject.getString("bike_no");


                        OrderModel orderModel = new OrderModel();

                        orderModel.setOrder_id(o_id);
                        orderModel.setW_order(service);
                        orderModel.setD_date(es_date);


                        orderModelList.add(orderModel);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                OrderAdapter adapter2 = new OrderAdapter(getActivity(), orderModelList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter2);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        requestQueue.add(jsonArrayRequest);










        return root;

    }


    }

