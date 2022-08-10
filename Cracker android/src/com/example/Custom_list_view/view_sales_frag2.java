package com.example.Custom_list_view;


import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 * Created by akil on 27-06-2016.
 */
public class view_sales_frag2 extends Fragment {
    private EditText txtView;
    private String initialDate;
    private String initialMonth;
    private String initialYear;
    private DatePickerDialog dialog = null;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_sales_frag2, container, false);
    }


    @Override
    public void onStart() {
        super.onStart();


        ImageButton btn = (ImageButton)getActivity().findViewById(R.id.button1);
        txtView = (EditText)getActivity().findViewById(R.id.textView1);
        context = getActivity().getApplicationContext();
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar dtTxt = null;

                String preExistingDate = (String) txtView.getText().toString();

                if(preExistingDate != null && !preExistingDate.equals("")){
                    StringTokenizer st = new StringTokenizer(preExistingDate,"/");
                    initialMonth = st.nextToken();
                    initialDate = st.nextToken();
                    initialYear = st.nextToken();
                    if(dialog == null)
                        dialog = new DatePickerDialog(v.getContext(),
                                new PickDate(),Integer.parseInt(initialYear),
                                Integer.parseInt(initialMonth)-1,
                                Integer.parseInt(initialDate));
                    dialog.updateDate(Integer.parseInt(initialYear),
                            Integer.parseInt(initialMonth)-1,
                            Integer.parseInt(initialDate));

                } else {
                    dtTxt = Calendar.getInstance();
                    if(dialog == null)
                        dialog = new DatePickerDialog(v.getContext(),new PickDate(),dtTxt.getTime().getYear(),dtTxt.getTime().getMonth(),
                                dtTxt.getTime().getDay());
                    dialog.updateDate(dtTxt.getTime().getYear(),dtTxt.getTime().getMonth(),
                            dtTxt.getTime().getDay());
                }

                dialog.show();
            }

        });
    }

    private class PickDate implements DatePickerDialog.OnDateSetListener {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            view.updateDate(year, monthOfYear, dayOfMonth);
            txtView.setText(monthOfYear+"/"+dayOfMonth+"/"+year);
            dialog.hide();
        }

    }
}