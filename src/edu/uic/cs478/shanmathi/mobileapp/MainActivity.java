package edu.uic.cs478.shanmathi.mobileapp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

	Button dialbutton; // Button to call the number entered
	EditText textbox; // Text Editor for entering the number
	String num;

	/*
	 * onCreate(Bundle) is where the activity is initailized. It calls
	 * setContentView(int) with a layout resource defining the UI and using
	 * findViewById(int) to retrieve the widgets in that UI that you need to
	 * interact with programmatically.
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Call onCreate(savedInstanceState) from Activity class
		super.onCreate(savedInstanceState);

		// Set the activity content from a layout activity_main
		setContentView(R.layout.activity_main);

		// Finds a view that was identified by the id attribute from the XML
		dialbutton = (Button) findViewById(R.id.button1);
		textbox = (EditText) findViewById(R.id.editText1);

		// definition for a callback to be invoked when button (view) is
		// clicked.
		dialbutton.setOnClickListener(buttonListener);
	}

	// Interface definition for a callback to be invoked when a button (view) is
	// clicked
	View.OnClickListener buttonListener = new View.OnClickListener() {
		public void onClick(View v) {
			try {
	//If textbox is empty 
				if (textbox == null) {
					Toast.makeText(getApplicationContext(),
							"Please enter the number", Toast.LENGTH_SHORT).show();
				}
				else {
					String number = textbox.getText().toString().replaceAll("\\s", "");
	//Check the phone number pattern
					Pattern pattern = Pattern
							.compile("\\(\\d\\d\\d\\)\\d\\d\\d-\\d\\d\\d\\d");
					Matcher matcher = pattern.matcher(number);

					if (matcher.find()){
						num = matcher.group(0);
	//Parse the phone number to the Phone Dialing Application
						Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"
								+ num));
						startActivity(i);
					}
	//If phone number pattern not found
					else
						Toast.makeText(getApplicationContext(),
								"Please enter the number in the format (xxx) yyy-zzzz",
								Toast.LENGTH_SHORT).show();
					
				}
			} catch (Exception e) {
				// Exception
			}
		}
	};

	
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
