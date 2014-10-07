package com.nanospark.cnc;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
//import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {
	GridLayout_Fragment profileGridFragment = new GridLayout_Fragment();
	EventList_Fragment eventListFragment = new EventList_Fragment();
	ContactList_Fragment contactListFragment = new ContactList_Fragment();
	FragmentManager transactionManager;
	FragmentTransaction transaction;
	CustomIOIO customioio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.AppTheme);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//insert the initial fragment for when the app boots.
		 transactionManager = getSupportFragmentManager();
		transaction = transactionManager.beginTransaction();
		transaction.add(R.id.fragment_frame, profileGridFragment);
		transaction.addToBackStack(null);
		transaction.commit();
		 customioio = (CustomIOIO) getApplicationContext();
		 customioio.create();
		 customioio.setTheme(R.style.AppTheme);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id) {
		case R.id.action_settings:
			openSettings();
			return true;
		case R.id.action_home:
			openHome();
			return true;
		case R.id.action_events:
			openEvents();
			return true;
		case R.id.action_help:
			openHelp();
			return true;
		case R.id.action_contacts:
			openContacts();
			return true;

		}
		return super.onOptionsItemSelected(item);
	}

	private void openContacts() {
		 transactionManager = getSupportFragmentManager();
		transaction = transactionManager.beginTransaction();
		transaction.replace(R.id.fragment_frame, contactListFragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	private void openHome() {
		 transactionManager = getSupportFragmentManager();
		transaction = transactionManager.beginTransaction();
		transaction.replace(R.id.fragment_frame, profileGridFragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	private void openHelp() {

	}

	private void openEvents() {
		 transactionManager = getSupportFragmentManager();
		transaction = transactionManager.beginTransaction();
		transaction.replace(R.id.fragment_frame, eventListFragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	private void openSettings() {

	}
}
