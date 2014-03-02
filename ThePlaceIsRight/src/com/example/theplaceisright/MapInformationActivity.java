package com.example.theplaceisright;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//** queries need to be finished. Marker needs to be show. Can use latitude/longitude or address. 
// Text needs to be set for advisory/consolate text view in UI

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
        	
        	// Get latitude, longitude from main activity
        	Intent intent = getIntent();
        	double latitude = intent.getDoubleExtra(MainActivity.LATITUDE, 0);
        	double longitude = intent.getDoubleExtra(MainActivity.LONGITUDE, 0);
        	LatLng consulateLL = new LatLng(latitude, longitude);

        	map.addMarker(new MarkerOptions().position(consulateLL));
        	// Set markers at consulate locations
        	
        	// If latitude/longitude is valid, add marker to map
        	//ArrayList<Consulate> consulates = DatabaseManager.getConsulates("Canada");
        	//for(Consulate consulate : consulates ){
        	//	double consulateLatitude = consulate.getLatitude();
        	//	double consulateLongitude = consulate.getLongitude();
        	//	LatLng consulateLL = new LatLng(consulateLatitude, consulateLongitude);
            //	map.addMarker(new MarkerOptions().position(consulateLL));
  			//}
        	
        	
        	// If latitude/longitude is not valid, use address to add marker to map
        	String address = "7128 Kerr St, Vancouver, BC";
        	Geocoder geoCoder = new Geocoder(this);
        	List<Address> location = new ArrayList<Address>();
        	try {
        		location = geoCoder.getFromLocationName(address, 1);
					
        	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	if (location!=null) {
        		LatLng consulateLLFromAddress = new LatLng(location.get(0).getLatitude(), location.get(0).getLongitude());
        		map.addMarker(new MarkerOptions().position(consulateLLFromAddress));	
        	}
        	
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
	boolean canAddItem = false;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
 
        if(item.getItemId() == R.id.action_search){
        	Intent intent = new Intent(this, MapInformationActivity.class);
    		startActivity(intent);
        }
        else if(item.getItemId() == R.id.change_locations){
        	Intent intent = new Intent(this, FindConsulateActivity.class);
    		startActivity(intent);
        }
        else if(item.getItemId() == R.id.go_home){
        	Intent intent = new Intent(this, MainActivity.class);
    		startActivity(intent);
        }else if(item.getItemId() == R.id.multiple){
        	Intent intent = new Intent(this, FindMultipleConsulateActivity.class);
    		startActivity(intent);
        }else{
        	
        }
 
        return super.onOptionsItemSelected(item);
    }

}
