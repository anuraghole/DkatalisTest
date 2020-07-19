package com.example.dkatalistest.model;

import com.google.gson.annotations.SerializedName;

public class Location{

	@SerializedName("zip")
	private String zip;

	@SerializedName("city")
	private String city;

	@SerializedName("street")
	private String street;

	@SerializedName("state")
	private String state;

	public void setZip(String zip){
		this.zip = zip;
	}

	public String getZip(){
		return zip;
	}

	public void setCity(String city){
		this.city = city;
	}

	public String getCity(){
		return city;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public String getStreet(){
		return street;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}

	@Override
 	public String toString(){
		return 
			"Location{" + 
			"zip = '" + zip + '\'' + 
			",city = '" + city + '\'' + 
			",street = '" + street + '\'' + 
			",state = '" + state + '\'' + 
			"}";
		}
}