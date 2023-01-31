package com.example.bikerstudio;



import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.ArraySet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class booking extends AppCompatActivity implements Adapter.OnItemClickListener {



    private static  String JSON_URL ="http://ns1.nodeserver.co.in:8001/v1/api/getservices";

    List<BookingModel> bookingModelList;
    RecyclerView recyclerView;
    Adapter adapter;
    TextView v_type,name,price,dis;
    Button button;
    String scooter= null,bike = null,superbike = null;
    ArrayList<String> arrayList;

    String vname=null;
    String view = null;

    String var;
    String nn[];

    int arraynum;

    String[] str ={} ;
    String[] array;
//    String[] scooter_1 = new String[];

    FloatingActionButton floatingActionButton;
    String id;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        bookingModelList = new ArrayList<>();
        arrayList=new ArrayList<>();
      recyclerView = findViewById(R.id.list_services);
      floatingActionButton = findViewById(R.id.add_fab3);


        name = findViewById(R.id.name);
        price = findViewById(R.id.price);
        dis = findViewById(R.id.description);
        button=findViewById(R.id.adding);
        Button contine = findViewById(R.id.adding);





        Intent intent = getIntent();

        scooter = intent.getStringExtra("scooter");
        bike = intent.getStringExtra("bike");
        superbike = intent.getStringExtra("sb");


        SharedPreferences settings = getSharedPreferences("YOUR_PREF_NAM", 0);
        id = settings.getString("id", "");

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(booking.this, Bike_add.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("user_id", id);
                startActivity(intent);
            }
        });


//
////
//        Toast.makeText(booking.this, scooter, Toast.LENGTH_SHORT).show();
//        Toast.makeText(booking.this, bike, Toast.LENGTH_SHORT).show();
//        Toast.makeText(booking.this, superbike, Toast.LENGTH_SHORT).show();

                RequestQueue queue = Volley.newRequestQueue(booking.this);
                String JSON_URL = "http://ns1.nodeserver.co.in:8001/v1/api/getservices";
                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

//                        JSONArray jsonArrContent = response.getJSONArray();
//                        String [] arr_shop_cat_id;
//                        String [] arr_shop_cat_name;
//                        for (int i = 0; i < jsonArrContent.length(); i++) {
//                            JSONObject jsonobj = jsonArrContent.getJSONObject(i);
//                            // get data from the json array objetcs
//                            arr_shop_cat_id[i] = jsonobj.getString("v_type");
//                            arr_shop_cat_name[i] = jsonobj.getString("shop_cat_name");
//
//                        }
//
                        String[] array;
                        for (int i = 0; i < response.length(); i++) {
                            try {


                                JSONObject jsonObject = response.getJSONObject(i);




                                if (jsonObject.getString("v_type").equals(scooter)){


                                    Log.i("scooter",jsonObject.toString());
                                    BookingModel bike = new BookingModel();

                                    bike.setId(jsonObject.getString("id"));

                                    bike.setName(jsonObject.getString("name"));
                                    bike.setPrice(jsonObject.getString("price"));
                                    bike.setDis(jsonObject.getString("dis"));
                                    bookingModelList.add(bike);

                                    Adapter adapter = new Adapter(getApplicationContext(), bookingModelList);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    recyclerView.setAdapter(adapter);
                                    adapter.setOnItemClickListener(booking.this);
                                }else if (jsonObject.getString("v_type").equals(superbike)){

                                    Log.i("superbike",jsonObject.toString());
                                    BookingModel bike = new BookingModel();

                                    bike.setId(jsonObject.getString("id"));

                                    bike.setName(jsonObject.getString("name"));
                                    bike.setPrice(jsonObject.getString("price"));
                                    bike.setDis(jsonObject.getString("dis"));
                                    bookingModelList.add(bike);

                                    Adapter adapter = new Adapter(getApplicationContext(), bookingModelList);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    recyclerView.setAdapter(adapter);
                                    adapter.setOnItemClickListener(booking.this);
                                }else if (jsonObject.getString("v_type").equals(bike)){


                                    Log.i("bike",jsonObject.toString());
                                    BookingModel bike = new BookingModel();

                                    bike.setId(jsonObject.getString("id"));

                                    bike.setName(jsonObject.getString("name"));
                                    bike.setPrice(jsonObject.getString("price"));
                                    bike.setDis(jsonObject.getString("dis"));
                                    bookingModelList.add(bike);

                                    Adapter adapter = new Adapter(getApplicationContext(), bookingModelList);
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                    recyclerView.setAdapter(adapter);
                                    adapter.setOnItemClickListener(booking.this);
                                }


//
//                                scooter_1= new String[];
//                               scooter_1 = jsonObject.getString("v_type").trim();



                    if("scooter".equals("scooter")){




                    }
//                    else {
//                        BookingModel bike = new BookingModel();
//
//                        bike.setId(jsonObject.getString("id"));
//
//                        bike.setName(jsonObject.getString("name"));
//                        bike.setPrice(jsonObject.getString("price"));
//                        bike.setDis(jsonObject.getString("dis"));
//                        bookingModelList.add(bike);
//
//                        Adapter adapter = new Adapter(getApplicationContext(), bookingModelList);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                        recyclerView.setAdapter(adapter);
//                        adapter.setOnItemClickListener(booking.this);
//
//                    }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }



                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("tag", "onErrorResponse: " + error.getMessage());
                    }
                });

                queue.add(jsonArrayRequest);

//                onItemClick();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(var != null){
                            Intent i = new Intent(booking.this,bike_view.class);
                            i.putExtra("vname","test");
                            i.putExtra("s_id",arrayList);
                            i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(i);

                        System.out.println(nn);
                        }else{
                            Toast.makeText(booking.this, "Please add your services", Toast.LENGTH_SHORT).show();
                        }
//                        arraynum=nn.length;
//                        nn =nn[]{vname};
////                        nn[arraynum]=vname;

                    }

                });


            }




    @Override
    public void onItemClick(int position) {

        BookingModel chi = bookingModelList.get(position);

        vname = chi.getId();


//        for (int i=0;i<vname.length();i++)
//        {
//            array = new String[]{vname};
//        }

        arrayList.add(vname);



        var = "value";
//        str = new String[]{vname};


    }




}