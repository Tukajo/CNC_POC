package com.nanospark.cnc;


import java.lang.reflect.Type;
import java.util.ArrayList;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GlobalData {
	private static GlobalData instance = null;

	// ArrayLists of Data.
	public Context context;
	public SharedPreferences mPrefs;
	public Editor preferenceEditor;
	public ArrayList<MachineProfile> machineProfileList = new ArrayList<MachineProfile>();
	public ArrayList<ContactInfo> contactInfoList = new ArrayList<ContactInfo>();
	public ArrayList<Event> eventInfoList = new ArrayList<Event>();
	public ArrayList<ReportEvent> reportEventInfoList = new ArrayList<ReportEvent>();
	public ArrayList<NotificationEvent> notificationEventInfoList = new ArrayList<NotificationEvent>();

	protected GlobalData() {
		// Exists only to defeat instantiation.
	}

	public static GlobalData getInstance() {
		if (instance == null) {
			instance = new GlobalData();
			
		}
		return instance;
	}

	public ArrayList<MachineProfile> getMachineProfileList() {
		return machineProfileList;
	}

	public void setMachineProfileList(
			ArrayList<MachineProfile> machineProfileList) {
		this.machineProfileList = machineProfileList;
	}

	public ArrayList<ContactInfo> getContactInfoList() {
		return contactInfoList;
	}

	public void setContactInfoList(ArrayList<ContactInfo> contactInfoList) {
		this.contactInfoList = contactInfoList;
	}

	public ArrayList<Event> getEventInfoList() {
		return eventInfoList;
	}

	public void setEventInfoList(ArrayList<Event> eventInfoList) {
		this.eventInfoList = eventInfoList;
	}
	
	public ArrayList<ReportEvent> getReportEventInfoList() {
		return reportEventInfoList;
	}

	public void setReportEventInfoList(ArrayList<ReportEvent> reportEventInfoList) {
		this.reportEventInfoList = reportEventInfoList;
	}

	public ArrayList<NotificationEvent> getNotificationEventInfoList() {
		return notificationEventInfoList;
	}

	public void setNotificationEventInfoList(
			ArrayList<NotificationEvent> notificationEventInfoList) {
		this.notificationEventInfoList = notificationEventInfoList;
	}

	public void storeGlobalData(Context context){
		mPrefs = context.getSharedPreferences("com.nanospark.cnc", Context.MODE_PRIVATE);
		preferenceEditor = mPrefs.edit();
		Gson gson = new Gson();
		//Transform the ArrayLists into JSON Data.
		String machineProfileListJSON = gson.toJson(getMachineProfileList());
		String contactInfoListJSON = gson.toJson(getContactInfoList());
		String reportEventInfoListJSON = gson.toJson(getReportEventInfoList());
		preferenceEditor.putString("machineProfileListJSONData", machineProfileListJSON);
		preferenceEditor.putString("contactInfoListJSONData", contactInfoListJSON);
		preferenceEditor.putString("reportEventInfoListJSONData", reportEventInfoListJSON);
		//Commit the changes.
		preferenceEditor.commit();
		
	}
	public void retrieveGlobalDataFromStorage(Context context){
		mPrefs = context.getSharedPreferences("com.nanospark.cnc", Context.MODE_PRIVATE);
		if(mPrefs.contains("machineProfileListJSONData")){
			 final GsonBuilder builder = new GsonBuilder()
		       .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
		       .registerTypeAdapter(LocalTime.class, new LocalTimeSerializer());
		    final Gson gson = builder.create();  
		    
			String machineProfileListJSON = mPrefs.getString("machineProfileListJSONData", "");
			String contactInfoListJSON = mPrefs.getString("contactInfoListJSONData", "");
			String reportEventInfoListJSON = mPrefs.getString("reportEventInfoListJSONData", "");
			  Type  machineProfileListType = new TypeToken<ArrayList<MachineProfile>>() {}.getType();
		        machineProfileList = (gson.fromJson(machineProfileListJSON, machineProfileListType));

		        Type contactInfoListType = new TypeToken<ArrayList<ContactInfo>>() {}.getType();
		      contactInfoList = gson.fromJson(contactInfoListJSON, contactInfoListType );

		      Type ReportEventListType = new TypeToken<ArrayList<ReportEvent>>() {}.getType();
		      reportEventInfoList = gson.fromJson(reportEventInfoListJSON, ReportEventListType);
		}
	}

}