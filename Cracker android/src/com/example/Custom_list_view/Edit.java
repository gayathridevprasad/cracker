package com.example.Custom_list_view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

/**
 * Created by akil on 06-05-2016.
 */

public class Edit extends Activity {
   EditText quan;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.edit);
        quan=(EditText)findViewById(R.id.editText6);
    }

    public void edit(View v)
    {
        Intent in=new Intent(this,MyActivity.class);
        in.putExtra("qu",quan.getText().toString());
        startActivity(in);
    }



}