package com.example.bikerstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.bikerstudio.utils.PreferenceUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class home extends AppCompatActivity {


    private static final int REQUEST_CALL = 1 ;
    BottomNavigationView bottomNavigationView;
    private long pressedTime;
    FloatingActionButton floatingActionButton;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MaterialToolbar toolbar1;
    AppBarLayout appBarLayout;
    LinearLayout linearLayout;
    NotificationBadge badge;
    ImageView wheel;
    int S_length = 0;
    int mCount =0;
    String service_name;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomnavigationbar);
        floatingActionButton = findViewById(R.id.fab);
        wheel = findViewById(R.id.wheel);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        appBarLayout = findViewById(R.id.nav_icon1);
        badge = findViewById(R.id.badge);
//        linearLayout = findViewById(R.id.lnr);






        wheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent1 = new Intent(home.this, Notification.class);
                intent1.putExtra("service_name",service_name);
                startActivity(intent1);
                wheel.clearAnimation();
                mCount = 0;

                badge.setNumber(mCount);
//
            }
        });




        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(home.this,
                drawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);


        toolbar1 = findViewById(R.id.topAppBar);

        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);

            }
        });



        navigationView.setCheckedItem(R.id.nav_home);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();

                switch (id) {

                    case R.id.nav_home:

                        Intent home = new Intent(com.example.bikerstudio.home.this, home.class);
                        home.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(home);
                        break;

                    case R.id.nav_bike:

                        Intent bike = new Intent(com.example.bikerstudio.home.this, bike_view.class);
                        bike.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(bike);


                        break;


                    case R.id.nav_sc:


                            Intent sc = new Intent(com.example.bikerstudio.home.this, booking.class);
                            sc.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            sc.putExtra("scooter", "scooter");
                            startActivity(sc);

                        break;

                    case R.id.nav_bk:

                        Intent bk = new Intent(com.example.bikerstudio.home.this, booking.class);
                        bk.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        bk.putExtra("bike","motorbike");
                        startActivity(bk);


                        break;

                    case R.id.nav_sbk:

                        Intent sbk = new Intent(com.example.bikerstudio.home.this, booking.class);
                        sbk.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        sbk.putExtra("sb","superbike");
                        startActivity(sbk);
                        break;
                    case R.id.nav_p:

                        Intent p = new Intent(com.example.bikerstudio.home.this, Emergency.class);
                        p.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        p.putExtra("punture","punture");
                        startActivity(p);
                        break;

                    case R.id.nav_b:

                        Intent b = new Intent(com.example.bikerstudio.home.this, Emergency.class);
                        b.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        b.putExtra("breakdown","breakdown");
                        startActivity(b);


                        break;

                    case R.id.nav_f:

                        Intent f = new Intent(com.example.bikerstudio.home.this, Emergency.class);
                        f.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        f.putExtra("fuel","fuel");
                        startActivity(f);
                        break;

                    case R.id.nav_About:

                        Intent a = new Intent(com.example.bikerstudio.home.this, About.class);
                        a.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(a);
                        break;


                    case R.id.nav_log_out:
                        String tokan = null;
                        int length = 0;
                        String uid = null;
                        String o_id = null;

                        SharedPreferences settings11 = getSharedPreferences("YOUR_PREF_NAME11", 0);
                        SharedPreferences.Editor editor11 = settings11.edit();
                        editor11.putInt("bv", length );
                        editor11.commit();

                        SharedPreferences settings = getSharedPreferences("YOUR_PREF_NAM", 0);
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("id", uid);
                        editor.commit();

                        PreferenceUtils.saveTokan(tokan, home.this);


                        SharedPreferences settings111 = getSharedPreferences("order_id", 0);
                        SharedPreferences.Editor editor111 = settings111.edit();
                        editor111.putString("o_id",o_id);
                        editor111.commit();

                        PreferenceUtils.saveLength(length, home.this);

                        Intent intent = new Intent(com.example.bikerstudio.home.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);

                        break;

                }


                return true;

            }

        });






        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Uri number = Uri.parse("tel:9965919585");
//                Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
//                startActivity(callIntent);
//
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:9965919585"));
//                startActivity(callIntent);
                makePhoneCall();
            }
        });



//        bottomNavigationView.setBackground(null);
//
//        bottomNavigationView.getMenu().getItem(2).setEnabled(false);
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new FragmentHome()).commit();

            String id;

            SharedPreferences settings = getSharedPreferences("YOUR_PREF_NAM", 0);
            id = settings.getString("id", "");

            RequestQueue requestQueue = Volley.newRequestQueue(home.this);
            String url = " http://ns1.nodeserver.co.in:8001/v1/api/get_vehicle?body=" + id;


            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                    SharedPreferences settings11 = getSharedPreferences("YOUR_PREF_NAME11", 0);
                    SharedPreferences.Editor editor11 = settings11.edit();
                    editor11.putInt("bv", response.length());
                    editor11.commit();
//
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            });
            requestQueue.add(jsonArrayRequest);
//            linearLayout.setVisibility(View.GONE);
//            appBarLayout.setVisibility(View.VISIBLE);
            wheel.setVisibility(View.VISIBLE);
            toolbar1.setVisibility(View.VISIBLE);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment temp = null;

                switch (item.getItemId()) {

                    case R.id.mHome:
                        temp = new FragmentHome();
//                        appBarLayout.setVisibility(View.VISIBLE);
//                        linearLayout.setVisibility(View.GONE);
                        wheel.setVisibility(View.VISIBLE);
                        toolbar1.setVisibility(View.VISIBLE);

                        break;
                    case R.id.mBike:
                        temp = new FragmentBike();
//                        appBarLayout.setVisibility(View.GONE);
//                        linearLayout.setVisibility(View.VISIBLE);
                        wheel.setVisibility(View.INVISIBLE);
                        toolbar1.setVisibility(View.INVISIBLE);



                        break;
                    case R.id.mPerson:
                        temp = new Fragment_Profile();
//                        appBarLayout.setVisibility(View.GONE);
//                        linearLayout.setVisibility(View.VISIBLE);
                        wheel.setVisibility(View.INVISIBLE);
                        toolbar1.setVisibility(View.INVISIBLE);

                        break;
                    case R.id.mOrders:
                        temp = new FragmentOrder();
//                        appBarLayout.setVisibility(View.GONE);
//                        linearLayout.setVisibility(View.VISIBLE);
                        wheel.setVisibility(View.INVISIBLE);
                        toolbar1.setVisibility(View.INVISIBLE);


                }

                getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, temp).commit();
                return true;
            }
        });




        RequestQueue queue = Volley.newRequestQueue(home.this);
        String JSON_URL = "http://ns1.nodeserver.co.in:8001/v1/api/getservices";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                S_length = response.length();




                try {
                    int last = response.length() - 1;
                JSONObject jsonObject1 = response.getJSONObject(last);



                    service_name = jsonObject1.getString("name");



//                    if (PreferenceUtils.getLength(home.this) == 0 ) {
//                        PreferenceUtils.saveLength(S_length, home.this);
//                    }else
//                    {
//                        PreferenceUtils.saveLength(S_length, home.this);
//                    }


                    if (PreferenceUtils.getLength(home.this) == 0 && PreferenceUtils.getLength(home.this) == (response.length())) {
                        PreferenceUtils.saveLength(S_length, home.this);
                    } else if (PreferenceUtils.getLength(home.this) < response.length()) {

                        PreferenceUtils.saveLength(response.length(), home.this);


                Animation animationUtils = AnimationUtils.loadAnimation(home.this, R.anim.rotate_clock);
                wheel.startAnimation(animationUtils);
                badge.setNumber(++mCount);
//

//
//
//        rotate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });



                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationChannel channel = new NotificationChannel("My Notification", "My Notification", NotificationManager.IMPORTANCE_HIGH);

                            NotificationManager manager = getSystemService(NotificationManager.class);
                            manager.createNotificationChannel(channel);
                        }
                        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        NotificationCompat.Builder builder =
                                new NotificationCompat.Builder(home.this, "My Notification")
                                        .setSound(uri)
                                        .setContentTitle("Service added")
                                        .setContentText("New " + service_name + " service is added")
                                        .setAutoCancel(true)
                                        .setSmallIcon(R.drawable.bike)
                                        .setVibrate(new long[]{1000, 1000, 1000,
                                                1000, 1000})
                                        .setOnlyAlertOnce(true);


                        Intent notificationIntent = new Intent(home.this, Notification.class);
                        PendingIntent contentIntent = PendingIntent.getActivity(home.this, 0, notificationIntent,
                                PendingIntent.FLAG_UPDATE_CURRENT);
                        builder.setContentIntent(contentIntent);

                        // Add as notification
                        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        manager.notify(0, builder.build());



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




    }

//    public  void onBackPressed(){
//
//        counter++;
//        if(counter ==1){
//            Intent intent = new Intent(home.this,home.class);
//            startActivity(intent);
//
//            super.onBackPressed();
//
//        }
    public void onBackPressed() {

         if (pressedTime + 2000 > System.currentTimeMillis()) {
            new AlertDialog.Builder(this).setIcon(R.drawable.ic_baseline_warning_24)
                    .setMessage("Are you sure want to exit")
                    .setNegativeButton(android.R.string.no,null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            home.super.onBackPressed();
                            finishAffinity();
                        }
                    }).create().show();

        }
          else  {

              Toast.makeText(home.this, "Press back to exit", Toast.LENGTH_SHORT).show();

        }
        pressedTime = System.currentTimeMillis();
    }

    private void makePhoneCall() {
        String number = "7708814444";
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(home.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(home.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }
            else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission Denied to make a call" +
                        "", Toast.LENGTH_SHORT).show();
            }
        }
    }



    }


