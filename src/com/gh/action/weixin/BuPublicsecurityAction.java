package com.gh.action.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

import com.gh.action.weixin.backstage.GetAccessToken;
import com.gh.action.weixin.front.userAppidReturn;
import com.gh.base.Action;
import com.gh.common.SMS;
import com.gh.entity.BuExamine;
import com.gh.entity.BuPublicsecurity;
import com.gh.service.BuExamineService;
import com.gh.service.BuHostelService;
import com.gh.service.BuPublicsecurityService;
import com.google.gson.Gson;

public class BuPublicsecurityAction extends Action {
	private BuPublicsecurityService buPublicsecurityService;// 公安
	private BuExamineService buExamineService;// 审核列表
	private BuHostelService buHostelService;
	private String phone;
	private List<BuExamine> buExamine_list;
	private String pageType;
	private String name;
	private String weixinid;
	private String code;
	private String Department;// 部门

	/**
	 * @author  查询是否有此微信ID
	 * @return
	 */
	public String isPhone() { 
		int i = buPublicsecurityService.getRecordCount(" WEIXINID='" + weixinid+"'");
		if (i > 0) {
			return "show";
		} else {
			if (code != null && code.length() > 0) { 
				String re =postSendMessage(
						"https://api.weixin.qq.com/sns/oauth2/access_token?appid="
								+ GetAccessToken.AppId + "&secret="
								+ GetAccessToken.AppSecret + "&code=" + code
								+ "&grant_type=authorization_code", "");
				Gson gson = new Gson();
				userAppidReturn userAppid = gson
						.fromJson(re, userAppidReturn.class);
					weixinid = userAppid.getOpenid();
				}
			return "phone";
		}
	}

	/**
	 * @see
	 * @return
	 */
	public String binding(){
		if (phone != null && phone.length() > 0) {
			List<BuPublicsecurity> bupublicsecurity_list=buPublicsecurityService.execSql("select * from BU_PUBLICSECURITY where MOBILE='"+phone+"'");
			if (bupublicsecurity_list != null && bupublicsecurity_list.size() > 0) {
				bupublicsecurity_list.get(0).setWeixinid(weixinid);
				buPublicsecurityService.updateSelective(bupublicsecurity_list.get(0));
				return "true";
			} 
		}
		return "false";
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

	/**
	 * @see 
	 * @return
	 * @throws IOException
	 */
	public String tverification() throws IOException {
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (phone != null && phone.length() > 0) {

			List<BuPublicsecurity> bupublicsecurity_list = buPublicsecurityService
					.execSql("select * from BU_PUBLICSECURITY where MOBILE='"
							+ phone + "'");
			if (bupublicsecurity_list != null
					&& bupublicsecurity_list.size() > 0) {
				bupublicsecurity_list.get(0).setPcode(rom());
				bupublicsecurity_list.get(0).setPtime(new Date());
				buPublicsecurityService.updateSelective(bupublicsecurity_list
						.get(0));
				SMS.sendAsms("", "", phone, "您的短信验证码为" 
						+ bupublicsecurity_list.get(0).getPcode()
						+ "，有效期限为10分钟", "");
			} else {
				out.print("该手机号不在库中，请联系管理员！");
			}
		} else {
			out.print("手机号不能为空！");
		}
		return null;
	}
	

	public static String rom() {
		Random random = new Random();
		String result = "";
		for (int i = 0; i < 6; i++) {
			result += random.nextInt(10);
		}
		return result;
	}

	/**
	 * @see post 发消息
	 */
	public static String postSendMessage(String httpsUrl, String context) {
		HttpsURLConnection urlCon = null;
		String result = "";
		try {
			urlCon = (HttpsURLConnection) (new URL(httpsUrl)).openConnection();
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setRequestMethod("GET");
			urlCon.setUseCaches(false);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlCon.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(new String(line.getBytes(), "utf-8"));
				result += new String(line.getBytes(), "utf-8");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public BuPublicsecurityService getBuPublicsecurityService() {
		return buPublicsecurityService;
	}

	public void setBuPublicsecurityService(
			BuPublicsecurityService buPublicsecurityService) {
		this.buPublicsecurityService = buPublicsecurityService;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeixinid() {
		return weixinid;
	}

	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public List<BuExamine> getBuExamine_list() {
		return buExamine_list;
	}

	public void setBuExamine_list(List<BuExamine> buExamine_list) {
		this.buExamine_list = buExamine_list;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
}
