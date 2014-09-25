package com.nanospark.cnc;

public class ContactInfo {

	String phoneNum;
	//String fName;
	//String lName;
	String name;
	String email;
	String carrier;
	
	public ContactInfo(String phoneNum, String name, String email, String carrier){
		this.phoneNum = phoneNum;
		//this.fName = fName;
		//this.lName = lName;
		this.name = name;
		this.email = email;
		this.carrier = carrier;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

/*	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}*/

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
	
	
}
