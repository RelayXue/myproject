package com.gh.action.weixin;

import java.util.Date;
import java.util.List;


import com.gh.base.Action;
import com.gh.common.StringIntercept;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuExamine;
import com.gh.service.BuExamineService;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

public class BuExamineAction extends Action{
	
	private static final long serialVersionUID = 1L;
	private BuExamineService buExamineService;
	private List<BuExamine> buExamine_list;
	private BuExamine buExamine;//实体类
	private String isAgree;
	
	
	public String getIsAgree() {
		return isAgree;
	}
	public void setIsAgree(String isAgree) {
		this.isAgree = isAgree;
	}
	public List<BuExamine> getBuExamine_list() {
		return buExamine_list;
	}
	public void setBuExamine_list(List<BuExamine> buExamine_list) {
		this.buExamine_list = buExamine_list;
	}
	public BuExamineService getBuExamineService() {
		return buExamineService;
	}
	public void setBuExamineService(BuExamineService buExamineService) {
		this.buExamineService = buExamineService;
	}
	public BuExamine getBuExamine() {
		return buExamine;
	}
	public void setBuExamine(BuExamine buExamine) {
		this.buExamine = buExamine;
	}
	/**
	 * @return
	 * @throws NumberFormatException
	 * @throws ParseException
	 * @throws java.text.ParseException
	 */
	public String updateBuExamine(){
		StringIntercept date=new StringIntercept();
		if(isAgree.equals("同意")){
			buExamine.setAuditstatus("1");
		}
		if(isAgree.equals("不同意")){
			buExamine.setAuditstatus("0");
		}
		buExamine.setEphone(date.intercept(buExamine.getEphone()));
		buExamineService.update(buExamine);
		return "isTrue";
	}
	/**
	 * tab_BuExamine分页查询操作
	 * Security：公安
	 * @return
	 */
	public String select_All(){
		String department=request.getParameter("department");
		if(department==null){
			return "err";
		}
		pageSize = 4;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buExamineService.getRecordCount(null);
		buExamine_list = buExamineService.selectByPage(indexPage, pageSize, null," createdate desc");
		if (pageType != null && pageType.equals("page")) {
			return department.equals("public_security")?"BuExaminePage":"BuExaminePage_lv";
		} else {
			return department.equals("public_security")?"BuExamineData":"BuExamineData_lv";
		}
	}
	
	
	/**
	 * 根据id查询
	 */
	public String select_All_where(){
		String department=request.getParameter("department");
		String fuid=request.getParameter("fuid");
		
		String sql="select * from bu_examine where fuid='"+fuid+"'";
		
		buExamine_list=buExamineService.execSql(sql);
		
		if(department.equals("public_security")){
			return "public_security";
		}else{
			return "Inn";
		}
	}
	
	/**
	 * 修改数据
	 * BuExamine
	 */
	public String updateBuExamine_All(){
		buExamineService.update(buExamine);
		return "true";
	}
	/**
	 * 插入数据
	 * BuExamine
	 */
	public String insertBuExamine(){
		Date date=new Date();
		buExamine.setArrivetime(date);
		buExamine.setAuditstatus("0");
		buExamine.setFuid(UUIDCreater.getUUID());
		buExamineService.save(buExamine);
		return "insertBuExamine";
	}
}
