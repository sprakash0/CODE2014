package com.houseornohouse;

public class CityPrice {
	private String city;
	private String province;
	private Integer price;
	
	CityPrice(String c, String prov, Integer p)
	{
		city = c;
		province = prov;
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

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
}
