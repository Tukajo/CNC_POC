package com.nanospark.cnc;

import java.util.ArrayList;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;


public class NotificationEvent extends Event {

	public NotificationEvent(String eventTitle,String eventDescription, int assignedPin, String trackingType,
			LocalDate startDate, LocalDate endDate, boolean watchAllDay,
			LocalTime startTime, LocalTime endTime, ArrayList<String> selectedDays,
			ArrayList<ContactInfo> selectedContact, String formatChoice,
			ReportTimeSpec reportTimingConfig) {
		super(eventTitle, eventDescription, assignedPin, trackingType, startDate, endDate, watchAllDay,
				startTime, endTime, selectedDays, selectedContact, formatChoice,
				reportTimingConfig);
		// TODO Auto-generated constructor stub
	}

}
