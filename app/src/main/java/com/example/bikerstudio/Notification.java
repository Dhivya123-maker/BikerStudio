package com.example.bikerstudio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity {

//    public static final Object FLAG_AUTO_CANCEL =1 ;
//    public static final int PRIORITY_MAX =0 ;

    List<NotificationModel> notificationModelList;
    RecyclerView recyclerView;
    String service_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Intent intent = getIntent();
        service_name =intent.getStringExtra("service_name");

     notificationModelList = new ArrayList<>();
        recyclerView = findViewById(R.id.notify_recycle);

        NotificationModel notification = new NotificationModel();

        notification.setBike_name(service_name
        );


        notificationModelList.add(notification);

        NotificationAdapter adapter1 = new NotificationAdapter(getApplicationContext(), notificationModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(adapter1);




//        String msg = getIntent().getStringExtra("message");
//        notification.setText(msg);
    }
}