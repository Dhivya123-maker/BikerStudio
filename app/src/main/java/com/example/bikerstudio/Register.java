package com.example.bikerstudio;

import static com.airbnb.lottie.L.TAG;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class Register<FirebaseMethods, FirebaseDatabase> extends AppCompatActivity {

    EditText email,username,phone_no,password;
    Button Register;
    TextView login;
    Toast toast;
    private long pressedTime;
    ImageView show_pass_reg;

    ImageButton back_btn1;
    String Email,Username,Phone,Password;


    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);






        email = (EditText) findViewById(R.id.emailtext);
        username = (EditText) findViewById(R.id.usernametext);
        phone_no = (EditText) findViewById(R.id.phonetext);
        password = (EditText) findViewById(R.id.passtext);
        Register = (Button) findViewById(R.id.Register);
        login=(TextView) findViewById(R.id.or2);
        show_pass_reg = findViewById(R.id.show_pass_btn_reg);



        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_pass_reg.setVisibility(View.VISIBLE);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(com.example.bikerstudio.Register.this,login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });


        Register.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {



                Email = email.getText().toString();
                Username = username.getText().toString();
                Phone = phone_no.getText().toString();
                Password = password.getText().toString();



                if (Email.isEmpty()) {
                    email.setError("Please enter mail id");
                    email.requestFocus();
                }
                else if (Username.isEmpty()) {
                    username.setError("Please enter the username");
                    username.requestFocus();
                }
                else if (Phone.isEmpty() ) {
                    phone_no.setError("Please enter the mobile number");
                    phone_no.requestFocus();


                } else if (Password.isEmpty()) {
                    password.setError("Please enter the password");
                    password.requestFocus();
                    show_pass_reg.setVisibility(View.INVISIBLE);

                } else if(email.getText().toString().trim().matches(emailPattern) && (phone_no.getText().toString().trim().matches(MobilePattern))){
                    email();
                }
                else {

                    Toast.makeText(Register.this, "Invalid email id or phone number", Toast.LENGTH_SHORT).show();

                }

            }


        });


    }

    public void onBackPressed() {

        if (pressedTime + 2000 > System.currentTimeMillis()) {
            new AlertDialog.Builder(this).setIcon(R.drawable.ic_baseline_warning_24)
                    .setMessage("Are you sure want to exit")
                    .setNegativeButton(android.R.string.no,null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Register.super.onBackPressed();
                            finishAffinity();
                        }
                    }).create().show();

        }
        else  {

            Toast.makeText(Register.this, "Press back to exit", Toast.LENGTH_SHORT).show();

        }
        pressedTime = System.currentTimeMillis();
    }

    public  void email(){
        Email = email.getText().toString();
        Username = username.getText().toString();
        Phone = phone_no.getText().toString();
        Password = password.getText().toString();


        RequestQueue requestQueue = Volley.newRequestQueue(Register.this);
        String URL = "http://ns1.nodeserver.co.in:8001/v1/api/signup";
        JSONObject jsonBody = new JSONObject();

        try {
            jsonBody.put("email", Email);
            jsonBody.put("username", Username);
            jsonBody.put("phone", Phone);
            jsonBody.put("password", Password);


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try{
                        String Success = response.getString("success");
                        String msg = response.getString("msg");
                        if (Success == "true" ){

                            Toast.makeText(Register.this, msg, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this,login.class);

                            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(intent);


                        }
                        else {
                            Toast.makeText(Register.this, msg, Toast.LENGTH_SHORT).show();


                        }


                    }catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

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
    public void ShowHidePass(View view){

        if(view.getId()==R.id.show_pass_btn_reg){

            if(password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                ((ImageView)(view)).setImageResource(R.drawable.ic_baseline_visibility_24);

                //Show Password
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            else{
                ((ImageView)(view)).setImageResource(R.drawable.ic_baseline_visibility_off_24);

                //Hide Password
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());

            }
        }
    }


    }
