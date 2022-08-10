package com.example.Custom_list_view;

/**
 * Created by akil on 27-06-2016.
 */
public class Stock_item {
    String item_num;
    String item_name;
    String rate;
    String stock;

    public Stock_item(String item_num, String item_name, String rate, String stock) {
        this.item_num = item_num;
        this.item_name = item_name;
        this.rate = rate;
        this.stock = stock;

    }


    public String getItem_num() {
        return item_num;
    }

    public void setItem_num(String item_num) {
        this.item_num = item_num;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}
