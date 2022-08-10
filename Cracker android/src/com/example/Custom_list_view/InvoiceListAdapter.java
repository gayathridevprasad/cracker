package com.example.Custom_list_view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akil on 05-05-2016.
 */
public class InvoiceListAdapter extends BaseAdapter {
    private Context mcontext;
    ArrayList<Product> mproductlist;

    public InvoiceListAdapter(Context mcontext, ArrayList<Product> mproductlist) {
        this.mcontext = mcontext;
        this.mproductlist = mproductlist;
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
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v=View.inflate(mcontext,R.layout.invoice_list,null);
        TextView t1=(TextView)v.findViewById(R.id.textView34);
        ImageView tax=(ImageView)v.findViewById(R.id.imageView);
        TextView t3=(TextView)v.findViewById(R.id.textView36);
        TextView t8=(TextView)v.findViewById(R.id.textView37);
        TextView t9=(TextView)v.findViewById(R.id.textView38);

        t1.setText(mproductlist.get(i).prname1);
        t8.setText(String.valueOf(mproductlist.get(i).prrate1));
       // t3.setText(String.valueOf(mproductlist.get(i).getPer()));
       // t8.setText(mproductlist.get(i).getCode());
        t3.setText(String.valueOf(mproductlist.get(i).pquan1));
        t9.setText(String.valueOf(mproductlist.get(i).pamount1));
        return v;
    }
}
