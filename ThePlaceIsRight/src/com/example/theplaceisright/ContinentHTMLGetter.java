package com.example.theplaceisright;

public class ContinentHTMLGetter extends HTMLGetter{
	
	  @Override
	  protected void onPostExecute(String result) {
	   // execution of result of Long time consuming operation
		  ContinentHTMLParser.parse(result);
	  }

}
