package com.houseornohouse;

public class CityPrice {
	private String city;

	private Integer price;
	
	CityPrice(String c, Integer p)
	{
		city = c;
		price = p;
	}

	public String getID() {
		return city;
	}

	public void setID(String city) {
		this.city = city;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}
}
