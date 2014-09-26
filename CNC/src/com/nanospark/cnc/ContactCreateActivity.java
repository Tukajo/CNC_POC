package com.nanospark.cnc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ContactCreateActivity extends Activity implements OnItemSelectedListener{
	EditText nameET;
	EditText phoneET;
	EditText emailET;
	Button contactCancelBtn;
	Button contactCreateBtn;
	Spinner carrierSpinner;
	GlobalData globaldata = GlobalData.getInstance();
	String selectedSpinnerItem = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.contact_creation_layout);
		nameET = (EditText) findViewById(R.id.contactNameET);
		phoneET = (EditText) findViewById(R.id.contactPhoneET);
		emailET = (EditText) findViewById(R.id.contactEmailET);
		carrierSpinner = (Spinner) findViewById(R.id.contactCarrierSpinner);
		contactCancelBtn = (Button) findViewById(R.id.contactLayoutCancelBtn);
		contactCreateBtn = (Button) findViewById(R.id.contactLayoutCreateBtn);

		contactCancelBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent myCancelIntent = new Intent(ContactCreateActivity.this,
						MainActivity.class);
				startActivity(myCancelIntent);
			}
		});

		contactCreateBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!nameET.getText().toString().equals(null)
						&& !nameET.getText().toString().equals("")
						&& !phoneET.getText().toString().equals(null)
						&& !phoneET.getText().toString().equals("")
						&& !emailET.getText().toString().equals(null)
						&& !emailET.getText().toString().equals("")
						&& !selectedSpinnerItem.equals(null)) {

					globaldata.getContactInfoList().add(
							new ContactInfo(phoneET.getText().toString(),
									nameET.getText().toString(), emailET
											.getText().toString(),
									selectedSpinnerItem));
					
					Intent myReturnintent = new Intent(ContactCreateActivity.this,
							MainActivity.class);
					startActivity(myReturnintent);
				} else {
					Toast.makeText(getBaseContext(),
							"You are missing a parameter!", Toast.LENGTH_LONG)
							.show();
				}
			}
		});
		ArrayAdapter<CharSequence> carrierAdapter  = ArrayAdapter.createFromResource(this,R.array.carrier_options, android.R.layout.simple_spinner_item);
		carrierAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		carrierSpinner.setAdapter(carrierAdapter);
		
		carrierSpinner.setOnItemSelectedListener(this);

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		selectedSpinnerItem = (String) parent.getItemAtPosition(pos);
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
			
	}
}