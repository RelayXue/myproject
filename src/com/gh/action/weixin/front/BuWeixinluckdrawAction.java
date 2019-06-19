package com.gh.action.weixin.front;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.DateUtil;
import com.gh.common.ExcelUtil;

import com.gh.common.StringUtil;
import com.gh.common.UUIDCreater;
import com.opensymphony.xwork2.ActionSupport;



import java.util.*;

import javax.servlet.ServletContext;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;
  
 

import com.gh.entity.*;
import com.gh.dao.*;

import com.gh.service.*;  

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuWeixinluckdrawAction  extends Action{
	
	
	private BuWeixinluckdrawService buWeixinluckdrawService;
	private String uid;
	private String id;
	private String skey;//查询关键字
	private List<BuWeixinluckdraw> buWeixinluckdraw_list;
	private BuWeixinluckdraw buWeixinluckdraw;
	
	/*查询参数*/
	private String drawStartTime;		//抽奖开始时间
	private String drawEndTime;			//抽奖结束时间
	private String iswinning;			//是否中奖(1:是,0否)
	private String isaward;				//是否领奖(1:是,0否);
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buWeixinluckdraw");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		String SqlWhere="1=1";
		if(skey!=null&&skey.length()>0){
			SqlWhere+=" and username like '%"+skey+"%'";
		}
		if(drawStartTime!=null&&drawStartTime.length()>0){
			SqlWhere+=" and DATE_FORMAT(DRAWTIME,'%Y-%m-%d') >= "+"'"+drawStartTime+"'";
		}
		if(drawEndTime!=null&&drawEndTime.length()>0){
			SqlWhere+=" and DATE_FORMAT(DRAWTIME,'%Y-%m-%d') <= "+"'"+drawEndTime+"'";
		}
		if(iswinning!=null&&iswinning.length()>0){
			SqlWhere+=" and iswinning ="+iswinning;
		}
		/*if(isaward!=null&&isaward.length()>0){
			SqlWhere+=" and isaward ="+isaward;
		}
		 */		
		//--------------------
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buWeixinluckdrawService.getRecordCount(SqlWhere);
		//buWeixinluckdraw_list=buWeixinluckdrawService.selectByPage(indexPage,pageSize, SqlWhere, " ISWINNING DESC, ISAWARD ASC ,DRAWTIME DESC ");
		buWeixinluckdraw_list=buWeixinluckdrawService.selectByPage(indexPage,pageSize, SqlWhere, " DRAWTIME DESC ");
		return "buWeixinluckdraw";
	}
	
	/**
	 * 导出
	*/
	public String countDataExport() throws UnsupportedEncodingException{
		String downloadTime =  new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date());
		this.fileName = new String(("抽奖数据统计-"+downloadTime+".xls").getBytes("gb2312"),"iso8859-1");
		//查询出来的数据导出
		String sql = " SELECT * FROM bu_weixinluckdraw where ";
		String SqlWhere="1=1";
		if(skey!=null&&skey.length()>0){
			SqlWhere+=" and username like '%"+skey+"%'";
		}
		if(drawStartTime!=null&&drawStartTime.length()>0){
			SqlWhere+=" and DATE_FORMAT(DRAWTIME,'%Y-%m-%d') >= "+"'"+drawStartTime+"'";
		}
		if(drawEndTime!=null&&drawEndTime.length()>0){
			SqlWhere+=" and DATE_FORMAT(DRAWTIME,'%Y-%m-%d') <= "+"'"+drawEndTime+"'";
		}
		if(iswinning!=null&&iswinning.length()>0){
			SqlWhere+=" and iswinning ="+iswinning;
		}
		/*if(isaward!=null&&isaward.length()>0){
			SqlWhere+=" and isaward ="+isaward;
		}*/
		sql+=SqlWhere +" ORDER BY  DRAWTIME DESC ";
		buWeixinluckdraw_list=buWeixinluckdrawService.execSql(sql);
		
		List<String> heads = new ArrayList<String>();
		heads.add("用户ID");
		heads.add("用户昵称");
		heads.add("抽奖时间");
		heads.add("是否中奖(1：中奖,0:未领取)");
		heads.add("领奖时间");
		heads.add("是否领奖(1:领取,0:未领取)");
		
		List<String> titles = new ArrayList<String>();
		titles.add("【抽奖人员统计报表】");
		//titles.add("【统计范围："+str+"】【统计时间:"+DateUtil.format(new Date(),DateUtil.yyyy_MM_dd_HH_mm_ss)+"】【制表人："+"WANGJIN"+"】");
		List<List<String>> datas = new ArrayList<List<String>>();
		for(int i=0;i<buWeixinluckdraw_list.size();i++){
			BuWeixinluckdraw buWeixinluckdraw = buWeixinluckdraw_list.get(i);
			List<String> item = new ArrayList<String>();
			//item.add(buWeixinluckdraw.getFuid());
			item.add(buWeixinluckdraw.getUserid());
			item.add(buWeixinluckdraw.getUsername());
			//item.add(buWeixinluckdraw.getDrawtime());//抽奖时间
			item.add(DateUtil.format(buWeixinluckdraw.getDrawtime(), "yyyy-MM-dd HH:mm:ss"));
			item.add(buWeixinluckdraw.getIswinning());
			item.add(DateUtil.format(buWeixinluckdraw.getAwardtime(),"yyyy-MM-dd HH:mm:ss"));//领奖时间
			item.add((buWeixinluckdraw.getIsaward()));//是否领奖
			datas.add(item);
		}
		
		ExcelUtil excelUtil = new ExcelUtil("抽奖活动",titles,16, heads, datas);
		inputStream = excelUtil.export();
		excelUtil.export();
	
		return "export";
	} 
	
	/***
	 * 领取奖品操作
	 * @return
	 * @throws Exception
	 */
	public String checkInfo() throws Exception {
		if(StringUtil.isNotBlank(id)){
			//领奖后设置 isaward=1 时间更新为当前系统时间
			buWeixinluckdraw = buWeixinluckdrawService.findById(id);
			buWeixinluckdraw.setIsaward("1");
			buWeixinluckdraw.setAwardtime(new Date());
			buWeixinluckdrawService.updateSelective(buWeixinluckdraw);
		}
		return this.show();
	}
	
	/**
	 * @see 修改及新增
	 * @author xiao
	 */
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buWeixinluckdraw.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buWeixinluckdraw");
			if(!com.getHisUpdate()){
				return "error";
			}
			buWeixinluckdrawService.updateSelective(buWeixinluckdraw);
		}else{
			com=CompetenceManager.getCom(roleid, "buWeixinluckdraw");
			if(!com.getHisUpdate()){
				return "error";
			}
			buWeixinluckdraw.setFuid(UUIDCreater.getUUID());
			buWeixinluckdrawService.save(buWeixinluckdraw);
		}
		return this.show();
	}
	
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buWeixinluckdraw=buWeixinluckdrawService.findById(id);
		return "buWeixinluckdrawEditor";
	}
	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		com = CompetenceManager.getCom(roleid, "buWeixinluckdraw");
		if (!com.getHisDelete()) {
			return "error";
		}
		if (uid != null && uid.length() > 0) {
			String ids[] = uid.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					buWeixinluckdrawService.delete(ids[a]);
				}
			}
		} else {
			if (id != null && id.length() > 0) {
				buWeixinluckdrawService.delete(id);
			}
		}
		return "show";
	}
	
	
	/*------set && get----------*/
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
	public BuWeixinluckdrawService getBuWeixinluckdrawService() {
		return buWeixinluckdrawService;
	}
	public void setBuWeixinluckdrawService(BuWeixinluckdrawService buWeixinluckdrawService) {
		this.buWeixinluckdrawService = buWeixinluckdrawService;
	}
	public List<BuWeixinluckdraw> getBuWeixinluckdraw_list() {
		return buWeixinluckdraw_list;
	}
	public void setBuWeixinluckdraw_list(List<BuWeixinluckdraw> buWeixinluckdraw_list) {
		this.buWeixinluckdraw_list = buWeixinluckdraw_list;
	}
	public BuWeixinluckdraw getBuWeixinluckdraw() {
		return buWeixinluckdraw;
	}
	public void setBuWeixinluckdraw(BuWeixinluckdraw buWeixinluckdraw) {
		this.buWeixinluckdraw = buWeixinluckdraw;
	}

	public String getDrawStartTime() {
		return drawStartTime;
	}

	public void setDrawStartTime(String drawStartTime) {
		this.drawStartTime = drawStartTime;
	}

	public String getDrawEndTime() {
		return drawEndTime;
	}

	public void setDrawEndTime(String drawEndTime) {
		this.drawEndTime = drawEndTime;
	}

	public String getIswinning() {
		return iswinning;
	}

	public void setIswinning(String iswinning) {
		this.iswinning = iswinning;
	}

	public String getIsaward() {
		return isaward;
	}

	public void setIsaward(String isaward) {
		this.isaward = isaward;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	
	
	
}
