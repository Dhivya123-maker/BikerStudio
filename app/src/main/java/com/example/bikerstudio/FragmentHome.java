package com.example.bikerstudio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentHome extends Fragment {


    ImageView gear, logo;
//    MaterialToolbar toolbar1;
    private int mCount = 0;
    LinearLayout scotterimg, bikeimg, superbikeimg, punture, breakdown, fuel, bike_access, external_access, internal_access;
    TextView name, price, dis;
    String user_id;
    ViewFlipper slider;
    ImageView rotate;
    Button notify_btn;
    ViewPager viewPager, viewPager1;
    LinearLayout sliderDotspanel, panel2;
    private int dotscount, dotscount2;
    private ImageView[] dots;
    private ImageView[] dots1;
    SliderView sliderView, sliderview1;
    int length =0;
    String id;


    private static String JSON_URL = "http://ns1.nodeserver.co.in:8001/v1/api/getservices";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);


        ArrayList<SliderData> sliderDataArrayList = new ArrayList<>();

        ArrayList<SliderDataTop> sliderDataArrayList1 = new ArrayList<>();


        sliderView = root.findViewById(R.id.slider_btm);
        sliderview1 = root.findViewById(R.id.slider_top);

        final String BTM_URL = "http://ns1.nodeserver.co.in:8001/v1/api/m_bottomposter";


        final RequestQueue requestQueue2 = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(
                Request.Method.GET,
                BTM_URL,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String obj = jsonObject.getString("img");

                        Log.i("123", obj);


                        SliderData sd = new SliderData();

                        sd.setImage("http://ns1.nodeserver.co.in:8001" + obj);
                        sliderDataArrayList.add(sd);


                        SliderBottomAdapter adapter = new SliderBottomAdapter(getActivity(), sliderDataArrayList);
                        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
                        sliderView.setSliderAdapter(adapter);
//                        sliderView.setScrollTimeInSec(3);
//                        sliderView.setAutoCycle(true);
//                        sliderView.startAutoCycle();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue2.add(jsonObjectRequest2);


        final String TOP_URL = "http://ns1.nodeserver.co.in:8001/v1/api/m_topposter";


        final RequestQueue requestQueue3 = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest3 = new JsonObjectRequest(
                Request.Method.GET,
                TOP_URL,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String obj = jsonObject.getString("img");

                        Log.i("123", obj);


                        SliderDataTop sd1 = new SliderDataTop();

                        sd1.setImage("http://ns1.nodeserver.co.in:8001" + obj);
                        sliderDataArrayList1.add(sd1);


                        SliderTopAdapter adapter1 = new SliderTopAdapter(getActivity(), sliderDataArrayList1);
                        sliderview1.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
                        sliderview1.setSliderAdapter(adapter1);
                        sliderview1.setScrollTimeInSec(3);
                        sliderview1.setAutoCycle(true);
                        sliderview1.startAutoCycle();


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue3.add(jsonObjectRequest3);


//
//        viewPager = (ViewPager) root.findViewById(R.id.viewPager);
//
//        viewPager1 = (ViewPager) root.findViewById(R.id.viewPager1);
//
//
//        ViewPagerAdapter1 viewPagerAdapter1 = new ViewPagerAdapter1(getActivity());
//
//
//        viewPager1.setAdapter(viewPagerAdapter1);
//
//
//        panel2 = (LinearLayout) root.findViewById(R.id.SliderDots1);
//

//
//
//        dotscount2 = viewPagerAdapter1.getCount();
//
//
//
//        dots1 = new ImageView[dotscount2];
//
//        for (int i = 0; i < dotscount2; i++) {
//
//            dots1[i] = new ImageView(getActivity());
//            dots1[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.non_active_dot));
//
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//
//            params.setMargins(8, 0, 8, 0);
//
//            panel2.addView(dots1[i], params);
//
//
//
//        dots1[0].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));
//
//
//
//
//
//        viewPager1.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//                for (int i = 0; i < dotscount2; i++) {
//                    dots1[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.non_active_dot));
//                }
//
//                dots1[position].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.active_dot));
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


//        drawerLayout = root.findViewById(R.id.drawer_layout);
//        navigationView = root.findViewById(R.id.nav_view);


        gear = root.findViewById(R.id.sidemenu);
        logo = root.findViewById(R.id.logo);
        slider = root.findViewById(R.id.silder);
//
//        NotificationBadge badge = root.findViewById(R.id.badge);
//        rotate = root.findViewById(R.id.wheel);
        bike_access = root.findViewById(R.id.bike_ac);
        external_access = root.findViewById(R.id.external_ac);
        internal_access = root.findViewById(R.id.internal_ac);


//


        bike_access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Accessories.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("bike_accessories", "bike_accessories");
                startActivity(intent);
            }
        });


        external_access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Accessories.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("external_accessories", "external_accessories");
                startActivity(intent);
            }
        });


        internal_access.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Accessories.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("internal_accessories", "internal_accessories");
                startActivity(intent);
            }
        });


//        DrawerLayout drawerLayout = root.findViewById(R.id.drawer_layout);
//
//
//        toolbar1 = root.findViewById(R.id.topAppBar);
//
//        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                drawerLayout.openDrawer(GravityCompat.START);
//
//            }
//        });


        scotterimg = (LinearLayout) root.findViewById(R.id.scooter);
        bikeimg = (LinearLayout) root.findViewById(R.id.Bike);
        superbikeimg = (LinearLayout) root.findViewById(R.id.super_bike);
        punture = (LinearLayout) root.findViewById(R.id.punture);
        breakdown = (LinearLayout) root.findViewById(R.id.Breakdown);
        fuel = (LinearLayout) root.findViewById(R.id.fuel);


        punture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Emergency.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("punture", "punture");
                startActivity(intent);
            }
        });

        breakdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Emergency.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("breakdown", "breakdown");
                startActivity(intent);
            }
        });
        fuel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), Emergency.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                intent.putExtra("fuel", "fuel");
                startActivity(intent);
            }
        });

//
//        navigationView.bringToFront();
//        ActionBarDrawerToggle toggle = new
//                ActionBarDrawerToggle(getActivity(),
//                drawerLayout,
//                R.string.navigation_drawer_open,
//                R.string.navigation_drawer_close);
//        drawerLayout.addDrawerListener(toggle);
//        toggle.syncState();


        name = root.findViewById(R.id.name);
        price = root.findViewById(R.id.price);
        dis = root.findViewById(R.id.description);

        Intent intent = getActivity().getIntent();
        user_id = intent.getStringExtra("user_id");















        scotterimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                SharedPreferences settings11 = getActivity().getSharedPreferences("YOUR_PREF_NAME11", 0);
                length  = settings11.getInt("bv", 0);





                Log.i("dygdhsdhdsfh", String.valueOf(length));


                if (length == 0) {

                    Intent bv = new Intent(getActivity(), Bike_add.class);
                    bv.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(bv);


                }else {

                    Intent intent = new Intent(getActivity(), booking.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtra("scooter", "scooter");
                    startActivity(intent);

                }


            }


        });

        superbikeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences settings11 = getActivity().getSharedPreferences("YOUR_PREF_NAME11", 0);
                length  = settings11.getInt("bv", 0);

                if (length == 0) {

                    Intent bv = new Intent(getActivity(), Bike_add.class);
                    bv.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(bv);


                }else {
                    Intent intent = new Intent(getActivity(), booking.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtra("sb", "superbike");
                    startActivity(intent);
                }

            }


        });

        bikeimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences settings11 = getActivity().getSharedPreferences("YOUR_PREF_NAME11", 0);
                length  = settings11.getInt("bv", 0);

                if (length == 0) {

                    Intent bv = new Intent(getActivity(), Bike_add.class);
                    bv.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(bv);


                }else {
                    Intent intent = new Intent(getActivity(), booking.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    intent.putExtra("bike", "motorbike");
                    startActivity(intent);
                }
            }


        });


//        navigationView.setCheckedItem(R.id.nav_home);
//
//        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//
//                int id = item.getItemId();
//
//                switch (id) {
//
//                    case R.id.nav_home:
//
//                        Intent home = new Intent(getActivity(), home.class);
//                        startActivity(home);
//                        break;
//
//                    case R.id.nav_bike:
//
//                        Intent bike = new Intent(getActivity(), FragmentBike.class);
//                        startActivity(bike);
//
//
//                        break;
//
//                    case R.id.nav_login:
//
//                        Intent login = new Intent(getActivity(), booking.class);
//                        startActivity(login);
//                        break;
//
//
//                    case R.id.nav_order:
//
//                        Intent orders = new Intent(getActivity(), com.example.bikerstudio.order_conformation.class);
//                        startActivity(orders);
//
//                }
//
//
//                return true;
//
//            }
//
//        });


//        notify_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Animation animationUtils = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate_clock);
//                rotate.startAnimation(animationUtils);
//                badge.setNumber(++mCount);
//
//            }
//        });
//
//
//        rotate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1 = new Intent(getActivity(), Notification.class);
//                startActivity(intent1);
//                rotate.clearAnimation();
//                mCount = 0;
//
//                badge.setNumber(mCount);
//
//
//            }
//        });


        return root;
    }


}



