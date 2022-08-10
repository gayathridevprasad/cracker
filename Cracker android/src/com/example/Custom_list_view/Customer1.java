package com.example.Custom_list_view;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by akil on 16-07-2016.
 */
public class Customer1 extends Activity {

    String name;
    String id;
    InputStream is = null;
    String result = null;
    String line = null;
    int code;
    SharedPreferences sh1;
    String ip;
    EditText cname,cadd1,cadd2,cadd3,ccity,ccode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.customer);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        sh1=getSharedPreferences("setting",MODE_PRIVATE);
        // String address=sh1.getString("address",null);
        ip=sh1.getString("ip",null);
        cname=(EditText)findViewById(R.id.custname);
        cadd1=(EditText)findViewById(R.id.cust_address1);
        cadd2=(EditText)findViewById(R.id.cust_address2);
        cadd3=(EditText)findViewById(R.id.cust_address3);
        ccity=(EditText)findViewById(R.id.ccity);
        ccode=(EditText)findViewById(R.id.cpostal);

        Toast.makeText(this,ip,Toast.LENGTH_LONG).show();



    }

    public void csave(View v)
    {
        insert();
    }


    public void insert() {
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        String add=cadd1.getText().toString()+" "+cadd1.getText().toString()+" "+cadd3.getText().toString();
        Toast.makeText(this,add,Toast.LENGTH_LONG).show();

        nameValuePairs.add(new BasicNameValuePair("cname", cname.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("caddress", cadd1.getText().toString()+" "+cadd1.getText().toString()+" "+cadd3.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("ccity", ccity.getText().toString()));
        nameValuePairs.add(new BasicNameValuePair("ccode", ccode.getText().toString()));
        try {
            HttpClient httpclient = new DefaultHttpClient();
            //HttpPost httppost = new HttpPost("http://172.16.4.41:81/cracker/insert.php");
            HttpPost httppost = new HttpPost("http://"+ip+"/cracker/insert_cust.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        } catch (Exception e) {
            Log.e("Fail 1", e.toString());
            Toast.makeText(getApplicationContext(), "Invalid IP Address",
                    Toast.LENGTH_LONG).show();
        }

        try {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        } catch (Exception e) {
            Log.e("Fail 2", e.toString());
        }

        try {
            JSONObject json_data = new JSONObject(result);
            code = (json_data.getInt("code"));

            if (code == 1) {
                Toast.makeText(getBaseContext(), "Inserted Successfully",
                        Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getBaseContext(), "Sorry, Try Again",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Log.e("Fail 3", e.toString());
        }


        this.finish();
    }
}