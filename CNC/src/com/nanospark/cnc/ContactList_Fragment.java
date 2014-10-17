package com.nanospark.cnc;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;


public class ContactList_Fragment extends Fragment {
	GlobalData globaldata = GlobalData.getInstance();
	View rootView;
	Button addContactBtn;
	ListView contactListView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		rootView = inflater.inflate(R.layout.generic_list_fragment_layout, container,
				false);
		addContactBtn = (Button) rootView.findViewById(R.id.addListItem);
		contactListView = (ListView) rootView.findViewById(android.R.id.list);
		
	     ArrayAdapter<ContactInfo> contactAdapter = new ArrayAdapter<ContactInfo>(inflater.getContext(),
	    		  android.R.layout.simple_list_item_1, globaldata.getContactInfoList());
		
		contactAdapter.notifyDataSetInvalidated();
		contactAdapter.notifyDataSetChanged();
		contactListView.setAdapter(contactAdapter);
		
		contactListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				// custom dialog
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.contact_inspection_dialog);
				dialog.setTitle(globaldata.getContactInfoList().get(position).getName());
				final int myPosition = position;
			
				Button dialogButtonOK = (Button) dialog.findViewById(R.id.dialogButtonOK);
				Button dialogButtonEdit = (Button) dialog.findViewById(R.id.dialogButtonEdit);
				TextView contactEmailTV = (TextView) dialog.findViewById(R.id.contactInspectEmailTV);
				TextView contactCarrierTV = (TextView) dialog.findViewById(R.id.contactInspectCarrierTV);
				TextView contactPhoneTV = (TextView) dialog.findViewById(R.id.contactInspectPhoneTV);
				
				//Set the text of the text fields for this dialog box.
				contactEmailTV.setText("Email:" + globaldata.getContactInfoList().get(position).getEmail().toString());
				contactCarrierTV.setText("Carrier: " + globaldata.getContactInfoList().get(position).getCarrier().toString());
				contactPhoneTV.setText("Phone: " + globaldata.getContactInfoList().get(position).getPhoneNum().toString());
				
				
				dialogButtonEdit.setOnClickListener(new View.OnClickListener(){
					@Override
					public void onClick(View v) {
						Intent contactEditIntent = new Intent(getActivity(), ContactCreateActivity.class);
						contactEditIntent.putExtra("edit", myPosition);
						startActivity(contactEditIntent);
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
		
	
		addContactBtn.setText("Add New Contact");
		addContactBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent contactCreationIntent = new Intent(getActivity(), ContactCreateActivity.class);
				startActivity(contactCreationIntent);
			}
		});
		
		
		
		return rootView;
	}


}
