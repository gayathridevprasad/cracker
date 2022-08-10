package com.example.Custom_list_view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.lion.swipelistview.BaseSwipeAdapter;
import com.lion.swipelistview.SwipeListView;
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
import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private SwipeListView mListView;
    private ArrayList<Product> mList = new ArrayList<Product>();

    private ListViewAdapter mAdapter;

    float historicX = Float.NaN, historicY = Float.NaN;
    static final int DELTA = 50;

    enum Direction {LEFT, RIGHT;}

    TextView titem,tamount;
    String   prname;
    String pcode;
    int pquan,available_stock;
    //int pquan=new Integer(e1.getText().toString()).intValue();
    double prrate;
    String prper;

    int pamount;

    int tot_amount=0;
    String id;
    private static final int PRODUCT_EDIT = 1;
    ListView l1;
    String name;
    InputStream is=null;
    String result=null;
    String line=null;
    ArrayList result1,result2,result3,result4,result5;
    ImageButton add;
    private AutoCompleteTextView et,et2;
    EditText e1,e2,e3,e4,mainsearch;
    RadioGroup rg;
    RadioButton radioselect;
    int i;
    //private  ProductListAdapter adaptor;
   // List<Product> mproductlist;
    Product product;
    Switch mySwitch;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        rg=(RadioGroup)findViewById(R.id.rg);
        radioselect=(RadioButton)findViewById(R.id.profoma_check);
        Toast.makeText(getApplicationContext(),radioselect.getText(),Toast.LENGTH_SHORT).show();
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                radioselect=(RadioButton)findViewById(i);

                Toast.makeText(getApplicationContext(),radioselect.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        mySwitch=(Switch)findViewById(R.id.switch1);




      //  mainsearch=(EditText)findViewById(R.id.mainsearch);
        et=(AutoCompleteTextView)findViewById(R.id.editText3);
        et2=(AutoCompleteTextView)findViewById(R.id.editText);
        e1=(EditText)findViewById(R.id.editText2);
        e4=(EditText)findViewById(R.id.main_stock_box);
        et2.requestFocus();



        e1.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                     pcode=et2.getText().toString();
                    prname=et.getText().toString();
                    pquan=Integer.parseInt(e1.getText().toString());
                    available_stock=Integer.parseInt(e4.getText().toString());
                    //int pquan=new Integer(e1.getText().toString()).intValue();
                     prrate=Double.parseDouble(e2.getText().toString());
                    prper=e3.getText().toString();

                     pamount=(int)(pquan*prrate);
                    if(mySwitch.isChecked())
                    {
                        if(pquan<=available_stock )
                        {
                            initData(pcode,pquan,prname,prrate,prper,pamount);
                            initView();
                            et2.requestFocus();
                            titem.setText(mList.size()+"");
                            tot_amount=0;
                            for(int i=0;i<mList.size();i++)
                            {
                                tot_amount=tot_amount+mList.get(i).pamount1;
                            }
                            tamount.setText(tot_amount+"");


                            et.setText("");
                            et2.setText("");
                            e1.setText(0+"");
                            e2.setText(0+"");
                            e3.setText("");
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Please enter Correct quntity",Toast.LENGTH_LONG).show();

                        }

                    }
                    else
                    {
                        initData(pcode,pquan,prname,prrate,prper,pamount);
                        initView();
                        et2.requestFocus();
                        titem.setText(mList.size()+"");
                        tot_amount=0;
                        for(int i=0;i<mList.size();i++)
                        {
                            tot_amount=tot_amount+mList.get(i).pamount1;
                        }
                        tamount.setText(tot_amount+"");


                        et.setText("");
                        et2.setText("");
                        e1.setText(0+"");
                        e2.setText(0+"");
                        e3.setText("");
                    }


                    //mproductlist.add(new Product("sd2",10,"sdfdsfdsf",3,"box",5000));









                    return true;
                }
                return false;
            }
        });

        e2=(EditText)findViewById(R.id.editText4);
        e3=(EditText)findViewById(R.id.editText5);
        titem=(TextView)findViewById(R.id.titems);
        tamount=(TextView)findViewById(R.id.totalamount);



        add=(ImageButton)findViewById(R.id.imageButton);
        et.setDropDownBackgroundResource(R.drawable.edit1);
        result1=new ArrayList();
        result2=new ArrayList();
        result3=new ArrayList();
        result4=new ArrayList();
        result5=new ArrayList();
        id="1";





        select();


      //  initView();
      //  mproductlist =new ArrayList<Product>();





    }



    private void initData(String pcode, int pquan, String prname, double prrate, String prper, int pamount) {

        System.out.println(pcode + prname);
        Product data = new Product(pcode,pquan,prname,prrate,prper,pamount);

        e4.setText(Integer.parseInt(e4.getText().toString())-pquan+"");
        mList.add(data);
    }

    private class ViewHolder {
        TextView tcode,tquan,tname,tprice,tper,tamount;
        ImageView mDelete,mFileInfo;
    }

    class ListViewAdapter extends BaseSwipeAdapter {


        public ListViewAdapter(Context context)
        {
            super(context, mListView);

        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = super.getView(position, convertView, parent);

                holder = new ViewHolder();

                holder.tcode = (TextView) convertView.findViewById(R.id.tcode);
                holder.tquan = (TextView) convertView.findViewById(R.id.tquan);
                holder.tname= (TextView) convertView.findViewById(R.id.tname);
                holder.tprice = (TextView) convertView.findViewById(R.id.trate);
                holder.tper= (TextView) convertView.findViewById(R.id.tper);
                holder.tamount = (TextView) convertView.findViewById(R.id.tamount);
                holder.mDelete = (ImageView) convertView.findViewById(R.id.delete);
                holder.mFileInfo = (ImageView) convertView.findViewById(R.id.infoButton);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.mDelete.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Toast.makeText(MyActivity.this, "DELETE", Toast.LENGTH_SHORT).show();
                    int position = mListView.getPositionForView(v);
                    mListView.hiddenRightView();
                    tot_amount=tot_amount-mList.get(position).pamount1;
                    tamount.setText(tot_amount+"");
                    titem.setText(mList.size()+"");

                    mList.remove(position);

                    mAdapter.notifyDataSetChanged();
                }
            });

            holder.mFileInfo.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    displayPopup(position);
                    Toast.makeText(MyActivity.this, "INFO", Toast.LENGTH_SHORT).show();
                }
            });

           // holder.tname.setText(((LeftData)getItem(position)).name);
          //  holder.tamount.setText(((LeftData)getItem(position)).desc);

            holder.tname.setText(((Product)getItem(position)).prname1);
            holder.tamount.setText(((Product)getItem(position)).pamount1+"");
            holder.tcode.setText(((Product)getItem(position)).pcode1);
            holder.tquan.setText(((Product)getItem(position)).pquan1+"");
            holder.tprice.setText(((Product)getItem(position)).prrate1+"");
            holder.tper.setText(((Product)getItem(position)).prper1);


            return convertView;
        }





    }


    private void initView() {
        mListView = (SwipeListView)findViewById(R.id.listview);
        mAdapter = new ListViewAdapter(this);
        mListView.setAdapter(mAdapter);
        mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);


        mListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener(){

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // TODO Auto-generated method stub
                Log.e("MultiChoiceModeListener", "onActionItemClicked");
                return false;
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                if (mListView.onCreateActionMode()) {
                    Log.e("MultiChoiceModeListener", "onCreateActionMode--before");
                    return true;
                } else {
                    Log.e("MultiChoiceModeListener", "onCreateActionMode--after");
                    return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                Log.e("MultiChoiceModeListener", "onDestroyActionMode");
                mListView.onDestroyActionMode();
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                Log.e("MultiChoiceModeListener", "onPrepareActionMode");
                return false;
            }

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                Log.e("MultiChoiceModeListener", "onItemCheckedStateChanged");
            }

        });


      /*  mListView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/
    }

    private void displayPopup(final int position)
    {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.pop_up, (ViewGroup) findViewById(R.id.PopupLayout1));
        final PopupWindow pw = new PopupWindow(layout,100,100,true);
        pw.showAtLocation(findViewById(R.id.listview), Gravity.CENTER, 0, 0);

        Button btnOk = (Button) layout.findViewById(R.id.btnOK);


        btnOk.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                EditText tp = (EditText) layout.findViewById(R.id.editText9);

                if (Integer.parseInt(tp.getText().toString()) != 0) {

                    mList.remove(position);
                    initData(pcode, Integer.parseInt(tp.getText().toString()), prname, prrate, prper, pamount);
                    mAdapter.notifyDataSetChanged();
                } else {

                    mList.remove(position);
                    initData(pcode, 0, prname, prrate, prper, pamount);
                    mAdapter.notifyDataSetChanged();
                }
                // TextView tv = (TextView) findViewById(R.id.textView1);
                //tv.setText( tm.toString());

                pw.dismiss();
            }
        });
    }


    public void select()
    {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("id",id));
        SharedPreferences sh1=getSharedPreferences("setting",MODE_PRIVATE);
        //String address=sh1.getString("address",null);
        String ip=sh1.getString("ip",null);


        try
        {
            HttpClient httpclient = new DefaultHttpClient();
           // HttpPost httppost = new HttpPost("http://172.16.4.41:81/cracker/select.php");
            HttpPost httppost = new HttpPost("http://"+ip+"/cracker/select.php");
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

            for (i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String code = jsonChildNode.optString("code");
                String pname = jsonChildNode.optString("name");
                String prate=jsonChildNode.optString("rate");
                String pper=jsonChildNode.optString("per");
                String stock=jsonChildNode.optString("stock");
                // String outPut = name + "-" + number;
                result1.add(pname);
                result2.add(code);
                result3.add(prate);
                result4.add(pper);
                result5.add(stock);
            }
            final String[] code_array = new String[result1.size()];
            final String[] pname_array = new String[result1.size()];
            final String[] prate_array = new String[result1.size()];
            final String[] pper_array = new String[result1.size()];
            final String[] stock_array = new String[result1.size()];

            for (int i = 0; i < result1.size(); i++) {
                pname_array[i] = result1.get(i).toString();
                code_array[i] = result2.get(i).toString();
                prate_array[i] = result3.get(i).toString();
                pper_array[i] = result4.get(i).toString();
                stock_array[i] = result5.get(i).toString();
            }
            ArrayAdapter ado=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,result1);
            et.setAdapter(ado);

            ArrayAdapter ado1=new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,result2);
            et2.setAdapter(ado1);



            et.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String val=et.getText().toString();
                    for(int j=0;j<pname_array.length;j++)
                    {

                        if(pname_array[j].equals(val))
                        {
                          //  Toast.makeText(getBaseContext(), "Name : "+array2[j] +":" +val,Toast.LENGTH_SHORT).show();
                            e1.requestFocus();
                            e1.getText().clear();
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                            et2.setText(code_array[j]);
                            e2.setText(prate_array[j]);
                            e3.setText(pper_array[j]);
                            e4.setText(stock_array[j]);
                        }
                    }
                }
            });

            et2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String val=et2.getText().toString();
                    for(int j=0;j<code_array.length;j++)
                    {

                        if(code_array[j].equals(val))
                        {
                            //  Toast.makeText(getBaseContext(), "Name : "+array2[j] +":" +val,Toast.LENGTH_SHORT).show();
                            e1.requestFocus();
                            e1.getText().clear();
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
                            et.setText(pname_array[j]);
                            e2.setText(prate_array[j]);
                            e3.setText(pper_array[j]);
                            e4.setText(stock_array[j]);
                        }
                    }
                }
            });
            // name=json_data.getString("name").toString();
            // id=(json_data.getString("id"));

        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }


    public void add1(View v)
    {



    }


    public void preview(View v)
    {
        Intent in=new Intent(getApplicationContext(),Invoice.class);
        in.putExtra("radiochoice",radioselect.getText());
        in.putExtra("LIST", (Serializable) mList);
        in.putExtra("totalstock",available_stock);
        startActivity(in);
    }
}
