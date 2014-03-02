package com.example.theplaceisright;

import android.annotation.TargetApi;
import android.app.Activity;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapInformationActivity extends Activity {
	
	private GoogleMap map;
	
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_information);
        
        // Check that Google Play Services is available
        if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext()) == ConnectionResult.SUCCESS) {
        
        	// Get a handle to the Map Fragment
        	map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        	map.setMyLocationEnabled(true);
        	
        	// Set marker at current location
            //LatLng locationLL = new LatLng(location.getLatitude(), location.getLongitude());
            //map.addMarker(new MarkerOptions().position(locationLL));
        
        	// Set markers at consulate locations
        	// set latitude and longitude of consulate
        	LatLng consulateLL = new LatLng(0, 0);
        	map.addMarker(new MarkerOptions().position(consulateLL));
        
        	// Text information
        	TextView advisoryTextView = (TextView)findViewById(R.id.advisoryTextView);
        	advisoryTextView.setText("show advisory");
        	
        	TextView consulateTextView = (TextView)findViewById(R.id.consulateTextView);
        	consulateTextView.setText("information about consulate");
      
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
