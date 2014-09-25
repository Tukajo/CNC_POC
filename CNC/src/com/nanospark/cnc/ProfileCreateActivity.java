package com.nanospark.cnc;

import com.chiralcode.colorpicker.ColorPickerDialog;
import com.chiralcode.colorpicker.ColorPickerDialog.OnColorSelectedListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileCreateActivity extends Activity {
	GlobalData globaldata = GlobalData.getInstance();
	EditText enterProfileNameET;
	Button createButton;
	Button cancelButton;
	Button pickColorButton;
	Boolean colorSelected = false;
	RGBClass myRGBVals = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_creation_layout);

		enterProfileNameET = (EditText) findViewById(R.id.profilenameedittext);

		pickColorButton = (Button) findViewById(R.id.pickcolor);
		createButton = (Button) findViewById(R.id.createbutton);
		cancelButton = (Button) findViewById(R.id.cancelbutton);

		createButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String profNameParam = enterProfileNameET.getText().toString();
				if (!profNameParam.equals(null) && !profNameParam.equals("") && colorSelected ) {
					globaldata.getMachineProfileList().add(
							new MachineProfile(enterProfileNameET.getText()
									.toString(), myRGBVals));
					Intent myReturnIntent = new Intent(
							ProfileCreateActivity.this, MainActivity.class);
					startActivity(myReturnIntent);

				} else {
					Toast.makeText(getBaseContext(),
							"You are missing a parameter!", Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
		cancelButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent myReturnIntent = new Intent(ProfileCreateActivity.this,
						MainActivity.class);
				startActivity(myReturnIntent);
			}
		});

		pickColorButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				showColorPickerDialog();
			}
		});

	}

	private void showColorPickerDialog() {

		int initialColor = Color.WHITE;

		ColorPickerDialog colorPickerDialog = new ColorPickerDialog(this,
				initialColor, new OnColorSelectedListener() {

					@Override
					public void onColorSelected(int color) {
						showToast(color);
						colorSelected=true;
						myRGBVals = new RGBClass(Color.red(color), Color.green(color),Color.blue(color));
					}

				});
		colorPickerDialog.show();

	}

	private void showToast(int color) {
		String rgbString = "R: " + Color.red(color) + " B: "
				+ Color.blue(color) + " G: " + Color.green(color);
		Toast.makeText(this, rgbString, Toast.LENGTH_SHORT).show();
	}

}
