package com.nanospark.cnc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EventList_Fragment extends Fragment {
	
	View rootView;
	Button addNewEventBtn;
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		rootView = inflater.inflate(R.layout.generic_list_fragment_layout,
				container, false);
		
		addNewEventBtn = (Button) rootView.findViewById(R.id.addListItem);
		
		addNewEventBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent myEventCreationIntent = new Intent(getActivity(),EventCreateActivity.class);
				startActivity(myEventCreationIntent);
				
			}
		});
		
		return rootView;
	}
}