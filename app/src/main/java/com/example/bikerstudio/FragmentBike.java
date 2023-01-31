package com.example.bikerstudio;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bikerstudio.utils.PreferenceUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentBike  extends Fragment implements Bike_Adapter.OnItemClickListener,Bike_Adapter.OnItemClickListener4 {


    TextView bike_num, bike_model, year, u_name, ins_no, ins_f_date, ins_to_date, kms;
    FloatingActionButton floatingActionButton;

    List<BikeViewModel> bikeviewModelList;
    RecyclerView recyclerView;
    String view = null;

    String vname = null;
    String length = null;
    private long pressedTime;
    int snowDensity = 31;
    String id;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_bike, container, false);


        bikeviewModelList = new ArrayList<>();

        floatingActionButton = root.findViewById(R.id.add_fab);

        TextView textView = root.findViewById(R.id.choose_vehicle);



        recyclerView = root.findViewById(R.id.rc_view);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Bike_add.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("user_id", id);
                startActivity(intent);
            }
        });






//Log.i("123",user);

        SharedPreferences settings = getActivity().getSharedPreferences("YOUR_PREF_NAM", 0);
        id = settings.getString("id", "");

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = " http://ns1.nodeserver.co.in:8001/v1/api/get_vehicle?body=" + id;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                int res = response.length();


//                SharedPreferences settings11 = getActivity().getSharedPreferences("YOUR_PREF_NAME11", 0);
//                SharedPreferences.Editor editor11 = settings11.edit();
//                editor11.putInt("bv", response.length());
//                editor11.commit();
//
//


                try {


                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);


                        BikeViewModel viewModel = new BikeViewModel();

                        viewModel.setBike_num(jsonObject.getString("bike_no"));
                        viewModel.setBike_model(jsonObject.getString("model"));
                        viewModel.setYear(jsonObject.getString("year"));
                        viewModel.setVersion(jsonObject.getString("version"));
                        viewModel.setBike_name(jsonObject.getString("name"));
//                        viewModel.setIns_no(jsonObject.getString("insu_no"));
//                        viewModel.setIns_f_date(jsonObject.getString("insu_from_date"));
//                        viewModel.setIns_to_date(jsonObject.getString("insu_to_date"));
                        viewModel.setKms(jsonObject.getString("run_km"));
                        viewModel.setId(jsonObject.getString("id"));

                        viewModel.setVname(null);

                        bikeviewModelList.add(viewModel);


                    }

//                    int last = response.length() - 1;
//
//                    JSONObject jsonObject1 = response.getJSONObject(last);
//
//                    String bike_name = jsonObject1.getString("name");
//
//
//                    Log.i("0", bike_name);
//                    Log.i("123", String.valueOf(PreferenceUtils.getLength(getActivity())));
//                    Log.i("456", String.valueOf(response.length()));
//
//                    if (PreferenceUtils.getLength(getActivity()) == 0 && PreferenceUtils.getLength(getActivity()) == (response.length())) {
//                        PreferenceUtils.saveLength(res, getActivity());
//                    } else if (PreferenceUtils.getLength(getActivity()) < response.length()) {
//
//                        PreferenceUtils.saveLength(response.length(), getActivity());
//
//
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_HIGH);
//
//
//                            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
//                            manager.createNotificationChannel(channel);
//                        }
//                        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                        NotificationCompat.Builder builder =
//                                new NotificationCompat.Builder(getActivity(), "My Notification")
//                                        .setSound(uri)
//                                        .setContentTitle("Bike added")
//                                        .setContentText("New " + bike_name + " bike is added")
//                                        .setAutoCancel(true)
//                                        .setSmallIcon(R.drawable.bike);
//
//                        Intent notificationIntent = new Intent(getActivity(), Notification.class);
//                        PendingIntent contentIntent = PendingIntent.getActivity(getActivity(), 0, notificationIntent,
//                                PendingIntent.FLAG_UPDATE_CURRENT);
//                        builder.setContentIntent(contentIntent);
//
//                        // Add as notification
//                        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
//                        manager.notify(0, builder.build());
//                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                Bike_Adapter adapter = new Bike_Adapter(getActivity(), bikeviewModelList);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(FragmentBike.this);
                adapter.setOnItemClickListener4( FragmentBike.this);


            }


            private void store() {
                SharedPreferences settings = getActivity().getSharedPreferences("YOUR_PREF_NAME", 0);
                snowDensity = settings.getInt("SNOW_DENSITY", 0);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        requestQueue.add(jsonArrayRequest);

        return root;

    }

    @Override
    public void onItemClick(int position) {


        if (vname != null)
        {

            Intent intent = new Intent(getActivity(), FragmentBike.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);


            startActivity(intent);


        }

    }



    @Override
    public void onItemClick4(int position) {
        BikeViewModel model = bikeviewModelList.get(position);

        Intent intent = new Intent(getActivity(),Bike_add.class);
        intent.putExtra("BIKENAME",model.getBike_name());
        intent.putExtra("BIKEMODEL",model.getBike_model());
        intent.putExtra("BIKEVERSION",model.getVersion());
        intent.putExtra("BIKENUMBER",model.getBike_num());
        intent.putExtra("KM",model.getKms());
        intent.putExtra("YEAR",model.getYear());
        intent.putExtra("BIKEID",model.getId());
        intent.putExtra("EDIT","EDIT");
        startActivity(intent);
    }



}
