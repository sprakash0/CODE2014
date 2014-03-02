package com.example.theplaceisright;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class MultipleSummaryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_multiple_summary);
		
		// Get the message from the intent
        Intent intent = getIntent();
        ArrayList<String> countries_list = intent.getStringArrayListExtra(FindMultipleConsulateActivity.COUNTRIES_LIST);
        boolean showAdvisory = intent.getBooleanExtra(FindMultipleConsulateActivity.SHOW_ADVISORY, true);
        boolean showConsulate = intent.getBooleanExtra(FindMultipleConsulateActivity.SHOW_CONSULATE, true);
        boolean showPassportOnly = intent.getBooleanExtra(FindMultipleConsulateActivity.SHOW_PASSPORT_ONLY, false);
        
        // Create the text view
        TextView textView = new TextView(this);
        if(countries_list != null) {
        	CountryInfoFormatter cif = new CountryInfoFormatter();
        	for (int i=0; i<countries_list.size(); i++) {
        		// Get country_name from the list of selected countries
        		String country_name = countries_list.get(i);
        		textView.setText(textView.getText() + "\n" + country_name + "\n"); 
        	
        		// If Advisory was checked, show advisory information
        		if (showAdvisory) {
        			String advisory = cif.getAdvisory(country_name);
        			textView.setText(textView.getText() + "Advisory: " + advisory +"\n");
        		}
        	
        		// If Consulate was checked, show consulate information
        		if (showConsulate) {
        			ArrayList<String> consulateInfo = cif.getConsulateInfo(country_name, showPassportOnly);
        			for (String info : consulateInfo) {
        				textView.setText(textView.getText() + info + "\n");
        			}
        		}		
        	}
        }
        
        // Set the text view as the activity layout
        setContentView(textView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.multiple_summary, menu);
		return true;
	}

}
