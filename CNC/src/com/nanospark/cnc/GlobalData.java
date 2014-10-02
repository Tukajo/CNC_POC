package com.nanospark.cnc;

import java.util.ArrayList;

import android.widget.ArrayAdapter;

public class GlobalData {
	private static GlobalData instance = null;

	// ArrayLists of Data.
	public ArrayList<MachineProfile> machineProfileList = new ArrayList<MachineProfile>();
	public ArrayList<ContactInfo> contactInfoList = new ArrayList<ContactInfo>();

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

}