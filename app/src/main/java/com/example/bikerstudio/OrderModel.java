package com.example.bikerstudio;

public class OrderModel {

    String order_id;
    String w_order;
    String d_date;
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public  OrderModel(){}

    public OrderModel(String order_id, String w_order, String d_date,String status) {
        this.order_id = order_id;
        this.w_order = w_order;
        this.d_date = d_date;
        this.status = status;
    }




    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getW_order() {
        return w_order;
    }

    public void setW_order(String w_order) {
        this.w_order = w_order;
    }

    public String getD_date() {
        return d_date;
    }

    public void setD_date(String d_date) {
        this.d_date = d_date;
    }
}
