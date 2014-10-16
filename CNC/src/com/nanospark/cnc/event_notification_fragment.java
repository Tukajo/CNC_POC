package com.nanospark.cnc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

public class event_notification_fragment extends Fragment{

	Button input;
	Button NotifyWhen;
	Button ChangeExistsFor;
	Button Contacts;
	CheckBox Email;
	CheckBox Text;
	Button Message;
	ToggleButton AllDay;
	Button StartTime;
	Button EndTime;
	Button Days;
	Button Create;
	Button Cancel;
	
	// Current date information
	final Calendar c = Calendar.getInstance();
	int mYear;
	int mMonth;
	int mDay;
	int mHour;
	int mMinute;
	int mSecond;
	int pinNumber;
	int dayToSendNum = 0;
	boolean EmailCheck = false;
	boolean TextCheck = false;
	boolean allDayToggle = false;
	String message = "";
	ArrayList<String> activeDaysArrayList = new ArrayList<String>();
	ArrayList<ContactInfo> activeContactsArrayList = new ArrayList<ContactInfo>();
	GlobalData globaldata = GlobalData.getInstance();
	View rootView;

	// set the startdate to default to today.
	LocalDate startDate = LocalDate.now();
	LocalDate endDate = new LocalDate();
	LocalTime startTime = LocalTime.now();
	LocalTime endTime = new LocalTime();
	String startTimeText = "";
	String endTimeText = "";
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.event_creation_notification_fragment,
				container, false);
		
		input = (Button) rootView.findViewById(R.id.notificationinputbutton);
		NotifyWhen = (Button) rootView.findViewById(R.id.notifywhenbutton);
		ChangeExistsFor = (Button) rootView.findViewById(R.id.changeexistsforbutton);
		Contacts = (Button) rootView.findViewById(R.id.contactsbutton);
		Email = (CheckBox) rootView.findViewById(R.id.emailcheckbox);
		Text = (CheckBox) rootView.findViewById(R.id.textcheckbox);
		Message = (Button) rootView.findViewById(R.id.messagebutton);
		AllDay = (ToggleButton) rootView.findViewById(R.id.alldaytoggle);
		StartTime = (Button) rootView.findViewById(R.id.starttimebutton);
		EndTime = (Button) rootView.findViewById(R.id.endtimebutton);
		Days = (Button) rootView.findViewById(R.id.daysbutton);
		Create = (Button) rootView.findViewById(R.id.createbutton);
		Cancel = (Button) rootView.findViewById(R.id.cancelbutton);
		
		Cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent myCancelEventCreationIntent = new Intent(getActivity()
						.getBaseContext(), MainActivity.class);
				startActivity(myCancelEventCreationIntent);
			}
		});
		
		// Collect all the information and make an report event object
		Create.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// Return to the main activity.
				Intent myReturningIntent = new Intent(getActivity()
						.getBaseContext(), MainActivity.class);
				startActivity(myReturningIntent);

			}
		});
		
		input.setOnClickListener(new View.OnClickListener() {

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
								input.setText("Input #: "
										+ (position + 1));
								pinNumber = position + 1;
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
		
		NotifyWhen.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.notify_when_dialog);
				dialog.setTitle("Select Stuff");

				Button button1 = (Button) dialog.findViewById(R.id.button1);
				EditText editText1 = (EditText) dialog.findViewById(R.id.editText1);
				final Spinner spinner2 = (Spinner) dialog
						.findViewById(R.id.spinner2);


				
			}
		});
		
		Contacts.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.generic_selection_wheel);
				dialog.setTitle("Select a contact");
				final ArrayAdapter<ContactInfo> contactSelectionAdapter = new ArrayAdapter<ContactInfo>(
						getActivity().getBaseContext(),
						android.R.layout.simple_spinner_item, globaldata
								.getContactInfoList());

				contactSelectionAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				Button dialogButtonOK = (Button) dialog
						.findViewById(R.id.dialogButtonOK);
				final Spinner selectionWheel = (Spinner) dialog
						.findViewById(R.id.genericselectionspinner);

				selectionWheel.setAdapter(contactSelectionAdapter);

				selectionWheel
						.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent,
									View view, int position, long id) {
								// check to see if the contact has already been
								// selected, if not add it. If it has do
								// nothing.

								if (!activeContactsArrayList
										.contains(globaldata
												.getContactInfoList().get(
														position))) {
									activeContactsArrayList
											.add(globaldata
													.getContactInfoList().get(
															position));

								} else if (activeContactsArrayList
										.contains(globaldata
												.getContactInfoList().get(
														position))) {
									activeContactsArrayList.remove(position);

								}
								Contacts
										.setText(activeContactsArrayList.size()
												+ " contacts selected");
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

		Email.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				EmailCheck = isChecked;
			}
		});		
		
		Text.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				TextCheck = isChecked;
			}
		});		
		
		Message.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

				alert.setTitle("Message");

				// Set an EditText view to get user input 
				final EditText input = new EditText(getActivity());
				input.setText(message);
				alert.setView(input);

				alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int whichButton) {

						message = input.getText().toString();
					}
				});
				alert.show();
			}
		});
		
		AllDay.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				allDayToggle = isChecked;
			}
		});
		
		StartTime.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				LocalTime currentTime = LocalTime.now();
				startTime = LocalTime.now();
				final DateTimeFormatter fmt = DateTimeFormat
						.forPattern("hh:mm aa");
				// custom dialog
				final TimePickerDialog dialog = new TimePickerDialog(
						getActivity(),
						new TimePickerDialog.OnTimeSetListener() {

							@Override
							public void onTimeSet(TimePicker view,
									int hourOfDay, int minute) {
								startTime = new LocalTime(hourOfDay, minute);
								startTimeText = startTime.toString(fmt);
								StartTime.setText("Start time: "
										+ startTimeText);
							};

						}, currentTime.getHourOfDay(), currentTime
								.getMinuteOfHour(), false);

				dialog.show();

			}
		});

		EndTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				LocalTime currentTime = startTime;
				final DateTimeFormatter fmt = DateTimeFormat
						.forPattern("hh:mm aa");
				// custom dialog
				final TimePickerDialog dialog = new TimePickerDialog(
						getActivity(),
						new TimePickerDialog.OnTimeSetListener() {

							@Override
							public void onTimeSet(TimePicker view,
									int hourOfDay, int minute) {
								endTime = new LocalTime(hourOfDay, minute);
								endTimeText = endTime.toString(fmt);
								EndTime.setText("End time: " + endTimeText);
							};

						}, currentTime.getHourOfDay(), currentTime
								.getMinuteOfHour(), false);

				dialog.show();

			}
		});
		
		Days.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// custom dialog
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.day_picker_dialog);
				dialog.setTitle("Select applicable days");

				Button submitBtn = (Button) dialog.findViewById(R.id.submitBtn);
				CheckBox mondayCB = (CheckBox) dialog
						.findViewById(R.id.mondayCB);
				CheckBox tuesdayCB = (CheckBox) dialog
						.findViewById(R.id.tuesdayCB);
				CheckBox wednesdayCB = (CheckBox) dialog
						.findViewById(R.id.wednesdayCB);
				CheckBox thursdayCB = (CheckBox) dialog
						.findViewById(R.id.thursdayCB);
				CheckBox fridayCB = (CheckBox) dialog
						.findViewById(R.id.fridayCB);
				CheckBox saturdayCB = (CheckBox) dialog
						.findViewById(R.id.saturdayCB);
				CheckBox sundayCB = (CheckBox) dialog
						.findViewById(R.id.sundayCB);
				mondayCB.setChecked(false);
				tuesdayCB.setChecked(false);
				wednesdayCB.setChecked(false);
				thursdayCB.setChecked(false);
				fridayCB.setChecked(false);
				saturdayCB.setChecked(false);
				sundayCB.setChecked(false);
				for (int i = 0; i < activeDaysArrayList.size(); i++) {
					if (activeDaysArrayList.get(i).equals("Sunday")) {
						sundayCB.setChecked(true);
					} else if (activeDaysArrayList.get(i).equals("Monday")) {
						mondayCB.setChecked(true);
					} else if (activeDaysArrayList.get(i).equals("Tuesday")) {
						tuesdayCB.setChecked(true);
					} else if (activeDaysArrayList.get(i).equals("Wednesday")) {
						wednesdayCB.setChecked(true);
					} else if (activeDaysArrayList.get(i).equals("Thursday")) {
						thursdayCB.setChecked(true);
					} else if (activeDaysArrayList.get(i).equals("Friday")) {
						fridayCB.setChecked(true);
					} else if (activeDaysArrayList.get(i).equals("Saturday")) {
						saturdayCB.setChecked(true);
					}
				}
				mondayCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							activeDaysArrayList.add("Monday");
						} else {
							activeDaysArrayList.remove(activeDaysArrayList
									.indexOf("Monday"));
						}

					}
				});
				tuesdayCB
						.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

							@Override
							public void onCheckedChanged(
									CompoundButton buttonView, boolean isChecked) {
								if (isChecked) {
									activeDaysArrayList.add("Tuesday");
								} else {
									activeDaysArrayList
											.remove(activeDaysArrayList
													.indexOf("Tuesday"));
								}
							}
						});
				wednesdayCB
						.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

							@Override
							public void onCheckedChanged(
									CompoundButton buttonView, boolean isChecked) {
								if (isChecked) {
									activeDaysArrayList.add("Wednesday");
								} else {
									activeDaysArrayList
											.remove(activeDaysArrayList
													.indexOf("Wednesday"));
								}
							}
						});
				thursdayCB
						.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

							@Override
							public void onCheckedChanged(
									CompoundButton buttonView, boolean isChecked) {
								if (isChecked) {
									activeDaysArrayList.add("Thursday");
								} else {
									activeDaysArrayList
											.remove(activeDaysArrayList
													.indexOf("Thursday"));
								}

							}
						});
				fridayCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							activeDaysArrayList.add("Friday");
						} else {
							activeDaysArrayList.remove(activeDaysArrayList
									.indexOf("Friday"));
						}
					}
				});
				saturdayCB
						.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

							@Override
							public void onCheckedChanged(
									CompoundButton buttonView, boolean isChecked) {
								if (isChecked) {
									activeDaysArrayList.add("Saturday");
								} else {
									activeDaysArrayList
											.remove(activeDaysArrayList
													.indexOf("Saturday"));
								}
							}
						});
				sundayCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						if (isChecked) {
							// Toast.makeText(getActivity().getBaseContext(),"Sunday Added",Toast.LENGTH_SHORT).show();;
							activeDaysArrayList.add("Sunday");
						} else {
							// Toast.makeText(getActivity().getBaseContext(),"Sunday removed",Toast.LENGTH_SHORT).show();
							activeDaysArrayList.remove(activeDaysArrayList
									.indexOf("Sunday"));
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

		
		return rootView;
	}
	
}
