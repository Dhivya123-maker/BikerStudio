package com.example.bikerstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class order_conformation extends AppCompatActivity {
    String b_id,bike_view,u_id,bike_num,bike_name,selectedRbText="";
    ArrayList<String> s_id;
    Button order;
    RadioButton rb1,rb2,radioButton;
    RadioGroup radioGroup;
    TextView tv_bikename,tv_bikenum,change;
    EditText des;
    int selectedRadioButtonId = -1;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_conformation);

        order=findViewById(R.id.order_button);
        change = findViewById(R.id.change_bike);



        rb1 = findViewById(R.id.radioButton);
        rb2 = findViewById(R.id.radioButton2);
        radioGroup = findViewById(R.id.radio_group);

        tv_bikename = findViewById(R.id.bike_order);
        tv_bikenum = findViewById(R.id.vechiel);
        des = findViewById(R.id.o_desc);

        Intent i = getIntent();
        b_id=i.getStringExtra("b_id");
        s_id=i.getStringArrayListExtra("s_id");


        SharedPreferences settings = getSharedPreferences("YOUR_PREF_NAM", 0);
        u_id = settings.getString("id", "");

        bike_view = i.getStringExtra("view");
        bike_name=i.getStringExtra("bike_name");
        bike_num = i.getStringExtra("bike_no");


        tv_bikename.setText(bike_name);
        tv_bikenum.setText(bike_num);




        if (rb1.isChecked()){
         rb2.setClickable(false);
            selectedRbText = rb1.getText().toString();

        }else{
            rb1.setClickable(true);
            selectedRbText = rb2.getText().toString();
        }










        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(order_conformation.this,bike_view.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("s_id",s_id);
                startActivity(intent);
            }
        });

        id="1";

        SharedPreferences settings111 = getSharedPreferences("order_id", 0);
        SharedPreferences.Editor editor111 = settings111.edit();
        editor111.putString("o_id",id);
        editor111.commit();




        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


              String des_1 = des.getText().toString().trim();

                selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                radioButton = findViewById(selectedRadioButtonId);

                //selectedRbText = radioButton.getText().toString();
                if (selectedRadioButtonId != -1)
                {
                    selectedRbText = radioButton.getText().toString();

                    RequestQueue requestQueue = Volley.newRequestQueue(order_conformation.this);
                    String URL = "http://ns1.nodeserver.co.in:8001/v1/api/order";

                    JSONObject jsonBody = new JSONObject();
                    try {
                        jsonBody.put("s_id", s_id);
                        jsonBody.put("u_id", u_id);
                        jsonBody.put("b_id", b_id);
                        jsonBody.put("des", des_1);
                        jsonBody.put("d_type", selectedRbText);



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            String success;
                            try {

                                success = response.getString("msg");

                                id= response.getString("orderid");

                                SharedPreferences settings111 = getSharedPreferences("order_id", 0);
                                SharedPreferences.Editor editor111 = settings111.edit();
                                editor111.putString("o_id",id);
                                editor111.commit();

                                if (success.equals("order placed")) {


                                    Intent intent = new Intent(order_conformation.this, home.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(intent);
                                    Toast.makeText(order_conformation.this, "order placed", Toast.LENGTH_SHORT).show();
                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            VolleyLog.d("TAG", "Error: " + error.getMessage());
                        }
                    });
                    jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                            1000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    requestQueue.add(jsonObjectRequest);
//
//                    try {
//                        RequestQueue requestQueue = Volley.newRequestQueue(order_conformation.this);
//                        String URL = "http://ns1.nodeserver.co.in:8001/v1/api/order";
////
//
//                    JSONObject jsonBody = new JSONObject();
//
//                        jsonBody.put("b_id", b_id);
//                      //  jsonBody.put("s_id", s_id);
//                        jsonBody.put("u_id", u_id);
//                        jsonBody.put("des", des.getText().toString().trim());
//                        jsonBody.put("d_type", selectedRbText);
//                        final String requestBody = jsonBody.toString();
//
//                        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                String success;
//
//
//                                success = response;
//
//
//                                if (success.equals("200")) {
//
//
//                                    Intent intent = new Intent(order_conformation.this, home.class);
//                                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                                    startActivity(intent);
//                                    Toast.makeText(order_conformation.this, "order placed", Toast.LENGTH_SHORT).show();
//                                }
//
//
//
//                            }
//                        }, new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Log.e("VOLLEY", error.toString());
//                            }
//                        }) {
//                            @Override
//                            public String getBodyContentType() {
//                                return "application/json; charset=utf-8";
//                            }
//
//                            @Override
//                            public byte[] getBody() throws AuthFailureError {
//                                try {
//                                    return requestBody == null ? null : requestBody.getBytes("utf-8");
//                                } catch (UnsupportedEncodingException uee) {
//                                    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
//                                    return null;
//                                }
//                            }
//
//                            @Override
//                            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                                String responseString = "";
//                                if (response != null) {
//                                    responseString = String.valueOf(response.statusCode);
//                                    // can get more details such as response.headers
//                                }
//                                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//                            }
//                        };
//
//                       stringRequest.setRetryPolicy(new DefaultRetryPolicy(
//                            1000,
//                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//                        requestQueue.add(stringRequest);
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }


//
//                    Toast.makeText(order_conformation.this, b_id, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(order_conformation.this, u_id, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(order_conformation.this, s_id.toString(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(order_conformation.this, selectedRbText, Toast.LENGTH_SHORT).show();
//                    Toast.makeText(order_conformation.this, des.getText().toString().trim(), Toast.LENGTH_SHORT).show();
//
//                    Log.i("11111111",u_id);
//                    Log.i("11111111",s_id.toString());
//                    Log.i("11111111",b_id);
//                    Log.i("11111111",des.getText().toString().trim());
//                    Log.i("11111111",selectedRbText);

                }else Toast.makeText(order_conformation.this, "Please select your delivery method", Toast.LENGTH_SHORT).show();

            }




//        if (selectedRbText != null){
//
//            selectedRbText = rb1.getText().toString();
//            selectedRbText = rb2.getText().toString();
//        }else Toast.makeText(order_conformation.this, "select", Toast.LENGTH_SHORT).show();







//
//


//
//




        });




    }
    public void onBackPressed() {


        Intent intent = new Intent(order_conformation.this,home.class);
        startActivity(intent);
    }
//    public  void checkButton (View v){
//        int radio = radioGroup.getCheckedRadioButtonId();
//        RadioButton radioButton = findViewById(radio);
//
//
//    }


}