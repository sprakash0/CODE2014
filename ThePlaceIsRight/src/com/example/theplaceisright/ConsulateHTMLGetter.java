package com.example.theplaceisright;

public class ConsulateHTMLGetter extends HTMLGetter{

	  @Override
	  protected void onPostExecute(String result) {
	   // execution of result of Long time consuming operation
		  ConsulateDataParser.readData(result);
	  }
	
	
}
