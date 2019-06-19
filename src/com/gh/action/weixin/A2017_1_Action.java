package com.gh.action.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.gh.action.weixin.backstage.GetAccessToken;
import com.gh.action.weixin.front.userAppidReturn;
import com.gh.base.Action;
import com.gh.common.DateUtil;
import com.gh.common.SMS;
import com.gh.common.UUIDCreater;
import com.gh.entity.A20171bridgeroad;
import com.gh.entity.A20171user;
import com.gh.service.A20171bridgeroadService;
import com.gh.service.A20171userService;
import com.google.gson.Gson;

public class A2017_1_Action extends Action{
	private A20171bridgeroadService a20171bridgeroadService;
	private A20171userService a20171userService;
	private List<A20171bridgeroad> list_a20171bridgeroad;
	private List<A20171user> list_a20171user;
	private A20171bridgeroad a20171bridgeroad;
	private A20171user a20171user;
	private String id;
	private String weixinid;
	private String code;
	private String is="false";
	private String phone;
	private Date time;
	
	/**
	 * 后台——路、桥基础信息
	 */
	public String list(){
		pageSize =6;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage=a20171bridgeroadService.getRecordCount(null);
		list_a20171bridgeroad = a20171bridgeroadService.selectByPage(indexPage, pageSize, null, null);
		return "list";
	}
	
	public String edit(){
		if(id!=null && !("".equals(id))){
			a20171bridgeroad=a20171bridgeroadService.findById(id);
		}
		return "edit";
	}
	
	public String addOrEdit(){
		if(a20171bridgeroad.getFuid()!=null && !("".equals(a20171bridgeroad.getFuid()))){
			a20171bridgeroadService.updateSelective(a20171bridgeroad);
		}else{
			a20171bridgeroad.setFuid(UUIDCreater.getUUID());
			a20171bridgeroad.setCreatetime(new Date());
			a20171bridgeroadService.save(a20171bridgeroad);
		}
		return "addOrEdit";
	}
	
	public String delete(){
		a20171bridgeroadService.delete(id);
		return "addOrEdit";
	}
	
	/**
	 * 后台——参与人员数据
	 */
	public String listPeople(){
		pageSize =6;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage=a20171userService.getRecordCount(null);
		list_a20171user = a20171userService.selectByPage(indexPage, pageSize, null, "createtime asc");
		return "listPeople";
	}
	public String updateIswing(){
		A20171user entity=a20171userService.findById(a20171user.getFuid());
		entity.setIsWinning(a20171user.getIsWinning());
		a20171userService.update(entity);
		return "updateIswing";
	}
	
	public String exportFront50() throws IOException{
		String da=DateUtil.getStringDate1(time);
		list_a20171user=a20171userService.execSql("select * from a2017_1_user where createTime>='"+da+" 00:00:00' and createTime<='"+da+" 23:59:59' order by createTime asc");
		String title=da+"——前50位幸运用户";
				
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet(title);
		HSSFRow row=sheet.createRow(0);
		row.createCell(0).setCellValue("姓名");
		row.createCell(1).setCellValue("电话");
		row.createCell(2).setCellValue("参加时间");
		for(int i=0;i<list_a20171user.size();i++){
			row=sheet.createRow(i+1);
			row.createCell(0).setCellValue(list_a20171user.get(i).getName());
			row.createCell(1).setCellValue(list_a20171user.get(i).getPhone());
			row.createCell(2).setCellValue(DateUtil.getStringDate1(list_a20171user.get(i).getCreatetime()));
		}
		response.setContentType("applicationnd.ms-excel");    
	    response.setHeader("Content-disposition", "attachment;filename="+ new String("幸运用户信息.xls".getBytes("GB2312"),"ISO8859-1"));
	    OutputStream ouputStream = response.getOutputStream();    
	    wb.write(ouputStream);    
	    ouputStream.flush();    
	    ouputStream.close();
		return null;
	}
	
	public String exportWinning() throws IOException{
		list_a20171user=a20171userService.execSql("select * from a2017_1_user where is_winning=2 order by createTime asc");
		String title="中奖者名单";
				
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet(title);
		HSSFRow row=sheet.createRow(0);
		row.createCell(0).setCellValue("姓名");
		row.createCell(1).setCellValue("电话");
		row.createCell(2).setCellValue("现在暂命名路、桥");
		row.createCell(3).setCellValue("参赛者命名路、桥");
		row.createCell(4).setCellValue("参加时间");
		for(int i=0;i<list_a20171user.size();i++){
			row=sheet.createRow(i+1);
			row.createCell(0).setCellValue(list_a20171user.get(i).getName());
			row.createCell(1).setCellValue(list_a20171user.get(i).getPhone());
			row.createCell(2).setCellValue(list_a20171user.get(i).getBridgeroadTemporaryName());
			row.createCell(3).setCellValue(list_a20171user.get(i).getBridgeroadName());
			row.createCell(4).setCellValue(DateUtil.getStringDate1(list_a20171user.get(i).getCreatetime()));
		}
		response.setContentType("applicationnd.ms-excel");    
	    response.setHeader("Content-disposition", "attachment;filename="+ new String("中奖者名单.xls".getBytes("GB2312"),"ISO8859-1"));
	    OutputStream ouputStream = response.getOutputStream();    
	    wb.write(ouputStream);    
	    ouputStream.flush();    
	    ouputStream.close();
		return null;
	}
	
	
	/**
	 * 微信端
	 * @return
	 */
	public String getWeiXinId(){
		if (weixinid==null || "".equals(weixinid)) {
			if (code != null && code.length() > 0) {
				String re =postSendMessage(
						"https://api.weixin.qq.com/sns/oauth2/access_token?appid="
								+ GetAccessToken.AppId + "&secret="
								+ GetAccessToken.AppSecret + "&code=" + code
								+ "&grant_type=authorization_code", "");
				Gson gson = new Gson();
				userAppidReturn userAppid = gson.fromJson(re, userAppidReturn.class);
				weixinid = userAppid.getOpenid();
				if(weixinid==null || "".equals(weixinid)){//没有关注的时候
					return "QRcode";
				}
				
				String re1 =postSendMessage("https://api.weixin.qq.com/cgi-bin/user/info?access_token="
						+ GetAccessToken.AccessToken + "&openid="
						+ weixinid + "&lang=zh_CN", "");
				
				JSONObject jsStr = JSONObject.fromObject(re1);
				System.out.println("re1-----"+jsStr.getString("subscribe"));
				int subscribe=Integer.parseInt(jsStr.getString("subscribe"));
				if(subscribe==0){
					return "QRcode";
				}
				
			}
			//weixinid="oSqiqt4e0GW-hMM-TqSpRr0GI9Kg";
			HttpSession session=ServletActionContext.getRequest().getSession();
			session.setAttribute("weixinid", weixinid);
			return "success";
		}
		return null;
	}
	
	public String listRoad(){
		list_a20171bridgeroad=a20171bridgeroadService.execSql("select * from a2017_1_bridgeroad");
		return "listRoad";
	}
	
	public String rename(){//路、桥重命名
		HttpSession session=ServletActionContext.getRequest().getSession();
		weixinid=(String) session.getAttribute("weixinid");
		
		if(weixinid!=null && !("".equals(weixinid))){
			List<A20171user> list=a20171userService.execSql("select * from a2017_1_user where weixinid='"+weixinid+"'");
			if(list.size()>0){
				a20171user=list.get(0);
				if(a20171user.getBridgeroadTemporaryId().equals(id)){//当该参与者是给该路、桥命名的时候
					is="true";
				}
			}
		}else{
			return "QRcode";//分享详情页无微信ID时
		}
		a20171bridgeroad=a20171bridgeroadService.findById(id);
		return "rename";
	}
	
	//验证手机号是否已经存在，不存在发送验证码
	public String isPhone() throws IOException{
		response.setContentType("text/html;charset=utf8");
		PrintWriter out=response.getWriter();
		Map<String, Object> map=new HashMap<String, Object>();
		weixinid=(String) request.getSession().getAttribute("weixinid");
		
		
		int isHave=a20171userService.getRecordCount("phone='"+phone+"' or weixinid='"+weixinid+"'");
		if(isHave>0){
			map.put("code", 500);
			map.put("value", "您已经参与过该活动了");
			out.print(toJson(map));
			return null;
		}
		String code=rom();
		SMS.sendAsms("", "", phone, "您的短信验证码为"+code, "");
		map.put("code", 200);
		map.put("value", code);
		out.print(toJson(map));
		return null;
	}
	
	public String save(){
		a20171user.setFuid(UUIDCreater.getUUID());
		a20171user.setCreatetime(new Date());
		a20171user.setIsWinning(1);
		a20171userService.save(a20171user);
		return "success";
	}
	
	
	public A20171bridgeroadService getA20171bridgeroadService() {
		return a20171bridgeroadService;
	}
	public void setA20171bridgeroadService(
			A20171bridgeroadService a20171bridgeroadService) {
		this.a20171bridgeroadService = a20171bridgeroadService;
	}
	public A20171userService getA20171userService() {
		return a20171userService;
	}
	public void setA20171userService(A20171userService a20171userService) {
		this.a20171userService = a20171userService;
	}
	public List<A20171bridgeroad> getList_a20171bridgeroad() {
		return list_a20171bridgeroad;
	}
	public void setList_a20171bridgeroad(
			List<A20171bridgeroad> list_a20171bridgeroad) {
		this.list_a20171bridgeroad = list_a20171bridgeroad;
	}
	public A20171bridgeroad getA20171bridgeroad() {
		return a20171bridgeroad;
	}
	public void setA20171bridgeroad(A20171bridgeroad a20171bridgeroad) {
		this.a20171bridgeroad = a20171bridgeroad;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public A20171user getA20171user() {
		return a20171user;
	}

	public void setA20171user(A20171user a20171user) {
		this.a20171user = a20171user;
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

	public String getIs() {
		return is;
	}

	public void setIs(String is) {
		this.is = is;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<A20171user> getList_a20171user() {
		return list_a20171user;
	}

	public void setList_a20171user(List<A20171user> list_a20171user) {
		this.list_a20171user = list_a20171user;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
		/**
		 * 二维码界面
		 */
		public String QRcode(){
			return "QRcode";
		}
		/**
		 * 随机数
		 */
		public static String rom() {
			Random random = new Random();
			String result = "";
			for (int i = 0; i < 4; i++) {
				result += random.nextInt(10);
			}
			return result;
		}
}
