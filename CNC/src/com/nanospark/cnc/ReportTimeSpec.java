package com.nanospark.cnc;

import java.util.Calendar;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import android.text.format.Time;

public class ReportTimeSpec {
	String frequency;
	LocalTime sendTime;
	String sendDateInfo;
	int sendDay;
	//hourly
	public ReportTimeSpec(String frequency) {
		this.frequency = frequency;
	}
	
	//daily
	public ReportTimeSpec(String frequency, LocalTime sendTime) {
		this.frequency = frequency;
		this.sendTime = sendTime;
	}
	//weekly
	public ReportTimeSpec(String frequency, LocalTime sendTime, int sendDay) {
		this.frequency = frequency;
		this.sendTime = sendTime;
		this.sendDay = sendDay;
	}
	//monthly
	public ReportTimeSpec(String frequency, LocalTime sendTime, String sendDateInfo) {
		this.frequency = frequency;
		this.sendDateInfo = sendDateInfo;
		this.sendTime = sendTime;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public LocalTime getSendTime() {
		return sendTime;
	}
	public void setSendTime(LocalTime sendTime) {
		this.sendTime = sendTime;
	}
	public String getSendDateInfo() {
		return sendDateInfo;
	}
	public void setSendDateInfo(String sendDateInfo) {
		this.sendDateInfo = sendDateInfo;
	}

	public int getSendDay() {
		return sendDay;
	}

	public void setSendDay(int sendDay) {
		this.sendDay = sendDay;
	}
	

}
