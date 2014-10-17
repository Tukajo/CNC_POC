package com.nanospark.cnc;

import java.util.ArrayList;

public class MachineProfile {
	String profileName;
	RGBClass RGBVals;
	ArrayList<Event> attachedEvents = new ArrayList<Event>();

	public MachineProfile(String profileName, RGBClass RGBVals, ArrayList<Event> attachedEvents) {
		this.profileName = profileName;
		
		this.RGBVals = RGBVals;
		this.attachedEvents = attachedEvents;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public RGBClass getRGBVals() {
		return RGBVals;
	}

	public void setRGBVals(RGBClass rGBVals) {
		RGBVals = rGBVals;
	}

	public ArrayList<Event> getAttachedEventsList() {
		return attachedEvents;
	}

	public void setAttachedEventsList(ArrayList<Event> attachedEvents) {
		this.attachedEvents = attachedEvents;
	}
	
}
