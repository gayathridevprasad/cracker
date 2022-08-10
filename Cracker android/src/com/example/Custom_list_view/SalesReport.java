package com.example.Custom_list_view;

/**
 * Created by akil on 27-06-2016.
 */
public class SalesReport {
    int sno;
    String billno,partyname,date;
    String netamount;

    public SalesReport(int sno,String billno,String partyname, String date, String netamount  ) {
        this.billno = billno;
        this.date = date;
        this.netamount = netamount;
        this.partyname = partyname;
        this.sno = sno;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNetamount() {
        return netamount;
    }

    public void setNetamount(String netamount) {
        this.netamount = netamount;
    }

    public String getPartyname() {
        return partyname;
    }

    public void setPartyname(String partyname) {
        this.partyname = partyname;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }
}
