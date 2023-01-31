package com.example.bikerstudio;

import static android.provider.Telephony.Mms.Part.TEXT;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class bike_view<equals> extends AppCompatActivity implements Bike_Adapter.OnItemClickListener, Bike_Adapter.OnItemClickListener4{

    TextView bike_num,bike_model,year,u_name,kms;
    FloatingActionButton floatingActionButton;

    List<BikeViewModel> bikeviewModelList;
    RecyclerView recyclerView;
    String view = null;
    String vname = null;
    String bike_no = null;
    ArrayList<String>  s_id ;
    String b_id = null;

    String length = null;
    int snowDensity = 31;
    String id;
   TextView edit;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_view);

        bikeviewModelList = new ArrayList<>();

        floatingActionButton = findViewById(R.id.add_fab);
        TextView textView = findViewById(R.id.choose_vehicle);
        edit = findViewById(R.id.edit_btn);


        recyclerView = findViewById(R.id.rc_view);
        SharedPreferences settings = getSharedPreferences("YOUR_PREF_NAM", 0);
        id = settings.getString("id", "");


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bike_view.this, Bike_add.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("user_id", id);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();
        vname = intent.getStringExtra("vname");

        s_id = intent.getStringArrayListExtra("s_id");

//        Toast.makeText(bike_view.this, s_id.toString(), Toast.LENGTH_SHORT).show();


        if (vname != null)
        {
            floatingActionButton.setVisibility(View.INVISIBLE);
        }

//Log.i("123",user);



        RequestQueue requestQueue = Volley.newRequestQueue(bike_view.this);
        String url = " http://ns1.nodeserver.co.in:8001/v1/api/get_vehicle?body=" + id;


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                int res = response.length();


                SharedPreferences settings11 = getSharedPreferences("YOUR_PREF_NAME11", 0);
                SharedPreferences.Editor editor11 = settings11.edit();
                editor11.putInt("bv", response.length());
                editor11.commit();
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
                        viewModel.setVname(vname);



                        bikeviewModelList.add(viewModel);


                    }

//                    int last = response.length() - 1;
//
//                    JSONObject jsonObject1 = response.getJSONObject(last);
//
//                    String bike_name = jsonObject1.getString("name");
//
//
//
//                    if (PreferenceUtils.getLength(bike_view.this) == 0 ) {
//                        PreferenceUtils.saveLength(res, bike_view.this);
//                    }else
//                    {
//                        PreferenceUtils.saveLength(response.length(), bike_view.this);
//                    }
//
//
//                    if (PreferenceUtils.getLength(bike_view.this) == 0 && PreferenceUtils.getLength(bike_view.this) == (response.length())) {
//                        PreferenceUtils.saveLength(res, bike_view.this);
//                    } else if (PreferenceUtils.getLength(bike_view.this) < response.length()) {
//
//                        PreferenceUtils.saveLength(response.length(), bike_view.this);
////
//                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_HIGH);
//
//                            NotificationManager manager = getSystemService(NotificationManager.class);
//                            manager.createNotificationChannel(channel);
//                        }
//                        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                        NotificationCompat.Builder builder =
//                                new NotificationCompat.Builder(bike_view.this, "My Notification")
//                                        .setSound(uri)
//                                        .setContentTitle("Bike added")
//                                        .setContentText("New " + bike_name + " bike is added")
//                                        .setAutoCancel(true)
//                                        .setSmallIcon(R.drawable.bike);
//
//                        Intent notificationIntent = new Intent(bike_view.this, Notification.class);
//                        PendingIntent contentIntent = PendingIntent.getActivity(bike_view.this, 0, notificationIntent,
//                                PendingIntent.FLAG_UPDATE_CURRENT);
//                        builder.setContentIntent(contentIntent);
//
//                        // Add as notification
//                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//                        manager.notify(0, builder.build());
//                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
                Bike_Adapter adapter = new Bike_Adapter(getApplicationContext(), bikeviewModelList);

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(bike_view.this);
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(bike_view.this);
                adapter.setOnItemClickListener4(bike_view.this);

            }


            private void store() {
                SharedPreferences settings = getSharedPreferences("YOUR_PREF_NAME", 0);
                snowDensity = settings.getInt("SNOW_DENSITY", 0);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        requestQueue.add(jsonArrayRequest);

    }


    @Override
    public void onItemClick(int position) {


        if (vname != null)
        {
            BikeViewModel model = bikeviewModelList.get(position);
            view = model.getBike_model();
            bike_no = model.getBike_num();
            b_id = model.getId();
            String bike_name = model.getBike_name();

            Intent intent = new Intent(bike_view.this, order_conformation.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("s_id", s_id);
            intent.putExtra("view", view);
            intent.putExtra("bike_no",bike_no);
            intent.putExtra("bike_name",bike_name);
            intent.putExtra("b_id",b_id);

            startActivity(intent);


        }

    }




    public void onBackPressed() {


        Intent intent = new Intent(bike_view.this,home.class);

        startActivity(intent);
    }

    @Override
    public void onItemClick4(int position) {


        BikeViewModel model = bikeviewModelList.get(position);

        Intent intent = new Intent(bike_view.this,Bike_add.class);
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