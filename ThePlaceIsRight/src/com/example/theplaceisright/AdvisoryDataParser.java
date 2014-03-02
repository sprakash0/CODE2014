package com.example.theplaceisright;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;


public class AdvisoryDataParser {

	private String url = "http://data.international.gc.ca/travel-voyage/index-alpha-eng.json";

	public void run() {
		new AdvisoryHTMLGetter().execute(url);
	}

	// For testing purposes; can be removed later
	public static void print(ArrayList<Advisory> result) {
		for (Advisory a : result) {
			System.out.println("countryCode: " + a.getCountryCode());
			System.out.println("country: " + a.getCountry());
			System.out.println("date: " + a.getDate());
			System.out.println("text: " + a.getText());
		}
	}	

	public static ArrayList<Advisory> readData(String str) {

		JSONObject jsonObject = (JSONObject) JSONValue.parse(str);

		JSONObject data = (JSONObject) jsonObject.get("data");
		int size = (int) data.keySet().size();

		ArrayList<Advisory> advisories = new ArrayList<Advisory>(size);

		for (Object obj : data.keySet()) {

			JSONObject node = (JSONObject) data.get(obj);

			// Only save this information for the countries that have advisory warnings; ignore others
			if (((Long) node.get("has-advisory-warning")).intValue() != 1) {
				continue;
			}
			Advisory advisory = new Advisory();

			advisory.setCountryCode(String.valueOf(obj));

			JSONObject eng = (JSONObject) node.get("eng");

			// Empty strings as key values treated as NULL
			String name = !((String) eng.get("name")).equals("")? (String) eng.get("name") : "NULL"; 
			advisory.setCountry(name);

			// Empty strings OK; indicate null value.
			advisory.setDate((String) eng.get("friendly-date"));
			advisory.setText((String) eng.get("advisory-text"));

			advisories.add(advisory);

		}

		return advisories;
	}

	public void setURL(String u) {

		url = u;
	}

}