package com.example.bikerstudio;

public class BookingModel {
    String id;
    String v_type;
    String name;
    String price;
    String dis;


    public BookingModel(String id,  String v_type,String name, String price, String dis) {
        this.id = id;
        this.v_type = v_type;
        this.name = name;

        this.price = price;
        this.dis = dis;
    }

    public BookingModel(){

    }

    public String getId() {
        return id;
    }

    public String setId(String id) {
        this.id = id;
        return id;
    }


    public String getV_type() {
        return v_type;
    }

    public String setV_type(String v_type) {
        this.v_type = v_type;
        return v_type;
    }
    public String getName() {
        return name;
    }
    public String setName(String name) {
        this.name = name;
        return name;
    }



    public String getPrice() {
        return price;
    }

    public String setPrice(String price) {
        this.price = price;
        return price;
    }

    public String getDis() {
        return dis;
    }

    public String setDis(String dis) {
        this.dis = dis;
        return dis;
    }


}
