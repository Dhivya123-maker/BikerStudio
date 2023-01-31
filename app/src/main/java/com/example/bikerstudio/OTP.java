package com.example.bikerstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.example.bikerstudio.utils.PreferenceUtils;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class OTP extends AppCompatActivity {
    TextView get_txt,resend_otp;

    EditText editText;
    Button verify;
    String Data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        editText= findViewById(R.id.otp);
        get_txt=findViewById(R.id.get_txt);
//        resend_otp= findViewById(R.id.resend_otp);



        Intent i = getIntent();
        Data = i.getStringExtra("email");
        get_txt.setText(Data);




        verify=findViewById(R.id.verify_btn);




        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {





                volleyPost();


                }

        });


//        resend_otp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String postUrl = "http://ns1.nodeserver.co.in:8001/v1/api/otp_verfy";
//                RequestQueue requestQueue = Volley.newRequestQueue(OTP.this);
//
//                JSONObject postData = new JSONObject();
//                try {
//                    String email = get_txt.getText().toString().trim();
//                    String otp = editText.getText().toString().trim();
//
//                    postData.put("email", email);
//                    postData.put("otp", otp);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//
//                        try {
//                            int Success = response.getInt("code");
//                            String otp = editText.getText().toString().trim();
//
//                            if(otp.isEmpty()){
//                                editText.setError("please enter the OTP");
//                                editText.requestFocus();
//                            }
//
//                            else if (Success == 200){
//
//                                Toast.makeText(OTP.this, "OTP Verified successfully", Toast.LENGTH_SHORT).show();
//
//                                Intent intent = new Intent(OTP.this,Password.class);
//                                intent.putExtra("email",Data);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                                startActivity(intent);
//                            }
//                            else {
//
//                                Log.i("success", String.valueOf(Success));
//                                Toast.makeText( OTP.this ,"invalid otp ",Toast.LENGTH_SHORT).show();
//                            }
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        error.printStackTrace();
//                    }
//                });
//
//                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
//                        10000,
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//                requestQueue.add(jsonObjectRequest);
//
//            }
//        });


    }


    public void volleyPost(){
        String postUrl = "http://ns1.nodeserver.co.in:8001/v1/api/otp_verfy";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JSONObject postData = new JSONObject();
        try {
            String email = get_txt.getText().toString().trim();
                String otp = editText.getText().toString().trim();

            postData.put("email", email);
            postData.put("otp", otp);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    int Success = response.getInt("code");
                    String otp = editText.getText().toString().trim();

                    if(otp.isEmpty()){
                        editText.setError("please enter the OTP");
                        editText.requestFocus();
                    }

                   else if (Success == 200){

                        Toast.makeText(OTP.this, "OTP Verified successfully", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(OTP.this,Password.class);
                        intent.putExtra("email",Data);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                    }
                    else {

                        Log.i("success", String.valueOf(Success));
                        Toast.makeText( OTP.this ,"invalid otp ",Toast.LENGTH_SHORT).show();
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

}