package com.gh.common;

public class StringIntercept {
	public String intercept(String date){
		String newDate="";
		if(date.length()>=11){
			newDate=date.substring(0, 11);
		}
		return newDate;
	}
}
