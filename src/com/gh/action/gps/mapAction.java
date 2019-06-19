package com.gh.action.gps;


import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.TransformJSON;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuAttractions;
import com.gh.service.BuAttractionsService;
import com.gh.common.Upload;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class mapAction  extends Action{
	
	
	private String id;
	private String skey;
	private BuAttractionsService buAttractionsService;
	private String Attractions_data;
	
	public String show(){
		List<BuAttractions>  buAttractions_list=buAttractionsService.execSql("select * from BU_ATTRACTIONS order by FULLNAME");
		Attractions_data=TransformJSON.toJSON(buAttractions_list);
		 return "map";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public BuAttractionsService getBuAttractionsService() {
		return buAttractionsService;
	}
	public void setBuAttractionsService(BuAttractionsService buAttractionsService) {
		this.buAttractionsService = buAttractionsService;
	}
	public String getAttractions_data() {
		return Attractions_data;
	}
	public void setAttractions_data(String attractions_data) {
		Attractions_data = attractions_data;
	}
	 
}
