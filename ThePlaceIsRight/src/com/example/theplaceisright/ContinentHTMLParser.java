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



import android.util.Log;

public class ContinentHTMLParser {
	
	private String url = "http://en.wikipedia.org/wiki/List_of_sovereign_states_and_dependent_territories_by_continent_%28data_file%29";
	
	ContinentHTMLParser()
	{
	}
	
	ContinentHTMLParser(String str){
		url = str;
	}
	
	public void run(){
		new HTMLGetter().execute(url);
	}
	
	public static ArrayList<ContinentCountry> parse(String str){
		ArrayList<ContinentCountry> array = new ArrayList<ContinentCountry>();
		Document doc = Jsoup.parse(str);
		Element content = doc.getElementById("pre");
		String[] text = content.toString().split("\n");
		
		for (int i = 0; i < text.length; i++)
		{
			String[] country = text[i].split("\\s+");
			//String c2 = 
			
			//ContinentCountry curr = new ContinentCountry();
			
		}		
		return array;
	}

}
