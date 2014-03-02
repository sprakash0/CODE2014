package com.example.theplaceisright;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity implements
	GooglePlayServicesClient.ConnectionCallbacks,
	GooglePlayServicesClient.OnConnectionFailedListener,
	LocationListener{
	
	private static final LocationRequest REQUEST = LocationRequest.create()
            .setInterval(5000)         // 5 seconds
            .setFastestInterval(16)    // 16ms = 60fps
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
	
	private GoogleMap map;
	private LocationClient locationClient;
	private Location location;

	//private static DatabaseManager DBManager;

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Get a handle to the Map Fragment
    	map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
    	locationClient = new LocationClient(this, this, this);

		//DBManager = new DatabaseManager(this);
		//DatabaseManager.initDatabases(this);
	}
	
//	@Override
//	protected void onStop(){
//		super.onStop();
//		DatabaseManager.close();
//	}


	@Override
	protected void onStart() {
        super.onStart();
        // Connect location client
        locationClient.connect();
    }

	@Override
	protected void onStop(){
		super.onStop();
	//	DatabaseManager.close();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 
		// Inflate the menu items for use in the action bar
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.main, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	boolean canAddItem = false;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
 
        if(item.getItemId() == R.id.action_search){
        	Intent intent = new Intent(this, MapInformationActivity.class);
    		startActivity(intent);
        }
        else{
        	
        }
 
        return super.onOptionsItemSelected(item);
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
	
	@Override
	public void onConnected(Bundle arg0) {
		// Show current location on map
		location = locationClient.getLastLocation();
		LatLng locationLL = new LatLng(location.getLatitude(), location.getLongitude());
		map.addMarker(new MarkerOptions().position(locationLL));
		CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(locationLL, 17);
	    map.animateCamera(cameraUpdate);
	    locationClient.requestLocationUpdates(REQUEST, this);
	}

	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onLocationChanged(Location location) {
	}

}
