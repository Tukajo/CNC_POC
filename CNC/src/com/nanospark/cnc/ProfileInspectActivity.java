package com.nanospark.cnc;

import android.app.Activity;
import android.os.Bundle;

public class ProfileInspectActivity extends Activity {
GlobalData globaldata = GlobalData.getInstance();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_inspection_dialog);
		globaldata.retrieveGlobalDataFromStorage(getBaseContext());
	}
	@Override
    protected void onPause(){
       super.onPause();
      globaldata.storeGlobalData(getBaseContext());
    }

	
}
