package com.example.theplaceisright;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.example.myfirstapp.HTMLGetter;

import android.util.Log;

public class ContinentHTMLParser {
	
	private String url = "http://en.wikipedia.org/wiki/List_of_sovereign_states_and_dependent_territories_by_continent_%28data_file%29";
	
	ContinentHTMLParser()
	{
	}
	
	ContinentHTMLParser(String str){
		url = str;
	}
	
	public ArrayList<ContinentCountry> parse(){
		ArrayList<ContinentCountry> array = new ArrayList<ContinentCountry>();
		
		HTMLGetter getter = new HTMLGetter();
		String resString = getter.doInBackground(url);
		if (resString == null) return array;
		
		Document doc = Jsoup.parse(resString);
		
		Element content = doc.getElementById("pre");
		String text = content.toString();
		
		System.out.println(text);
		
		return array;
	
	}

}
