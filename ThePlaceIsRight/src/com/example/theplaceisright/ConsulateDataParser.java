package com.example.theplaceisright;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class ConsulateDataParser {

	public static void main(String[] args) {

		try {

			Object obj = JSONValue.parse(new FileReader("C:/Users/jenni/workspace/embassies-consulates-list.json"));

			JSONObject jsonObject = (JSONObject) obj;

			//			System.out.println(obj);
			System.out.println(jsonObject.keySet());

			JSONObject data = (JSONObject) jsonObject.get("data");

			for (Object n : data.keySet()) {
				
				JSONObject node = (JSONObject) data.get(n);
				JSONObject country = (JSONObject) node.get("country");
				JSONObject eng = (JSONObject) country.get("eng");
				
				String countryName = (String) eng.get("name");
//				System.out.println(countryName);
				
				JSONArray offices = (JSONArray) node.get("offices");
				
				for (int i = 0; i < offices.size(); i++) {
					
					JSONObject office = (JSONObject) offices.get(i);
					
					if (!((String) office.get("lat")).equals("")) {
						Double latitude = Double.valueOf((String) office.get("lat"));
					}
					if (!((String) office.get("lng")).equals("")) {
						Double longitude = Double.valueOf((String) office.get("lng"));
					}
					
				}
				
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
