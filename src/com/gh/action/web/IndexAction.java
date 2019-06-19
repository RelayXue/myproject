package com.gh.action.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.gh.base.Action;
import com.gh.common.DateUtil;
import com.gh.common.StringUtil;
import com.gh.entity.BuNews;
import com.gh.entity.BuWeather;
import com.gh.entity.OpenEntity;
import com.gh.service.BuNewsService;
import com.gh.service.BuWeatherService;

/**
 * @ClassName Index 
 * @Description web首页
 * @author ieastar
 * @date 2014-11-11
 * @version V1.0
 */
public class IndexAction extends Action{
	
	private String q="";
	
	private String name;
	
	private String xy;
	
	private OpenEntity oe;
	private BuWeather bw;
	private BuWeatherService buWeatherService;
	private String currentTime;
	private static final long serialVersionUID = 1L;
	
	public String index() throws UnsupportedEncodingException{
		
		if(oe==null){
			oe = new OpenEntity();
		}
		if(StringUtil.isNotTrimBlank(q)){
			//情宿今宵|120.48720981,30.75231226|oe_l
			q= URLDecoder.decode(q, "utf-8");
			String str [] = q.split("\\|");
			//前往
			if(str.length==3){
				name = str[0];
				xy = str[1];
				setOpenWindow(str[2]);
				q="1";
			}
		}
		currentTime = DateUtil.format(Calendar.getInstance().getTime(),"yyyy-MM-dd HH:mm:ss");
		return SUCCESS;
	}
	
	private void setOpenWindow(String o){
		if(o.indexOf("oe_a")!=-1){
			oe.setFood(true);
		} 
		if(o.indexOf("oe_b")!=-1){
			oe.setStay(true);
		} 
		if(o.indexOf("oe_c")!=-1){
			oe.setEntertainment(true);
		}
		if(o.indexOf("oe_d")!=-1){
			oe.setTraffic(true);
		}
		if(o.indexOf("oe_e")!=-1){
			oe.setBusiness(true);
		}
		if(o.indexOf("oe_f")!=-1){
			oe.setSearch(true);
		}
		if(o.indexOf("oe_g")!=-1){
			oe.setViewSearch(true);
		}
		if(o.indexOf("oe_h")!=-1){
			oe.setFullScreen(true);
		}
		if(o.indexOf("oe_i")!=-1){
			oe.setView360(true);
		}
		if(o.indexOf("oe_j")!=-1){
			oe.setMonitorTraffic(true);
		}
		if(o.indexOf("oe_k")!=-1){
			oe.setShare(true);
		}
		if(o.indexOf("oe_l")!=-1){
			oe.setCar(true);
			q="10";
		}
		if(o.indexOf("oe_m")!=-1){
			oe.setWalk(true);
		}
	}

	public OpenEntity getOe() {
		return oe;
	}

	public void setOe(OpenEntity oe) {
		this.oe = oe;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getXy() {
		return xy;
	}

	public void setXy(String xy) {
		this.xy = xy;
	}

	public BuWeather getBw() {
		bw = buWeatherService.findById("1");
		return bw;
	}

	public void setBw(BuWeather bw) {
		this.bw = bw;
	}

	public BuWeatherService getBuWeatherService() {
		return buWeatherService;
	}

	public void setBuWeatherService(BuWeatherService buWeatherService) {
		this.buWeatherService = buWeatherService;
	}

	public String getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(String currentTime) {
		this.currentTime = currentTime;
	}

}
