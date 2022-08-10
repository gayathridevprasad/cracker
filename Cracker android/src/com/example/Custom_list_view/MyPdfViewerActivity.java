package com.example.Custom_list_view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import net.sf.andpdf.pdfviewer.PdfViewerActivity;

public class MyPdfViewerActivity extends PdfViewerActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout lm =new LinearLayout(this);
		lm.setOrientation(LinearLayout.VERTICAL);
		// create the layout params that will be used to define how your
		// button will be displayed
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

		//Create four
		for (int j = 0; j <= 4; j++) {
			// Create LinearLayout
			LinearLayout ll = new LinearLayout(this);
			ll.setOrientation(LinearLayout.HORIZONTAL);

			// Create TextView
			TextView product = new TextView(this);
			product.setText(" Product" + j + "    ");
			ll.addView(product);

			// Create TextView
			TextView price = new TextView(this);
			price.setText("  $" + j + "     ");
			ll.addView(price);

			// Create Button
			final Button btn = new Button(this);
			// Give button an ID
			btn.setId(j + 1);
			btn.setText("Add To Cart");
			// set the layoutParams on the button
			btn.setLayoutParams(params);

			final int index = j;
			// Set click listener for button
			btn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					Log.i("TAG", "index :" + index);

					Toast.makeText(getApplicationContext(),
							"Clicked Button Index :" + index,
							Toast.LENGTH_LONG).show();

				}
			});

			//Add button to LinearLayout
			ll.addView(btn);
			//Add button to LinearLayout defined in XML
			lm.addView(ll);
		}

	}

	public int getPreviousPageImageResource() {
		return R.drawable.left_arrow;
	}

	public int getNextPageImageResource() {
		return R.drawable.right_arrow;
	}

	public int getZoomInImageResource() {
		return R.drawable.zoom_in;
	}

	public int getZoomOutImageResource() {
		return R.drawable.zoom_out;
	}

	public int getPdfPasswordLayoutResource() {
		return R.layout.pdf_file_password;
	}

	public int getPdfPageNumberResource() {
		return R.layout.dialog_pagenumber;
	}

	public int getPdfPasswordEditField() {
		return R.id.etPassword;
	}

	public int getPdfPasswordOkButton() {
		return R.id.btOK;
	}

	public int getPdfPasswordExitButton() {
		return R.id.btExit;
	}

	public int getPdfPageNumberEditField() {
		return R.id.pagenum_edit;
	}
	// @Override
	// public int getNextPageImageResource() {
	// return 0;
	// }
	//
	// @Override
	// public int getPdfPageNumberEditField() {
	// return 0;
	// }
	//
	// @Override
	// public int getPdfPageNumberResource() {
	// return 0;
	// }
	//
	// @Override
	// public int getPdfPasswordEditField() {
	// return 0;
	// }
	//
	// @Override
	// public int getPdfPasswordExitButton() {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	//
	// @Override
	// public int getPdfPasswordLayoutResource() {
	// // TODO Auto-generated method stub
	// return 0;
	// }
	//
	// @Override
	// public int getPdfPasswordOkButton() {
	// // TODO Auto-generated method stub
	// return 0;
	// }

}
