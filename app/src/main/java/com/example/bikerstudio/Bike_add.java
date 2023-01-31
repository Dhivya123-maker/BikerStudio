package com.example.bikerstudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Bike_add extends AppCompatActivity {
    TextView bike_name,bikemodel,bikeversion;
    ArrayList<String> arrayList;
    ArrayList<String> arrayList2;
    ArrayList<String> arrayList3;
    Dialog dialog;

    String BIKENAME,BIKEMODEL,BIKEVERSION,BIKENUMBER,KM,YEAR,BIKEID,EDIT,id;

    String bikeno="^[A-Z]{2}[ -][0-9]{1,2}(?: [A-Z])?(?: [A-Z]*)? [0-9]{4}$";


    RequestQueue requestQueue;

//    List<BikeNamemodel> bikename;
//    List<BikeModalModel> bikeModal;
//    List<BikeVersionModel> bikeVersion;


  EditText bike_number,bike_kms,year,bike_ins,from_date,to_date;
  Spinner v_type;
  Button button;
    String bkn,bkm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bike_add);

        // assign variable
        bike_name=findViewById(R.id.bike_name_spinnner);
        bikemodel=findViewById(R.id.bike_model_spinnner);
        bikeversion=findViewById(R.id.bike_version_spinnner);


        bike_number =(EditText) findViewById(R.id.Bike_no);
        bike_kms =(EditText) findViewById(R.id.Bike_kilometer);
//        bike_ins = findViewById(R.id.Bike_insurance);
//        from_date= findViewById(R.id.editTextDate);
//        to_date=findViewById(R.id.editTextDate2);
        year =(EditText) findViewById(R.id.Year);
        v_type = findViewById(R.id.spinner);

        // initialize array list
        arrayList=new ArrayList<>();


        // set value in array list


        arrayList2=new ArrayList<>();

//        arrayList2.add("R15");
//        arrayList2.add("MT15");
//        arrayList2.add("FZ");

        arrayList3=new ArrayList<>();
//
//        arrayList3.add("v1");
//        arrayList3.add("v2");
//        arrayList3.add("v3");





        Intent intent=getIntent();

        BIKENAME=intent.getStringExtra("BIKENAME");
        BIKEMODEL=intent.getStringExtra("BIKEMODEL");
        BIKEVERSION=intent.getStringExtra("BIKEVERSION");
        BIKENUMBER=intent.getStringExtra("BIKENUMBER");
        KM=intent.getStringExtra("KM");
        YEAR=intent.getStringExtra("YEAR");
        BIKEID=intent.getStringExtra("BIKEID");
        EDIT=intent.getStringExtra("EDIT");
        id = intent.getStringExtra("user_id");

//        Toast.makeText(Bike_add.this, BIKENAME, Toast.LENGTH_SHORT).show();
//        Toast.makeText(Bike_add.this, BIKEMODEL, Toast.LENGTH_SHORT).show();
//        Toast.makeText(Bike_add.this,BIKEVERSION , Toast.LENGTH_SHORT).show();
//        Toast.makeText(Bike_add.this,BIKENUMBER , Toast.LENGTH_SHORT).show();
//        Toast.makeText(Bike_add.this, KM, Toast.LENGTH_SHORT).show();
//        Toast.makeText(Bike_add.this,YEAR , Toast.LENGTH_SHORT).show();
//       // Toast.makeText(Bike_add.this, BIKEID, Toast.LENGTH_SHORT).show();
//
//        Log.i("1111111111",BIKENAME);
//        Log.i("11111111112",BIKEMODEL);
//        Log.i("11111111113",BIKEVERSION);
//        Log.i("11111111114",BIKENUMBER);
//        Log.i("11111111115",KM);
//        Log.i("11111111116",YEAR);
     //  Log.i("11111111117",BIKEID);



        if (EDIT != null) {
            bike_name.setText(BIKENAME);
            bikemodel.setText(BIKEMODEL);
            bikeversion.setText(BIKEVERSION);
            bike_number.setText(BIKENUMBER.trim());
            bike_kms.setText(KM.trim());
            year.setText(YEAR.trim());
        }

        RequestQueue queue = Volley.newRequestQueue(Bike_add.this);
        String JSON_URL = "http://ns1.nodeserver.co.in:8001/v1/api/get_v_type";
        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {


                JSONArray jsonArray = response.getJSONArray("data");

                for (int i = 0; i < jsonArray.length(); i++) {


                    JSONObject jsonObject = jsonArray.getJSONObject(i);


                    arrayList.add(jsonObject.getString("bike_name"));
//                        arrayList.add("Yamaha");
//                        arrayList.add("Bajaj");
//                        arrayList.add("Hero");
//                        arrayList.add("TVs");
//                        arrayList.add("Royal Enfield");
//                        arrayList.add("Kawasaki");
//                        arrayList.add("BMw");


//                        if (jsonObject.getString("bike_name").equals(scooter)){
//
//
//                        }else if (jsonObject.getString("v_type").equals(superbike)){
//
//
//                        }else if (jsonObject.getString("v_type").equals(bike)){
//
//
//                        }


                }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);









        bike_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize dialog


                dialog=new Dialog(Bike_add.this);

                // set custom dialog
                dialog.setContentView(R.layout.bike_name_spinner);

                // set custom height and width
//                dialog.getWindow().setLayout(600,800);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                // set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // show dialog
                dialog.show();

                // Initialize and assign variable
                EditText editText=dialog.findViewById(R.id.edit_text);
                ListView listView=dialog.findViewById(R.id.list_view);

                // Initialize array adapter
                ArrayAdapter<String> adapter=new ArrayAdapter<>(Bike_add.this, R.layout.activity_color,arrayList);

                // set adapter
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // when item selected from list
                        // set selected item on textView
                        bike_name.setText(adapter.getItem(position));
                        bkn=adapter.getItem(position);
                        arrayList2.clear();

                        RequestQueue queue1 = Volley.newRequestQueue(Bike_add.this);
                        String JSON_URL1 = "http://ns1.nodeserver.co.in:8001/v1/api/get_v_type";
                        JsonObjectRequest jsonArrayRequest1 = new JsonObjectRequest(Request.Method.GET, JSON_URL1, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {


                                    JSONArray jsonArray = response.getJSONArray("data");

                                    for (int i = 0; i < jsonArray.length(); i++) {


                                        JSONObject jsonObject = jsonArray.getJSONObject(i);




                                        if (jsonObject.getString("bike_name").equals(bkn)){

                                            arrayList2.add(jsonObject.getString("bike_model"));
                                        }
//
//
//                        }else if (jsonObject.getString("v_type").equals(bike)){
//
//
//                        }


                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("tag", "onErrorResponse: " + error.getMessage());
                            }
                        });

                        queue1.add(jsonArrayRequest1);

                        // Dismiss dialog
                        dialog.dismiss();

                    }
                });



            }
        });






        bikemodel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {








                // Initialize dialog
                dialog=new Dialog(Bike_add.this);

                // set custom dialog
                dialog.setContentView(R.layout.bike_model_spinner);

                // set custom height and width
//                dialog.getWindow().setLayout(600,800);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                // set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // show dialog
                dialog.show();

                // Initialize and assign variable
                EditText editText=dialog.findViewById(R.id.edit_text);
                ListView listView=dialog.findViewById(R.id.list_view);

                // Initialize array adapter
                ArrayAdapter<String> adapter=new ArrayAdapter<>(Bike_add.this, R.layout.activity_color,arrayList2);

                // set adapter
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // when item selected from list
                        // set selected item on textView
                        bikemodel.setText(adapter.getItem(position));

                        bkm=adapter.getItem(position);
                        arrayList3.clear();

                        RequestQueue queue1 = Volley.newRequestQueue(Bike_add.this);
                        String JSON_URL1 = "http://ns1.nodeserver.co.in:8001/v1/api/get_v_type";
                        JsonObjectRequest jsonArrayRequest1 = new JsonObjectRequest(Request.Method.GET, JSON_URL1, null, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {


                                    JSONArray jsonArray = response.getJSONArray("data");

                                    for (int i = 0; i < jsonArray.length(); i++) {


                                        JSONObject jsonObject = jsonArray.getJSONObject(i);




                                        if (jsonObject.getString("bike_model").equals(bkm)){

                                            arrayList3.add(jsonObject.getString("bike_version"));
                                        }
//
//
//                        }else if (jsonObject.getString("v_type").equals(bike)){
//
//
//                        }


                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("tag", "onErrorResponse: " + error.getMessage());
                            }
                        });

                        queue1.add(jsonArrayRequest1);

                        // Dismiss dialog
                        dialog.dismiss();
                    }
                });
            }
        });
        bikeversion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize dialog
                dialog=new Dialog(Bike_add.this);

                // set custom dialog
                dialog.setContentView(R.layout.bike_version_spinner);

                // set custom height and width
//                dialog.getWindow().setLayout(600,800);

                dialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                // set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


                // show dialog
                dialog.show();

                // Initialize and assign variable
                EditText editText=dialog.findViewById(R.id.edit_text);
                ListView listView=dialog.findViewById(R.id.list_view);

                // Initialize array adapter
                ArrayAdapter<String> adapter=new ArrayAdapter<>(Bike_add.this, R.layout.activity_color,arrayList3);

                // set adapter
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        // when item selected from list
                        // set selected item on textView
                        bikeversion.setText(adapter.getItem(position));

                        // Dismiss dialog
                        dialog.dismiss();
                    }
                });
            }
        });



//        bikename = new ArrayList<>();
//        bikeModal = new ArrayList<>();
//        bikeVersion = new ArrayList<>();


//        bike_name = findViewById(R.id.bike_name_spinner);
//        bikemodel =findViewById(R.id.bike_model_spinner);
//        bikeversion = findViewById(R.id.bike_version_spinner);


//        BikeNamemodel bn = new BikeNamemodel();
//        bn.setName("Select your bike name");
//        //               pa.setImage(familyObject.getString("avater"));
//        bikename.add(bn);
//
//        BikeName_Adapter adapter1= new BikeName_Adapter(Bike_add.this,bikename);
//
//        bike_name.setAdapter(adapter1);








//        BikeModalModel bm = new BikeModalModel();
//        bm.setModel("Select your bike model");
//
//        bikeModal.add(bm);
//
//        BikeModel_Adapter adapter2= new BikeModel_Adapter(Bike_add.this,bikeModal);
//
//        bikemodel.setAdapter(adapter2);





//        BikeVersionModel bv = new BikeVersionModel();
//        bv.setVersion("Select your bike version");
//
//        bikeVersion.add(bv);
//
//        BikeVersionAdapter adapter3= new BikeVersionAdapter(Bike_add.this,bikeVersion);
//
//        bikeversion.setAdapter(adapter3);



        bike_number =(EditText) findViewById(R.id.Bike_no);
        bike_kms =(EditText) findViewById(R.id.Bike_kilometer);
//        bike_ins = findViewById(R.id.Bike_insurance);
//        from_date= findViewById(R.id.editTextDate);
//        to_date=findViewById(R.id.editTextDate2);
        year =(EditText) findViewById(R.id.Year);
        v_type = findViewById(R.id.spinner);




        ArrayAdapter<String> Adapter1 = new ArrayAdapter<String>(Bike_add.this,R.layout.activity_color,
              getResources().getStringArray(R.array.vehicle_type));
        Adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        v_type.setAdapter(Adapter1);


        button=findViewById(R.id.button);






        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//
                String Bike_name = bike_name.getText().toString().trim();
                String Bike_number = bike_number.getText().toString().trim();
                String Bike_model = bikemodel.getText().toString().trim();
                String Bike_version = bikeversion.getText().toString().trim();
                String Bike_kms = bike_kms.getText().toString().trim();
//                String Bike_ins = bike_ins.getText().toString().trim();
                String Year = year.getText().toString().trim();
//                String From_date = from_date.getText().toString().trim();
//                String To_date = to_date.getText().toString().trim();
                String vehicle_type = v_type.getSelectedItem().toString().trim();



//                if (Bike_name.isEmpty()) {
//                    bike_name.setError("Please enter the bike name");
//                    bike_name.requestFocus();
//                }        Toast.makeText(Bike_add.this, BIKENAME, Toast.LENGTH_SHORT).show();



                if (id != null) {

                    if (bike_name.getText().toString().trim().equals(null)) {
                        Toast.makeText(Bike_add.this, "Please select bike name", Toast.LENGTH_SHORT).show();
                    } else if (Bike_number.isEmpty()) {
                        bike_number.setError("Please enter the bike number");
                        bike_number.requestFocus();
                    } else if (bikemodel.getText().toString().trim().equals(null)) {
                        Toast.makeText(Bike_add.this, "Please select bike model", Toast.LENGTH_SHORT).show();
                    } else if (bikeversion.getText().toString().trim().equals(null)) {
                        Toast.makeText(Bike_add.this, "Please select bike version", Toast.LENGTH_SHORT).show();
                    }

//                else if (Bike_model.isEmpty()) {
//                    bikemodel.setError("Please enter the bike model");
//                    bikemodel.requestFocus();
//                } else if (Bike_version.isEmpty()) {
//                    bikeversion.setError("Please enter the bike version");
//                    bikeversion.requestFocus();
//                }
                    else if (Bike_kms.isEmpty()) {
                        bike_kms.setError("Please enter the bike kilometer");
                        bike_kms.requestFocus();
                    }

//                else if (Bike_ins.isEmpty()) {
//                    bike_ins.setError("Please enter the insurance number");
//                    bike_ins.requestFocus();
//                }
                    else if (Year.isEmpty()) {
                        year.setError("Please enter the year");
                        year.requestFocus();
                    }
//                else if (From_date.isEmpty()) {
//                    from_date.setError("Please enter the from date");
//                    from_date.requestFocus();
//                } else if (To_date.isEmpty()) {
//                    to_date.setError("Please enter the to date");
//                    to_date.requestFocus();
//                }

                    else if (v_type.getSelectedItem().toString().trim().equals(null)) {
                        Toast.makeText(Bike_add.this, "Please select vehicle type", Toast.LENGTH_SHORT).show();
                    } else {

                        String postUrl = "http://ns1.nodeserver.co.in:8001/v1/api/add_v";
                        RequestQueue requestQueue = Volley.newRequestQueue(Bike_add.this);
                        JSONObject jsonBody = new JSONObject();
                        try {
                            jsonBody.put("name", Bike_name);
                            jsonBody.put("bike_no", Bike_number);
                            jsonBody.put("model", Bike_model);
                            jsonBody.put("version", Bike_version);
                            jsonBody.put("km", Bike_kms);
                            jsonBody.put("year", Year);
                            jsonBody.put("user_id", id);
                            jsonBody.put("v_type", vehicle_type);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, jsonBody, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {


                                try {


                                    String Success = response.getString("success");


                                    if (Success == "true") {


                                        Intent intent = new Intent(Bike_add.this, bike_view.class);

                                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                        startActivity(intent);


                                    }

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                            }
                        });
                        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                                10000,
                                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        requestQueue.add(jsonObjectRequest);
                    }


                }else if (BIKENAME != null){




                        if (bike_name.getText().toString().trim().equals(null)) {
                            Toast.makeText(Bike_add.this, "Please select bike name", Toast.LENGTH_SHORT).show();
                        }
                        else if (Bike_number.isEmpty()) {
                            bike_number.setError("Please enter the bike number");
                            bike_number.requestFocus();
                        }
                        else  if (bikemodel.getText().toString().trim().equals(null)) {
                            Toast.makeText(Bike_add.this, "Please select bike model", Toast.LENGTH_SHORT).show();
                        }
                        else  if (bikeversion.getText().toString().trim().equals(null)) {
                            Toast.makeText(Bike_add.this, "Please select bike version", Toast.LENGTH_SHORT).show();
                        }

//                else if (Bike_model.isEmpty()) {
//                    bikemodel.setError("Please enter the bike model");
//                    bikemodel.requestFocus();
//                } else if (Bike_version.isEmpty()) {
//                    bikeversion.setError("Please enter the bike version");
//                    bikeversion.requestFocus();
//                }
                        else if (Bike_kms.isEmpty()) {
                            bike_kms.setError("Please enter the bike kilometer");
                            bike_kms.requestFocus();}

//                else if (Bike_ins.isEmpty()) {
//                    bike_ins.setError("Please enter the insurance number");
//                    bike_ins.requestFocus();
//                }
                        else if (Year.isEmpty()) {
                            year.setError("Please enter the year");
                            year.requestFocus();
                        }
//                else if (From_date.isEmpty()) {
//                    from_date.setError("Please enter the from date");
//                    from_date.requestFocus();
//                } else if (To_date.isEmpty()) {
//                    to_date.setError("Please enter the to date");
//                    to_date.requestFocus();
//                }

                        else if (v_type.getSelectedItem().toString().trim().equals(null)) {
                            Toast.makeText(Bike_add.this, "Please select vehicle type", Toast.LENGTH_SHORT).show();
                        } else {

                            String updateurl = "http://ns1.nodeserver.co.in:8001/v1/api/update_m_vehicle";
                            RequestQueue requestQueue2 = Volley.newRequestQueue(Bike_add.this);
                            JSONObject jsonBody2 = new JSONObject();
                            try {
                                jsonBody2.put("name", Bike_name);
                                jsonBody2.put("bike_no", Bike_number);
                                jsonBody2.put("model", Bike_model);
                                jsonBody2.put("version", Bike_version);
                                jsonBody2.put("run_km", Bike_kms);
                                jsonBody2.put("year", Year);
                                jsonBody2.put("id", BIKEID);
                                jsonBody2.put("vehicle_type", vehicle_type);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.POST, updateurl, jsonBody2, new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {


                                    try {


                                        String msg = response.getString("msg");


                                        if (msg.equals("Vehicle has been updated.")) {


                                            Intent intent = new Intent(Bike_add.this, bike_view.class);

                                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                            startActivity(intent);
                                            Toast.makeText(Bike_add.this, msg, Toast.LENGTH_SHORT).show();


                                        }
                                        else Toast.makeText(Bike_add.this, "Bike could not be updated", Toast.LENGTH_SHORT).show();

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                }
                            });
                            jsonObjectRequest2.setRetryPolicy(new DefaultRetryPolicy(
                                    10000,
                                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                            requestQueue2.add(jsonObjectRequest2);
                        }

                }

            }





        });

}}




