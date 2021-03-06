package com.nanospark.cnc;

import android.graphics.Color;

public class RGBClass {
	int red;
	int green;
	int blue;
	public RGBClass(int red, int green, int blue){
		this.red = red;
		this.blue = blue;
		this.green = green;
	}
	public int getRed() {
		return red;
	}
	public void setRed(int red) {
		this.red = red;
	}
	public int getGreen() {
		return green;
	}
	public void setGreen(int green) {
		this.green = green;
	}
	public int getBlue() {
		return blue;
	}
	public void setBlue(int blue) {
		this.blue = blue;
	}
	public int toColor(){
		return Color.rgb(this.red, this.green, this.blue);
	}
	public String toString(){
		  return "RGB Values: " + "R: " + red + " B: " + blue + " G: " + green;
	}
	
}
