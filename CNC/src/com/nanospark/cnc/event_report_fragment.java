package com.nanospark.cnc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
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
	//Current date information
	final Calendar c = Calendar.getInstance();
	int mYear;
	int mMonth;
	int mDay;
	int mHour;
	int mMinute;
	int mSecond;
	boolean allDayToggle = false;
	ArrayList<String> activeDaysArrayList = new ArrayList<String>();
	ArrayList<ContactInfo> activeContactsArrayList = new ArrayList<ContactInfo>();
	GlobalData globaldata = GlobalData.getInstance();
	View rootView;
	Time startDate = new Time();
	Time endDate = new Time();
	Time startTime = new Time();
	Time endTime = new Time();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.event_creation_report_fragment,
				container, false);
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		mHour = c.get(Calendar.HOUR);
		mMinute = c.get(Calendar.MINUTE);
		mSecond = c.get(Calendar.SECOND);
		
		
		
		startDate.set(mDay,mMonth,mYear);

		reportInputBtn = (Button) rootView.findViewById(R.id.reportinputbutton);
		whatToTrackBtn = (Button) rootView.findViewById(R.id.whattotrackbutton);
		startDateBtn = (Button) rootView
				.findViewById(R.id.reportstartdatebutton);
		endDateBtn = (Button) rootView.findViewById(R.id.enddatereportbutton);
		allDayTB = (ToggleButton) rootView
				.findViewById(R.id.alldayreporttoggle);
		startTimeBtn = (Button) rootView
				.findViewById(R.id.starttimereportbutton);
		endTimeBtn = (Button) rootView.findViewById(R.id.endtimereportbutton);
		selectDaysBtn = (Button) rootView
				.findViewById(R.id.dayselectionreportbutton);
		selectContactsBtn = (Button) rootView
				.findViewById(R.id.selectcontactsreportbutton);
		selectFormatBtn = (Button) rootView
				.findViewById(R.id.selectformatreportbutton);
		whenToSendBtn = (Button) rootView
				.findViewById(R.id.whentosendreportbutton);
		repeatThisReportTB = (ToggleButton) rootView
				.findViewById(R.id.repeatreporttoggle);

		startDateBtn.setText("Start Date: "
				+ startDate.monthDay + "-" + (startDate.month + 1)
				+ "-" + startDate.year);
		
		reportInputBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// custom dialog
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.generic_selection_wheel);
				dialog.setTitle("Select a report input pin");
				ArrayAdapter<CharSequence> pinSpinnerAdapter = ArrayAdapter
						.createFromResource(getActivity().getBaseContext(),
								R.array.pin_choices,
								android.R.layout.simple_spinner_item);
				pinSpinnerAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				Button dialogButtonOK = (Button) dialog
						.findViewById(R.id.dialogButtonOK);
				Spinner selectionWheel = (Spinner) dialog
						.findViewById(R.id.genericselectionspinner);

				selectionWheel.setAdapter(pinSpinnerAdapter);

				selectionWheel
						.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent,
									View view, int position, long id) {
								// Change the text of the button to fit which
								// selection the user has made.
								reportInputBtn.setText("Input #: " + position
										+ 1);
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
				dialog.setTitle("Select what to track");
				final ArrayAdapter<CharSequence> whatToTrackSpinnerAdapter = ArrayAdapter
						.createFromResource(getActivity().getBaseContext(),
								R.array.what_to_track_array,
								android.R.layout.simple_spinner_item);
				whatToTrackSpinnerAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				Button dialogButtonOK = (Button) dialog
						.findViewById(R.id.dialogButtonOK);
				Spinner selectionWheel = (Spinner) dialog
						.findViewById(R.id.genericselectionspinner);

				selectionWheel.setAdapter(whatToTrackSpinnerAdapter);

				selectionWheel
						.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent,
									View view, int position, long id) {
								// Change the name of the button to fit what was
								// selected.
								whatToTrackBtn.setText("What to Track: "
										+ whatToTrackSpinnerAdapter
												.getItem(position));
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

				mYear = c.get(Calendar.YEAR);
				mMonth = c.get(Calendar.MONTH);
				mDay = c.get(Calendar.DAY_OF_MONTH);

				// Construct the datepicker dialog using the parameters.
				// mYear,mMonth and mDay are all for the current date.
				final DatePickerDialog dpd = new DatePickerDialog(
						getActivity(),
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								startDateBtn.setText("Start Date: "
										+ dayOfMonth + "-" + (monthOfYear + 1)
										+ "-" + year);

								startDate.set(dayOfMonth, monthOfYear, year);
							}
						}, mYear, mMonth, mDay);
				dpd.getDatePicker().setMinDate(c.getTimeInMillis());
				dpd.show();

			}
		});

		endDateBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// Retrieve the current date to start off the datepicker dialog
				// correctly.

				mYear = startDate.year;
				mMonth = startDate.month;
				mDay = startDate.monthDay;


				// Construct the datepicker dialog using the parameters.
				// mYear,mMonth and mDay are all for the current date.
				final DatePickerDialog dpd = new DatePickerDialog(
						getActivity(),
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {
								endDate.set(dayOfMonth, monthOfYear, year);
								endDateBtn.setText("End Date: " + dayOfMonth
										+ "-" + (monthOfYear + 1) + "-" + year);
							}
						}, mYear, mMonth, mDay);
				dpd.getDatePicker().setMinDate(startDate.toMillis(false));

				dpd.show();
			}
		});

		
		
		allDayTB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				allDayToggle = isChecked;
			}
		});

		startTimeBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				

				// custom dialog
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.time_picker_dialog);
				dialog.setTitle("Select starting time");
			
				Button setBtn = (Button) dialog.findViewById(R.id.setButton);
				Button cancelBtn = (Button) dialog.findViewById(R.id.cancelButton);
				final NumberPicker hrPkr = (NumberPicker) dialog.findViewById(R.id.hourPicker);
				final NumberPicker minPkr = (NumberPicker) dialog.findViewById(R.id.minutePicker);
				final NumberPicker secPkr = (NumberPicker) dialog.findViewById(R.id.secondPicker);
				hrPkr.setMaxValue(23);
				hrPkr.setMinValue(0);
				minPkr.setMaxValue(59);
				minPkr.setMinValue(0);
				secPkr.setMaxValue(59);
				secPkr.setMinValue(0);
				int currentSec = 0;
				int currentMin = 0;
				int currentHour = 0;

				final Calendar myTimeSelection = Calendar.getInstance();
				myTimeSelection.set(Calendar.SECOND, currentSec);
				myTimeSelection.set(Calendar.MINUTE, currentMin);
				myTimeSelection.set(Calendar.HOUR, currentHour);
				
				final TextView timeText = (TextView) dialog.findViewById(R.id.timetxt);
				timeText.setText(myTimeSelection.get(Calendar.HOUR_OF_DAY)+":"+myTimeSelection.get(Calendar.MINUTE)+":"+myTimeSelection.get(Calendar.SECOND));
				
				hrPkr.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
					
					@Override
					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {;
						myTimeSelection.set(Calendar.HOUR_OF_DAY, newVal);
						
						timeText.setText(myTimeSelection.get(Calendar.HOUR_OF_DAY)+":"+myTimeSelection.get(Calendar.MINUTE)+":"+myTimeSelection.get(Calendar.SECOND));
					}
				});
				minPkr.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
					
					@Override
					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
						myTimeSelection.set(Calendar.MINUTE, newVal);
						timeText.setText(myTimeSelection.get(Calendar.HOUR_OF_DAY)+":"+myTimeSelection.get(Calendar.MINUTE)+":"+myTimeSelection.get(Calendar.SECOND));
						
					}
				});
				secPkr.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
					
					@Override
					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
						myTimeSelection.set(Calendar.SECOND, newVal);
						timeText.setText(myTimeSelection.get(Calendar.HOUR_OF_DAY)+":"+myTimeSelection.get(Calendar.MINUTE)+":"+myTimeSelection.get(Calendar.SECOND));
						
					}
				});
				
				
				setBtn.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						startTime.set(secPkr.getValue(), minPkr.getValue(), hrPkr.getValue(), mDay, mMonth, mYear);
						startTimeBtn.setText("Start time: " + myTimeSelection.get(Calendar.HOUR_OF_DAY)+":"+myTimeSelection.get(Calendar.MINUTE)+":"+myTimeSelection.get(Calendar.SECOND));
						dialog.dismiss();
					}
				});

				// if button is clicked, close the custom dialog
				cancelBtn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startTimeBtn.setText("Start time: " + myTimeSelection.get(Calendar.HOUR_OF_DAY)+":"+myTimeSelection.get(Calendar.MINUTE)+":"+myTimeSelection.get(Calendar.SECOND));
						dialog.dismiss();
					}
				});

				dialog.show();
			}
		});

		endTimeBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// custom dialog
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.time_picker_dialog);
				dialog.setTitle("Select starting time");
			
				Button setBtn = (Button) dialog.findViewById(R.id.setButton);
				Button cancelBtn = (Button) dialog.findViewById(R.id.cancelButton);
				final NumberPicker hrPkr = (NumberPicker) dialog.findViewById(R.id.hourPicker);
				final NumberPicker minPkr = (NumberPicker) dialog.findViewById(R.id.minutePicker);
				final NumberPicker secPkr = (NumberPicker) dialog.findViewById(R.id.secondPicker);
				hrPkr.setMaxValue(23);
				hrPkr.setMinValue(0);
				minPkr.setMaxValue(59);
				minPkr.setMinValue(0);
				secPkr.setMaxValue(59);
				secPkr.setMinValue(0);
				int currentSec = 0;
				int currentMin = 0;
				int currentHour = 0;

				final Calendar myTimeSelection = Calendar.getInstance();
				myTimeSelection.set(Calendar.SECOND, currentSec);
				myTimeSelection.set(Calendar.MINUTE, currentMin);
				myTimeSelection.set(Calendar.HOUR, currentHour);
				
				final TextView timeText = (TextView) dialog.findViewById(R.id.timetxt);
				timeText.setText(myTimeSelection.get(Calendar.HOUR_OF_DAY)+":"+myTimeSelection.get(Calendar.MINUTE)+":"+myTimeSelection.get(Calendar.SECOND));
				
				hrPkr.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
					
					@Override
					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {;
						myTimeSelection.set(Calendar.HOUR_OF_DAY, newVal);
						
						timeText.setText(myTimeSelection.get(Calendar.HOUR_OF_DAY)+":"+myTimeSelection.get(Calendar.MINUTE)+":"+myTimeSelection.get(Calendar.SECOND));
					}
				});
				minPkr.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
					
					@Override
					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
						myTimeSelection.set(Calendar.MINUTE, newVal);
						timeText.setText(myTimeSelection.get(Calendar.HOUR_OF_DAY)+":"+myTimeSelection.get(Calendar.MINUTE)+":"+myTimeSelection.get(Calendar.SECOND));
						
					}
				});
				secPkr.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
					
					@Override
					public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
						myTimeSelection.set(Calendar.SECOND, newVal);
						timeText.setText(myTimeSelection.get(Calendar.HOUR_OF_DAY)+":"+myTimeSelection.get(Calendar.MINUTE)+":"+myTimeSelection.get(Calendar.SECOND));
						
					}
				});
				
				
				setBtn.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						endTime.set(secPkr.getValue(), minPkr.getValue(), hrPkr.getValue(), mDay, mMonth, mYear);
						endTimeBtn.setText("End time: " + myTimeSelection.get(Calendar.HOUR_OF_DAY)+":"+myTimeSelection.get(Calendar.MINUTE)+":"+myTimeSelection.get(Calendar.SECOND));
						dialog.dismiss();
					}
				});

				// if button is clicked, close the custom dialog
				cancelBtn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						startTimeBtn.setText("End time: " + myTimeSelection.get(Calendar.HOUR_OF_DAY)+":"+myTimeSelection.get(Calendar.MINUTE)+":"+myTimeSelection.get(Calendar.SECOND));
						dialog.dismiss();
					}
				});

				dialog.show();
			}
		});

		selectDaysBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// custom dialog
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.day_picker_dialog);
				dialog.setTitle("Select applicable days");
			
				Button submitBtn = (Button) dialog.findViewById(R.id.submitBtn);
				CheckBox mondayCB = (CheckBox) dialog.findViewById(R.id.mondayCB);
				CheckBox tuesdayCB = (CheckBox) dialog.findViewById(R.id.tuesdayCB);
				CheckBox wednesdayCB = (CheckBox) dialog.findViewById(R.id.wednesdayCB);
				CheckBox thursdayCB = (CheckBox) dialog.findViewById(R.id.thursdayCB);
				CheckBox fridayCB = (CheckBox) dialog.findViewById(R.id.fridayCB);
				CheckBox saturdayCB = (CheckBox) dialog.findViewById(R.id.saturdayCB);
				CheckBox sundayCB = (CheckBox) dialog.findViewById(R.id.sundayCB);
				
				mondayCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(isChecked){
							activeDaysArrayList.add("Monday");
						}else{
							activeDaysArrayList.remove(activeDaysArrayList.indexOf("Monday"));
						}
						
					}
				});
				tuesdayCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(isChecked){
							activeDaysArrayList.add("Tuesday");
						}else{
							activeDaysArrayList.remove(activeDaysArrayList.indexOf("Tuesday"));
						}
					}
				});
				wednesdayCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(isChecked){
							activeDaysArrayList.add("Wednesday");
						}else{
							activeDaysArrayList.remove(activeDaysArrayList.indexOf("Wednesday"));
						}
					}
				});
				thursdayCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(isChecked){
							activeDaysArrayList.add("Thursday");
						}else{
							activeDaysArrayList.remove(activeDaysArrayList.indexOf("Thursday"));
						}
						
					}
				});
				fridayCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(isChecked){
							activeDaysArrayList.add("Friday");
						}else{
							activeDaysArrayList.remove(activeDaysArrayList.indexOf("Friday"));
						}
					}
				});
				saturdayCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(isChecked){
							activeDaysArrayList.add("Saturday");
						}else{
							activeDaysArrayList.remove(activeDaysArrayList.indexOf("Saturday"));
						}
					}
				});
				sundayCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if(isChecked){
							activeDaysArrayList.add("Sunday");
						}else{
							activeDaysArrayList.remove(activeDaysArrayList.indexOf("Sunday"));
						}
					}
				});
				
				submitBtn.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});

				dialog.show();
			}
		});

		selectContactsBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.generic_selection_wheel);
				dialog.setTitle("Select a contact");
				ArrayAdapter<ContactInfo> contactSelectionAdapter = new ArrayAdapter<ContactInfo>(getActivity().getBaseContext(),
								android.R.layout.simple_spinner_item,globaldata.getContactInfoList());
				
				contactSelectionAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				Button dialogButtonOK = (Button) dialog
						.findViewById(R.id.dialogButtonOK);
				Spinner selectionWheel = (Spinner) dialog
						.findViewById(R.id.genericselectionspinner);

				selectionWheel.setAdapter(contactSelectionAdapter);

				selectionWheel
						.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent,
									View view, int position, long id){
									//check to see if the contact has already been selected, if not add it. If it has do nothing.
									if(!activeContactsArrayList.contains(globaldata.getContactInfoList().get(position))){
										activeContactsArrayList.add(globaldata.getContactInfoList().get(position));
										Log.v("Event Contact Add", "Contact not found, adding to list!");
									}else{
										Log.v("Event Contact Found", "Contact match found, not re-adding to list.");
									}
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

		selectFormatBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
					final Dialog dialog = new Dialog(getActivity());
					dialog.setContentView(R.layout.generic_selection_wheel);
					dialog.setTitle("Select a contact");
					ArrayAdapter<ContactInfo> contactSelectionAdapter = new ArrayAdapter<ContactInfo>(getActivity().getBaseContext(),
									android.R.layout.simple_spinner_item,globaldata.getContactInfoList());
					
					contactSelectionAdapter
							.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

					Button dialogButtonOK = (Button) dialog
							.findViewById(R.id.dialogButtonOK);
					Spinner selectionWheel = (Spinner) dialog
							.findViewById(R.id.genericselectionspinner);

					selectionWheel.setAdapter(contactSelectionAdapter);

					selectionWheel
							.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

								@Override
								public void onItemSelected(AdapterView<?> parent,
										View view, int position, long id){
										//check to see if the contact has already been selected, if not add it. If it has do nothing.
										if(!activeContactsArrayList.contains(globaldata.getContactInfoList().get(position))){
											activeContactsArrayList.add(globaldata.getContactInfoList().get(position));
											Log.v("Event Contact Add", "Contact not found, adding to list!");
										}else{
											Log.v("Event Contact Found", "Contact match found, not re-adding to list.");
										}
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

		whenToSendBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

			}
		});

		repeatThisReportTB
				.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {

					}
				});

		return rootView;
	}

}
