package com.nanospark.cnc;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class EventCreateActivity extends FragmentActivity {
	EditText enterEventNameET;
	EditText enterEventDescriptionET;
	RadioGroup selectEventType;
	RadioButton reportRB;
	RadioButton notificationRB;
	Button createEventBtn;
	Button cancelEventBtn;
	Fragment eventFragment;
	FragmentManager transactionManager = getSupportFragmentManager();
	FragmentTransaction transaction;
	GlobalData globaldata = GlobalData.getInstance();
	boolean reportIsChecked = true;
	boolean notificationIsChecked = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_creation_layout);
		
		selectEventType = (RadioGroup) findViewById(R.id.radioGroup1);
		reportRB = (RadioButton) findViewById(R.id.radio0);
		notificationRB = (RadioButton) findViewById(R.id.radio1);
		
		eventFragment = new event_report_fragment();
		transaction = transactionManager.beginTransaction();
		transaction.replace(R.id.event_fragment_frame, eventFragment);
		transaction.addToBackStack(null);
		transaction.commit();
		
		notificationRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				eventFragment = new event_report_fragment();
				transaction = transactionManager.beginTransaction();
				transaction.replace(R.id.event_fragment_frame, eventFragment);
				transaction.addToBackStack(null);
				transaction.commit();
				notificationIsChecked = isChecked;
			
			}
				
		});
		
		reportRB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				eventFragment = new event_report_fragment();
				transaction = transactionManager.beginTransaction();
				transaction.replace(R.id.event_fragment_frame, eventFragment);
				transaction.addToBackStack(null);
				transaction.commit();
				reportIsChecked = isChecked;
			}
		});
		
		

	}
	
	public void constructEventObject(){
		
	}
}
