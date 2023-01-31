package com.example.bikerstudio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONException;
import org.json.JSONObject;

public class Emergency extends AppCompatActivity {
    FusedLocationProviderClient mFusedLocationClient;

    // Initializing other items
    // from layout file
    TextView latitudeTextView, longitTextView;
    private static final int REQUEST_CALL = 1 ;

    protected static final int REQUEST_CHECK_SETTINGS = 0x2;
    private GoogleApiClient googleApiClient;


    EditText phone_no,address,desc;
    String punture = null,breakdown = null,fuel = null,person_id;
    Spinner vehicle_type;
    int PERMISSION_ID = 44;
    Button btn,emergency_btn;
    String MobilePattern = "[0-9]{10}";
    Status status;
    String location;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency2);


        googleApiClient = getAPIClientInstance();
        if (googleApiClient != null) {
            googleApiClient.connect();
            requestGPSSettings();
        }





        latitudeTextView = findViewById(R.id.lat);
        longitTextView = findViewById(R.id.log);
        phone_no = findViewById(R.id.num);
        address = findViewById(R.id.address);
        desc= findViewById(R.id.desc);
        emergency_btn = findViewById(R.id.emergency_btn);



        btn=findViewById(R.id.order_button);


        SharedPreferences settings = getSharedPreferences("YOUR_PREF_NAM", 0);
        person_id = settings.getString("id", "");

        Intent intent = getIntent();
        punture = intent.getStringExtra("punture");
        breakdown = intent.getStringExtra("breakdown");
        fuel = intent.getStringExtra("fuel");





//        googleApiClient = getAPIClientInstance();
//        if (googleApiClient != null) {
//            googleApiClient.connect();
//            requestGPSSettings();
//        }
//    }


        emergency_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makePhoneCall();
            }
        });







        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String Phone_no =phone_no.getText().toString().trim();
                String Address =address.getText().toString().trim();
                String Desc =desc.getText().toString().trim();






            //    requestGPSSettings();

                String lat,log;
                lat = latitudeTextView.getText().toString();
                log = longitTextView.getText().toString();

                if (Phone_no.isEmpty()) {
                    phone_no.setError("Please enter your valid number");
                    phone_no.requestFocus();
                } else if (Address.isEmpty()) {
                    address.setError("Please enter your address");
                    address.requestFocus();
                } else if (Desc.isEmpty()) {
                    desc.setError("Please enter your description");
                    desc.requestFocus();
                }else if (isLocationEnabled()){
                    RequestQueue requestQueue = Volley.newRequestQueue(Emergency.this);
                    String URL = "http://ns1.nodeserver.co.in:8001/v1/api/emergency";
                    JSONObject jsonBody = new JSONObject();
                    try {
                        jsonBody.put("phone", phone_no.getText().toString().trim());
                        jsonBody.put("address", address.getText().toString().trim());
                        jsonBody.put("des", desc.getText().toString().trim());
                        jsonBody.put("lat", lat);
                        jsonBody.put("lan", log);
                        jsonBody.put("person_id", person_id);


                        if (punture != null) {
                            jsonBody.put("type", punture);

                        } else if (breakdown != null) {
                            jsonBody.put("type", breakdown);

                        } else if (fuel != null) {
                            jsonBody.put("type", fuel);

                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL, jsonBody, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            String success;
                            try {
                                success = response.getString("success");



                                 if (success.equals("true")) {

                                    Intent intent = new Intent(Emergency.this, home.class);
                                     intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                                    startActivity(intent);
                                    Toast.makeText(Emergency.this, "Your problem is taken", Toast.LENGTH_SHORT).show();
                                     Log.i("Lrgfergdhgd", String.valueOf(status.getStatusCode()));
                                }


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
                }else Toast.makeText(Emergency.this, "Please turn on your location", Toast.LENGTH_SHORT).show();

                //
//
//                Toast.makeText(Emergency.this, person_id, Toast.LENGTH_SHORT).show();
//                Toast.makeText(Emergency.this, address.getText().toString().trim(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(Emergency.this, punture, Toast.LENGTH_SHORT).show();
//                Toast.makeText(Emergency.this, breakdown, Toast.LENGTH_SHORT).show();
//                Toast.makeText(Emergency.this, fuel, Toast.LENGTH_SHORT).show();
//                Toast.makeText(Emergency.this,  desc.getText().toString().trim(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(Emergency.this, latitudeTextView.getText().toString(), Toast.LENGTH_SHORT).show();
//                Toast.makeText(Emergency.this, longitTextView.getText().toString(), Toast.LENGTH_SHORT).show();
//
//                Log.i("11111111",phone_no.getText().toString().trim());
//                Log.i("111111121",address.getText().toString().trim());
//                Log.i("111111131",desc.getText().toString().trim());
//  //              Log.i("111111141",fuel);
//                Log.i("111111151",lat);
//                Log.i("111111161",log);
//                Log.i("111111171",person_id);
//                Log.i("111111181",punture);
//           //     Log.i("111111191",breakdown);




            }
        });


//
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        // method to get the location
        getLastLocation();



    }

    @SuppressLint("MissingPermission")
    private void getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {

                // getting last
                // location from
                // FusedLocationClient
                // object
                mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        Location location = task.getResult();
                        if (location == null) {
                            requestNewLocationData();
                        } else {
                            latitudeTextView.setText(location.getLatitude() + "");
                            longitTextView.setText(location.getLongitude() + "");
                        }
                    }
                });
            } else {
              //Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(intent);
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions();
        }
    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            latitudeTextView.setText(mLastLocation.getLatitude()+"");
            longitTextView.setText(mLastLocation.getLongitude()+"");
        }
    };

    // method to check for permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        // If we want background location
        // on Android 10.0 and higher,
        // use:
        // ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    // method to check
    // if location is enabled
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // If everything is alright then
    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }else if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_DENIED){
                Intent intent = new Intent(Emergency.this,home.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);



                Toast.makeText(Emergency.this, "Please turn on your location", Toast.LENGTH_LONG).show();
            }
        }

        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission Denied to make a call" +
                        "", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (checkPermissions()) {
            getLastLocation();
        }
    }

    private void makePhoneCall() {
        String number = "7708814444";
        if (number.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(Emergency.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Emergency.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            }
            else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }
    }




    private GoogleApiClient getAPIClientInstance() {
        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API).build();
        return mGoogleApiClient;
    }

    private void requestGPSSettings() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        locationRequest.setInterval(2000);
        locationRequest.setFastestInterval(500);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);
        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                status = result.getStatus();
               // Toast.makeText(Emergency.this,status.getStatusCode() , Toast.LENGTH_SHORT).show();
                Log.i("srgfergdhgd", String.valueOf(status.getStatusCode()));
                location = String.valueOf(status.getStatusCode());
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
//                        Log.i("", "All location settings are satisfied.");
                     //   Toast.makeText(getApplication(), "GPS is already enable", Toast.LENGTH_SHORT).show();
                        break;

                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:

                        try {

                            status.startResolutionForResult(Emergency.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.e("Applicationsett", e.toString());

                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i("", "Location settings are inadequate, and cannot be fixed here. Dialog " + "not created.");
                        Toast.makeText(getApplication(), "Location settings are inadequate, and cannot be fixed here", Toast.LENGTH_SHORT).show();
                        break;


                }
            }
        });
    }






}