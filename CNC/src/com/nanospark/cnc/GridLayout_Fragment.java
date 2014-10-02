package com.nanospark.cnc;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class GridLayout_Fragment extends Fragment {
	View rootView;
	GridView gridView;
	GlobalData globaldata = GlobalData.getInstance();
	Button addProfileButton;
	CustomGrid adapter;
	ArrayList<MachineProfile> profileArrayList = new ArrayList<MachineProfile>();
	DataWriter myDataManager = new DataWriter();
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.grid_fragment_layout, container,
				false);
		gridView = (GridView) rootView.findViewById(R.id.gridView1);
		addProfileButton = (Button) rootView
				.findViewById(R.id.addProfileButton);

		
		
		addProfileButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Intent myProfileCreateActivity = new Intent(getActivity(),
						ProfileCreateActivity.class);

				startActivity(myProfileCreateActivity);

			}
		});
		populateTable();
		adapter = new CustomGrid(getActivity(), profileArrayList);
		adapter.notifyDataSetChanged();
		gridView.invalidateViews();
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				// custom dialog
				final Dialog dialog = new Dialog(getActivity());
				dialog.setContentView(R.layout.profile_inspection_dialog);
				dialog.setTitle(globaldata.getMachineProfileList()
						.get(position).getProfileName());

				Button dialogButtonOK = (Button) dialog
						.findViewById(R.id.dialogButtonOK);
				Button dialogButtonEdit = (Button) dialog
						.findViewById(R.id.dialogButtonEdit);
				TextView RGBTV = (TextView) dialog
						.findViewById(R.id.contactInspectNameTV);
				TableRow RGBTR = (TableRow) dialog.findViewById(R.id.tableRow1);
				TextView profileAttachedContactTV = (TextView) dialog
						.findViewById(R.id.profileAttachedContactNameTV);

				profileAttachedContactTV.setText("Contact: "
						+ globaldata.getMachineProfileList().get(position)
								.getAttachedContact().getName());
				RGBTV.setText(globaldata.getMachineProfileList().get(position)
						.getRGBVals().toString());
				RGBTR.setBackgroundColor(globaldata.getMachineProfileList()
						.get(position).getRGBVals().toColor());

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

		return rootView;
	}

	public class CustomGrid extends BaseAdapter {
		private Context mContext;
		private ArrayList<MachineProfile> profileList;

		public CustomGrid(Context c, ArrayList<MachineProfile> profileList) {
			mContext = c;
			this.profileList = profileList;
		}

		@Override
		public int getCount() {
			return profileList.size();
		}

		@Override
		public MachineProfile getItem(int position) {
			return profileList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View grid;
			LayoutInflater inflater = (LayoutInflater) mContext
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			if (convertView == null) {
				grid = new View(mContext);
				grid = inflater.inflate(R.layout.grid_single, null);
				TextView textView = (TextView) grid
						.findViewById(R.id.grid_text);
				grid.setBackgroundColor(profileList.get(position).getRGBVals()
						.toColor());
				grid.setAlpha((float) .50);
				textView.setText(profileList.get(position).getProfileName());

			} else {
				grid = (View) convertView;
			}
			return grid;
		}
	}

	public void populateTable() {
		getActivity().runOnUiThread(new Runnable() {
			public void run() {
				for(MachineProfile myProfile : globaldata.getMachineProfileList()){
					profileArrayList.add(myProfile);
				}
			}
		});

	}
}
