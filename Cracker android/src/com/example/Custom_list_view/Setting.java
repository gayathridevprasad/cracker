package com.example.Custom_list_view;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by akil on 13-07-2016.
 */
public class Setting extends Activity {
    EditText e1,e2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.setting);
        SharedPreferences sh1=getSharedPreferences("setting",MODE_PRIVATE);
        String address=sh1.getString("address",null);
        String ip=sh1.getString("ip",null);




        e1=(EditText)findViewById(R.id.setting_address);
        e2=(EditText)findViewById(R.id.setting_ip);
        e1.setText(address);
        e2.setText(ip);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);//Menu Resource, Menu
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                SharedPreferences sh=getSharedPreferences("setting",MODE_PRIVATE);
                SharedPreferences.Editor edi=sh.edit();
                edi.putString("address",e1.getText().toString());
                edi.putString("ip",e2.getText().toString());




                edi.commit();


                return true;
            case R.id.item2:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}