package com.gh.common;

/**
 * @ClassName SystemTypeConstant 
 * @Description 系统常量配置
 * @author ieastar
 * @date 2014-11-16
 * @version V1.0
 */
public class SystemTypeConstant {
	
	public static String DATA_TYPE_DINING = "002018";
	
	public static String DATA_TYPE_STAY = "002017";
	
	public static String DATA_TYPE_SHOPPING = "002015";
	
	public static String DATA_TYPE_FUNNY = "002016";
	
	public static String DATA_TYPE_GEOGRAPHY = "4";
	
	public static String getTableName(String t){
		String table = "";
		if(t.equals(DATA_TYPE_STAY)){
			table = "bu_stay";
		}else if(t.equals(DATA_TYPE_SHOPPING)){
			table = "bu_entertainmentshopping";
		}else if(t.equals(DATA_TYPE_GEOGRAPHY)){
			table = "bu_geography";
		}else{
			table = "bu_dining";
		}
		return table;
	}

}
