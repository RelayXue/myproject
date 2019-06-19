package com.gh.common;
import java.util.List;

import com.google.gson.Gson;
public class TransformJSON {
	static Gson gson = new Gson();
	
	public static String toJsonArr(List<?> arr){
		return gson.toJson(arr); 
	}
	public static String toJSON(Object arr){
		return gson.toJson(arr); 
	}
}
