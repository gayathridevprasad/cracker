package com.example.Custom_list_view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

/**
 * Created by akil on 16-05-2016.
 */
public class HomeActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.home);
        showOverLay();
    }


    public void newinvoice(View v)
    {
        Intent in=new Intent(this,MyActivity.class);
        startActivity(in);
    }

    public void salesreport(View v)
    {
        Intent in=new Intent(this,View_sales_main.class);
        startActivity(in);
    }


    public void viewinvoice(View v)
    {
        Intent in=new Intent(this,View_invoice.class);
        startActivity(in);
    }

    public void setting(View v)
    {
        Intent in=new Intent(this,Setting.class);
        startActivity(in);
    }

    public void stock(View v)
    {
        Intent in=new Intent(this,Stock.class);
        startActivity(in);
    }
    public void customer(View v)
    {
        Intent in=new Intent(this,Customer1.class);
        startActivity(in);
    }


    private void showOverLay(){

        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);

        dialog.setContentView(R.layout.overlay_view);

        LinearLayout layout = (LinearLayout) dialog.findViewById(R.id.overlayLayout);

        layout.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View arg0) {

                dialog.dismiss();

            }

        });

        dialog.show();

    }
}