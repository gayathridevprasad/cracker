package com.example.Custom_list_view;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akil on 05-05-2016.
 */
public class Stock_adapter extends BaseAdapter {
    private Context mcontext;
    List<Stock_item> mproductlist;
    List<Stock_item> mproductlistoriginal;
    ValueFilter valueFilter;


    public Stock_adapter(Context mcontext, List<Stock_item> mproductlist) {
        this.mcontext = mcontext;
        this.mproductlist = mproductlist;
        mproductlistoriginal=mproductlist;
    }

    @Override
    public int getCount() {
        return mproductlist.size();
    }

    @Override
    public Object getItem(int i) {
        return mproductlist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final View v = View.inflate(mcontext, R.layout.stock_list_item, null);
        //TextView bill_number_sales=(TextView)v.findViewById(R.id.bill_number_sales);

        System.out.println("inside");


        TextView itemnum = (TextView) v.findViewById(R.id.stock_item_id);
        TextView itemname = (TextView) v.findViewById(R.id.stock_item_name);
        TextView rate = (TextView) v.findViewById(R.id.stock_item_rate);
        TextView stock = (TextView) v.findViewById(R.id.stock_item_num);


        itemnum.setText(String.valueOf(mproductlist.get(i).item_num));
        itemname.setText(String.valueOf(mproductlist.get(i).item_name));
        rate.setText(String.valueOf(mproductlist.get(i).rate));
        stock.setText(String.valueOf(mproductlist.get(i).stock));


        return v;
    }



    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }


    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<Stock_item> filterList = new ArrayList<Stock_item>();
                for (int i = 0; i <  mproductlistoriginal.size(); i++) {
                    if (( mproductlistoriginal.get(i).getItem_num().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {

                        Stock_item country = new Stock_item( mproductlistoriginal.get(i).getItem_num(), mproductlistoriginal.get(i).getItem_name(), mproductlistoriginal.get(i).getRate(), mproductlistoriginal.get(i).getStock());


                        filterList.add(country);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mproductlistoriginal.size();
                results.values = mproductlistoriginal;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mproductlist=(ArrayList<Stock_item>)filterResults.values;
                notifyDataSetChanged();
        }
    }
}