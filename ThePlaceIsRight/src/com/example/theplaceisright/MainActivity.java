package com.example.theplaceisright;

import com.google.android.gms.maps.GoogleMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

public class MainActivity extends Activity {
	
	//private GoogleMap map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 
		// Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}

	// Called when the user clicks the search button
	public void search(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, MapInformationActivity.class);
		startActivity(intent);
	}
	
	public void changeLocation(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, FindConsulateActivity.class);
		startActivity(intent);
	}
}
