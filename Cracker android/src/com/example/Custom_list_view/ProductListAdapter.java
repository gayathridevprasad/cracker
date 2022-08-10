package com.example.Custom_list_view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.lion.swipelistview.BaseSwipeAdapter;
import com.lion.swipelistview.SwipeListView;


import java.util.List;

/**
 * Created by akil on 05-05-2016.
 */
public class ProductListAdapter extends BaseSwipeAdapter {
    private Context mcontext;
    List<Product> mproductlist;

    public ProductListAdapter(Context context, SwipeListView swipeListView) {
        super(context, swipeListView);
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

        View v=View.inflate(mcontext,R.layout.item_product_list,null);
        View v1=View.inflate(mcontext,R.layout.rightlayout,null);
        TextView t1=(TextView)v.findViewById(R.id.textView);
        TextView t2=(TextView)v.findViewById(R.id.textView2);
        TextView t3=(TextView)v.findViewById(R.id.textView3);
        TextView t8=(TextView)v.findViewById(R.id.textView8);
        TextView t9=(TextView)v.findViewById(R.id.textView9);
        TextView t10=(TextView)v.findViewById(R.id.textView10);

        ImageView mDelete = (ImageView) v1.findViewById(R.id.delete);
        ImageView mFileInfo = (ImageView)v1.findViewById(R.id.infoButton);


        return v;
    }
}
