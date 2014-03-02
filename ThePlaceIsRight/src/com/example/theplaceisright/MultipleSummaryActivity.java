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
   //     List<String> countries_list = intent.getStringExtra(FindMultipleConsulateActivity.COUNTRIES_LIST);

        // Create the text view
        TextView textView = new TextView(this);
        
     //   for (int i=0; i<countries_list.size(); i++) {
        	// Get country_name, advisory, and consulate
        	String country_name = "";
        	
        	Advisory a = new Advisory();
        	a.setCountry(country_name);
        	String advisory = a.getText();
        	
        	Consulate c = new Consulate();
        	c.setCountry(country_name);
        	String consulate = c.getAddress();
        	
        	textView.setText(textView.getText() + "\n" 
        		+ country_name + "\n" 
        		+ "Advisory: " + advisory +"\n"
        		+ "Consulate Location: " + consulate + "\n");
  //      }
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
