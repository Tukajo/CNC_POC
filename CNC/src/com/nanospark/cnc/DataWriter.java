package com.nanospark.cnc;

import java.util.ArrayList;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;

public class DataWriter {
	Editor prefsEditor;
	SharedPreferences mPrefs;

	public void SaveArrayToStorage(ArrayList array, String name) {
		Gson gson = new Gson();
		String json = gson.toJson(array);
		prefsEditor.putString(name, json);
	}

	public ArrayList<?> FetchArrayFromStorage(String name) {
		Gson gson = new Gson();
		String json = mPrefs.getString("name","");
		 ArrayList<?> retrArr = gson.fromJson(json, ArrayList.class);
		return retrArr;
	}

}
