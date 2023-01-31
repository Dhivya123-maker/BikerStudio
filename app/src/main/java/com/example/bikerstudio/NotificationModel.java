package com.example.bikerstudio;

public class NotificationModel {

    String bike_name;

    public NotificationModel(String bike_name) {
        this.bike_name = bike_name;
    }

    public NotificationModel() {

    }

    public String getBike_name() {
        return bike_name;
    }

    public void setBike_name(String bike_name) {
        this.bike_name = bike_name;
    }


}
