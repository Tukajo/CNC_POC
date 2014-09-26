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
	Fragment replaceableFragment = new GridLayout_Fragment();
	FragmentManager transactionManager = getSupportFragmentManager();
	FragmentTransaction transaction;
	CustomIOIO customioio;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//insert the initial fragment for when the app boots.
		transaction = transactionManager.beginTransaction();
		transaction.replace(R.id.fragment_frame, replaceableFragment);
		transaction.commit();
		 customioio = (CustomIOIO) getApplicationContext();
		 customioio.create();
		 customioio.start();

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
		replaceableFragment = new ContactList_Fragment();
		transaction = transactionManager.beginTransaction();
		transaction.replace(R.id.fragment_frame, replaceableFragment);
		transaction.addToBackStack(null);
		transaction.commit();
	}

	private void openHome() {
		replaceableFragment = new GridLayout_Fragment();
		transaction = transactionManager.beginTransaction();
		transaction.replace(R.id.fragment_frame, replaceableFragment);
		transaction.addToBackStack(null);

		transaction.commit();
	}

	private void openHelp() {

	}

	private void openEvents() {

	}

	private void openSettings() {

	}
}
