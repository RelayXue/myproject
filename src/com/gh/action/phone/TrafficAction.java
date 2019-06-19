package com.gh.action.phone;

import java.util.List;

import net.sf.json.util.JSONUtils;

import com.gh.base.Action;
import com.gh.entity.BuNews;
import com.gh.phone.JSONPR;
import com.gh.phone.ReturnInfo;
import com.gh.phone.StringUtil;
import com.gh.service.BuNewsService;

public class TrafficAction extends Action {
	
	BuNewsService buNewsService;
	
	int count1;	//实时路况播报点赞数
	int count2;	//交通时刻表点赞数
	int count3;	//自驾交通情况点赞数
	String category;
	int readnum1;	//阅读数量
	int readnum2;
	
	List<BuNews> news_list;
	BuNews news;
	String id;
	
	ReturnInfo ri = new ReturnInfo();
	
	/**
	 * 交通播报主页
	 * @return
	 */
	public String showMain(){
		try {
			count1 = buNewsService.SumFieldForPraise(" type = '004014' and deletemark = '0'");
			count2 = buNewsService.SumFieldForPraise(" type = '004015' and deletemark = '0'");
			count3 = buNewsService.SumFieldForPraise(" type = '004017' and deletemark = '0'");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "traffic_news";
	}
	
	/**
	 * 实时路况播报列表
	 * @return
	 */
	public String syncTrafficList(){
		try {
			news_list = buNewsService.selectByPage(indexPage, 15, " type = '004014' and deletemark = '0'", "createdate desc");
			if(news_list.size() > 0){
				ri.setO(news_list);
				ri.setCode(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		ReturnInfo.printJson(request, response, ri, false);
		return "sync_traffic_list";
	}
	
	/**
	 * 交通时刻表列表
	 * @return
	 */
	public String timetableList(){
		news_list = buNewsService.selectByPage(indexPage, 15, " type = '004015' and deletemark = '0'", "createdate desc");
		if(news_list.size() > 0){
			ri.setO(JSONPR.list2json(news_list, "fuid;fullname;praise"));
			ri.setCode(1);
		}
		ReturnInfo.printJson(request, response, ri, false);
		return "timetable_list";
	}
	
	/**
	 * 停车服务列表
	 * @return
	 */
	public String parkingServiceList(){
		try {
			news_list = buNewsService.selectByPage(indexPage, 15, " type = '004016' and deletemark = '0'", "createdate desc");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(news_list.size() > 0){
			ri.setO(news_list);
			ri.setCode(1);
		}
		ReturnInfo.printJson(request, response, ri, false);
		return "parking_service_list";
	}
	
	/**
	 * 自驾交通情况
	 * @return
	 */
	public String selfDriveTrafficSituation(){
		news_list = buNewsService.selectByPage(indexPage, 15, " type = '004017' and deletemark = '0'", "createdate desc");
		if(news_list.size() > 0){
			ri.setO(news_list);
			ri.setCode(1);
		}
		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	/**
	 * 推荐路线主页
	 * @return
	 */
	public String recommendRouteMain(){
		//点赞数
		count1 = buNewsService.SumFieldForPraise(" type = '004026' and deletemark = '0'");
		count2 = buNewsService.SumFieldForPraise(" type = '004027' and deletemark = '0'");
		
		//阅读数
		readnum1 = buNewsService.SumFieldForReadNum(" type = '004026' and deletemark = '0'");
		readnum2 = buNewsService.SumFieldForReadNum(" type = '004027' and deletemark = '0'");
		
		//热线推荐
		news_list = buNewsService.execSql("select * from bu_news where  type in ('004026','004027') and deletemark = '0'order by praise desc limit 0,15");
		return "recommend_route_main";
	}
	
	/**
	 * 推荐路线列表
	 * @return
	 */
	public String recommendRouteList(){
		news_list = buNewsService.selectByPage(indexPage, 15, " type = '"+category+"' and deletemark = '0'", "createdate desc");
		if(news_list.size() > 0){
			ri.setO(news_list);
			ri.setCode(1);
		}
		ReturnInfo.printJson(request, response, ri, false);
		return null;
	}
	
	/**
	 * 新闻详细信息
	 * @return
	 */
	public String detail(){
		news = buNewsService.findById(id);
		
		//阅读数+1
		news.setReadnum(StringUtil.toInt(news.getReadnum())+1);
		buNewsService.update(news);
		
		return "traffic_news_detail";
	}

	public BuNewsService getBuNewsService() {
		return buNewsService;
	}


	public void setBuNewsService(BuNewsService buNewsService) {
		this.buNewsService = buNewsService;
	}


	public int getCount1() {
		return count1;
	}


	public void setCount1(int count1) {
		this.count1 = count1;
	}


	public int getCount2() {
		return count2;
	}


	public void setCount2(int count2) {
		this.count2 = count2;
	}


	public int getCount3() {
		return count3;
	}


	public void setCount3(int count3) {
		this.count3 = count3;
	}

	public List<BuNews> getNews_list() {
		return news_list;
	}

	public void setNews_list(List<BuNews> news_list) {
		this.news_list = news_list;
	}

	public BuNews getNews() {
		return news;
	}

	public void setNews(BuNews news) {
		this.news = news;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ReturnInfo getRi() {
		return ri;
	}

	public void setRi(ReturnInfo ri) {
		this.ri = ri;
	}

	public int getReadnum1() {
		return readnum1;
	}

	public void setReadnum1(int readnum1) {
		this.readnum1 = readnum1;
	}

	public int getReadnum2() {
		return readnum2;
	}

	public void setReadnum2(int readnum2) {
		this.readnum2 = readnum2;
	}
}
