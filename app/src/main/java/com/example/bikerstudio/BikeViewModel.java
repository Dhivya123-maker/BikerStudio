package com.example.bikerstudio;

public class BikeViewModel {

 String bike_num;
 String bike_model;
 String year;
 String bike_name;
// String ins_no;
// String ins_f_date;
// String ins_to_date;
 String kms;
 String version;
 String id;
 String vname;

    public String getVname() {
        return vname;
    }

    public void setVname(String vname) {
        this.vname = vname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BikeViewModel(String bike_num,String vname, String id, String bike_model, String year, String bike_name, String kms, String version) {
        this.bike_num = bike_num;
        this.bike_model = bike_model;
        this.year = year;
        this.bike_name = bike_name;
//        this.ins_no = ins_no;
//        this.ins_f_date = ins_f_date;
//        this.ins_to_date = ins_to_date;
        this.kms = kms;
        this.version = version;
        this.id = id;
        this.vname = vname;


    }
    public String getBike_num() {
        return bike_num;
    }

    public String setBike_num(String bike_num) {
        this.bike_num = bike_num;
        return bike_num;
    }

    public String getBike_model() {
        return bike_model;
    }

    public String setBike_model(String bike_model) {
        this.bike_model = bike_model;
        return bike_model;
    }

    public String getYear() {
        return year;
    }

    public String setYear(String year) {
        this.year = year;
        return year;
    }

    public String getBike_name() {
        return bike_name;
    }

    public String setBike_name(String bike_name) {
        this.bike_name = bike_name;
        return bike_name;

    }

//    public String getIns_no() {
//        return ins_no;
//    }
//
//    public String setIns_no(String ins_no) {
//        this.ins_no = ins_no;
//        return ins_no;
//    }
//
//    public String getIns_f_date() {
//        return ins_f_date;
//    }
//
//    public String setIns_f_date(String ins_f_date) {
//        this.ins_f_date = ins_f_date;
//        return ins_f_date;
//    }
//
//    public String getIns_to_date() {
//        return ins_to_date;
//    }
//
//    public String setIns_to_date(String ins_to_date) {
//        this.ins_to_date = ins_to_date;
//        return ins_to_date;
//    }

    public String getKms() {
        return kms;
    }

    public String setKms(String kms) {
        this.kms = kms;
        return kms;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public  BikeViewModel(){

    }


}
