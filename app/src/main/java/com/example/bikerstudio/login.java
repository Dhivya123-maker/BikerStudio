package com.example.bikerstudio;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.bikerstudio.utils.PreferenceUtils;


import org.json.JSONException;
import org.json.JSONObject;

public class login extends AppCompatActivity {

    EditText email,password;
    ImageView show_pass_btn;

    Button login;
     ImageButton   back_btn;
    TextView create,forget;
    Toast toast = null;
    private long pressedTime;
    String change_pwd;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    ImageView show_pass_reg;




    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);


        show_pass_btn=(ImageView) findViewById(R.id.show_pass_btn1);

        email = (EditText)findViewById(R.id.emailtext);
        password = (EditText)findViewById(R.id.passtext);
        login = (Button)findViewById(R.id.login3);
        create = (TextView)findViewById(R.id.create);

        Intent i = getIntent();
        change_pwd = i.getStringExtra("email");
       password.setText(change_pwd);





        forget = findViewById(R.id.forget);


        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(login.this,Forget.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });




        show_pass_reg = findViewById(R.id.show_pass_btn_reg);



        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_pass_reg.setVisibility(View.VISIBLE);
            }
        });





        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(login.this,Register.class);
                i.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(i);
            }
        });



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final String mail = email.getText().toString();
                final String pass = password.getText().toString();
                 String tokan = null;




                 if (mail.isEmpty()) {
                    email.setError("Please enter mail id");
                    email.requestFocus();
                } else if (pass.isEmpty()) {
                    password.setError("Please enter the password");
                    password.requestFocus();
                    show_pass_reg.setVisibility(View.INVISIBLE);
                } else if(email.getText().toString().trim().matches(emailPattern)){


                     final String URL = "http://ns1.nodeserver.co.in:8001/v1/api/login?email=" + mail + "&password=" + pass;


                     final RequestQueue requestQueue = Volley.newRequestQueue(login.this);
                     JsonObjectRequest request = new JsonObjectRequest(
                             Request.Method.GET,
                             URL,
                             null,
                             new Response.Listener<JSONObject>() {

                                 @Override
                                 public void onResponse(JSONObject response) {

//                                Intent login = new Intent(login.this,home.class);
//                                startActivity(login);

                                     Log.i("LOG_VOLLEY", response.toString());
//                                Toast.makeText( com.example.bikerstudio.login.this ,"Success..."+response.toString(),Toast.LENGTH_LONG).show();

                                     try {

//                                    JSONObject jsonObject = new JSONObject(response.toString());

                                         String Success;


                                         Success = response.getString("success");

                                         String msg = response.getString("msg");


                                         if (Success == "true") {


                                             Intent intent = new Intent(login.this, home.class);
                                             String id = response.getString("id");
                                             intent.putExtra("user_id", id);
                                             SharedPreferences settings = getSharedPreferences("YOUR_PREF_NAM", 0);
                                             SharedPreferences.Editor editor = settings.edit();
                                             editor.putString("id", id);
                                             editor.commit();
                                             PreferenceUtils.saveid(id, login.this);
                                             String tokan = response.getString("tokan");
                                             PreferenceUtils.saveTokan(tokan, login.this);
                                             intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

                                             startActivity(intent);


                                         } else if (Success == "false") {
//                                        if(toast!=null){
//                                            toast.cancel();
//                                            login.super.onBackPressed();
//                                            finish();
//                                        }
                                             Log.i("success", Success.toString());
                                             Toast.makeText(login.this, msg, Toast.LENGTH_SHORT).show();


                                         }


                                     } catch (JSONException e) {
                                         e.printStackTrace();
                                     }


                                 }


                             },
                             new Response.ErrorListener() {
                                 @Override
                                 public void onErrorResponse(VolleyError error) {

                                 }

                             });


                     request.setRetryPolicy(new DefaultRetryPolicy(
                             10000,
                             DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                             DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                     requestQueue.add(request);

                 }else {
                     Toast.makeText(login.this, "Invalid email id", Toast.LENGTH_SHORT).show();
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
                            login.super.onBackPressed();
                            finishAffinity();
                        }
                    }).create().show();

        }
        else  {

            Toast.makeText(login.this, "Press back to exit", Toast.LENGTH_SHORT).show();

        }
        pressedTime = System.currentTimeMillis();
    }


    public void ShowHidePass(View view){

        if(view.getId()==R.id.show_pass_btn1){

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