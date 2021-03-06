package com.example.theplaceisright;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class ConsulateDataParser {

	private String url = "http://data.international.gc.ca/travel-voyage/embassies-consulates-list.json";
	
	public void run(){
		new ConsulateHTMLGetter().execute(url);
	}
	
	// For testing purposes; can be removed later
	public static void print(ArrayList<Consulate> result) {
		for (Consulate c : result) {
			System.out.println("countryCode: " + c.getCountryCode());
			System.out.println("country: " + c.getCountry());
			System.out.println("officeID: " + c.getOfficeID());
			System.out.println("city: " + c.getCity());
			System.out.println("type: " + c.getType());
			System.out.println("latitude: " + c.getLatitude());
			System.out.println("longitude: " + c.getLongitude());
			System.out.println("address: " + c.getAddress());
			System.out.println("telephone: " + c.getTelephone());
			System.out.println("fax: " + c.getFax());
			System.out.println("isPrimary: " + c.getIsPrimary());
			System.out.println("hassPassport: " + c.getHasPassport());
		}
	}	
	
	public static ArrayList<Consulate> readData(String str) {
	
			JSONObject jsonObject = (JSONObject) JSONValue.parse(str);

			JSONObject data = (JSONObject) jsonObject.get("data");
			int size = (int) data.keySet().size();
			
			ArrayList<Consulate> consulates = new ArrayList<Consulate>(size);

			for (Object n : data.keySet()) {
				
				
				JSONObject node = (JSONObject) data.get(n);
				JSONObject country = (JSONObject) node.get("country");
				JSONObject eng = (JSONObject) country.get("eng");
				
				// Empty strings as key values treated as NULL (country, countryCode, office-id)
				String name = !((String) eng.get("name")).equals("")? (String) eng.get("name") : "NULL"; 
				
				String code = !((String) country.get("country-iso")).equals("")? (String) country.get("country-iso") : "NULL";

				JSONArray offices = (JSONArray) node.get("offices");
				
				for (int i = 0; i < offices.size(); i++) {
					
					JSONObject office = (JSONObject) offices.get(i);
					
					Consulate consulate = new Consulate(); 
					consulate.setCountry(name);
					consulate.setCountryCode(code);
					consulate.setOfficeID(((Long) office.get("office-id")).intValue()); 	
					
					boolean isPrimary = ((Long) office.get("is-primary") != 0)? true : false; 
					consulate.setIsPrimary(isPrimary);
					
					boolean hasPassport = ((Long) office.get("has-passport-services") != 0)? true : false;
					consulate.setHasPassport(hasPassport);
					
					// Set latitude and longitude if available; otherwise use min value of float. 
					Float latitude = (!((String) office.get("lat")).equals(""))? Float.valueOf((String) office.get("lat")) : Float.MIN_VALUE;  
					consulate.setLatitude(latitude);
					
					Float longitude = (!((String) office.get("lng")).equals(""))? Float.valueOf((String) office.get("lng")) : Float.MIN_VALUE;  
					consulate.setLongitude(longitude);
					
					JSONObject embassy = (JSONObject) office.get("eng");
					
					// Empty strings OK; indicate null value.
					consulate.setCity((String) embassy.get("city"));
					consulate.setType((String) embassy.get("type"));
					consulate.setAddress((String) embassy.get("address"));
					consulate.setTelephone((String) embassy.get("tel-legacy"));
					consulate.setFax((String) embassy.get("fax-legacy"));
					consulate.setEmail((String) embassy.get("email-1"));
					consulate.setEmergency((String) embassy.get("emergency-toll-free-legacy"));
					
					consulates.add(consulate);
				}				
			}
			
		return consulates;	


	}
	
	public void setURL(String u) {
		
		url = u;
	}
	
}