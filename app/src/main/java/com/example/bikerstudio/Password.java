package com.example.bikerstudio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class Password extends AppCompatActivity {
    Button submit;
    EditText New_pwd,Confirm_pwd;
    TextView change;
    String pass=null;
    String data=null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);



        New_pwd = (EditText)findViewById(R.id.new_pwd);
        Confirm_pwd = (EditText) findViewById(R.id.confirm_pwd);
        change = (TextView)findViewById(R.id.change);



        submit =findViewById(R.id.submit_btn);
        Intent i = getIntent();
        data = i.getStringExtra("email");
        Log.i("data",data);


        submit.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {





                String new_pwd = New_pwd.getText().toString().trim();
                String confirm_pwd = Confirm_pwd.getText().toString().trim();


                Log.i("new_pass",new_pwd);
                Log.i("email",confirm_pwd);

//
                if (new_pwd.isEmpty()) {
                    New_pwd.setError("Please enter mail id");
                    New_pwd.requestFocus();
                } else if (confirm_pwd.isEmpty()) {
                    Confirm_pwd.setError("Please enter the password");
                    Confirm_pwd.requestFocus();
                }else if ( new_pwd.equals(confirm_pwd)){


                    pass = new_pwd;
                    volleyPost();


                }
                else {
                    Toast.makeText(Password.this, "password doesn't match", Toast.LENGTH_SHORT).show();


                }





            }



        });


        }
    public void volleyPost(){
        String postUrl = "http://ns1.nodeserver.co.in:8001/v1/api/new_pass";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String new_pwd = New_pwd.getText().toString().trim();
        String  data1 = data.trim();





        JSONObject postData = new JSONObject();
        try {
          postData.put("new_pass", new_pwd);
          postData.put("email", data);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    int Success = response.getInt("code");


                    if (Success == 200 ){


                        Intent intent = new Intent(Password.this,login.class);
                        Toast.makeText(Password.this, "password changed successfully", Toast.LENGTH_SHORT).show();
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);


                    }
                    else {
                        Toast.makeText(Password.this, "Password doesn't changed,try again!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Password.this,login.class);

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
        requestQueue.add(jsonObjectRequest);}

}