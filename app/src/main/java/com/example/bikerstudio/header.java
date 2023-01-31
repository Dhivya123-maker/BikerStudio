package com.example.bikerstudio;;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class header extends AppCompatActivity {

    String username,id;
    TextView user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        user = (TextView) findViewById(R.id.USER);

        SharedPreferences settings = getSharedPreferences("YOUR_PREF_NAM", 0);
        id = settings.getString("id", "");

        RequestQueue requestQueue = Volley.newRequestQueue(header.this);
        String url = " http://ns1.nodeserver.co.in:8001/v1/api/get_m_profile?id=" + id;




        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = response.getJSONObject(i);

                        username = jsonObject.getString("username");


                        Toast.makeText(header.this, username, Toast.LENGTH_SHORT).show();
                        Toast.makeText(header.this, id, Toast.LENGTH_SHORT).show();

                        user.setText(username);

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
    }
}
