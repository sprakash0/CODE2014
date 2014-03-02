package com.example.theplaceisright;

public class ContinentCountry {
	private String countryCode2;
	private String continent;
	private String countryCode3;
	
	public static final String[] continentNames = {"Africa","Asia","Europe","North America","South America","Oceania","Antarctica"};
	private static final String[] continentCodes = {"AF","AS","EU","NA","SA","OC","AN"};
	
	
	public static String continentNameToCode(String name){
		for (int i = 0; i < continentNames.length; i++)
		{
			if (continentNames[i].equals(name))
				return continentCodes[i];
		}
		return null;
	}

	public static String continentCodeToName(String code){
		for (int i = 0; i < continentCodes.length; i++)
		{
			if (continentCodes[i].equals(code))
				return continentNames[i];
		}
		return null;
	}

	
	ContinentCountry(String c2, String con, String c3){
		countryCode2 = c2;
		continent = con;
		countryCode3 = c3;
	}

	public String getCountryCode2() {
		return countryCode2;
	}

	public void setCountryCode2(String countryCode2) {
		this.countryCode2 = countryCode2;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getCountryCode3() {
		return countryCode3;
	}

	public void setCountryCode3(String countryCode3) {
		this.countryCode3 = countryCode3;
	}
	
	
	
}
