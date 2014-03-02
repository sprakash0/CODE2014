package com.example.theplaceisright;

public class AdvisoryHTMLGetter extends HTMLGetter{

	  @Override
	  protected void onPostExecute(String result) {
	   // execution of result of Long time consuming operation
		  DatabaseManager.importAdvisory(AdvisoryDataParser.readData(result));
	  }
	
	
}
