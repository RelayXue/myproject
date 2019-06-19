package com.gh.action.touch;


import java.util.List;

import com.gh.base.Action;
import com.gh.entity.BuNews;
import com.gh.service.BuNewsService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class indexAction  extends Action{
	
	
	private BuNewsService buNewsService;
	private String re;
	private String skey;
	private String id;
	private List<BuNews> buNews_list;
	private List<BuNews> buNews02_list;
	private BuNews buNews;
	
	/**
	 * @see 首页
	 * @return
	 */
	public String show(){
		buNews_list=buNewsService.execSql("select * from bu_news where type='004007'");
		buNews02_list=buNewsService.execSql("select * from bu_news where type='004002'");
		
		return "index";
	}
	/**
	 * @see 乌镇历史
	 * @return
	 */
	public String history(){
		buNews_list=buNewsService.execSql("select * from bu_news where type='004009'");
		return "history";
	}
	/**
	 * @see 乌镇地理
	 * @return
	 */
	public String geography(){
		buNews_list=buNewsService.execSql("select * from bu_news where type='004008'");
		return "geography";
	}
	/**
	 * @see 乌镇名俗
	 * @return
	 */
	public String folklore(){
		buNews_list=buNewsService.execSql("select * from bu_news where type='004010'");
		return "folklore";
	}
	/**
	 * @see  乌镇故事
	 * @return
	 */
	public String story(){
		buNews_list=buNewsService.execSql("select * from bu_news where type='004011'");
		return "story";
	}
	/**
	 * @see 乌镇名人
	 * @return
	 */
	public String celebrity(){
		buNews_list=buNewsService.execSql("select * from bu_news where type='004012'");
		return "celebrity";
	}
	/**
	 * @see 乌镇保护
	 *  @return
	 */
	public String protection(){
		buNews_list=buNewsService.execSql("select * from bu_news where type='004013'");
		return "protection";
	}
	/**
	 * @see 新鲜速递
	 *  @return
	 */
	public String fresh(){
		buNews=buNewsService.findById(id);
		return "fresh";
	}


	public BuNewsService getBuNewsService() {
		return buNewsService;
	}


	public void setBuNewsService(BuNewsService buNewsService) {
		this.buNewsService = buNewsService;
	}


	public String getRe() {
		return re;
	}


	public void setRe(String re) {
		this.re = re;
	}


	public String getSkey() {
		return skey;
	}


	public void setSkey(String skey) {
		this.skey = skey;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public List<BuNews> getBuNews_list() {
		return buNews_list;
	}


	public void setBuNews_list(List<BuNews> buNews_list) {
		this.buNews_list = buNews_list;
	}


	public BuNews getBuNews() {
		return buNews;
	}


	public void setBuNews(BuNews buNews) {
		this.buNews = buNews;
	}
	public List<BuNews> getBuNews02_list() {
		return buNews02_list;
	}
	public void setBuNews02_list(List<BuNews> buNews02_list) {
		this.buNews02_list = buNews02_list;
	}
	 
}
