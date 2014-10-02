package com.nanospark.cnc;

import java.util.ArrayList;
import java.util.Calendar;

import android.text.format.Time;

public class Event {
	String type;
	Pin assignedPin;
	String trackingType;
	Calendar startDate;
	Calendar endDate;
	boolean watchAllDay;
	Time startTime;
	Time endTime;
	ArrayList<String> selectedDays = new ArrayList<String>();
	ContactInfo selectedContact;
	String formatChoice;
	TimingConfig reportTimingConfigs;

	public Event(String type, Pin assignedPin, String trackingType,
			Calendar startDate, Calendar endDate, boolean watchAllDay,
			Time startTime, Time endTime, ArrayList<String> selectedDays,
			ContactInfo selectedContact, String formatChoice, TimingConfig reportTimingConfig) {
		this.type = type;
		this.assignedPin = assignedPin;
		this.trackingType = trackingType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.watchAllDay = watchAllDay;
		this.startTime = startTime;
		this.endTime = endTime;
		this.selectedDays = selectedDays;
		this.selectedContact = selectedContact;
		this.formatChoice = formatChoice;
		this.reportTimingConfigs = reportTimingConfigs;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Pin getAssignedPin() {
		return assignedPin;
	}

	public void setAssignedPin(Pin assignedPin) {
		this.assignedPin = assignedPin;
	}

	public String getTrackingType() {
		return trackingType;
	}

	public void setTrackingType(String trackingType) {
		this.trackingType = trackingType;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public boolean isWatchAllDay() {
		return watchAllDay;
	}

	public void setWatchAllDay(boolean watchAllDay) {
		this.watchAllDay = watchAllDay;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public ArrayList<String> getSelectedDays() {
		return selectedDays;
	}

	public void setSelectedDays(ArrayList<String> selectedDays) {
		this.selectedDays = selectedDays;
	}

	public ContactInfo getSelectedContact() {
		return selectedContact;
	}

	public void setSelectedContact(ContactInfo selectedContact) {
		this.selectedContact = selectedContact;
	}

	public String getFormatChoice() {
		return formatChoice;
	}

	public void setFormatChoice(String formatChoice) {
		this.formatChoice = formatChoice;
	}

	public TimingConfig getReportTimingConfigs() {
		return reportTimingConfigs;
	}

	public void setReportTimingConfigs(TimingConfig reportTimingConfigs) {
		this.reportTimingConfigs = reportTimingConfigs;
	}
	
	

}