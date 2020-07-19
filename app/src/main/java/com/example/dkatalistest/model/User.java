package com.example.dkatalistest.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.dkatalistest.repository.localdb.convertors.DataConverter;
import com.google.gson.annotations.SerializedName;

@Entity
public class User{

	/*@PrimaryKey
	private long id;*/

	@SerializedName("salt")
	private String salt;

	@SerializedName("gender")
	private String gender;

	@SerializedName("sha256")
	private String sha256;

	@SerializedName("registered")
	private String registered;

	@SerializedName("cell")
	private String cell;

	@SerializedName("picture")
	private String picture;

	@SerializedName("SSN")
	private String sSN;

	@SerializedName("sha1")
	private String sha1;

	@SerializedName("password")
	private String password;

	@SerializedName("phone")
	private String phone;

	@SerializedName("dob")
	private String dob;

	@TypeConverters(DataConverter.class)
	@SerializedName("name")
	private Name name;

	@TypeConverters(DataConverter.class)
	@SerializedName("location")
	private Location location;

	@PrimaryKey
	@NonNull
	@SerializedName("email")
	private String email;

	@SerializedName("username")
	private String username;

	@SerializedName("md5")
	private String md5;

	private boolean isFavourite;

	public boolean isFavourite() {
		return isFavourite;
	}

	public void setFavourite(boolean favourite) {
		isFavourite = favourite;
	}

	public void setSalt(String salt){
		this.salt = salt;
	}

	public String getSalt(){
		return salt;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return gender;
	}

	public void setSha256(String sha256){
		this.sha256 = sha256;
	}

	public String getSha256(){
		return sha256;
	}

	public void setRegistered(String registered){
		this.registered = registered;
	}

	public String getRegistered(){
		return registered;
	}

	public void setCell(String cell){
		this.cell = cell;
	}

	public String getCell(){
		return cell;
	}

	public void setPicture(String picture){
		this.picture = picture;
	}

	public String getPicture(){
		return picture;
	}

	public void setSSN(String sSN){
		this.sSN = sSN;
	}

	public String getSSN(){
		return sSN;
	}

	public void setSha1(String sha1){
		this.sha1 = sha1;
	}

	public String getSha1(){
		return sha1;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setDob(String dob){
		this.dob = dob;
	}

	public String getDob(){
		return dob;
	}

	public void setName(Name name){
		this.name = name;
	}

	public Name getName(){
		return name;
	}

	public void setLocation(Location location){
		this.location = location;
	}

	public Location getLocation(){
		return location;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	public void setMd5(String md5){
		this.md5 = md5;
	}

	public String getMd5(){
		return md5;
	}

//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			//"id = '" + id + '\'' +
			"salt = '" + salt + '\'' +
			",gender = '" + gender + '\'' +
			",sha256 = '" + sha256 + '\'' + 
			",registered = '" + registered + '\'' + 
			",cell = '" + cell + '\'' + 
			",picture = '" + picture + '\'' + 
			",sSN = '" + sSN + '\'' + 
			",sha1 = '" + sha1 + '\'' + 
			",password = '" + password + '\'' + 
			",phone = '" + phone + '\'' + 
			",dob = '" + dob + '\'' + 
			",name = '" + name + '\'' + 
			",location = '" + location + '\'' + 
			",email = '" + email + '\'' + 
			",username = '" + username + '\'' + 
			",md5 = '" + md5 + '\'' + 
			",isFavourite = '" + isFavourite + '\'' +
			"}";
		}
}