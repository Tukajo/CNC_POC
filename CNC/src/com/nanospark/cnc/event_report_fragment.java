package com.nanospark.cnc;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class event_report_fragment extends Fragment {

	Button reportInputBtn;
	Button whatToTrackBtn;
	Button startDateBtn;
	Button endDateBtn;
	ToggleButton allDayTB;
	Button startTimeBtn;
	Button endTimeBtn;
	Button selectDaysBtn;
	Button selectContactsBtn;
	Button selectFormatBtn;
	Button whenToSendBtn;
	ToggleButton repeatThisReportTB;
	
	View rootView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.event_creation_report_fragment, container,false);
		
		reportInputBtn = (Button) rootView.findViewById(R.id.reportinputbutton);
		whatToTrackBtn = (Button) rootView.findViewById(R.id.whattotrackbutton);
		startDateBtn = (Button) rootView.findViewById(R.id.reportstartdatebutton);
		endDateBtn = (Button) rootView.findViewById(R.id.enddatereportbutton);
		allDayTB = (ToggleButton) rootView.findViewById(R.id.alldayreporttoggle);
		startTimeBtn = (Button) rootView.findViewById(R.id.starttimereportbutton);
		endTimeBtn = (Button) rootView.findViewById(R.id.endtimereportbutton);
		selectDaysBtn = (Button) rootView.findViewById(R.id.dayselectionreportbutton);
		selectContactsBtn = (Button) rootView.findViewById(R.id.selectcontactsreportbutton);
		selectFormatBtn = (Button) rootView.findViewById(R.id.selectformatreportbutton);
		whenToSendBtn = (Button) rootView.findViewById(R.id.whentosendreportbutton);
		repeatThisReportTB = (ToggleButton) rootView.findViewById(R.id.repeatreporttoggle);
		
		reportInputBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// custom dialog
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.generic_selection_wheel);
				dialog.setTitle("Select a report input pin");
				ArrayAdapter<CharSequence> pinSpinnerAdapter = ArrayAdapter
						.createFromResource(getActivity().getBaseContext(), R.array.pin_choices,
								android.R.layout.simple_spinner_item);
				pinSpinnerAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			
				Button dialogButtonOK = (Button) dialog.findViewById(R.id.dialogButtonOK);
				Spinner selectionWheel = (Spinner) dialog.findViewById(R.id.genericselectionspinner);
				
				selectionWheel.setAdapter(pinSpinnerAdapter);

				selectionWheel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						//Change the text of the button to fit which selection the user has made.
						reportInputBtn.setText("Input #: " + position+1);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						
						
					}
					
				});
			
				// if button is clicked, close the custom dialog
				dialogButtonOK.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
	 
				dialog.show();
			}
		});
		
		whatToTrackBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				// custom dialog
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.generic_selection_wheel);
				dialog.setTitle("Select a report input pin");
				final ArrayAdapter<CharSequence> whatToTrackSpinnerAdapter = ArrayAdapter
						.createFromResource(getActivity().getBaseContext(), R.array.what_to_track_array,
								android.R.layout.simple_spinner_item);
				whatToTrackSpinnerAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			
				Button dialogButtonOK = (Button) dialog.findViewById(R.id.dialogButtonOK);
				Spinner selectionWheel = (Spinner) dialog.findViewById(R.id.genericselectionspinner);
				
				selectionWheel.setAdapter(whatToTrackSpinnerAdapter);

				selectionWheel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						//Change the name of the button to fit what was selected.
						whatToTrackBtn.setText("What to Track: " + whatToTrackSpinnerAdapter.getItem(position));
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
						
					}
					
				});
			
				// if button is clicked, close the custom dialog
				dialogButtonOK.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
	 
				dialog.show();
			}	
			
		});
		
		startDateBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {		
			}
		});
		
		endDateBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		allDayTB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			
			}
		});
		
		startTimeBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		endTimeBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		selectDaysBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		selectContactsBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		selectFormatBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		});
		
		whenToSendBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

			}
		});
		
		repeatThisReportTB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				
			}
		});
		
		
		return rootView;
	}

}