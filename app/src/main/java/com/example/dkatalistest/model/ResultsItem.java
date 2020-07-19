package com.example.dkatalistest.model;

import com.google.gson.annotations.SerializedName;

public class ResultsItem{

	@SerializedName("seed")
	private String seed;

	@SerializedName("user")
	private User user;

	@SerializedName("version")
	private String version;

	public void setSeed(String seed){
		this.seed = seed;
	}

	public String getSeed(){
		return seed;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setVersion(String version){
		this.version = version;
	}

	public String getVersion(){
		return version;
	}

	@Override
 	public String toString(){
		return 
			"ResultsItem{" + 
			"seed = '" + seed + '\'' + 
			",user = '" + user + '\'' + 
			",version = '" + version + '\'' + 
			"}";
		}
}