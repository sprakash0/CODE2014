package com.example.theplaceisright;

import java.util.ArrayList;

import com.google.android.gms.maps.model.LatLng;

public class CountryInfoFormatter {
	
	public ArrayList<String> getConsulateInfo(String country) {

		ArrayList<String> info = new ArrayList<String>();

		ArrayList<Consulate> consulates = DatabaseManager.getConsulates(country);

		// TO DO: figure out if there is a particular order in which these should be displayed
		// Include: city, type, address, telephone, fax, email, isPrimary, hasPassport  
		for (Consulate cons : consulates) {
			String isPrimary = cons.getIsPrimary()? "Primary consulate \n" : "";
			String hasPassport = cons.getHasPassport()? "Passport services" : "";
			String consulateInfo = 	cons.getCity() + "\n"
					+ cons.getType() + "\n"
					+ cons.getAddress() + "\n"
					+ "tel: " + cons.getTelephone() + "\n"
					+ "fax:" + cons.getFax() + "\n"
					+ "email: " + cons.getEmail() + "\n"
					+  isPrimary + hasPassport;

			info.add(consulateInfo);
		}

		return info;
	}
	
	public ArrayList<LatLng> getAllCoordinates(String country) {
		
		ArrayList<LatLng> coordinates = new ArrayList<LatLng>();
		
		ArrayList<Consulate> consulates = DatabaseManager.getConsulates(country);
		
		for (Consulate cons : consulates) {
			LatLng latlng = new LatLng(cons.getLatitude(), cons.getLongitude());
			
			coordinates.add(latlng);
		}
		
		return coordinates;
	}

	// If advisory exists, return the text of the advisory, otherwise return empty string
	public String getAdvisory(String country) {

		Advisory adv = DatabaseManager.getAdvisory(country);

		String info = (adv != null)? adv.getText() : "";

		return info;
	}

}