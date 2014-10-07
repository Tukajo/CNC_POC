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

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
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
	Button createEventBtn;
	Button cancelEventBtn;
	ToggleButton repeatThisReportTB;
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
	String whatToTrackSelection;
	ReportTimeSpec myReportTimeSpec;
	String frequencyTypeChosen = "Hourly"; // hourly by default.
	boolean allDayToggle = false;
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.event_creation_report_fragment,
				container, false);
		/*
		 * mYear = startDate.getYear(); mMonth = startDate.getMonthOfYear();
		 * mDay = startDate.getDayOfMonth(); mHour = startTime.getHourOfDay();
		 * mMinute = startTime.getMinuteOfHour(); mSecond =
		 * startTime.getSecondOfMinute();
		 */

		createEventBtn = (Button) rootView
				.findViewById(R.id.eventreportbuttoncreate);
		cancelEventBtn = (Button) rootView
				.findViewById(R.id.eventreportbuttoncancel);
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

		startDateBtn.setText("Start Date: " + (startDate.getMonthOfYear())
				+ "-" + (startDate.getDayOfMonth() - 1) + "-"
				+ startDate.getYear());

		// Cancel the event creation screen if the user wants to.
		cancelEventBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent myCancelEventCreationIntent = new Intent(getActivity()
						.getBaseContext(), MainActivity.class);
				startActivity(myCancelEventCreationIntent);
			}
		});
		// Collect all the information and make an report event object
		createEventBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				globaldata.getEventInfoList().add(
						new ReportEvent(((EventCreateActivity) getActivity())
								.getEventTitle(),
								((EventCreateActivity) getActivity())
										.getEventDescription(), pinNumber,
								whatToTrackSelection, startDate, endDate,
								allDayToggle, startTime, endTime,
								activeDaysArrayList, activeContactsArrayList,
								frequencyTypeChosen, myReportTimeSpec));

				// Return to the main activity.
				Intent myReturningIntent = new Intent(getActivity()
						.getBaseContext(), MainActivity.class);
				startActivity(myReturningIntent);

			}
		});

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
								reportInputBtn.setText("Input #: "
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
								whatToTrackSelection = (String) whatToTrackSpinnerAdapter
										.getItem(position);
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

				// Construct the datepicker dialog using the parameters.
				// mYear,mMonth and mDay are all for the current date.
				final DatePickerDialog dpd = new DatePickerDialog(
						getActivity(),
						new DatePickerDialog.OnDateSetListener() {

							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {

								startDate = new LocalDate(year,
										monthOfYear + 1, dayOfMonth);
								DateTimeFormatter fmt = DateTimeFormat
										.forPattern("dd MMMM, yyyy");
								String formatDate = startDate.toString(fmt);
								startDateBtn.setText("Start Date: "
										+ formatDate);

							}
						}, LocalDate.now().getYear(), LocalDate.now()
								.getMonthOfYear() - 1, LocalDate.now()
								.getDayOfMonth() - 1);
				/*
				 * dpd.getDatePicker().setMinDate(
				 * startDate.toDateTimeAtStartOfDay().getMillis());
				 */
				dpd.show();

			}
		});

		endDateBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// Retrieve the current date to start off the datepicker dialog
				// correctly.

				/*
				 * mYear = startDate.getYear(); mMonth =
				 * startDate.getMonthOfYear(); mDay = startDate.getDayOfMonth();
				 */
				// Construct the datepicker dialog using the parameters.
				// mYear,mMonth and mDay are all for the current date.
				final DatePickerDialog dpd1 = new DatePickerDialog(
						getActivity(),
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, int year,
									int monthOfYear, int dayOfMonth) {

								endDate = new LocalDate(year, monthOfYear + 1,
										dayOfMonth);
								DateTimeFormatter fmt = DateTimeFormat
										.forPattern("dd MMMM, yyyy");
								String formatDate = endDate.toString(fmt);
								endDateBtn.setText("End Date: " + formatDate);

							}
						}, startDate.getYear(), startDate.getMonthOfYear() - 1,
						startDate.getDayOfMonth());
				/*
				 * dpd.getDatePicker().setMinDate(
				 * startDate.().toDateTimeAtStartOfDay().getMillis());
				 */

				dpd1.show();
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
								startTimeBtn.setText("Start time: "
										+ startTimeText);
							};

						}, currentTime.getHourOfDay(), currentTime
								.getMinuteOfHour(), false);

				dialog.show();

			}
		});

		endTimeBtn.setOnClickListener(new View.OnClickListener() {
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
								endTimeBtn.setText("End time: " + endTimeText);
							};

						}, currentTime.getHourOfDay(), currentTime
								.getMinuteOfHour(), false);

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

		selectContactsBtn.setOnClickListener(new View.OnClickListener() {

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
								selectContactsBtn
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

		selectFormatBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.generic_selection_wheel);
				dialog.setTitle("Select a contact");
				final ArrayAdapter<CharSequence> whatFormatSpinner = ArrayAdapter
						.createFromResource(getActivity().getBaseContext(),
								R.array.what_format_choice_array,
								android.R.layout.simple_spinner_item);
				;

				whatFormatSpinner
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				Button dialogButtonOK = (Button) dialog
						.findViewById(R.id.dialogButtonOK);
				Spinner selectionWheel = (Spinner) dialog
						.findViewById(R.id.genericselectionspinner);

				selectionWheel.setAdapter(whatFormatSpinner);

				selectionWheel
						.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent,
									View view, int position, long id) {
								// check to see if the contact has already been
								// selected, if not add it. If it has do
								// nothing.
								selectFormatBtn.setText("Format: "
										+ whatFormatSpinner.getItem(position));
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
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.report_send_timing_dialog);
				dialog.setTitle("When to send report");
				final ArrayAdapter<CharSequence> frequencySpinnerAdapter = ArrayAdapter
						.createFromResource(getActivity().getBaseContext(),
								R.array.when_to_send_report_array,
								android.R.layout.simple_spinner_item);

				/*
				 * final ArrayAdapter<CharSequence> timeToSendSpinnerAdapter =
				 * ArrayAdapter
				 * .createFromResource(getActivity().getBaseContext(),
				 * R.array.time_to_send_report_array,
				 * android.R.layout.simple_spinner_item);
				 */
				final ArrayAdapter<CharSequence> dateToSendSpinnerAdapter = ArrayAdapter
						.createFromResource(getActivity().getBaseContext(),
								R.array.dates_to_send_report_array,
								android.R.layout.simple_spinner_item);

				final ArrayAdapter<CharSequence> dayToSendSpinnerAdapter = ArrayAdapter
						.createFromResource(getActivity().getBaseContext(),
								R.array.days_of_the_week,
								android.R.layout.simple_spinner_item);

				/*
				 * timeToSendSpinnerAdapter
				 * .setDropDownViewResource(android.R.layout
				 * .simple_spinner_dropdown_item);
				 */
				frequencySpinnerAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				dateToSendSpinnerAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				dayToSendSpinnerAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

				Button submitBtn = (Button) dialog
						.findViewById(R.id.submitWhenToSendBtn);
				Spinner frequencySpinner = (Spinner) dialog
						.findViewById(R.id.freqspinner);
				/*
				 * final Spinner timeSpinner = (Spinner) dialog
				 * .findViewById(R.id.timespinner);
				 */
				final TimePicker myTimePicker = (TimePicker) dialog
						.findViewById(R.id.timePicker1);
				final Spinner dateToSendSpinner = (Spinner) dialog
						.findViewById(R.id.datespinner);
				final Spinner dayToSendSpinner = (Spinner) dialog
						.findViewById(R.id.dayspinner);

				dateToSendSpinner.setAdapter(dateToSendSpinnerAdapter);
				/* timeSpinner.setAdapter(timeToSendSpinnerAdapter); */
				frequencySpinner.setAdapter(frequencySpinnerAdapter);
				dayToSendSpinner.setAdapter(dayToSendSpinnerAdapter);
				

				dateToSendSpinner
						.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent,
									View view, int position, long id) {
								// TODO Auto-generated method stub
		

							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {
								// TODO Auto-generated method stub

							}
						});
				
				frequencySpinner
						.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent,
									View view, int position, long id) {
								switch (position) {
								case 0:
									myTimePicker.setEnabled(false);
									dateToSendSpinner.setEnabled(false);
									dayToSendSpinner.setEnabled(false);
									frequencyTypeChosen = "Hourly";
									break;
								case 1:
									myTimePicker.setEnabled(true);
									dateToSendSpinner.setEnabled(false);
									dayToSendSpinner.setEnabled(false);
									frequencyTypeChosen = "Daily";
									break;
								case 2:
									myTimePicker.setEnabled(true);
									dateToSendSpinner.setEnabled(false);
									dayToSendSpinner.setEnabled(true);
									frequencyTypeChosen = "Weekly";
									break;
								case 3:
									myTimePicker.setEnabled(true);
									dateToSendSpinner.setEnabled(true);
									dayToSendSpinner.setEnabled(false);
									frequencyTypeChosen = "Monthly";
									break;
								}

							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {

							}

						});
				dayToSendSpinner
						.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

							@Override
							public void onItemSelected(AdapterView<?> parent,
									View view, int position, long id) {
								// TODO Auto-generated method stub
								dayToSendNum = position;
							}

							@Override
							public void onNothingSelected(AdapterView<?> parent) {
								// TODO Auto-generated method stub

							}

						});

				// if button is clicked, close the custom dialog
				submitBtn.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						if (frequencyTypeChosen.equals("Hourly")) {

							myReportTimeSpec = new ReportTimeSpec("Hourly");
							whenToSendBtn.setText("When To Send: " + "Hourly");
						} else if (frequencyTypeChosen.equals("Daily")) {

							myReportTimeSpec = new ReportTimeSpec("Daily",
									new LocalTime(
											myTimePicker.getCurrentHour(),
											myTimePicker.getCurrentMinute()));

							whenToSendBtn.setText("When To Send: " + "Daily");

						} else if (frequencyTypeChosen.equals("Weekly")) {

							myReportTimeSpec = new ReportTimeSpec("Weekly",
									new LocalTime(
											myTimePicker.getCurrentHour(),
											myTimePicker.getCurrentMinute()),
									dayToSendNum);
							whenToSendBtn.setText("When To Send: " + "Weekly");
						} else {
							// Monthly
							if(dateToSendSpinner.getSelectedItem().equals("1st")){
								myReportTimeSpec = new ReportTimeSpec("Monthly",
										new LocalTime(
												myTimePicker.getCurrentHour(),
												myTimePicker.getCurrentMinute()),
												"1st");
							}else if(dateToSendSpinner.getSelectedItem().equals("15th")){
								
								myReportTimeSpec = new ReportTimeSpec("Monthly",
										new LocalTime(
												myTimePicker.getCurrentHour(),
												myTimePicker.getCurrentMinute()),
												"15th");
							}else if(dateToSendSpinner.getSelectedItem().equals("Last Day")){
								myReportTimeSpec = new ReportTimeSpec("Monthly",
										new LocalTime(
												myTimePicker.getCurrentHour(),
												myTimePicker.getCurrentMinute()),
												"Last Day");
							}else if(dateToSendSpinner.getSelectedItem().equals("First Monday")){
								myReportTimeSpec = new ReportTimeSpec("Monthly",
										new LocalTime(
												myTimePicker.getCurrentHour(),
												myTimePicker.getCurrentMinute()),
												"First Monday");
							}else //"Last Friday"
								{
								myReportTimeSpec = new ReportTimeSpec("Monthly",
										new LocalTime(
												myTimePicker.getCurrentHour(),
												myTimePicker.getCurrentMinute()),
												"Last Friday");
						
								}
						
							whenToSendBtn.setText("When To Send: " + "Monthly - " + myReportTimeSpec.getSendDateInfo());
						}

						dialog.dismiss();
					}
				});

				dialog.show();
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
