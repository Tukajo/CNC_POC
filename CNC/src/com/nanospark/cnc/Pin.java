package com.nanospark.cnc;

public class Pin {
	String name;
	int number;
	String type;
	public Pin(String name, String Type, int number){
		this.name = name;
		this.type = type;
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
