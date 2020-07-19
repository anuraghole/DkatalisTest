package com.example.dkatalistest.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseModel{

	@SerializedName("results")
	private List<ResultsItem> results;

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public List<ResultsItem> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"ResponseModel{" + 
			"results = '" + results + '\'' + 
			"}";
		}
}