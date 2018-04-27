package com.example.tj.cafeteriaadmin;

/**
 * Created by tj on 16/4/18.
 */

public class Enteries {

    private String mName;
    private String mDEsc;
    private String mprice;
    private String mtype;
    private String mimageUrl;

    public Enteries(){

    }

    public Enteries(String mName, String mDEsc, String mprice,String mtype,String mimageUrl) {
        this.mName = mName;
        this.mDEsc = mDEsc;
        this.mprice = mprice;
        this.mtype = mtype;
        this.mimageUrl= mimageUrl;
    }

    public Enteries(String mName, String mDEsc, String mprice, String mimageUrl) {
        this.mName = mName;
        this.mDEsc = mDEsc;
        this.mprice = mprice;
        this.mimageUrl = mimageUrl;
    }

    public String getmName() {
        return mName;
    }

    public String getmDEsc() {
        return mDEsc;
    }

    public String getMprice() {
        return mprice;
    }

    public String getMtype() {
        return mtype;
    }

    public String getMimageUrl() {
        return mimageUrl;
    }
}
