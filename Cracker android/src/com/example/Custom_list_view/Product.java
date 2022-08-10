package com.example.Custom_list_view;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by akil on 05-05-2016.
 */
public class Product implements Serializable {

    String pcode1;
    int pquan1;
    String prname1;
    double prrate1;
    String prper1;
    int pamount1;
    public Product(String pcode1, int pquan1, String prname1, double prrate1, String prper1, int pamount1) {
        this.pcode1=pcode1;
        this.pquan1=pquan1;
        this.prname1=prname1;
        this.prrate1=prrate1;
        this.prper1=prper1;
        this.pamount1=pamount1;
    }





}
