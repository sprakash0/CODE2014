package com.example.theplaceisright;

import java.util.ArrayList;

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
									+ "email: " + cons.getEmail()
									+  isPrimary + hasPassport;
			
			info.add(consulateInfo);
		}
		
		return info;
	}

}
