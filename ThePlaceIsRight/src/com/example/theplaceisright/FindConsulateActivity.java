package com.example.theplaceisright;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

//**Populated continents - uses getter to retrieve the country, still need to populate countries

public class FindConsulateActivity extends Activity {
	
	public final static String COUNTRY = "com.example.theplaceisright.MESSAGE";
	
	// Continents
	 private static Spinner dynamicColorSpinnerContinent;
	 private TextView dynamicColorLabelContinent;
	 private static ArrayList<String> optionsContinent;
	 static ArrayAdapter<String> dataAdapterContinent;
	 
	 //Countries
	 private static Spinner dynamicColorSpinnerCountry;
	 private TextView dynamicColorLabelCountry;
	 private static ArrayList<String> optionsCountry;
	 static ArrayAdapter<String> dataAdapterCountry;
	 
	 private String country;
	 private String continent;
	 private int prevIdx = -1;
	 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_find_consulate);
		//addToDropDownContinent(ContinentCountry.continentNames);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_consulate, menu);
		
		//Countries
		optionsCountry = new ArrayList<String>();
		dynamicColorSpinnerCountry = (Spinner) findViewById(R.id.continent_spinner);
		dynamicColorLabelCountry = (TextView) findViewById(R.id.textView1);
		optionsCountry.add("Countries");
		dataAdapterCountry = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, optionsCountry);
		dataAdapterCountry.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dynamicColorSpinnerCountry.setAdapter(dataAdapterCountry);
		
		//Continents
		optionsContinent = new ArrayList<String>();
		dynamicColorSpinnerContinent = (Spinner) findViewById(R.id.country_spinner);
		dynamicColorLabelContinent = (TextView) findViewById(R.id.textView2);

		// Add continents to drop down list
		addToDropDownContinent(ContinentCountry.continentNames);
		
		dataAdapterContinent = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, optionsContinent);
		dataAdapterContinent.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dynamicColorSpinnerContinent.setAdapter(dataAdapterContinent);
		
		setContinentListener();
		setCountryListener();
		
		return true;
	}
	
	public void go(View view) {
	    // Do something in response to button
		Intent intent = new Intent(this, MapInformationActivity.class);
		intent.putExtra(COUNTRY, country);
		startActivity(intent);
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
	
	public static void addToDropDownCountry(ArrayList<String> itemList){
		optionsCountry.clear();
		for(String item : itemList ){
			  optionsCountry.add(item);
			}
		dynamicColorSpinnerCountry.setAdapter(dataAdapterCountry);

	}
	
	public static void addToDropDownContinent(String[] itemList){
		optionsContinent.clear();
		for(int i = 0; i < itemList.length; i++){
			  optionsContinent.add(itemList[i]);
			}
		dynamicColorSpinnerContinent.setAdapter(dataAdapterContinent);
	}

private void setContinentListener() {
	
	//Continent
	dynamicColorSpinnerContinent.setOnItemSelectedListener(new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			if (prevIdx >= 0 && prevIdx == pos) return;
			prevIdx = pos;
			
			continent = parent.getItemAtPosition(pos).toString();
			System.out.println(continent);
			ArrayList<String> temp = DatabaseManager.getCountries(continent);
			if (temp!= null)
			{
				System.out.println("printing countries");
				for (String c: temp)
					System.out.println(c);
			}
			else
			{
				System.out.println("Error! null list of countries");
				
			}
			addToDropDownCountry(temp);
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub	
		}
    });

}

private void setCountryListener() {
	//Country
	dynamicColorSpinnerCountry.setOnItemSelectedListener(new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent2, View view, int pos,
				long id) {
			country = parent2.getItemAtPosition(pos).toString();
			//dynamicColorLabel.setText(country);
			//dynamicColorLabel.setTextColor(Color.RED);
			//options2.add(country);
			//dynamicColorSpinner2.setAdapter(dataAdapter2);
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			
			
		}
    });
}

public String getCountry(){
	
	return country;
}

public String getContinent(){
	
	return continent;

}

}

