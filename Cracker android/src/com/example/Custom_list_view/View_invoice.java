package com.example.Custom_list_view;

import android.app.Activity;
import android.content.Intent;
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
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by akil on 09-07-2016.
 */
public class View_invoice extends Activity {
    private ListView lv,lv1;

    // Listview Adapter
    ArrayAdapter<String> adapter,adapter1;

    // Search EditText
    EditText inputSearch,inputsearch1;

    String id,billnum;
    private static final int PRODUCT_EDIT = 1;
    ListView l1;
    String name;
    InputStream is=null;
    String result=null;
    String line=null;
    ArrayList result1,result2,result3,result4,result5,result6;
    ImageButton add;
    SharedPreferences sh1;
    EditText e1,e2,e3;
    String ip;
    int i,j;
    String invoice;
    String[] qty_array;
    String[] pname_array;
    String[] pprice_array;
    String[] pper_array;
    String[] pamount_array;

    ImageButton print;


    // ArrayList for Listview
    ArrayList<HashMap<String, String>> productList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.view_invoice);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        result1=new ArrayList();
        result2=new ArrayList();
        result3=new ArrayList();


        // Listview Data


        lv = (ListView) findViewById(R.id.viewinvoice_invoice);
        lv1 = (ListView) findViewById(R.id.viewinvoice_profoma);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        inputsearch1 = (EditText) findViewById(R.id.inputsearch1);

        // Adding items to listview
        select();
        int count=0;
        int count1=0;
        for(int j=0;j<pname_array.length;j++) {
            if ((pname_array[j].charAt(0) == 'p') || (pname_array[j].charAt(0) == 'P')) {
                result2.add(pname_array[j]);
            }
            else if((pname_array[j].charAt(0) == 'i') || (pname_array[j].charAt(0) == 'I'))
            {
                result3.add(pname_array[j]);
            }
        }

        adapter = new ArrayAdapter<String>(this, R.layout.viewinvoice_list_item, R.id.product_name, result2);
        lv.setAdapter(adapter);
        adapter1 = new ArrayAdapter<String>(this, R.layout.viewinvoice_list_item, R.id.product_name, result3);
        lv1.setAdapter(adapter1);

        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                View_invoice.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }


        });

        inputsearch1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                View_invoice.this.adapter1.getFilter().filter(cs);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
                                          int arg3) {
                // TODO Auto-generated method stub

            }


        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String bill=lv.getItemAtPosition(i).toString();

                Toast.makeText(getApplicationContext(),bill,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), Pdftoimage_display.class);
                //intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME,                "storage/sdcard/Code Analyser/"+billnum+".pdf");
                intent.putExtra("billnum",bill);
                startActivity(intent);

            }
        });

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String bill=lv1.getItemAtPosition(i).toString();

                Toast.makeText(getApplicationContext(),bill,Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), Pdftoimage_display.class);
                //intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME,                "storage/sdcard/Code Analyser/"+billnum+".pdf");
                intent.putExtra("billnum",bill);
                startActivity(intent);

            }
        });

    }


    public void select()
    {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("id","hi"));
        //nameValuePairs.add(new BasicNameValuePair("radiochoice", radiochoice));

        SharedPreferences sh=getSharedPreferences("setting",MODE_PRIVATE);
        String ip=sh.getString("ip",null);

        System.out.println("Ip Address"+ip);

        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            // HttpPost httppost = new HttpPost("http://172.16.4.41:81/cracker/select_invoice.php");
            HttpPost httppost = new HttpPost("http://"+ip+"/cracker/select_invoice_view.php");
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("pass 1", "connection success_Select ");
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
            Log.e("pass 2", "connection success_Select ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {
            JSONObject json_data = new JSONObject(result);
            JSONArray jsonMainNode = json_data.optJSONArray("name1");

            for (i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                invoice = jsonChildNode.optString("invoice");
                System.out.println(invoice);


                // String outPut = name + "-" + number;
                result1.add(invoice);

            }
            pname_array= new String[result1.size()];
          //  qty_array= new String[result1.size()];
            //pamount_array=new String[result1.size()];


            for (int i = 0; i < result1.size(); i++) {
                pname_array[i] = result1.get(i).toString();



            }

            // name=json_data.getString("name").toString();
            // id=(json_data.getString("id"));

        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }


    }

}