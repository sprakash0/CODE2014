package com.example.theplaceisright;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class FindConsulateActivity extends Activity {
	
	// Continents
	 private Spinner dynamicColorSpinner;
	 private TextView dynamicColorLabel;
	 private static ArrayList<String> options;
	 
	 //Countries
	 private Spinner dynamicColorSpinner2;
	 private TextView dynamicColorLabel2;
	 private static ArrayList<String> options2;
//	 
//	@Override
//	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_find_consulate);
//		addToDropDownContinent(ContinentCountry.continentNames);
//	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.find_consulate, menu);
		
		//Continents
		options = new ArrayList<String>();
		dynamicColorSpinner = (Spinner) findViewById(R.id.continent_spinner);
		dynamicColorLabel = (TextView) findViewById(R.id.textView1);
		options.add("White");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, options);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dynamicColorSpinner.setAdapter(dataAdapter);
		
		//Countries
		options2 = new ArrayList<String>();
		dynamicColorSpinner2 = (Spinner) findViewById(R.id.country_spinner);
		dynamicColorLabel2 = (TextView) findViewById(R.id.textView2);
		options2.add("Black");
		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, options2);
		dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dynamicColorSpinner2.setAdapter(dataAdapter2);
		
		return true;
	}
	
	public static void addToDropDownCountry(ArrayList<String> itemList){
		for(String item : itemList ){
			  options.add(item);
			}
	}
	
	public static void addToDropDownContinent(String[] itemList){
		for(int i = 0; i < itemList.length; i++){
			  options2.add(itemList[i]);
			}
	}
	


}
