package com.example.theplaceisright;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	
	private String url = "http://en.wikipedia.org/w/index.php?title=List_of_sovereign_states_and_dependent_territories_by_continent_(data_file)&action=raw";
	
	ContinentHTMLParser()
	{
	}
	
	ContinentHTMLParser(String str){
		url = str;
	}
	
	//testing
	public static void print(ArrayList<ContinentCountry> result){
		for (ContinentCountry c : result) {
			System.out.println("Country2: " + c.getCountryCode2());
			System.out.println("Country3: " + c.getCountryCode3());
			System.out.println("Continent: " + c.getContinent());
			System.out.println();
		}
	}
	
	public void run(){
		new ContinentHTMLGetter().execute(url);
	}
	
	public static ArrayList<ContinentCountry> parse(String str){
		ArrayList<ContinentCountry> array = new ArrayList<ContinentCountry>();
    	Pattern p = Pattern.compile(
                "<pre>(.*)</pre>",
                Pattern.DOTALL
            );
    	Matcher matcher = p.matcher(str);
    	matcher.find();
    	String content = matcher.group(1);
		String[] text = content.toString().split("\n");
		
		for (int i = 0; i < text.length; i++)
		{
			if(text[i].trim().isEmpty()) continue;
			String[] country = text[i].split("\\s+");
			String continent = country[0]; 
			String code2 = country[1];
			String code3 = country[2];
			
			ContinentCountry curr = new ContinentCountry(code2, continent, code3);
			
			array.add(curr);
			
		}		
		
		print(array);
		
		return array;
	}

}
