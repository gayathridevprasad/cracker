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
public class Salesreport_adaptor extends BaseAdapter {
    private Context mcontext;
    List<SalesReport> mproductlist;

    public Salesreport_adaptor(Context mcontext, List<SalesReport> mproductlist) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final View v=View.inflate(mcontext,R.layout.sales_list_items,null);
        //TextView bill_number_sales=(TextView)v.findViewById(R.id.bill_number_sales);

        TextView partyname_sales=(TextView)v.findViewById(R.id.partyname_sales);
        TextView invoicenum_sales=(TextView)v.findViewById(R.id.invoice_num_sales);
        TextView date_sales=(TextView)v.findViewById(R.id.date_sales);
        TextView netamount_sales=(TextView)v.findViewById(R.id.netamount_sales);

        ImageButton ib=(ImageButton)v.findViewById(R.id.sales_image);




       /// bill_number_sales.setText(String.valueOf(mproductlist.get(i).sno));
        partyname_sales.setText(mproductlist.get(i).partyname);
        // t3.setText(String.valueOf(mproductlist.get(i).getPer()));
        // t8.setText(mproductlist.get(i).getCode());
        invoicenum_sales.setText(String.valueOf(mproductlist.get(i).billno));
        date_sales.setText(String.valueOf(mproductlist.get(i).date));
        netamount_sales.setText(String.valueOf(mproductlist.get(i).netamount));


        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(mcontext,mproductlist.get(i).billno,Toast.LENGTH_LONG).show();
                Intent in=new Intent(mcontext,Pdftoimage_display.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("billnum",mproductlist.get(i).billno);
                mcontext.startActivity(in);


            }
        });





        return v;
    }
}
