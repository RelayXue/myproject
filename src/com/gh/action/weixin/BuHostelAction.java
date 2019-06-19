package com.gh.action.weixin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Random;



import com.gh.base.Action;
import com.gh.common.SMS;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuExamine;
import com.gh.entity.BuHostel;
import com.gh.entity.BuPublicsecurity;
import com.gh.service.BuExamineService;
import com.gh.service.BuHostelService;

public class BuHostelAction extends Action{
	private String phone_lv;
	private String name_lv;
	private String auditstatus;
	private String Department;//部门
	private BuHostel buHostel;
	private BuExamineService buExamineService;//审核列表
	private BuHostelService buHostelService;
	private List<BuExamine> buExamine_list;
	
	
	
	public List<BuExamine> getBuExamine_list() {
		return buExamine_list;
	}
	public void setBuExamine_list(List<BuExamine> buExamine_list) {
		this.buExamine_list = buExamine_list;
	}
	public BuHostel getBuHostel() {
		return buHostel;
	}
	public void setBuHostel(BuHostel buHostel) {
		this.buHostel = buHostel;
	}
	public String getAuditstatus() {
		return auditstatus;
	}
	public void setAuditstatus(String auditstatus) {
		this.auditstatus = auditstatus;
	}
	public BuExamineService getBuExamineService() {
		return buExamineService;
	}
	public void setBuExamineService(BuExamineService buExamineService) {
		this.buExamineService = buExamineService;
	}
	public BuHostelService getBuHostelService() {
		return buHostelService;
	}
	public void setBuHostelService(BuHostelService buHostelService) {
		this.buHostelService = buHostelService;
	}
	public String getName_lv() {
		return name_lv;
	}
	public void setName_lv(String name_lv) {
		this.name_lv = name_lv;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}



	public String isPhone_lv(){
		String sql="select * from bu_hostel where mobile="+phone_lv;
		int i=buHostelService.execSql(sql).size();
		if(i!=0){
			BuExamineAction buExamineAction=new BuExamineAction();
			buExamineAction.select_All();
		}
		return "phone_lv";
	}
	
	public String insertPhone_lv(){//当点击绑定的时候，公安注册，并跳转到查询页面
		
		BuExamine buExamine=new BuExamine();
		BuHostel buHostel=new BuHostel();
		
		Date date=new Date();
		buExamine.setEphone(phone_lv);
		buExamine.setCreatedate(date);
		buExamine.setAuditstatus(auditstatus);
		buExamine.setFuid(UUIDCreater.getUUID());
		buExamineService.save(buExamine);//审核插入
		
		buHostel.setHname(name_lv);
		buHostel.setMobile(phone_lv);
		buHostel.setExamine(auditstatus);
		buHostel.setFuid(UUIDCreater.getUUID());
		buHostelService.save(buHostel);//旅店插入
		return "true_lv";
	}
	
	public String show(){
		pageSize = 4;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buExamineService.getRecordCount(null);
		buExamine_list = buExamineService.selectByPage(indexPage, pageSize,
				null, " createdate desc");
		if (pageType != null && pageType.equals("page")) {
			return "BuExaminePage";
		} else {
			return "BuExamineData";
		}
	}
	
	public String tverification() throws IOException {
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (phone_lv != null && phone_lv.length() > 0) {
			
			String sql="select * from BU_HOSTEL where MOBILE='" + phone_lv + "'";
			List<BuHostel> buHostel_list = buHostelService.execSql(sql);
			System.out.println(buHostel_list.size());
			if (buHostel_list != null
					&& buHostel_list.size() > 0) {
				
				buHostel_list.get(0).setPcode(rom());
				buHostel_list.get(0).setPtime(new Date());
				buHostelService.updateSelective(buHostel_list.get(0));
				SMS.sendAsms("", "", phone_lv, "【乌镇民情】您的短信验证码为"
						+ buHostel_list.get(0).getPcode()
						+ "，有效期限为10分钟", "");
			} else {
				out.print("该手机号不在库中，请联系管理员！");
			}
		} else {
			out.print("手机号不能为空！");
		}
		return null;
	}
	
	public String getPhone_lv() {
		return phone_lv;
	}
	public void setPhone_lv(String phone_lv) {
		this.phone_lv = phone_lv;
	}
	public static String rom() {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}
		return result;
	}
}
