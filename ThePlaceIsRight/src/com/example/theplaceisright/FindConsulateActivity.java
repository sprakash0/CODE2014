package com.example.theplaceisright;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class FindConsulateActivity extends Activity {
	
	// Continents
	 private static Spinner dynamicColorSpinner;
	 private TextView dynamicColorLabel;
	 private static ArrayList<String> options;
	 static ArrayAdapter<String> dataAdapter;
	 
	 //Countries
	 private static Spinner dynamicColorSpinner2;
	 private TextView dynamicColorLabel2;
	 private static ArrayList<String> options2;
	 static ArrayAdapter<String> dataAdapter2;
	 
	 private String country;
	 private String continent;
	 
	 
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
		options = new ArrayList<String>();
		dynamicColorSpinner = (Spinner) findViewById(R.id.continent_spinner);
		dynamicColorLabel = (TextView) findViewById(R.id.textView1);
		options.add("Countries");
		dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, options);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dynamicColorSpinner.setAdapter(dataAdapter);
		
		//Continents
		options2 = new ArrayList<String>();
		dynamicColorSpinner2 = (Spinner) findViewById(R.id.country_spinner);
		dynamicColorLabel2 = (TextView) findViewById(R.id.textView2);
		options2.add("Continents");
		dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, options2);
		dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dynamicColorSpinner2.setAdapter(dataAdapter2);
		
		setContinentListener();
		setCountryListener();
		
		return true;
	}
	
	public static void addToDropDownCountry(ArrayList<String> itemList){
		options.clear();
		for(String item : itemList ){
			  options.add(item);
			}
		dynamicColorSpinner.setAdapter(dataAdapter2);

	}
	
	public static void addToDropDownContinent(String[] itemList){
		options2.clear();
		for(int i = 0; i < itemList.length; i++){
			  options2.add(itemList[i]);
			}
		dynamicColorSpinner2.setAdapter(dataAdapter);
	}

private void setContinentListener() {
	//Continent
	dynamicColorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			continent = parent.getItemAtPosition(pos).toString();
			//options.add(continent);
			//dynamicColorSpinner.setAdapter(dataAdapter);
		}
		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub	
		}
    });

}

private void setCountryListener() {
	//Country
	dynamicColorSpinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
		@Override
		public void onItemSelected(AdapterView<?> parent, View view, int pos,
				long id) {
			country = parent.getItemAtPosition(pos).toString();
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

