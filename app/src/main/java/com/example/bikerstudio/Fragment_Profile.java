package com.example.bikerstudio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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


public class Fragment_Profile extends Fragment {

    TextView user,phone,mail,save;
    ImageButton edit;
    EditText user_name,user_p_num,user_email;
    String id=null;
    String p_username,p_email,p_phone;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String MobilePattern = "[0-9]{10}";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View root =  inflater.inflate(R.layout.fragment__profile, container, false);

        user = root.findViewById(R.id.user_name);
        phone =root.findViewById(R.id.contact);
        mail = root.findViewById(R.id.mail_address);

        user_name = root.findViewById(R.id.et_user_name);
        user_p_num = root.findViewById(R.id.et_contact);
        user_email = root.findViewById(R.id.et_mail_address);

        edit = root.findViewById(R.id.edit_button);
        save = root.findViewById(R.id.save_button);



        SharedPreferences settings = getActivity().getSharedPreferences("YOUR_PREF_NAM", 0);
        id = settings.getString("id", "");

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        String url = " http://ns1.nodeserver.co.in:8001/v1/api/get_m_profile?id=" + id;




            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = null;
                        try {
                            jsonObject = response.getJSONObject(i);

                            p_username = jsonObject.getString("username");
                            p_email = jsonObject.getString("email");
                            p_phone = jsonObject.getString("phone");

                            user.setText(p_username);
                            phone.setText(p_phone);
                            mail.setText(p_email);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }



                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            });
            requestQueue.add(jsonArrayRequest);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.setVisibility(View.GONE);
                user.setVisibility(View.GONE);
                phone.setVisibility(View.GONE);
                mail.setVisibility(View.GONE);
                save.setVisibility(View.VISIBLE);
                user_email.setVisibility(View.VISIBLE);
                user_name.setVisibility(View.VISIBLE);
                user_p_num.setVisibility(View.VISIBLE);
                user_name.setText(user.getText().toString());
                user_p_num.setText(phone.getText().toString());
                user_email.setText(mail.getText().toString());


            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Email = user_email.getText().toString();
                String Username = user_name.getText().toString();
                String Phone = user_p_num.getText().toString();




                if (Email.isEmpty()) {
                    user_email.setError("Please enter mail id");
                    user_email.requestFocus();
                }
                else if (Username.isEmpty()) {
                    user_name.setError("Please enter the username");
                    user_name.requestFocus();
                }
                else if (Phone.isEmpty() ) {
                    user_p_num.setError("Please enter the mobile number");
                    user_p_num.requestFocus();


                }

                else if(user_email.getText().toString().trim().matches(emailPattern) && (user_p_num.getText().toString().trim().matches(MobilePattern))){

                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                    String URL = "http://ns1.nodeserver.co.in:8001/v1/api/update_m_profile";

                    JSONObject jsonBody = new JSONObject();
                    try {
                        jsonBody.put("username", user_name.getText().toString().trim());
                        jsonBody.put("email", user_email.getText().toString().trim());
                        jsonBody.put("phone", user_p_num.getText().toString().trim());
                        jsonBody.put("id",id);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            String msg;
                            try {

                                msg = response.getString("msg");


                                if (msg.equals("Profile has been updated")) {

                                    edit.setVisibility(View.VISIBLE);
                                    user.setVisibility(View.VISIBLE);
                                    phone.setVisibility(View.VISIBLE);
                                    mail.setVisibility(View.VISIBLE);
                                    save.setVisibility(View.GONE);
                                    user_email.setVisibility(View.GONE);
                                    user_name.setVisibility(View.GONE);
                                    user_p_num.setVisibility(View.GONE);
                                    Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();

                                    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                                    String url = " http://ns1.nodeserver.co.in:8001/v1/api/get_m_profile?id=" + id;


                                    JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                                        @Override
                                        public void onResponse(JSONArray response) {

                                            for (int i = 0; i < response.length(); i++) {
                                                JSONObject jsonObject = null;
                                                try {
                                                    jsonObject = response.getJSONObject(i);

                                                    p_username = jsonObject.getString("username");
                                                    p_email = jsonObject.getString("email");
                                                    p_phone = jsonObject.getString("phone");

                                                    user.setText(p_username);
                                                    phone.setText(p_phone);
                                                    mail.setText(p_email);
                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }



                                        }

                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {


                                        }
                                    });
                                    requestQueue.add(jsonArrayRequest);

                                }else Toast.makeText(getActivity(), "Profile could not be updated", Toast.LENGTH_SHORT).show();


                            } catch (JSONException e) {
                                e.printStackTrace();
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
                }
                else {

                    Toast.makeText(getActivity(), "Invalid email id or phone number", Toast.LENGTH_SHORT).show();

                }



            }
        });

        return root;

    }

}