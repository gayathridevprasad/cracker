package com.example.Custom_list_view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by akil on 27-06-2016.
 */
public class Stock extends Activity {

    private  Stock_adapter adaptor;
    List<Stock_item> mproductlist;
    Stock_item salesReport;
    InputStream is=null;
    String result=null,monthandyear;
    ListView l1;
    String line=null;
    ArrayList result1,result2,result3,result4;
    EditText inputview_sales,stock_search;
    ImageButton et1;
    TextView day;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.stock);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        et1=(ImageButton)findViewById(R.id.et1);
       stock_search=(EditText)findViewById(R.id.stock_search);
        l1=(ListView)findViewById(R.id.listView3);
        mproductlist =new ArrayList<Stock_item>();
        result1=new ArrayList();
        result2=new ArrayList();
        result3=new ArrayList();
        result4=new ArrayList();







        select();

        stock_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                adaptor.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }




    public void select()
    {



        SharedPreferences sh1=getSharedPreferences("setting", Context.MODE_PRIVATE);
        // String address=sh1.getString("address",null);
        String ip=sh1.getString("ip",null);
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("id","mon"));

        try
        {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://"+ip+"/cracker/select_item.php");
            // HttpPost httppost = new HttpPost("http://10.0.2.2:81/cracker/select_sales.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 1", e.toString());
            Toast.makeText(getApplicationContext(), "Invalid IP Address",
                    Toast.LENGTH_LONG).show();
        }

        try
        {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            JSONObject json_data = new JSONObject(result);
            JSONArray jsonMainNode = json_data.optJSONArray("name1");

            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String item_num = jsonChildNode.optString("code");
                String item_name = jsonChildNode.optString("name");
                String rate =jsonChildNode.optString("rate");
                String stock=jsonChildNode.optString("stock");
                System.out.println(item_num);
                System.out.println(rate);System.out.println(stock);

                //String outPut = name + "-" + number;
                result1.add(item_num);
                result2.add(item_name);
                result3.add(rate);
                result4.add(stock);

            }
            final String[] itemnum_array = new String[result1.size()];
            final String[] itemname_array = new String[result1.size()];
            final String[] rate_array = new String[result1.size()];
            final String[] stock_array = new String[result1.size()];

            for (int i = 0; i < result1.size(); i++) {
                itemnum_array[i] = result1.get(i).toString();
                itemname_array[i] = result2.get(i).toString();
                rate_array[i] = result3.get(i).toString();
                stock_array[i] = result4.get(i).toString();

                mproductlist.add(new Stock_item(itemnum_array[i],itemname_array[i],rate_array[i],stock_array[i]));

                //mproductlist.add(new Product("sd2",10,"sdfdsfdsf",3,"box",5000));



                System.out.println("hi");
                adaptor=new Stock_adapter(getApplicationContext(),mproductlist);
                l1.setAdapter(adaptor);


            }





            // ArrayAdapter ado=new ArrayAdapter(getActivity().getApplicationContext(),android.R.layout.simple_list_item_1,result1);
            /*et.setAdapter(ado);



            //et.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String val=et.getText().toString();
                    for(int j=0;j<pname_array.length;j++)
                    {

                        if(pname_array[j].equals(val))
                        {
                            //  Toast.makeText(getBaseContext(), "Name : "+array2[j] +":" +val,Toast.LENGTH_SHORT).show();
                            et2.setText(code_array[j]);
                            e2.setText(prate_array[j]);
                            e3.setText(pper_array[j]);
                        }
                    }
                }
            });*/
            // name=json_data.getString("name").toString();
            // id=(json_data.getString("id"));

        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }

}