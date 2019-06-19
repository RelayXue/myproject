package com.gh.action.weixin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.apache.struts2.ServletActionContext;

import com.gh.action.weixin.backstage.GetAccessToken;
import com.gh.action.weixin.file.Upload;
import com.gh.action.weixin.front.WeixinUserInfo;
import com.gh.action.weixin.front.userAppidReturn;
import com.gh.base.Action;
import com.gh.common.DateUtil;
import com.gh.common.ImageCompression;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuAccommodation;
import com.gh.entity.BuWeixinVotingRecord;
import com.gh.entity.BuWeixinreward;
import com.gh.service.BuAccommodationService;
import com.gh.service.BuWeixinVotingRecordService;
import com.gh.service.BuWeixinrewardService;
import com.google.gson.Gson;

public class AccommodationAction extends Action{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BuAccommodationService buAccommodationService;
	private List<BuAccommodation> buAccommodation_list;
	private BuAccommodation buAccommodation;
	private BuWeixinVotingRecordService buWeixinVotingRecordService;
	private String name;
	private  String address="/upload/";//地址
	private File image;
	private String imageFileName;//图片名字
	
	private BuWeixinrewardService rewardService;
	private List<BuWeixinreward> list_Reward;
	private List<BuWeixinVotingRecord> list_BuWeixinVotingRecord;
	private String code;
	private String weixinid;
	private String rewardInn;//抽奖后奖励的旅店id
	private String phone_1;
	private String phone_2;
	private boolean ctime;
	private boolean is;
	private String isLuckDraw;
	
	private String paixu;
	private String msg;
	private String skey;
	private String win;
	private String val;
	private int batch;
	private String isVote;
	private String AccommodationFuid;
	private String strWhere;
	/**
	 * 跳转到首页
	 * @return
	 * @throws ParseException 
	 */
	public String Jump() throws ParseException{
		ctime=false;
		SimpleDateFormat localTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sdate = localTime.parse("2016-02-3 10:00:00");
		long time = System.currentTimeMillis();
		if(time>=sdate.getTime()){
			ctime=true;
		}
		return "Jump";
	}
	/**
	 * 分页查询
	 * @return
	 */
	public String Paging() {
		pageSize =10;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buAccommodationService.getRecordCount(null);
		buAccommodation_list = buAccommodationService.selectByPage(indexPage, pageSize, null, "createdate desc");
		if (pageType != null) {
			return "PagingPage";
		} else {
			return "Paging";
		}
	}
	/**
	 * 获取微信ID
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
			}else{
				return "Jump";
			}
			/*weixinid="123";*/
		}
		return "getWeiXinId";
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
	 * 投票分页
	 * @throws ParseException 
	 */
	public String Vote() throws ParseException{
		ctime=false;
		SimpleDateFormat localTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sdate = localTime.parse("2016-02-3 10:00:00");
		Date edate=localTime.parse("2016-02-12 22:00:00");
		long time = System.currentTimeMillis();
		if(time>=sdate.getTime()&& time<=edate.getTime()){
			ctime=true;
		}
		
		//检测是否有微信ID
		if(weixinid==null || "".equals(weixinid)){
			return null;
		}
		/**
		 * 检测是否已经投过票
		 * false:投过
		 * true:没有
		 */
		int i = rewardService.getRecordCount("WEIXINID='" + weixinid+"'");
		if(i>0){
			is=false;
		}else{
			is=true;
		}
		/**
		 * 检测是否已经抽过奖
		 * false:抽过
		 * true:没有
		 */
		int j = rewardService.getRecordCount(" WEIXINID='"+weixinid+"' and ACCOMMODATION_ID is null");
		if(j>0){
			isLuckDraw="true";
		}else{
			isLuckDraw="false"; 
		} 
		skey=skey==null?"":skey;
		int haveWeixinid=rewardService.getRecordCount("WEIXINID='"+weixinid+"'");
		pageSize =20;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buAccommodationService.getRecordCount(" hname like '%"+skey+"%' or morder like '%"+skey+"%'");
		if(haveWeixinid>0){
			buAccommodation_list = buAccommodationService.selectByPage(indexPage, pageSize, " (hname like '%"+skey+"%' or morder like '%"+skey+"%') and isvote=1", "NUM2 desc");
			batch=2;
		}else{
			buAccommodation_list = buAccommodationService.selectByPage(indexPage, pageSize, " (hname like '%"+skey+"%' or morder like '%"+skey+"%') and isvote=1 ", " morder");
			batch=2;
		}
		
		if (pageType != null && ("page").equals(pageType)) {
			return "Votepage";
		} else {
			return "Vote";
		}
	} 
	/**
	 * 投票功能
	 * @throws IOException 
	 */
	public String Voting() throws IOException{
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		String id=request.getParameter("id");//旅店的主键id
		if(id==null || id.length()==0 || weixinid==null ||weixinid.length()==0){
			return "accommodationShow";
		}
		int isWeixin=rewardService.getRecordCount("WEIXINID='"+weixinid+"'");
		if(isWeixin>0){
			return "getWeiXinId";
		}
		Gson gson = new Gson();
		String nickname="";
		String headimgurl="";
		String re = postSendMessage("https://api.weixin.qq.com/cgi-bin/user/info?access_token="
						+ GetAccessToken.AccessToken + "&openid="
						+ weixinid + "&lang=zh_CN", "");
		System.out.println("------------------------"+re);
		if(re.contains("errcode")){
			return null;
		}
		//---------------
		String sub="0";
		try { 
			WeixinUserInfo weixinUserInfo = gson.fromJson(re,WeixinUserInfo.class);
			sub=weixinUserInfo.getSubscribe();
			if("0".equals(sub)){
				out.print("只有关注的用户才能投票！");
				return null;
			}
			System.out.println("--------------sub1------"+sub);
			nickname = weixinUserInfo.getNickname();
			headimgurl=weixinUserInfo.getHeadimgurl();
		} catch (Exception e) {
			String su[] = re.split("subscribe");
			if (su != null && su.length > 0) {
				String t1[] = su[1].split(",");
				sub = t1[0].substring(2, t1[0].length());
			}
			if("0".equals(sub)){
				out.print("只有关注的用户才能投票！");
				return null;
			}
			System.out.println("--------------sub2------"+sub);
			String tt[] = re.split("nickname");
			if (tt != null && tt.length > 0) {
				String t1[] = tt[1].split(",");
				nickname = t1[0].substring(3, t1[0].length() - 1);
			}
			String img[] = re.split("headimgurl");
			if (img != null && img.length > 0) {
				String t1[] = img[1].split(",");
				headimgurl = t1[0].substring(3, t1[0].length() - 1);
			}
		}
		//投票-----------------
		buAccommodation=buAccommodationService.findById(id);
		BuWeixinVotingRecord buWeixinVotingRecord=new BuWeixinVotingRecord();
		buWeixinVotingRecord.setAccommodationName(buAccommodation.getHname());
			
		if(batch==1){
			if(buAccommodation.getNum()==null){
				buAccommodation.setNum(1);
			}else{
				buAccommodation.setNum(buAccommodation.getNum()+1);
			}
			buAccommodationService.updateSelective(buAccommodation);
		}else if(batch==2){
			if(buAccommodation.getNum2()==null){
				buAccommodation.setNum2(1);
			}else{
				buAccommodation.setNum2(buAccommodation.getNum2()+1);
			}
			buAccommodationService.updateSelective(buAccommodation);
		}else{
			//return "accommodationShow";
		}

		//buAccommodationService.updateSelective(buAccommodation);
		
		BuWeixinreward reward = new BuWeixinreward();
		reward.setWeixinname(nickname); 
		headimgurl = headimgurl!=null?headimgurl.replaceAll("\\\\",""):headimgurl;
		reward.setWeixinimg(headimgurl);
		reward.setWeixinid(weixinid);
		reward.setFuid(UUIDCreater.getUUID());
		reward.setCreatedate(new Date());
		rewardService.save(reward);
			
		//记录投票人跟所投票
		buWeixinVotingRecord.setWeixinid(weixinid);
		buWeixinVotingRecord.setFuid(UUIDCreater.getUUID());
		buWeixinVotingRecord.setCreatetime(new Date());
		buWeixinVotingRecordService.save(buWeixinVotingRecord);
		return "getWeiXinId";
	}
	/**
	 *跳转抽奖页面 
	 */
	public String goReward(){
		if(weixinid==null || "".equals(weixinid)){
			return "getWeiXinId";
		}else{
			String sql="select * from bu_weixinreward where WEIXINID='"+weixinid+"' and ACCOMMODATION_ID is null";
			int j = rewardService.execSql(sql).size();
			if(j>0){
				isLuckDraw="true";
			}else{
				isLuckDraw="false";
			}
		}
		return "goReward";
	}
	/**
	 * 随机抽奖
	 * @throws IOException 
	 */
	public String getReward() throws IOException{
		request.setCharacterEncoding("utf-8");  //这里不设置编码会有乱码
        response.setContentType("text/html;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache"); 
        PrintWriter out=response.getWriter();
        
        if(weixinid==null || "".equals(weixinid)){
			out.print("请先投票，再来抽奖");
			return null;
		}
        String isVoteSql="select * from bu_weixinreward where WEIXINID='"+weixinid+"'";
        String Acc = rewardService.execSql(isVoteSql).get(0).getAccommodationId();
        if(Acc==null || "".equals(Acc)){
        }else{
        	out.print("您已抽过一次奖品");
        	return null;
        }
        
        BuWeixinreward entity=new BuWeixinreward();
        entity=rewardService.execSql("select * from bu_weixinreward where WEIXINID='"+weixinid+"'").get(0);//拿到此微信ID的reward实体，方便下面更改
        
        Date time=new Date();
        String date = DateUtil.getStringDate1(time);
        String date1 = DateUtil.getStringDate1(DateUtil.addDate(time, 1));
        String strWhere="ACCOMMODATION_ID is not null and ACCOMMODATION_ID not in('No') and (CREATEDATE BETWEEN '"+date+"' and '"+date1+"')";
        int toDate=rewardService.getRecordCount(strWhere);
        if(toDate>0){
        	entity.setAccommodationId("No");
            rewardService.update(entity);
        	out.print("感谢您的参与，很遗憾，您没有中奖");
        	return null;
        }
		//先查询出已奖励出的房间      AccommodationId字段为投票完后的20家客栈的序号
        String sql="select * from bu_weixinreward where ACCOMMODATION_ID is not null and ACCOMMODATION_ID not in('No')";//查询出中奖人的旅店顺序
		list_Reward=rewardService.execSql(sql);
		List<String> Array=new ArrayList();//前20家的旅店的排序
		for(int i=1;i<21;i++){//总共有40件奖品，前20家旅店的住宿券，每家两个名额(每家旅店奖励出两个房间)。
			Array.add(i+"");
		}
		for(int i=0;i<list_Reward.size();i++){
			String AccNum=list_Reward.get(i).getAccommodationId();//旅店序号
			for(int j=0;j<Array.size();j++){
				String ArrNum=Array.get(j);
				if(AccNum.equals(ArrNum)){//当发现list里面的序号跟数据里的旅店顺序重复时进行，list里面的那个序号删掉
					int k=rewardService.getRecordCount("ACCOMMODATION_ID='"+ArrNum+"'");//获取重复的这个顺序是否在数据库里存在次数
					if(k>1){//存在两次的时候删掉
						Array.remove(j);
						j--;
					}
				}
			}
		}
        //调用抽奖的方法 Y:中奖 N:没中奖
		String isResults=this.LuckDraw();
        
        if("Y".equals(isResults)){
        	//选择奖品[(int)(Math.random()*NotSold.length)];
        	if(Array.size()>=0){
    			String accommodation_id=Array.get((int)(Math.random()*Array.size()));
    			entity.setAccommodationId(accommodation_id);
    			rewardService.update(entity);
    			out.print("恭喜您已中奖，中奖民宿为排名第"+accommodation_id+"的乌镇人家，请在评选结束后关注“乌镇发布”的排名公告后自行联系相应民宿预约订房。");
    			return null;
    		}
        }
        entity.setAccommodationId("No");
        rewardService.update(entity);
		out.print("感谢您的参与，很遗憾，您没有中奖");
		return null;
	}
	/**
	 * 抽奖方法
	 */
	public String LuckDraw(){
		//判断是否中奖
		/*Date time=new Date();
		String toDay=DateUtil.getStringDate1(time);
		String Tomorrow=DateUtil.getStringDate1(DateUtil.addDate(time, 1));
		String sql_data="select * from bu_weixinreward where CREATEDATE >='"+toDay+"' and CREATEDATE<'"+Tomorrow+"'";
		list_Reward=rewardService.execSql(sql_data);
		int isResults=(int)(Math.random()*(500-list_Reward.size()));*/
		if(Math.random()*100<10){
			return "Y";
		}
		return "N";
	}
	/**
	 * 查询详情
	 * @return
	 */
	public String selectById(){
		String fuid=request.getParameter("fuid");
		String id=request.getParameter("id");
		if(fuid!=null && id==null){
			buAccommodation=buAccommodationService.findById(fuid);
			String phone=buAccommodation.getMobile();
			this.getPhone(phone);
			return "selectById";
		}else if(fuid==null && id!=null){
			buAccommodation=buAccommodationService.findById(id);
			return "updateById";
		}
		return null;
	}
	/**
	 * 后台维护
	 * @return
	 */
	public String selectByPage(){
		pageSize =10;
		indexPage = indexPage == 0 ? 1 : indexPage;
		if(name==null){
			name="";
		}
		String where="hname like '%"+name+"%'";
		totalPage = buAccommodationService.getRecordCount(where);
		buAccommodation_list = buAccommodationService.selectByPage(indexPage, pageSize, where, "createdate desc");
		return "selectByPage";
	}
	/**
	 * 后台添加跳转
	 * @return
	 */
	public String addHostl(){
		String path = ServletActionContext.getServletContext().getRealPath(address);
		String picName;
		try {
			picName = Upload.upload(image, imageFileName, path);
			if(picName.length()>0){
				buAccommodation.setHimg(picName);//保存图片到数据库
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//压缩图片
		ImageCompression mypic = new ImageCompression();
		String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
		mypic.compressPic(targetDirectory + "/", targetDirectory + "/", buAccommodation.getHimg(), "B_"+buAccommodation.getHimg(), 500, 450, true);
		mypic.compressPic(targetDirectory + "/", targetDirectory + "/", buAccommodation.getHimg(), "L_"+buAccommodation.getHimg(), 223, 222, true);
		
		buAccommodation.setFuid(UUIDCreater.getUUID());
		Date date=new Date();
		buAccommodation.setCreatedate(date);
		buAccommodationService.save(buAccommodation);
		return "show";
	} 
	/**
	 * 后台删除
	 * @return
	 */
	public String deleteHostl(){
		buAccommodationService.delete(request.getParameter("id"));
		return "show";
	}
	/**
	 * 后台修改数据
	 * @return
	 */
	public String updateHostl(){
		String path = ServletActionContext.getServletContext().getRealPath(address);
		String picName;
		try {
			picName = Upload.upload(image, imageFileName, path);
			if(picName.length()>0){
				buAccommodation.setHimg(picName);//保存图片到数据库
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		//压缩图片
		ImageCompression mypic = new ImageCompression();
		String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
		mypic.compressPic(targetDirectory + "/", targetDirectory + "/", buAccommodation.getHimg(), "B_"+buAccommodation.getHimg(), 500, 450, true);
		mypic.compressPic(targetDirectory + "/", targetDirectory + "/", buAccommodation.getHimg(), "L_"+buAccommodation.getHimg(), 223, 222, true);
		buAccommodationService.updateSelective(buAccommodation);
		return "show";
	}
	
	/**
	 * 处理详情手机，显示两个
	 * @return
	 */
	public void getPhone(String phone){
		if(phone.contains("/")){//判断是否存在
			int i=phone.indexOf("/");//获取所在位置
			phone_1=phone.substring(0, i);
			phone_2=phone.substring(i+1, phone.length());
		}
		if(phone.contains("\\")){//判断是否存在
			int i=phone.indexOf("\\");//获取所在位置
			phone_1=phone.substring(0, i);
			phone_2=phone.substring(i+1, phone.length());
		}
		if(!(phone.contains("\\"))&&!(phone.contains("/"))){
			phone_1=phone;
		}
	}
	
	/**
	 * 投票后台功能
	 * @return
	 */
	public String listReward(){
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		String where ="ACCOMMODATION_ID is not null";
		if(win!=null && !("".equals(win))){
			if("1".equals(win)){	
				where=where+" and ACCOMMODATION_ID";
			}
			if("0".equals(win)){
				where=where+" and ACCOMMODATION_ID='No'";
			}
		}
		if(val!=null && !("".equals(val))){
			if(win!=null && !("".equals(win))){
				where=where+" and WEIXINNAME like '%"+val+"%'";
			}else{
				where=where+" and WEIXINNAME like '%"+val+"%'";
			}
		}
		totalPage=rewardService.getRecordCount(where);
		list_Reward=rewardService.selectByPage(indexPage,pageSize, where,"createdate desc");
		return "listReward";
	}
	/**
	 * 领奖功能
	 * @return
	 */
	public String isReceive(){
		Date time =new Date();
		String fuid=request.getParameter("id");
		BuWeixinreward entity=new BuWeixinreward();
		entity.setFuid(fuid);
		entity.setReceivetime(time);
		rewardService.updateSelective(entity);
		return "isReceive";
	}
	/**
	 * 后台搜索功能
	 * @return
	 */
	public String search(){
		String where="";
		if((win!=null && !("".equals(win))) || (val!=null && !("".equals(val)))){
			where="ACCOMMODATION_ID is not null and ";
		}else{
			where="ACCOMMODATION_ID is not null";
		}
		if(win!=null && !("".equals(win))){
			if("1".equals(win)){	
				where=where+"ACCOMMODATION_ID";
			}
			if("0".equals(win)){
				where=where+"ACCOMMODATION_ID='No'";
			}
		}
		if(val!=null && !("".equals(val))){
			if(win!=null && !("".equals(win))){
				where=where+" and WEIXINNAME like '%"+val+"%'";
			}else{
				where=where+" WEIXINNAME like '%"+val+"%'";
			}
		}
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=rewardService.getRecordCount(where);
		list_Reward=rewardService.selectByPage(indexPage,pageSize, where,"createdate desc");
		return "listReward";
	}
	/**
	 * 抽奖后
	 * @return
	 */
	public String excessive(){
		return "excessive";
	}
	
	/**
	 * 投票记录
	 */
	public String VotingRecord(){
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buWeixinVotingRecordService.getRecordCount(null);
		list_BuWeixinVotingRecord=buWeixinVotingRecordService.selectByPage(indexPage,pageSize, null, null);
		return "VotingRecord";
	}
	/**
	 * 规则
	 */
	public String Rule(){
		return "rule";
	}
	/**
	 * 后台设置投票
	 * @return
	 */
	public String SetUpVoteList(){
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		if(strWhere==null || "".equals(strWhere)){
			totalPage=buAccommodationService.getRecordCount(null);
			buAccommodation_list=buAccommodationService.selectByPage(indexPage,pageSize, null,"CREATEDATE DESC");
		}else{
			String where="hname like '%"+strWhere+"%' or householder like '%"+strWhere+"%'";
			totalPage=buAccommodationService.getRecordCount(where);
			buAccommodation_list=buAccommodationService.selectByPage(indexPage,pageSize, where,"CREATEDATE DESC");
		}
		return "SetUpVoteList";
	}
	/**
	 * 投票/不投票
	 * @return
	 */
	public String setVote(){
		buAccommodation=buAccommodationService.findById(AccommodationFuid);
		BuAccommodation entity =new BuAccommodation();
		entity.setFuid(AccommodationFuid);
		entity.setCreatedate(buAccommodation.getCreatedate());
		if("false".equals(isVote)){  
			entity.setIsvote("0");
		}else{
			entity.setIsvote("1");
		}
		buAccommodationService.updateSelective(entity);
		return "setVote";
	}
	
	
	/**
	 * 删除没关注的人跟所投票票数-1
	 * buAccommodationService
	 * buWeixinVotingRecordService
	 * rewardService
	 * @return
	 */
	public String delete(){
		List<BuAccommodation> buAccommodation=buAccommodationService.execSql("select * from bu_accommodation");
		for(int i=0;i<buAccommodation.size();i++){
			String where="ACCOMMODATION_NAME ='"+buAccommodation.get(i).getHname()+"'";
			int k = buWeixinVotingRecordService.getRecordCount(where);
			BuAccommodation entity=new BuAccommodation();
			entity.setFuid(buAccommodation.get(i).getFuid());
			entity.setNum(k);
			buAccommodationService.updateSelective(entity);
		}
		
		
		
		
		
		/*String sql="select * from bu_weixinreward where WEIXINNAME is null and WEIXINIMG is null";
		List<BuWeixinreward> buWeixinreward=rewardService.execSql(sql);
		for(int i=0;i<buWeixinreward.size();i++){
			rewardService.delete(buWeixinreward.get(i).getFuid());
		}
		
		String sql2="select * from bu_weixin_voting_record where WEIXINID not in(select WEIXINID from bu_weixinreward)";
		List<BuWeixinVotingRecord> buWeixinVotingRecord=buWeixinVotingRecordService.execSql(sql2);
		List<String> str=new ArrayList<String>();
		for(int j=0;j<buWeixinVotingRecord.size();j++){
			str.add(buWeixinVotingRecord.get(j).getAccommodationName());
			buWeixinVotingRecordService.delete(buWeixinVotingRecord.get(j).getFuid());
		}
		
		for(int k=0;k<str.size();k++){
			String sql3="select * from bu_accommodation where hname='"+str.get(k)+"'";
			BuAccommodation accommodation=buAccommodationService.execSql(sql3).get(0);
			if(accommodation.getNum()==null || "".equals(accommodation.getNum()) || accommodation.getNum()<1){
				accommodation.setNum(0);
			}else{
				accommodation.setNum(accommodation.getNum()-1);
			}
			buAccommodationService.updateSelective(accommodation);
		}
		String sql="select * from bu_weixin_voting_record where WEIXINID = '"+weixinid+"'";
		BuWeixinVotingRecord buWeixinVotingRecord = buWeixinVotingRecordService.execSql(sql).get(0);
		String AccommodationName=buWeixinVotingRecord.getAccommodationName();//所投客栈名
		buWeixinVotingRecordService.delete(buWeixinVotingRecord.getFuid());//删除记录表中的这个
		
		String sqlHos="select * from bu_accommodation where HNAME='"+AccommodationName+"'";
		buAccommodation=buAccommodationService.execSql(sqlHos).get(0);
		buAccommodation.setNum(buAccommodation.getNum()-1);
		buAccommodationService.updateSelective(buAccommodation);
		
		String where="WEIXINID = '"+weixinid+"'";
		rewardService.deleteByWhere(where);*/
		return null;
	}
	
	
	
	
	
	
	
	
	
	
	public BuAccommodationService getBuAccommodationService() {
		return buAccommodationService;
	}
	public void setBuAccommodationService(
			BuAccommodationService buAccommodationService) {
		this.buAccommodationService = buAccommodationService;
	}
	public java.util.List<BuAccommodation> getBuAccommodation_list() {
		return buAccommodation_list;
	}
	public void setBuAccommodation_list(
			java.util.List<BuAccommodation> buAccommodation_list) {
		this.buAccommodation_list = buAccommodation_list;
	}
	public BuAccommodation getBuAccommodation() {
		return buAccommodation;
	}
	public void setBuAccommodation(BuAccommodation buAccommodation) {
		this.buAccommodation = buAccommodation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	public String getPhone_1() {
		return phone_1;
	}
	public void setPhone_1(String phone_1) {
		this.phone_1 = phone_1;
	}
	public String getPhone_2() {
		return phone_2;
	}
	public void setPhone_2(String phone_2) {
		this.phone_2 = phone_2;
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
	public String getRewardInn() {
		return rewardInn;
	}
	public void setRewardInn(String rewardInn) {
		this.rewardInn = rewardInn;
	}
	public boolean isIs() {
		return is;
	}
	public void setIs(boolean is) {
		this.is = is;
	}
	public String getIsLuckDraw() {
		return isLuckDraw;
	}
	public void setIsLuckDraw(String isLuckDraw) {
		this.isLuckDraw = isLuckDraw;
	}
	public String getWin() {
		return win;
	}
	public void setWin(String win) {
		this.win = win;
	}
	public String getVal() {
		return val;
	}
	public void setVal(String val) {
		this.val = val;
	}
	public BuWeixinrewardService getRewardService() {
		return rewardService;
	}
	public void setRewardService(BuWeixinrewardService rewardService) {
		this.rewardService = rewardService;
	}
	public List<BuWeixinreward> getList_Reward() {
		return list_Reward;
	}
	public void setList_Reward(List<BuWeixinreward> list_Reward) {
		this.list_Reward = list_Reward;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public BuWeixinVotingRecordService getBuWeixinVotingRecordService() {
		return buWeixinVotingRecordService;
	}
	public void setBuWeixinVotingRecordService(
			BuWeixinVotingRecordService buWeixinVotingRecordService) {
		this.buWeixinVotingRecordService = buWeixinVotingRecordService;
	}
	public List<BuWeixinVotingRecord> getList_BuWeixinVotingRecord() {
		return list_BuWeixinVotingRecord;
	}
	public void setList_BuWeixinVotingRecord(
			List<BuWeixinVotingRecord> list_BuWeixinVotingRecord) {
		this.list_BuWeixinVotingRecord = list_BuWeixinVotingRecord;
	}
	public int getBatch() {
		return batch;
	}
	public void setBatch(int batch) {
		this.batch = batch;
	}
	public String getAccommodationFuid() {
		return AccommodationFuid;
	}
	public void setAccommodationFuid(String accommodationFuid) {
		AccommodationFuid = accommodationFuid;
	}
	public String getIsVote() {
		return isVote;
	}
	public void setIsVote(String isVote) {
		this.isVote = isVote;
	}
	public String getStrWhere() {
		return strWhere;
	}
	public void setStrWhere(String strWhere) {
		this.strWhere = strWhere;
	}
	public String getPaixu() {
		return paixu;
	}
	public void setPaixu(String paixu) {
		this.paixu = paixu;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public boolean isCtime() {
		return ctime;
	}
	public void setCtime(boolean ctime) {
		this.ctime = ctime;
	}
	
}
