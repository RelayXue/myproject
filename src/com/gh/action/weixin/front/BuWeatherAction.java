package com.gh.action.weixin.front;

import java.util.List;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.entity.BuWeather;
import com.gh.service.BuWeatherService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuWeatherAction  extends Action{
	
	
	private BuWeatherService buWeatherService;
	private String uid;
	private String id;
	private String skey;
	private List<BuWeather> buWeather_list;
	private BuWeather buWeather;
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		buWeather_list=buWeatherService.execSql("select * from bu_weather where fuid<5  order by fuid");
		return "buWeather";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public BuWeatherService getBuWeatherService() {
		return buWeatherService;
	}
	public void setBuWeatherService(BuWeatherService buWeatherService) {
		this.buWeatherService = buWeatherService;
	}
	public List<BuWeather> getBuWeather_list() {
		return buWeather_list;
	}
	public void setBuWeather_list(List<BuWeather> buWeather_list) {
		this.buWeather_list = buWeather_list;
	}
	public BuWeather getBuWeather() {
		return buWeather;
	}
	public void setBuWeather(BuWeather buWeather) {
		this.buWeather = buWeather;
	}
}
