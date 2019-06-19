package com.gh.action.business;

import com.opensymphony.xwork2.ActionSupport;

public class SelectAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 人员位置
	 * @return
	 */
	public String personSite(){
		
		return "personSite";
	}
	
	/**
	 * 人员轨迹
	 * @return
	 */
	public String personGJ(){
		
		return "personGJ";
	}
	/**
	 * 选择人员
	 * @return
	 */
	public String selectTJ(){
		
		return "selectTJ";
	}

	
	
	

}
