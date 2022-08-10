package com.example.Custom_list_view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.*;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import net.sf.andpdf.pdfviewer.PdfViewerActivity;
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

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by akil on 13-05-2016.
 */
public class Invoice extends Activity {
    ArrayList<Product> mproductlist;
    ListView list_invoice;
    ScrollView sv;
    long date = System.currentTimeMillis();
    TextView today;
    private  InvoiceListAdapter adaptor;

    String invoice;
    String dateString;
    int code;

    String[] name1;
    int[] qty;
    double[] price;
    String[] per;
    int[] amount;
    String tax="5",discount="50";
    TextView tax_text,discount_text,sub_total_text,tax_calc,discount_calc,total_calc,invoice_text,partyaddress;
    TextView partyname;

    EditText input;
    int sub_total=0;
    SharedPreferences sp;
    int in_num,profoma_num;



    PdfPTable table=new PdfPTable(2);
    PdfPTable header_table=new PdfPTable(2);
    PdfPTable footer_table=new PdfPTable(2);
    PdfPCell cell1, cell2,cell3,cell4, cell5,cell6,cell7, cell8,cell9,cell10,cell11,cell12,cell13,cell14;
    File cacheDir;
    PdfPCell hcell1,hcell2;
    final Context context = this;



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
    String ip,store_address;
    int i,j;

    String[] qty_array,cname_array,address_array;
    String[] pname_array;
    String[] pprice_array;
    String[] pper_array;
    String[] pamount_array;

    ImageButton print;

    double double_subtotal;
    double double_tax;
    double double_discount;
    double tax_final;
    double discount_final;
    String radiochoice;
    int availabe_stock;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.invoice);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        today=(TextView)findViewById(R.id.textView14);
        tax_text=(TextView)findViewById(R.id.textView30);
        discount_text=(TextView)findViewById(R.id.textView28);
        sub_total_text=(TextView)findViewById(R.id.textView25);
        tax_calc=(TextView)findViewById(R.id.textView31);
        discount_calc=(TextView)findViewById(R.id.textView27);
        invoice_text=(TextView)findViewById(R.id.textView12);
        total_calc=(TextView)findViewById(R.id.textView33);

        partyaddress=(TextView)findViewById(R.id.partyaddress);
        partyname=(TextView)findViewById(R.id.partyname);
       sh1=getSharedPreferences("setting",MODE_PRIVATE);
        // String address=sh1.getString("address",null);
        ip=sh1.getString("ip",null);
        store_address=sh1.getString("address",null);

        tax_text.setText("("+tax+"%)");
        discount_text.setText("("+discount+"%)");
        print=(ImageButton)findViewById(R.id.imageButton4);
        result1=new ArrayList();
        result2=new ArrayList();
        result3=new ArrayList();
        result4=new ArrayList();
        result5=new ArrayList();
        result6=new ArrayList();

        //for storing invoive number



        list_invoice=(ListView)findViewById(R.id.listView2);
        sv=(ScrollView)findViewById(R.id.scrollView1);
        Intent i = getIntent();
        mproductlist = (ArrayList<Product>) i.getSerializableExtra("LIST");
        availabe_stock=i.getExtras().getInt("totalstock");
        radiochoice=i.getStringExtra("radiochoice");

        sp=getSharedPreferences("invoice",MODE_PRIVATE);

        Toast.makeText(this,radiochoice,Toast.LENGTH_LONG).show();

        in_num=sp.getInt("invoice_no",0);
        profoma_num=sp.getInt("profoma_no",0);
        if(radiochoice.equals("Profoma"))
        {

            if (profoma_num == 0) {
                profoma_num = 1;
                billnum="PR" + profoma_num;
                invoice_text.setText(billnum);
            } else {
                billnum="PR" + profoma_num;
                invoice_text.setText( billnum);
            }

        }
        else {
            if (in_num == 0) {
                in_num = 1;
                billnum="IN" + in_num;
                invoice_text.setText(billnum);
            } else {
                billnum="IN" + in_num;
                invoice_text.setText(billnum);
            }

        }
        adaptor=new InvoiceListAdapter(getApplicationContext(),mproductlist);
        list_invoice.setAdapter(adaptor);


        //for working listview in scrollview
        Helper.getListViewSize(list_invoice);

        name1 = new String[mproductlist.size()];
        qty=new int[mproductlist.size()];
        price=new double[mproductlist.size()];
        per = new String[mproductlist.size()];
        amount=new int[mproductlist.size()];

        for(j=0;j<mproductlist.size();j++) {
            Product firstA = mproductlist.get(j);
            name1[j] = firstA.prname1;
            qty[j] = firstA.pquan1;
            price[j] = firstA.prrate1;
            per[j] = firstA.prper1;
            amount[j] = firstA.pamount1;
            sub_total = sub_total + amount[j];}

        sub_total_text.setText(sub_total+"");
        double_subtotal=Double.parseDouble(sub_total+"");
        double_tax=Double.parseDouble(tax);
        double_discount=Double.parseDouble(discount);
        tax_final=double_subtotal*(double_tax/100);
        tax_calc.setText(tax_final+"");
        discount_final=double_subtotal*(double_discount/100);
        discount_calc.setText(discount_final+"");
        total_calc.setText((double_subtotal+tax_final)-discount_final+"");
        select_cust();
            print.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),billnum,Toast.LENGTH_LONG).show();
                    for (j = 0; j < mproductlist.size(); j++) {

                        String netamount = total_calc.getText().toString();
                        String dtae = today.getText().toString();
                        String partyname1 = partyname.getText().toString();
                        Toast.makeText(getApplicationContext(), availabe_stock-qty[j]+"", Toast.LENGTH_SHORT).show();
                        //   insert(name1[j],qty[j],price[j],per[j], amount[j]);

                        ArrayList < NameValuePair > nameValuePairs = new ArrayList<NameValuePair>();
                        nameValuePairs.add(new BasicNameValuePair("radiochoice", radiochoice));
                        nameValuePairs.add(new BasicNameValuePair("invoice", billnum));

                        nameValuePairs.add(new BasicNameValuePair("name", name1[j]));
                        nameValuePairs.add(new BasicNameValuePair("qty", qty[j] + ""));
                        nameValuePairs.add(new BasicNameValuePair("availstock", availabe_stock-qty[j]+""));
                        nameValuePairs.add(new BasicNameValuePair("price", price[j] + ""));
                        nameValuePairs.add(new BasicNameValuePair("per", per[j]));
                        nameValuePairs.add(new BasicNameValuePair("amount", amount[j] + ""));
                        nameValuePairs.add(new BasicNameValuePair("netamount", netamount));
                        nameValuePairs.add(new BasicNameValuePair("date", dtae));
                        nameValuePairs.add(new BasicNameValuePair("partyname", partyname1));


                        try {
                            HttpClient httpclient = new DefaultHttpClient();
                            //HttpPost httppost = new HttpPost("http://172.16.4.41:81/cracker/insert.php");
                            HttpPost httppost = new HttpPost("http://"+ip+"/cracker/insert.php");
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


                    }
                    select();



                }
            });





        discount_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);


                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);


                alertDialogBuilder.setMessage("Edit Discount Charge");
                final TextView input1 = new TextView(context);

                input = new EditText(context);
                alertDialogBuilder.setView(input1);
                alertDialogBuilder.setView(input);
                input1.setText("Edit  Discount Charge");
               /* input.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == event.KEYCODE_ENTER) {
                            t21.setText(input.getText());


                            return true;
                        }
                        return false;
                    }

                });*/

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        discount = input.getText().toString();
                                        discount_text.setText("(" + discount + ")" + "%");

                                        double_subtotal=Double.parseDouble(sub_total+"");
                                        double_tax=Double.parseDouble(tax);
                                        double_discount=Double.parseDouble(discount);
                                        tax_final=double_subtotal*(double_tax/100);
                                        tax_calc.setText(tax_final+"");
                                        discount_final=double_subtotal*(double_discount/100);
                                        discount_calc.setText(discount_final+"");
                                        total_calc.setText((double_subtotal+tax_final)-discount_final+"");

                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();


            }
        });



        partyname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Dialog dialog = new Dialog(context);


                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View post = inflater.inflate(R.layout.autotext_cust, null);
                final AutoCompleteTextView  textView = (AutoCompleteTextView)post.findViewById((R.id.autoCompleteTextView));
                 textView.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
                ArrayAdapter ado1=new ArrayAdapter(context,android.R.layout.simple_list_item_1,cname_array);
                textView.setAdapter(ado1);
                textView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        partyaddress.setText(address_array[i]);
                    }
                });

                Button dialogButton = (Button) post.findViewById(R.id.autotext_ok);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        partyname.setText(textView.getText().toString());
                        dialog.dismiss();
                    }
                });

////Autocomplete

//textView.setThreshold(2);

                Window window = dialog.getWindow();
                WindowManager.LayoutParams wlp = window.getAttributes();

                wlp.gravity = Gravity.TOP;
                wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                window.setAttributes(wlp);

                dialog.setContentView(post);
                dialog.setTitle("Post");
                dialog.show();
                /*LayoutInflater li = LayoutInflater.from(context);
                final View vi = getLayoutInflater().inflate(R.layout.autotext_cust, null);

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);


                alertDialogBuilder.setMessage("Enter Customer Name");
                final TextView input1 = new TextView(context);


                auto_cust_input = (AutoCompleteTextView)vi.findViewById(R.id.autoCompleteTextView);
                auto_cust_input.setImeOptions(EditorInfo.IME_FLAG_NO_EXTRACT_UI);
                ado1=new ArrayAdapter(context,android.R.layout.simple_list_item_1,cname_array);
                auto_cust_input.setAdapter(ado1);
                auto_cust_input.setThreshold(1);
                alertDialogBuilder.setView(input1);
                alertDialogBuilder.setView(auto_cust_input);
                auto_cust_input.setHint("Enter Customer Name");
               /* input.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == event.KEYCODE_ENTER) {
                            t21.setText(input.getText());


                            return true;
                        }
                        return false;
                    }

                });*/

                // set dialog message
              /*  alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
                                        partyname.setText(auto_cust_input.getText().toString());

                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                */

            }
        });


        tax_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(context);


                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);


                alertDialogBuilder.setMessage("Edit Tax");
                final TextView input1 = new TextView(context);

                input = new EditText(context);
                alertDialogBuilder.setView(input1);
                alertDialogBuilder.setView(input);
                input1.setText("Edit Tax");
               /* input.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == event.KEYCODE_ENTER) {
                            t21.setText(input.getText());


                            return true;
                        }
                        return false;
                    }

                });*/

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
                                        tax=input.getText().toString();
                                        tax_text.setText("("+tax+")"+"%");

                                        double_subtotal=Double.parseDouble(sub_total+"");
                                        double_tax=Double.parseDouble(tax);
                                        double_discount=Double.parseDouble(discount);
                                        tax_final=double_subtotal*(double_tax/100);
                                        tax_calc.setText(tax_final+"");
                                        discount_final=double_subtotal*(double_discount/100);
                                        discount_calc.setText(discount_final+"");
                                        total_calc.setText((double_subtotal+tax_final)-discount_final+"");
                                    }
                                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });


        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy , h:mm a");
        dateString = sdf.format(date);
        today.setText(dateString);






    }



    public void insert(String name,int qty,double price,String per,int amount)
    {



    }



    public void print1()
    {
       // in_num=in_num+1;
        System.out.println("HI Print");
        sp=getSharedPreferences("invoice",MODE_PRIVATE);
        SharedPreferences.Editor edi=sp.edit();
        edi.putInt("invoice_no",in_num+1);
        edi.putInt("profoma_no",profoma_num+1);
        edi.commit();

        if(partyname.getText().toString().equals("Name") || partyaddress.getText().toString().equals("Address"))
        {
            Toast.makeText(getApplicationContext(),"Please Enter Customer Name and Address",Toast.LENGTH_LONG).show();
        }
        else {
        String FILE = Environment.getExternalStorageDirectory().toString() + "/Code Analyser/" + billnum +".pdf";

        // Create New Blank Document
        Document document = new Document(PageSize.A4);

        // Create Pdf Writer for Writting into New Created Document
        try {
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            // Open Document for Writting into document
            document.open();
            // User Define Method
            addTitlePage(document);
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Close Document after writting all content
        document.close();

        Toast.makeText(this, "PDF File is Created."+FILE, Toast.LENGTH_LONG).show();


        //Cloud Print







            Intent intent = new Intent(this, Pdftoimage_display.class);
            //intent.putExtra(PdfViewerActivity.EXTRA_PDFFILENAME,                "storage/sdcard/Code Analyser/"+billnum+".pdf");
            intent.putExtra("billnum", billnum);
            startActivity(intent);
        }

    }

    public  void back(View v)
    {
        finish();
    }



    public boolean isNetworkAvailable() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            Log.e("Network Testing", "***Available***");
            return true;
        }
        Log.e("Network Testing", "***Not Available***");
        return false;
    }



    public void select()
    {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("id",billnum));
        nameValuePairs.add(new BasicNameValuePair("radiochoice", radiochoice));



        try
        {
            HttpClient httpclient = new DefaultHttpClient();
           // HttpPost httppost = new HttpPost("http://172.16.4.41:81/cracker/select_invoice.php");
            HttpPost httppost = new HttpPost("http://"+ip+"/cracker/select_invoice.php");
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
                String pname = jsonChildNode.optString("name");
                String pqty=jsonChildNode.optString("qty");
                String pprice=jsonChildNode.optString("price");
                String pper=jsonChildNode.optString("per");
                String pamount=jsonChildNode.optString("amount");
                // String outPut = name + "-" + number;
                result1.add(invoice);
                result2.add(pname);
                result3.add(pqty);
                result4.add(pprice);
                result5.add(pper);
                result6.add(pamount);
            }
            qty_array = new String[result1.size()];
            pname_array = new String[result1.size()];
            pprice_array = new String[result1.size()];
            pper_array = new String[result1.size()];
            pamount_array = new String[result1.size()];

            for (int i = 0; i < result1.size(); i++) {
                pname_array[i] = result2.get(i).toString();
                qty_array[i] = result3.get(i).toString();
                pprice_array[i] = result4.get(i).toString();
                pper_array[i] = result5.get(i).toString();
                pamount_array[i] = result6.get(i).toString();
            }

            // name=json_data.getString("name").toString();
            // id=(json_data.getString("id"));

        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }

        print1();
    }



    public void select_cust()
    {

        ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

        nameValuePairs.add(new BasicNameValuePair("id","hi"));
       // nameValuePairs.add(new BasicNameValuePair("radiochoice", radiochoice));



        try
        {
            HttpClient httpclient = new DefaultHttpClient();
            // HttpPost httppost = new HttpPost("http://172.16.4.41:81/cracker/select_invoice.php");
            HttpPost httppost = new HttpPost("http://"+ip+"/cracker/select_cust.php");
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

                String pname = jsonChildNode.optString("name");
                String address=jsonChildNode.optString("address");

                // String outPut = name + "-" + number;
                result1.add(pname);
                result2.add(address);

            }
            cname_array = new String[result1.size()];
            address_array = new String[result1.size()];

            for (int i = 0; i < result1.size(); i++) {
                cname_array[i] = result1.get(i).toString();
                System.out.println(cname_array[i]);
                address_array[i] = result2.get(i).toString();

            }

            // name=json_data.getString("name").toString();
            // id=(json_data.getString("id"));

        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }


    }



    public void addTitlePage(Document document) throws DocumentException
    {
        // Font Style for Document
        Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
        Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD| Font.UNDERLINE, BaseColor.GRAY);
        Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
        Font normal = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);

        // Start New Paragraph
        Paragraph prHead = new Paragraph();
        // Set Font in this Paragraph
        prHead.setFont(titleFont);
        // Add item into Paragraph
        prHead.add(store_address);
        //prHead.add("\n");
        prHead.setAlignment(Element.ALIGN_CENTER);

        Paragraph cat = new Paragraph();
        cat.setFont(catFont);
        cat.add("\n");
        cat.add("INVOICE\n");
        cat.add("\n");
        cat.setAlignment(Element.ALIGN_CENTER);

        // Add all above details into Document
        document.add(prHead);
        document.add(cat);
        document.add(table);
        document.add(header_table);

        header_table=new PdfPTable(2);
        Font colfont1 = new Font(Font.FontFamily.HELVETICA, 8);
        hcell1=new PdfPCell();


        PdfPTable hcell2_nested=new PdfPTable(2);
        hcell2_nested.setHorizontalAlignment(Rectangle.LEFT);
        PdfPCell hnested_cell1=new PdfPCell(new Phrase("Invoice No. :",colfont1));
        hnested_cell1.setBorder(Rectangle.NO_BORDER);
        hnested_cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell hnested_cell2=new PdfPCell(new Phrase(invoice,colfont1));
        hnested_cell2.setBorder(Rectangle.NO_BORDER);
        hnested_cell2.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell hnested_cell3=new PdfPCell(new Phrase("Date :",colfont1));
        hnested_cell3.setBorder(Rectangle.NO_BORDER);
        hnested_cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell hnested_cell4=new PdfPCell(new Phrase(dateString,colfont1));
        hnested_cell4.setBorder(Rectangle.NO_BORDER);
        hnested_cell4.setHorizontalAlignment(Element.ALIGN_LEFT);;

        hcell2_nested.addCell(hnested_cell1);
        hcell2_nested.addCell(hnested_cell2);
        hcell2_nested.addCell(hnested_cell3);
        hcell2_nested.addCell(hnested_cell4);

        hcell1.addElement(hcell2_nested);
        hcell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        hcell1.setBorder(Rectangle.NO_BORDER);
        hcell1.setPadding(5);

        hcell2=new PdfPCell(new Phrase("InVoice No.: " + "1123\n" + "Date:" + "13/05/2015" ,colfont1));
        hcell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        hcell2.setBorder(Rectangle.NO_BORDER);
        hcell2.setPadding(5);




        header_table.addCell(hcell1);
        header_table.addCell(hcell2);

        document.add(header_table);

			/* Header values*/
        table = new PdfPTable(6);
        Font colfont = new Font(Font.FontFamily.HELVETICA, 8);
        cell1 = new PdfPCell(new Phrase("Item",colfont));
        cell2 = new PdfPCell(new Phrase("Tax",colfont));
        cell11 = new PdfPCell(new Phrase("Quantity",colfont));
        cell12 = new PdfPCell(new Phrase("Price",colfont));
        cell13 = new PdfPCell(new Phrase("Per",colfont));
        cell14 = new PdfPCell(new Phrase("Amount",colfont));

        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell14.setHorizontalAlignment(Element.ALIGN_CENTER);

        //	cell1.setBorder(Rectangle.NO_BORDER);
        cell1.setPadding(5);

        //cell2.setBorder(Rectangle.NO_BORDER);
        cell2.setPadding(5);
        //	cell11.setBorder(Rectangle.NO_BORDER);
        cell11.setPadding(5);
        //	cell12.setBorder(Rectangle.NO_BORDER);
        cell12.setPadding(5);
        //	cell13.setBorder(Rectangle.NO_BORDER);
        cell13.setPadding(5);
        //	cell14.setBorder(Rectangle.NO_BORDER);
        cell14.setPadding(5);

        cell1.setBackgroundColor(BaseColor.GRAY);
        cell2.setBackgroundColor(BaseColor.GRAY);
        cell11.setBackgroundColor(BaseColor.GRAY);
        cell12.setBackgroundColor(BaseColor.GRAY);
        cell13.setBackgroundColor(BaseColor.GRAY);
        cell14.setBackgroundColor(BaseColor.GRAY);
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell11);
        table.addCell(cell12);
        table.addCell(cell13);
        table.addCell(cell14);

        for(int i=0;i<pname_array.length;i++)
        {
            table.addCell(new PdfPCell(new Phrase(pname_array[i],colfont)));
            table.addCell(new PdfPCell(new Phrase("hi",colfont)));
            table.addCell(new PdfPCell(new Phrase(qty_array[i],colfont)));
            table.addCell(new PdfPCell(new Phrase(pprice_array[i],colfont)));
            table.addCell(new PdfPCell(new Phrase(pper_array[i],colfont)));
            table.addCell(new PdfPCell(new Phrase(pamount_array[i],colfont)));

        }
			/*Table values*/
		/*	cell3 = new PdfPCell(new Phrase("Name",colfont));
			cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
		//	cell3.setBorder(Rectangle.NO_BORDER);
			cell3.setPadding(1);

			cell4 = new PdfPCell(new Phrase(edit1,colfont));
			cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			//cell4.setBorder(Rectangle.NO_BORDER);
			cell4.setPadding(1);

			cell5 = new PdfPCell(new Phrase("Mobile Number",colfont));
			cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
			//cell5.setBorder(Rectangle.NO_BORDER);
			cell5.setPadding(1);

			cell6 = new PdfPCell(new Phrase(edit2,colfont));
			cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
			//cell6.setBorder(Rectangle.NO_BORDER);
			cell6.setPadding(1);

			cell7 = new PdfPCell(new Phrase("Mail Id",colfont));
			cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			//cell7.setBorder(Rectangle.NO_BORDER);
			cell7.setPadding(1);

			cell8 = new PdfPCell(new Phrase(edit3,colfont));
			cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
			//cell8.setBorder(Rectangle.NO_BORDER);
			cell8.setPadding(1);

			cell9 = new PdfPCell(new Phrase("City",colfont));
			cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
			//cell9.setBorder(Rectangle.NO_BORDER);
			cell9.setPadding(1);

			cell10 = new PdfPCell(new Phrase(edit4,colfont));
			cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
			//cell10.setBorder(Rectangle.NO_BORDER);
			cell10.setPadding(1);



			table.addCell(cell3);
			table.addCell(cell4);
			table.addCell(cell5);
			table.addCell(cell6);
			table.addCell(cell7);
			table.addCell(cell8);
			table.addCell(cell9);
			table.addCell(cell10);*/
        float[] columnWidths = new float[] {40f, 10f, 10f, 10f,10f,10f};
        table.setWidths(columnWidths);
        // add table into document
        document.add(table);


        footer_table=new PdfPTable(2);

        PdfPCell fcell=new PdfPCell(new Phrase("Sub Total",colfont));
        fcell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
        footer_table.addCell(fcell);
        footer_table.addCell(new PdfPCell(new Phrase(double_subtotal+"",colfont)));
        float[] columnWidths1 = new float[] {80f,10f};
        footer_table.setWidths(columnWidths1);
        //document.add(footer_table);

        PdfPCell fcell1=new PdfPCell(new Phrase("TAX",colfont));
        fcell1.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
        footer_table.addCell(fcell1);
        footer_table.addCell(new PdfPCell(new Phrase(tax_final+"",colfont)));

        footer_table.setWidths(columnWidths1);
        //document.add(footer_table);

        PdfPCell fcell2=new PdfPCell(new Phrase("DISCOUNT",colfont));
        fcell2.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
        footer_table.addCell(fcell2);
        footer_table.addCell(new PdfPCell(new Phrase(discount_final+"",colfont)));

        footer_table.setWidths(columnWidths1);

        PdfPCell fcell3=new PdfPCell(new Phrase("TOTAL",colfont));
        fcell3.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
        footer_table.addCell(fcell3    );
        footer_table.addCell(new PdfPCell(new Phrase((double_subtotal+tax_final)-discount_final+"",colfont)));

        footer_table.setWidths(columnWidths1);
        document.add(footer_table);


        Paragraph thank = new Paragraph();
        thank.setFont(catFont);
        thank.add("\n");
        thank.add("\n");
        thank.add("\n");
        thank.add("THANKS FOR SHOPPPING WITH US\n");
        thank.add("\n");
        thank.setAlignment(Element.ALIGN_CENTER);


        document.add(thank);
        // Create new Page in PDF
        document.newPage();
        //Toast.makeText(this, "PDF File is Created.", Toast.LENGTH_LONG).show();
    }

}