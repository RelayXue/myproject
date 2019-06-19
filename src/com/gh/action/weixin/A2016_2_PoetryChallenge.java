package com.gh.action.weixin;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.jms.Session;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.gh.action.weixin.backstage.GetAccessToken;
import com.gh.action.weixin.backstage.SHA1;
import com.gh.action.weixin.front.WeixinUserInfo;
import com.gh.action.weixin.front.userAppidReturn;
import com.gh.base.Action;
import com.gh.common.DateUtil;
import com.gh.common.FileUpload;
import com.gh.common.HtmlUtil;
import com.gh.common.ImageCompression;
import com.gh.common.TuJingUtil;
import com.gh.common.UUIDCreater;
import com.gh.entity.A20161activity;
import com.gh.entity.A20161characterworks;
import com.gh.entity.A20161click;
import com.gh.entity.A20162subject;
import com.gh.entity.A20162user;
import com.gh.service.A20161activityService;
import com.gh.service.A20161characterworksService;
import com.gh.service.A20161clickService;
import com.gh.service.A20162subjectService;
import com.gh.service.A20162userService;
import com.google.gson.Gson;

public class A2016_2_PoetryChallenge extends Action{
	private A20161activityService a20161activityService;
	private A20161activity a20161activity;
	private A20162subjectService a20162subjectService;
	private A20162userService a20162userService;
	private List<A20162subject> list_a20162subject;
	private A20162subject a20162subject;
	private A20162user a20162user;
	
	
	private String code;
	private String weixinid;
	private String id;
	private int num;
	private String answer;
	private String name;
	private String phone;
	private int numCount;
	//微信接口
	private String nonceStr;
	private long timestamp;
	private String signature;
	
	//后台方法
	public String list(){
		pageSize =6;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage=a20162subjectService.getRecordCount(null);
		list_a20162subject = a20162subjectService.selectByPage(indexPage, pageSize, null, null);
		return "list";
	}
	public String add(){
		return "add";
	}
	public String update(){
		a20162subject=a20162subjectService.findById(id);
		if(a20162subject.getOne()!=null && !("".equals(a20162subject.getOne()))){
			a20162subject.setOne(a20162subject.getOne().substring(2, a20162subject.getOne().length()));
		}
		if(a20162subject.getTwo()!=null && !("".equals(a20162subject.getTwo()))){
			a20162subject.setTwo(a20162subject.getTwo().substring(2, a20162subject.getTwo().length()));
		}
		if(a20162subject.getThree()!=null && !("".equals(a20162subject.getThree()))){
			a20162subject.setThree(a20162subject.getThree().substring(2, a20162subject.getThree().length()));
		}
		if(a20162subject.getFour()!=null && !("".equals(a20162subject.getFour()))){
			a20162subject.setFour(a20162subject.getFour().substring(2, a20162subject.getFour().length()));
		}
		return "add";
	}
	public String addOrUpdate(){
		//选项一
		if(a20162subject.getOne()==null || "".equals(a20162subject.getOne())){
			a20162subject.setOne("");
		}else{
			a20162subject.setOne("A、"+a20162subject.getOne());
		}
		//选项二
		if(a20162subject.getTwo()==null || "".equals(a20162subject.getTwo())){
			a20162subject.setTwo("");
		}else{
			a20162subject.setTwo("B、"+a20162subject.getTwo());
		}
		//选项三
		if(a20162subject.getThree()==null || "".equals(a20162subject.getThree())){
			a20162subject.setThree("");
		}else{
			a20162subject.setThree("C、"+a20162subject.getThree());
		}
		//选项四
		if(a20162subject.getFour()==null || "".equals(a20162subject.getFour())){
			a20162subject.setFour("");
		}else{
			a20162subject.setFour("D、"+a20162subject.getFour());
		}
		
		if(a20162subject.getFuid()==null || "".equals(a20162subject.getFuid())){
			a20162subject.setFuid(UUIDCreater.getUUID());
			a20162subject.setCreatetime(new Date());
			a20162subjectService.save(a20162subject);
		}else{
			a20162subjectService.updateSelective(a20162subject);
		}
		return "addOrUpdate";
	}
	public String delete(){
		a20162subjectService.delete(id);
		return "addOrUpdate";
	}
	
	
	// 获取微信ID
	public String getWeiXinId(){
		if (weixinid==null || "".equals(weixinid)) {
			/*if (code != null && code.length() > 0) {
				String re =postSendMessage(
						"https://api.weixin.qq.com/sns/oauth2/access_token?appid="
								+ GetAccessToken.AppId + "&secret="
								+ GetAccessToken.AppSecret + "&code=" + code
								+ "&grant_type=authorization_code", "");
				Gson gson = new Gson();
				userAppidReturn userAppid = gson.fromJson(re, userAppidReturn.class);
				weixinid = userAppid.getOpenid();
				
				String sub=re.substring(re.indexOf("subscribe")+11, re.indexOf("subscribe")+12);
				if("0".equals(sub)){
					return "QRcode";
				}
			}*/
			//weixinid="oSqiqt8UbL8qoR52QsNMzlBCFkNw";
			//weixinid="oSqiqt0oAD9VwIflPLdnisRzdJzc";
			weixinid="oSqiqt4UHUYqWLLRmMBG3pziyYfg";
		}
		if(weixinid==null || "".equals(weixinid)){
			return "getWeixin";
		}
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession(); 
		session.setAttribute("weixinid", weixinid);
		int count=a20162userService.execSql("select * from a2016_2_user where weixinid='"+weixinid+"'").size();
		if(count<1){
			a20162user=new A20162user();
			a20162user.setCreatetime(new Date());
			a20162user.setFuid(UUIDCreater.getUUID());
			a20162user.setWeixinid(weixinid);
			a20162user.setIsyeas(0);
			a20162user.setIsluck(0);
			a20162user.setIsshare(0);
			a20162user.setIswinning(3);
			a20162userService.save(a20162user);
		}
		return "error";
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
	public String homePage(){
		a20161activity=a20161activityService.findById("2");
		return "homePage";
	}
	public String answer(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession(); 
		
		weixinid=(String) session.getAttribute("weixinid");
		if(weixinid==null || "".equals(weixinid)){
			return "getWeixin";
		}
		
		String sql="select * from a2016_2_user where weixinid='"+weixinid+"'";
		List<A20162user> list_user=a20162userService.execSql(sql);
		if(list_user.size()<1){
			return "getWeixin";
		}
		a20162user=list_user.get(0);
		a20162user.setIsyeas(0);
		a20162userService.updateSelective(a20162user);
		
		//如果缓存已有，先清除缓存
		if(session.getAttribute("simple")!=null){
			session.removeAttribute("simple");
		}
		if(session.getAttribute("middling")!=null){
			session.removeAttribute("middling");
		}
		if(session.getAttribute("difficulty")!=null){
			session.removeAttribute("difficulty");
		}
		list_a20162subject=a20162subjectService.execSql("select * from a2016_2_subject");
		List<A20162subject> simple=new ArrayList<A20162subject>();//简单
		List<A20162subject> middling=new ArrayList<A20162subject>();//中等
		List<A20162subject> difficulty=new ArrayList<A20162subject>();//困难
		for(int i=0;i<list_a20162subject.size();i++){
			if(list_a20162subject.get(i).getType()==2){
				simple.add(list_a20162subject.get(i));
			}else if(list_a20162subject.get(i).getType()==3){
				middling.add(list_a20162subject.get(i));
			}
			else if(list_a20162subject.get(i).getType()==4){
				difficulty.add(list_a20162subject.get(i));
			}
		}
		
		session.setAttribute("simple", simple);
		session.setAttribute("middling", middling);
		session.setAttribute("difficulty", difficulty);
		return "answer";
	}
	public int getRandom(int num){
		Random random=new Random();
		int ran=random.nextInt(num);
		return ran;
	}
	public String getSubject() throws IOException{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out= response.getWriter();
		HttpSession session = request.getSession(); 
		//排除若是session中若无题目
		if(session.getAttribute("simple")==null || session.getAttribute("middling")==null || session.getAttribute("difficulty")==null){
			return "error";
		}
		if(num<=3){//取简单的
			list_a20162subject=(List<A20162subject>) session.getAttribute("simple");
			int ran=this.getRandom(list_a20162subject.size());
			a20162subject=list_a20162subject.get(ran);
			list_a20162subject.remove(ran);
			session.setAttribute("simple", list_a20162subject);
		}else if(num>=4 && num<=6){//取中等的
			list_a20162subject=(List<A20162subject>) session.getAttribute("middling");
			int ran=this.getRandom(list_a20162subject.size());
			a20162subject=list_a20162subject.get(ran);
			list_a20162subject.remove(ran);
			session.setAttribute("simple", list_a20162subject);
		}else if(num>=7){//取困难的
			list_a20162subject=(List<A20162subject>) session.getAttribute("difficulty");
			int ran=this.getRandom(list_a20162subject.size());
			a20162subject=list_a20162subject.get(ran);
			list_a20162subject.remove(ran);
			session.setAttribute("simple", list_a20162subject);
		}
		String title=a20162subject.getTitle();
		title=title.replace("，" , "，<br/><br/>");
		a20162subject.setTitle(title);
		out.print(toJson(a20162subject));
		return null;
	}
	public String answerFun(){
		String sql="select * from a2016_2_user where weixinid='"+weixinid+"'";
		a20162user=a20162userService.execSql(sql).get(0);
		
		a20162subject=a20162subjectService.findById(id);
		int Answer=0;
		if("A".equals(answer)){
			Answer=0;
		}else if("B".equals(answer)){
			Answer=1;
		}else if("C".equals(answer)){
			Answer=2;
		}else if("D".equals(answer)){
			Answer=3;
		}
		if(!(Answer==a20162subject.getYes())){
			a20162user.setIsyeas(1);
			a20162userService.updateSelective(a20162user);
		}
		return null;
	}
	public String isAllyear(){
		if(weixinid==null || "".equals(weixinid)){
			return "getWeixin";
		}
		String sql="select * from a2016_2_user where weixinid='"+weixinid+"'";
		a20162user=a20162userService.execSql(sql).get(0);
		if(numCount<=3){
			name="秀才";
		}else if(numCount>3 && numCount<=6){
			name="举人";
		}else if(numCount==7){
			name="进士";
		}else if(numCount==8){
			name="探花";
		}else if(numCount==9){
			name="榜眼";
		}else if(numCount==10){
			name="状元";
		}
		
		GetAccessToken v=new GetAccessToken();
		String jsapi_ticket=v.jsapi_ticket;
		nonceStr = UUIDCreater.getUUID().substring(0, 16);
		timestamp = (new Date()).getTime();
		String jsurl = "http://wuzhen.gov.cn/poetryChallenge!isAllyear";
		String s1 = "jsapi_ticket=" + jsapi_ticket
				+ "&noncestr=" + nonceStr + "&timestamp=" + timestamp + "&url="
				+ jsurl;
		signature = new SHA1().getDigestOfString(s1.getBytes()).toLowerCase();
		 
		return "All";
	}
	public String share(){
		String sql="select * from a2016_2_user where weixinid='"+weixinid+"'";
		a20162user=a20162userService.execSql(sql).get(0);
		a20162user.setIsshare(1);
		a20162userService.updateSelective(a20162user);
		return null;
	}
	
	
	public String isHaveLuck() throws IOException{
		PrintWriter out=ServletActionContext.getResponse().getWriter();
		String sql="select * from a2016_2_user where weixinid='"+weixinid+"'";
		a20162user=a20162userService.execSql(sql).get(0);
		out.print(a20162user.getIsluck());
		return null;
	}
	public String luckDraw(){
		if(weixinid==null || "".equals(weixinid)){
			return "getWeixin";
		}
		String sql="select * from a2016_2_user where weixinid='"+weixinid+"'";
		a20162user=a20162userService.execSql(sql).get(0);
		if(a20162user.getIsluck()==1 || a20162user.getIswinning()!=3){
			name="您已经抽过一次奖品";
			if(a20162user.getIswinning()==0){
				return "No";
			}else{
				return "Year";
			}
		}
		
		a20162user.setIsluck(1);
		a20162user.setIswinning(0);
		
		String date=DateUtil.getStringDate1(new Date());
		String where = "CREATETIME > '" + date + " 00:00:00' and CREATETIME <='" + date + " 23:59:59' and ISWINNING=1 ";
		int count=a20162userService.getRecordCount(where);
		if(count<10){
			num=getRandom(100);
			if(num<60){
				a20162userService.updateSelective(a20162user);
				return "No";
			}else{
				a20162user.setIswinning(1);
				a20162userService.updateSelective(a20162user);
				return "Year";
			}
		}else{
			a20162userService.updateSelective(a20162user);
			return "No";
		}
	}
	public String receive(){
		return "receive";
	}
	public String updateUse(){
		String sql="select * from a2016_2_user where weixinid='"+weixinid+"'";
		A20162user entity=a20162userService.execSql(sql).get(0);
		entity.setPhone(phone);
		entity.setName(name);
		a20162userService.updateSelective(entity);
		return "error";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//get/set方法
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getWeixinid() {
		return weixinid;
	}
	public void setWeixinid(String weixinid) {
		this.weixinid = weixinid;
	}
	public A20161activityService getA20161activityService() {
		return a20161activityService;
	}
	public void setA20161activityService(A20161activityService a20161activityService) {
		this.a20161activityService = a20161activityService;
	}
	public A20162subjectService getA20162subjectService() {
		return a20162subjectService;
	}
	public void setA20162subjectService(A20162subjectService a20162subjectService) {
		this.a20162subjectService = a20162subjectService;
	}
	public A20162userService getA20162userService() {
		return a20162userService;
	}
	public void setA20162userService(A20162userService a20162userService) {
		this.a20162userService = a20162userService;
	}
	public List<A20162subject> getList_a20162subject() {
		return list_a20162subject;
	}
	public void setList_a20162subject(List<A20162subject> list_a20162subject) {
		this.list_a20162subject = list_a20162subject;
	}
	public A20162subject getA20162subject() {
		return a20162subject;
	}
	public void setA20162subject(A20162subject a20162subject) {
		this.a20162subject = a20162subject;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public A20161activity getA20161activity() {
		return a20161activity;
	}
	public void setA20161activity(A20161activity a20161activity) {
		this.a20161activity = a20161activity;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public A20162user getA20162user() {
		return a20162user;
	}
	public void setA20162user(A20162user a20162user) {
		this.a20162user = a20162user;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getNumCount() {
		return numCount;
	}
	public void setNumCount(int numCount) {
		this.numCount = numCount;
	}
	
}
