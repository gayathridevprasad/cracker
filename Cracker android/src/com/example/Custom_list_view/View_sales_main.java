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
public class View_sales_main extends Activity {

    private  Salesreport_adaptor adaptor;
    List<SalesReport> mproductlist;
    SalesReport salesReport;
    InputStream is=null;
    String result=null,monthandyear;
    ListView l1;
    String line=null;
    ArrayList result1,result2,result3,result4;
    EditText inputview_sales;
    ImageButton et1;
    TextView day;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.view_sales_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        et1=(ImageButton)findViewById(R.id.et1);
        l1=(ListView)findViewById(R.id.listView);
        mproductlist =new ArrayList<SalesReport>();
        result1=new ArrayList();
        result2=new ArrayList();
        result3=new ArrayList();
        result4=new ArrayList();

        day=(TextView)findViewById(R.id.month_yaer);

        et1.setOnClickListener(new View.OnClickListener() {
            // @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();

                int y = c.get(Calendar.YEAR)+4;
                int m = c.get(Calendar.MONTH)-2;
                int d = c.get(Calendar.DAY_OF_MONTH);
                final String[] MONTH = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

                DatePickerDialog dp = new DatePickerDialog(View_sales_main.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                String erg = "";
                                erg = String.valueOf(dayOfMonth);
                                erg += "." + String.valueOf(monthOfYear + 1);
                                erg += "." + year;

                                ((TextView) day).setText(erg);

                            }

                        }, y, m, d);
                dp.setTitle("Calender");
                dp.setMessage("Select Your Graduation date Please?");

                dp.show();



            }
        });


       /* cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                Toast.makeText(getApplicationContext(),i+ "/"+i1+"/"+i2,Toast.LENGTH_LONG).show();


                String month=formatMonth(1+i1 + "");
                monthandyear=month+"_"+i;
                day.setText(monthandyear);
            }
        });*/



        select("all");


        day.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mproductlist.clear();
                mproductlist.indexOf(0);
                adaptor.notifyDataSetChanged();
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                select(monthandyear);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    public String formatMonth(String month) {
        String mo = null;
        SimpleDateFormat monthParse = new SimpleDateFormat("MM");
        SimpleDateFormat monthDisplay = new SimpleDateFormat("MMMM");
        try {
            mo=monthDisplay.format(monthParse.parse(month));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mo;
    }

    public void select(String mon)
    {



        SharedPreferences sh1=getSharedPreferences("setting", Context.MODE_PRIVATE);
        // String address=sh1.getString("address",null);
        String ip=sh1.getString("ip",null);
        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("id",mon));

        try
        {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://"+ip+"/cracker/select_sales.php");
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
                String invoice = jsonChildNode.optString("invoice");
                String parname = jsonChildNode.optString("partyname");
                String dat=jsonChildNode.optString("date");
                String netamount=jsonChildNode.optString("netamount");
                System.out.println(invoice);
                //String outPut = name + "-" + number;
                result1.add(invoice);
                result2.add(parname);
                result3.add(dat);
                result4.add(netamount);

            }
            final String[] invoice_array = new String[result1.size()];
            final String[] parname_array = new String[result1.size()];
            final String[] dat_array = new String[result1.size()];
            final String[] netamount_array = new String[result1.size()];

            for (int i = 0; i < result1.size(); i++) {
                invoice_array[i] = result1.get(i).toString();
                parname_array[i] = result2.get(i).toString();
                dat_array[i] = result3.get(i).toString();
                netamount_array[i] = result4.get(i).toString();

                mproductlist.add(new SalesReport(i,invoice_array[i],parname_array[i],dat_array[i],netamount_array[i]));

                //mproductlist.add(new Product("sd2",10,"sdfdsfdsf",3,"box",5000));




                adaptor=new Salesreport_adaptor(getApplicationContext(),mproductlist);
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