package com.example.bikerstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Forget extends AppCompatActivity {

    EditText editText_mail;
    Button reset_btn;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

//    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);



        editText_mail = (EditText) findViewById(R.id.email);

        reset_btn= (Button) findViewById(R.id.reset_btn);

//        String mail = editText_mail.getText().toString();
//
//
//


        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//


        String email = editText_mail.getText().toString();




                try {
            RequestQueue requestQueue = Volley.newRequestQueue(Forget.this);
            String URL = "http://ns1.nodeserver.co.in:8001/v1/api/forget_pass";
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("email", email);





            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL,jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {


                    Log.i("LOG_VOLLEY", response.toString());

                    if (email.isEmpty()) {
                        editText_mail.setError("Please enter mail id");
                        editText_mail.requestFocus();
                    } else if ((editText_mail.getText().toString().trim().matches(emailPattern))) {
//                        if(toast!=null){
//                            toast.cancel();
//                            Register.super.onBackPressed();
//                            finish();}


                        Toast.makeText(Forget.this, "OTP send your registered mail id", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Forget.this, OTP.class);
                        intent.putExtra("email", email);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);


                    } else {
//                        if(toast!=null){
//                            toast.cancel();
//                            Register.super.onBackPressed();
//                            finish();}
                        Toast.makeText(Forget.this, "please enter a valid email address  ", Toast.LENGTH_LONG).show();

                    }


                }




            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LOG_VOLLEY", error.toString());
                }
            });
                    jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                            10000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            requestQueue.add(jsonObjectRequest);
        } catch (JSONException e) {
            e.printStackTrace();
        }

            }
        });

    }}