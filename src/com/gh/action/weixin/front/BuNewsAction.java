package com.gh.action.weixin.front;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.gh.action.system.CompetenceManager;
import com.gh.action.weixin.backstage.GetAccessToken;
import com.gh.action.weixin.backstage.SHA1;
import com.gh.base.Action;
import com.gh.common.EmojiFilter;
import com.gh.common.HtmlUtil;
import com.gh.common.TransformJSON;
import com.gh.common.UUIDCreater;
import com.gh.dao.IntegrateddataDAO;
import com.gh.entity.BuDining;
import com.gh.entity.BuEntertainmentshopping;
import com.gh.entity.BuNews;
import com.gh.entity.BuStay;
import com.gh.entity.BuWeixinactivity;
import com.gh.entity.BuWeixinconfig;
import com.gh.entity.BuWeixinluckdraw;
import com.gh.entity.BuWeixinvote;
import com.gh.entity.IntegrateddataWithBLOBs;
import com.gh.entity.Opinion;
import com.gh.entity.Votingrecord;
import com.gh.service.BuDiningService;
import com.gh.service.BuEntertainmentshoppingService;
import com.gh.service.BuNewsService;
import com.gh.service.BuStayService;
import com.gh.service.BuWeixinactivityService;
import com.gh.service.BuWeixinconfigService;
import com.gh.service.BuWeixinluckdrawService;
import com.gh.service.BuWeixinvoteService;
import com.gh.service.OpinionService;
import com.gh.service.VotingrecordService;
import com.google.gson.Gson;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

public class BuNewsAction  extends Action{
	
	private IntegrateddataDAO integrateddataDAO;
	private BuDiningService buDiningService;
	private BuWeixinvoteService buWeixinvoteService;
	private BuWeixinluckdrawService buWeixinluckdrawService;
	private BuEntertainmentshoppingService buEntertainmentshoppingService;
	private String qid;
	private BuWeixinactivity buWeixinactivity;
	private BuStayService buStayService;
	private BuWeixinconfigService buWeixinconfigService;
	private BuNewsService buNewsService;
	private OpinionService opinionService;
	private BuWeixinactivityService buWeixinactivityService;
	private List<BuWeixinactivity>  buWeixinactivity_list;
	private BuWeixinconfig buWeixinconfig;
	private String type;
	private String openid;
	private String nickname;
	private String weixinid;
	private String x;
	private String y;
	private String re;
	private String code;
	private String headimgurl;
	private String skey;
	private String id;
	private List<BuNews> buNews_list;
	private BuNews buNews;
	private List<BuNews> other_places_route;
	private VotingrecordService votingrecordService;
	private List<Votingrecord> votingrecord_list;
	private boolean is;
	private String text;
	
	
	
	//微信接口
	private String nonceStr;
	private long timestamp;
	private String signature;
	
	/**
	 * @see 乌镇概况
	 * @author xiao
	 */
	public String summary() {
		/*buNews_list = buNewsService
		.execSql("select * from bu_news where type='004001' and deletemark!=1");
		buNews = buNews_list != null ? buNews_list.get(0) : null;
		// -----------------阅读数
		int readnum = buNews.getReadnum() != null ? buNews.getReadnum() : 0;
		readnum++;
		buNews.setReadnum(readnum);
		buNewsService.updateSelective(buNews);
		type = "004001";*/
		return "summary";
	}
	/**
	 * @see 乌镇概况
	 * @author xiao
	 */
	public String survey() {
		buNews_list = buNewsService
				.execSql("select * from bu_news where type='004001' and deletemark!=1");
		buNews = buNews_list != null ? buNews_list.get(0) : null;
		// -----------------阅读数
		int readnum = buNews.getReadnum() != null ? buNews.getReadnum() : 0;
		readnum++;
		buNews.setReadnum(readnum);
		buNewsService.updateSelective(buNews);
		type = "004001";
		return "WzSurvey";
	}

	/**
	 * @see 乌镇历史
	 * @author xiao
	 */
	public String histroy() {
		buNews_list = buNewsService
				.execSql("select * from bu_news where type='004009' and deletemark!=1");
		buNews = buNews_list != null ? buNews_list.get(0) : null;
		// -----------------阅读数
		int readnum = buNews.getReadnum() != null ? buNews.getReadnum() : 0;
		readnum++;
		buNews.setReadnum(readnum);
		buNewsService.updateSelective(buNews);
		type = "004009";
		return "WzSurvey";
	}

	/**
	 * @see 乌镇人文
	 * @author xiao
	 */
	public String Humanities() {
		buNews_list = buNewsService
				.execSql("select * from bu_news where type='004033' and deletemark!=1");
		buNews = buNews_list != null ? buNews_list.get(0) : null;
		// -----------------阅读数
		int readnum = buNews.getReadnum() != null ? buNews.getReadnum() : 0;
		readnum++;
		buNews.setReadnum(readnum);
		buNewsService.updateSelective(buNews);
		type = "004033";
		return "WzSurvey";
	}


	/**
	 * @see 乌镇名人
	 * @author xiao
	 */
	public String Celebrity() {
		buNews_list = buNewsService.execSql("select * from bu_news where type='004012' and deletemark!=1");
		type = "004012";
		return "WzCelebrity";
	}
	
	public String other() {
		buNews_list = buNewsService
				.execSql("select * from bu_news where type='"+type+"' and deletemark!=1");
		buNews = buNews_list != null ? buNews_list.get(0) : null;
		// -----------------阅读数
		int readnum = buNews.getReadnum() != null ? buNews.getReadnum() : 0;
		readnum++;
		buNews.setReadnum(readnum);
		buNewsService.updateSelective(buNews);
		return "WzSurvey";
	}
	/**
	 * @see 诚信推荐列表页
	 * @author xiao
	 */
	public String recommend() {
		String SqlWhere = " type='004003' and deletemark!=1";
		pageSize = 4;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buNewsService.getRecordCount(SqlWhere);
		buNews_list = buNewsService.selectByPage(indexPage, pageSize, SqlWhere,
				"  createdate desc");
		if (pageType != null && pageType.equals("page")) {
			return "RecommendPage";
		} else {
			return "Recommend";
		}
	}

	/**
	 * @see 乌镇规划
	 * @author xiao
	 */
	public String plan() {
		buNews_list = buNewsService.execSql("select * from bu_news where type='004037' and deletemark!=1 order by createdate");
		for(int a=0;a<buNews_list.size();a++){
			buNews_list.get(a).setContent(HtmlUtil.delHTMLTag(buNews_list.get(a).getContent()));
		}
		return "newList";
	}
	/**
	 * 查询列表
	 */
	public String showNews() {
		buNews = buNewsService.findById(id);
		return "news";
	}
	/**
	 * @see 诚信推荐详情页
	 * @author xiao
	 */
	public String recommendDetails() {
		buNews = buNewsService.findById(id);
		// -----------------阅读数
		int readnum = buNews.getReadnum() != null ? buNews.getReadnum() : 0;
		readnum++;
		buNews.setReadnum(readnum);
		buNewsService.updateSelective(buNews);
		return "RecommendDetails";
	}

	/**
	 * @see 诚信推荐详情页客户端浏览
	 * @author wjc
	 */
	public String recommendDetails1() {
		buNews = buNewsService.findById(id);
		// -----------------阅读数
		int readnum = buNews.getReadnum() != null ? buNews.getReadnum() : 0;
		readnum++;
		buNews.setReadnum(readnum);
		buNewsService.updateSelective(buNews);
		return "RecommendDetails1";
	}

	/**
	 * @see 美图欣赏
	 * @author xiao
	 */
	public String share() {
		String SqlWhere = " type='004005' and deletemark!=1";
		pageSize = 6;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buNewsService.getRecordCount(SqlWhere);
		buNews_list = buNewsService.selectByPage(indexPage, pageSize, SqlWhere,
				" createdate desc");
		if (pageType != null && pageType.equals("page")) {
			return "ykfxPage";
		} else {
			return "ykfx";
		}
	}

	/**
	 * @see 美图欣赏详情页
	 * @author xiao
	 */
	public String shareDetails() {
		buNews = buNewsService.findById(id);
		// -----------------阅读数
		int readnum = buNews.getReadnum() != null ? buNews.getReadnum() : 0;
		readnum++;
		buNews.setReadnum(readnum);
		buNewsService.updateSelective(buNews);
		return "shareDetails";
	}

	/**
	 * @see 新鲜速递列表页
	 * @author xiao
	 */
	public String fresh() {
		String SqlWhere = " type='004002' and deletemark!=1";
		pageSize = 4;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buNewsService.getRecordCount(SqlWhere);
		buNews_list = buNewsService.selectByPage(indexPage, pageSize, SqlWhere,
				" createdate desc");
		if (pageType != null && pageType.equals("page")) {
			return "freshPage";
		} else {
			return "fresh";
		}
	}

	/**
	 * @see 新鲜速递详情页
	 * @author xiao
	 */
	public String freshDetails() {
		buNews = buNewsService.findById(id);
		// -----------------阅读数
		int readnum = buNews.getReadnum() != null ? buNews.getReadnum() : 0;
		readnum++;
		buNews.setReadnum(readnum);
		buNewsService.updateSelective(buNews);
		return "freshDetails";
	}

	/**
	 * @see 这厢有礼列表页
	 * @author xiao
	 */
	public String youli() {
		String SqlWhere = " type='004036' and deletemark!=1";
		pageSize = 4;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buNewsService.getRecordCount(SqlWhere);
		buNews_list = buNewsService.selectByPage(indexPage, pageSize, SqlWhere,
				" createdate desc");
		if (pageType != null && pageType.equals("page")) {
			return "youliPage";
		} else {
			return "youli";
		}
	}

	/**
	 * @see 这厢有礼列详情页
	 * @author xiao
	 */
	public String youliDetails() {
		buNews = buNewsService.findById(id);
		// -----------------阅读数
		int readnum = buNews.getReadnum() != null ? buNews.getReadnum() : 0;
		readnum++;
		buNews.setReadnum(readnum);
		buNewsService.updateSelective(buNews);
		return "youliDetails";
	}

	/**
	 * @see 显示抽奖
	 * @author xiao
	 * @throws IOException
	 * @throws ParseException 
	 */
	public String LuckDraw() throws IOException, ParseException {
		// 根据code获取个人信息
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//抽奖时间
		List<BuWeixinconfig> buWeixinconfig_list=buWeixinconfigService.execSql("select * from bu_weixinconfig where type='005003'");
		Boolean ctime=true;
		SimpleDateFormat localTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date sdate = localTime.parse("2015-09-1 01:00:00");
		Date edate=localTime.parse("2015-10-1 22:00:00");
		String sd="";
		String ed="";
		int swe=1;
		int ewe=3;
		if(buWeixinconfig_list!=null&&buWeixinconfig_list.size()>0){
			buWeixinconfig=buWeixinconfig_list.get(0);
			sdate=buWeixinconfig_list.get(0).getDrawstarttime();
			edate=buWeixinconfig_list.get(0).getDrawendtime();
			sd=localTime.format(sdate);
			ed=localTime.format(edate);
			swe=buWeixinconfig_list.get(0).getWeekstart();
			ewe=buWeixinconfig_list.get(0).getWeekend();
		} 
		long time = System.currentTimeMillis();
		if(time>=sdate.getTime()&& time<=edate.getTime()){
			 Calendar cal = Calendar.getInstance();
			 cal.setTime(new Date());
			 //判断周1-3
			 if(cal.get(Calendar.DAY_OF_WEEK)-swe>0&&cal.get(Calendar.DAY_OF_WEEK)-1<=ewe){
				 //判断 7-22点
				 if((cal.get(Calendar.DAY_OF_WEEK)-1<=1&&cal.get(Calendar.HOUR_OF_DAY)<7)||(cal.get(Calendar.DAY_OF_WEEK)-1>=3&&cal.get(Calendar.HOUR_OF_DAY)>=22)){
				 }else{
					 ctime=false;
				 }
			 }
		}
		if(ctime){
			return "LuckDrawTime";
		}
		
		if (code != null && code.length() > 0) { 
			String re = postSendMessage(
					"https://api.weixin.qq.com/sns/oauth2/access_token?appid="
							+ GetAccessToken.AppId + "&secret="
							+ GetAccessToken.AppSecret + "&code=" + code
							+ "&grant_type=authorization_code", "");
			Gson gson = new Gson();
			userAppidReturn userAppid = gson
					.fromJson(re, userAppidReturn.class);
			weixinid = userAppid.getOpenid();
			if (weixinid != null && weixinid.length() > 0) {
				// 获取昵称等信息

				String re1 = postSendMessage(
						"https://api.weixin.qq.com/cgi-bin/user/info?access_token="
								+ GetAccessToken.AccessToken + "&openid="
								+ weixinid + "&lang=zh_CN", ""); 
				if(re1.contains("errcode")){
					out.print("您未关注微信，获取个人信息失败，请先关注！");
					return null;
				}else{ 
					try { 
						WeixinUserInfo weixinUserInfo = gson.fromJson(re1,WeixinUserInfo.class);
						nickname = weixinUserInfo.getNickname();
						headimgurl=weixinUserInfo.getHeadimgurl();
					} catch (Exception e) {
						// TODO: handle exception
						String tt[] = re1.split("nickname");
						if (tt != null && tt.length > 0) {
							String t1[] = tt[1].split(",");
							nickname = t1[0].substring(3, t1[0].length() - 1);
						}
						String img[] = re1.split("headimgurl");
						if (img != null && img.length > 0) {
							String t1[] = img[1].split(",");
							headimgurl = t1[0].substring(3, t1[0].length() - 1);
						}
					}
				}
				
				openid = weixinid;
			} else {
				out.print("获取Appid失败，错误代码：" + userAppid.getErrmsg());
				return null;
			}
		} else {
			out.print("获取用户信息失败，	请稍后再试");
			return null;
		}

		List<BuWeixinluckdraw> buWeixinluckdraw_list = buWeixinluckdrawService
				.execSql("select * from BU_WEIXINLUCKDRAW where userid='"
						+ openid
						+ "'  and drawtime >'"+sd+"' and drawtime<'"+ed+"' order by DRAWTIME desc");
		// 抽过奖
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (buWeixinluckdraw_list != null && buWeixinluckdraw_list.size() > 0) {
			for (int a = 0; a < buWeixinluckdraw_list.size(); a++) {
				String nn = df.format(new Date());
				String dtime = df.format(buWeixinluckdraw_list.get(a)
						.getDrawtime());
				if (nn.equals(dtime)) {
					return "LuckDrawNext";
				}
			}
			// 未抽奖
		} else {
			return "LuckDraw";
		}
		return "LuckDraw";
	}

	/**
	 * @see 抽奖
	 * @author xiao
	 * @throws IOException
	 */
	public String HasLuckDraw() {
		try {
			Boolean hasLuck = true;
			Boolean iswin=false;
			if(openid==null||openid.length()==0){
				return "LuckDrawFail";
			}
			List<BuWeixinconfig> buWeixinconfig_list=buWeixinconfigService.execSql("select * from bu_weixinconfig where type='005003'");
			String sd="";
			String ed="";
			SimpleDateFormat localTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(buWeixinconfig_list!=null&&buWeixinconfig_list.size()>0){
				sd=localTime.format(buWeixinconfig_list.get(0).getDrawstarttime());
				ed=localTime.format(buWeixinconfig_list.get(0).getDrawendtime());
			} 
			List<BuWeixinluckdraw> buWeixinluckdraw_list = buWeixinluckdrawService
					.execSql("select * from BU_WEIXINLUCKDRAW where userid='"+ openid + "' order by DRAWTIME desc");// and drawtime >'"+sd+"' and drawtime<'"+ed+"'
			int count = buWeixinluckdrawService
					.getRecordCount(" date(drawtime) = curdate() and ISWINNING=1");
			// select * from BU_WEIXINLUCKDRAW where
			// 抽过奖 
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			if (buWeixinluckdraw_list != null&& buWeixinluckdraw_list.size() > 0) {
				for (int a = 0; a < buWeixinluckdraw_list.size(); a++) {
					String nn = df.format(new Date());
					String dtime = df.format(buWeixinluckdraw_list.get(a).getDrawtime());
					// 每天一次
					if (nn.equals(dtime)) {
						hasLuck = false;
						return "LuckDrawNext";
					}
					//已经中奖的
					if (buWeixinluckdraw_list.get(a).getIswinning().equals("1")) {
						iswin=true;
					}
				}
			}
			// 抽奖
			if (hasLuck) {
				String rtn = "";
				int random = new Random().nextInt(100);
				int gl = 0;
				BuWeixinluckdraw buWeixinluckdraw = new BuWeixinluckdraw();
				// 设置中奖概率
/*				if (df.format(new Date()).equals("2015-07-20")) {
					if (count >= 20) {
						gl = 0;
					} 
				} 
*/				
				int allcount=0;
				if(buWeixinconfig_list!=null&&buWeixinconfig_list.size()>0){
					allcount=buWeixinconfig_list.get(0).getDrawTotal();
					gl=Integer.parseInt(buWeixinconfig_list.get(0).getContent());
					buWeixinconfig=buWeixinconfig_list.get(0);
				}
				if (count >= allcount) {
					gl = 0;
				} 
				//已经中过奖的
				if(iswin){
					gl=0; 
				}
				System.out.println("--------allcount:"+allcount);
				System.out.println("--------count:"+count);
				System.out.println("--------gl:"+gl);
				if (random >= 1 && random <= gl) {
					buWeixinluckdraw.setIswinning("1");
					buWeixinluckdraw.setIsaward("0");
					rtn = "LuckDrawWin";
				} else {
					buWeixinluckdraw.setIswinning("0");
					rtn = "LuckDrawFail";
				}
				int tt = buWeixinluckdrawService.getRecordCount(" date(drawtime) = curdate() and  userid='"+ openid+ "' ");
				try {
					if(tt==0){
						if(openid!=null&&openid.length()>0){
							buWeixinluckdraw.setFuid(UUIDCreater.getUUID());
							buWeixinluckdraw.setUserid(openid);
							buWeixinluckdraw.setUsername(EmojiFilter.filterEmoji(nickname));
							buWeixinluckdraw.setDrawtime(new Date());
							headimgurl=headimgurl!=null?headimgurl.replaceAll("\\\\",""):headimgurl;
							buWeixinluckdraw.setHeaderimg(headimgurl);
							buWeixinluckdrawService.save(buWeixinluckdraw);
						}
					}
				} catch (Exception ex) {
					if(tt==0){
						if(openid!=null&&openid.length()>0){
							buWeixinluckdraw.setFuid(UUIDCreater.getUUID());
							buWeixinluckdraw.setUserid(openid);
							buWeixinluckdraw.setDrawtime(new Date());
							headimgurl=headimgurl!=null?headimgurl.replaceAll("\\\\",""):headimgurl;
							buWeixinluckdraw.setHeaderimg(headimgurl);
							buWeixinluckdrawService.save(buWeixinluckdraw);
							ex.printStackTrace();
						}
					}
				}
				if(tt>0){
					rtn = "LuckDrawNext";
				}
				return rtn;
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return "LuckDrawFail";
		}
		return "LuckDrawNext";
	}
	/**
	 * @see 显示抽奖规则
	 * @author xiao
	 */ 
	public String showRule(){
		
		 List<BuWeixinconfig> buWeixinconfig_list=buWeixinconfigService.execSql("select * from bu_weixinconfig  where type='005003'");
		if(buWeixinconfig_list!=null&&buWeixinconfig_list.size()>0){
			buWeixinconfig=buWeixinconfig_list.get(0);
		}
		return "LuckDrawRule"; 
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
	 * @see 显示赛诗会首页
	 * @author xiao
	 */
	public String showPoetry() {
		return "poem";
	}
	public String showRule2(){
		return "showRule2";
	}
	
	/**
	 * @see 活动专区赛诗会
	 * @author xiao
	 */
	public String poetry() {
		pageSize = 5;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buWeixinactivityService.getRecordCount("");
		buWeixinactivity_list = buWeixinactivityService.selectByPage(indexPage, pageSize, "", " WORDER desc");
		if (pageType != null && pageType.equals("page")) {
			return "PoeticWorksPage";
		} else {
			return "PoeticWorks";
		}
	}
	/**
	 * @see 赛诗会详细页
	 * @author xiao
	 */
	public String poetryDetails() {
		buWeixinactivity = buWeixinactivityService.findById(id);
		return "PoeticWorksDetails";
	}
	
	/**
	 * @see 赛诗会投票页列表页
	 * @author xiao
	 */
	public String GetWeixin(){
		if(weixinid!=null&&weixinid.length()>0){
		}else{
			/*if(code!=null&&code.length()>0){
				String re = postSendMessage(
						"https://api.weixin.qq.com/sns/oauth2/access_token?appid="
								+ GetAccessToken.AppId + "&secret="
								+ GetAccessToken.AppSecret + "&code=" + code
								+ "&grant_type=authorization_code", "");
				Gson gson = new Gson();
				userAppidReturn userAppid = gson.fromJson(re, userAppidReturn.class);
				weixinid = userAppid.getOpenid();
			}*/
			weixinid="999";
			HttpSession session=request.getSession();
			session.setAttribute("weixinid", weixinid);
		}
		return "GetWeixin";
	}
	
	public String poetryVoteList() {
		HttpSession session=request.getSession();
		weixinid=(String)session.getAttribute("weixinid");
		if(weixinid==null || "".equals(weixinid)){
			//返回获取微信ID页面
			return null;
		}
		String where=" isvote=1";
		if(skey!=null){
			where+=" and title like '%"+skey+"%'";
		}
		String sql="select * from votingrecord where WEIXINID='"+weixinid+"'";
		votingrecord_list=votingrecordService.execSql(sql);
		is=votingrecord_list.size()>0?false:true;
		String order="worder";
		if(is==false){
			order="number desc,worder asc";
		}
		
		buWeixinactivity_list = buWeixinactivityService.execSql("select * from bu_weixinactivity where"+where+" order by "+order);
		
		return "PoeticWorksVote";
	}
	/**
	 * 投票
	 */
	public String updateVote(){
		weixinid=(String)request.getSession().getAttribute("weixinid");
		if(weixinid==null || "".equals(weixinid)){
			return null;
		}
		
		String oneFuid=request.getParameter("onefuid");
		String twoFuid=request.getParameter("twofuid");
		String threeFuid=request.getParameter("threefuid");
		
		Date date =new Date();
		String becast="";
		if(oneFuid!=null && !("".equals(oneFuid))){
			buWeixinactivity=buWeixinactivityService.findById(oneFuid);
			becast=becast+"{"+buWeixinactivity.getTitle()+"},";
			if(buWeixinactivity.getNumber()==null){
				buWeixinactivity.setNumber(1);
			}else{
				buWeixinactivity.setNumber(buWeixinactivity.getNumber()+1);
			}
			buWeixinactivityService.updateSelective(buWeixinactivity);
		}
		if(twoFuid!=null && !("".equals(twoFuid))){
			buWeixinactivity=buWeixinactivityService.findById(twoFuid);
			if(buWeixinactivity!=null){
				becast=becast+"{"+buWeixinactivity.getTitle()+"},";
				if(buWeixinactivity.getNumber()==null){
					buWeixinactivity.setNumber(1);
				}else{
					buWeixinactivity.setNumber(buWeixinactivity.getNumber()+1);
				}
				buWeixinactivityService.updateSelective(buWeixinactivity);
			}
		}
		if(threeFuid!=null && !("".equals(threeFuid))){
			buWeixinactivity=buWeixinactivityService.findById(threeFuid);
			if(buWeixinactivity!=null){
				becast=becast+"{"+buWeixinactivity.getTitle()+"}";
				if(buWeixinactivity.getNumber()==null){
					buWeixinactivity.setNumber(1);
				}else{
					buWeixinactivity.setNumber(buWeixinactivity.getNumber()+1);
				}
				buWeixinactivityService.updateSelective(buWeixinactivity);
			}
		}
		
		Votingrecord v=new Votingrecord();
		v.setCreatedate(date);
		v.setFuid(UUIDCreater.getUUID());
		v.setWeixinid(weixinid);
		v.setBecast(becast);
		votingrecordService.save(v);
		return "updateVote";
	}
	/**
	 * 投票详情
	 */
	public String voteEntity(){
		String fuid=request.getParameter("fuid");
		buWeixinactivity=buWeixinactivityService.findById(fuid);
		return "voteEntity";
	}
	/**
	 * 意见征询
	 */
	public String opinion(){
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = ServletActionContext.getResponse().getWriter();
			Opinion opinion=new Opinion();
			opinion.setFuid(UUIDCreater.getUUID());
			opinion.setContent(skey);
			opinion.setCreatedate(new Date());
			opinionService.save(opinion);
			out.print("1");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 搜索
	 */
	public String likeFind(){
		String sql="";
		if(is==false){
			sql="select * from bu_weixinactivity where isVote='1' and (title like '%"+text+"%' or author like '%"+text+"%') order by number desc";
			}else{
			sql="select * from bu_weixinactivity where isVote='1' and (title like '%"+text+"%' or author like '%"+text+"%') order by worder";
			}
		buWeixinactivity_list=buWeixinactivityService.execSql(sql);
		return "PoeticWorksVote";
	}
	/**
	 * 查询votingrecord
	 */
	public String votingrecordPage(){
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=votingrecordService.getRecordCount(null);
		votingrecord_list=votingrecordService.selectByPage(indexPage,pageSize, null, null);
		return "votingrecordPage";
	}
	
	/**
	 * @see 赛诗会投票
	 * @author xiao
	 * @throws IOException 
	 */
	public String poetryVote() throws IOException {
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (weixinid != null && weixinid.length() > 0) { 
			int a=buWeixinvoteService.getRecordCount(" weixinid='"+weixinid+"'");
			if(a>0){
				out.print("您已经投过票了！");
				return null;
			}else{
				if(id!=null){
					BuWeixinvote buWeixinvote=new BuWeixinvote();
					buWeixinvote.setFuid(UUIDCreater.getUUID());
					buWeixinvote.setActivityid(id);
					buWeixinvote.setWeixinid(weixinid);
					buWeixinvote.setCreatetime(new Date());
					buWeixinvoteService.save(buWeixinvote);
					buWeixinactivity = buWeixinactivityService.findById(id);
					int num=buWeixinactivity.getNumber()!=null?buWeixinactivity.getNumber():0;
					num++;
					buWeixinactivity.setNumber(num);
					buWeixinactivityService.updateSelective(buWeixinactivity);
					out.print("success");
					return null;
				}else{
					out.print("网络错误，请刷新页面再试！");
					return null;
				}
			}
		}else{
			out.print("获取用户信息失败，	请稍后再试");
			return null;
		}
	}
	/**
	 * @see 自驾攻略列表页
	 * @author xiao
	 */
	public String selfDriving() {
		String SqlWhere = " type = '004004002' and deletemark!=1";
		pageSize = 15;
		// System.out.println(indexPage);
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buNewsService.getRecordCount(SqlWhere);
		buNews_list = buNewsService.selectByPage(indexPage, pageSize, SqlWhere,
				" ");

		// 外地到乌镇自驾列表
		other_places_route = buNewsService
				.execSql("select * from bu_news where type = '004004001'");
		if (pageType != null && pageType.equals("page")) {
			return "selfDrivingPage";
		} else {
			return "selfDriving";
		}

	}
	/**
	 * @see 店铺根据qid扫码查询
	 * @author xiao
	 */
	public String qserach() {
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out;
		try {
			out = ServletActionContext.getResponse().getWriter();
			List<IntegrateddataWithBLOBs> stay_list = integrateddataDAO
					.SimpleIntegratedQueryByPage(1, 1, " qid='" + qid + "'",
							null);
			if (stay_list != null && stay_list.size() > 0) {
				IntegrateddataWithBLOBs integrateddataWithBLOBs=stay_list.get(0);
				String data=TransformJSON.toJSON(integrateddataWithBLOBs);
				out.print(data);	
			}  

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * @see 店铺查询是否绑定qid
	 * @author xiao
	 */
	public String sbind() {
		PrintWriter out;
		try {
			out = ServletActionContext.getResponse().getWriter();
			List<IntegrateddataWithBLOBs> stay_list = integrateddataDAO
					.SimpleIntegratedQueryByPage(1, 1, " qid='" + qid + "'",
							null);
			if (stay_list == null || stay_list.size() == 0) {
				out.print("true");	
			} else {
				out.print("" + stay_list.get(0).getQid());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see 店铺查询
	 * @author xiao
	 */
	public String dsearch() {
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = ServletActionContext.getResponse().getWriter();
			List<IntegrateddataWithBLOBs> stay_list = integrateddataDAO
					.SimpleIntegratedQueryByPage(1, 8, " (fullname like '%"
							+ skey + "%' or address  like '%" + skey + "%')",
							null);
			String data = TransformJSON.toJSON(stay_list);
			out.print(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see 店铺绑定
	 * @author xiao
	 */
	public String bind() {
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = ServletActionContext.getResponse().getWriter();
			// 住宿
			if (type != null && type.startsWith("002017")) {
				BuStay buStay = buStayService.findById(id);
				if (buStay != null) {
					buStay.setQid(qid);
					////buStay.setFx(x);
					//buStay.setFy(y);
					buStayService.updateSelective(buStay);
				}
			}
			// 餐饮
			if (type != null && type.startsWith("002018")) {
				BuDining buDining = buDiningService.findById(id);
				if (buDining != null) {
					buDining.setQid(qid);
					//buDining.setFx(x);
					//buDining.setFy(y);
					buDiningService.updateSelective(buDining);
				}
			}
			// 购物娱乐
			if (type != null
					&& (type.startsWith("002016") || type.startsWith("002015"))) {
				BuEntertainmentshopping buEntertainmentshopping = buEntertainmentshoppingService
						.findById(id);
				if (buEntertainmentshopping != null) {
					buEntertainmentshopping.setQid(qid);
				//	buEntertainmentshopping.setFx(x);
				//	buEntertainmentshopping.setFy(y);
					buEntertainmentshoppingService
							.updateSelective(buEntertainmentshopping);
				}
			}
			out.print("success");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see 自驾攻略详情页
	 * @author xiao
	 */
	public String selfDrivingDetails() {
		buNews = buNewsService.findById(id);
		// -----------------阅读数
		int readnum = buNews.getReadnum() != null ? buNews.getReadnum() : 0;
		readnum++;
		buNews.setReadnum(readnum);
		buNewsService.updateSelective(buNews);
		return "selfDrivingDetails";
	}

	/**
	 * @see 点赞
	 * @author xiao
	 * @throws IOException
	 */
	public String Praise() throws IOException {
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		buNews = buNewsService.findById(id);
		if (buNews != null) {
			int pra = buNews.getPraise() != null ? buNews.getPraise() : 0;
			buNews.setPraise(pra + 1);
			buNewsService.updateSelective(buNews);
			out.print(pra + 1);
		} else {
			out.print("error");
		}
		return null;
	}

	/**
	 * @see 修改及新增
	 * @author xiao
	 */
	public String edit() {
		String id = buNews.getFuid();
		String roleid = (String) request.getSession().getAttribute("roleid");
		if (id != null && id.length() > 0) {
			com = CompetenceManager.getCom(roleid, "buNews");
			if (!com.getHisUpdate()) {
				return "error";
			}
			buNews.setModifydate(new Date());
			buNewsService.updateSelective(buNews);
		} else {
			com = CompetenceManager.getCom(roleid, "buNews");
			if (!com.getHisUpdate()) {
				return "error";
			}
			buNews.setFuid(UUIDCreater.getUUID());
			buNews.setCreatedate(new Date());
			buNews.setModifydate(new Date());
			buNewsService.save(buNews);
		}
		return "show";
	}

	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show() {
		buNews = buNewsService.findById(id);
		return "buNewsEditor";
	}

	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		com = CompetenceManager.getCom(roleid, "buNews");
		if (!com.getHisDelete()) {
			return "error";
		}
		if (id != null && id.length() > 0) {
			String ids[] = id.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					buNewsService.delete(ids[a]);
				}
			} else {
				buNewsService.delete(id);
			}
		}
		return "show";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSkey() {
		return skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}

	public BuNewsService getBuNewsService() {
		return buNewsService;
	}

	public void setBuNewsService(BuNewsService buNewsService) {
		this.buNewsService = buNewsService;
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

	public String getRe() {
		return re;
	}

	public void setRe(String re) {
		this.re = re;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<BuNews> getOther_places_route() {
		return other_places_route;
	}

	public void setOther_places_route(List<BuNews> other_places_route) {
		this.other_places_route = other_places_route;
	}

	public IntegrateddataDAO getIntegrateddataDAO() {
		return integrateddataDAO;
	}

	public void setIntegrateddataDAO(IntegrateddataDAO integrateddataDAO) {
		this.integrateddataDAO = integrateddataDAO;
	}

	public String getQid() {
		return qid;
	}

	public void setQid(String qid) {
		this.qid = qid;
	}

	public BuDiningService getBuDiningService() {
		return buDiningService;
	}

	public void setBuDiningService(BuDiningService buDiningService) {
		this.buDiningService = buDiningService;
	}

	public BuEntertainmentshoppingService getBuEntertainmentshoppingService() {
		return buEntertainmentshoppingService;
	}

	public void setBuEntertainmentshoppingService(
			BuEntertainmentshoppingService buEntertainmentshoppingService) {
		this.buEntertainmentshoppingService = buEntertainmentshoppingService;
	}

	public BuStayService getBuStayService() {
		return buStayService;
	}

	public void setBuStayService(BuStayService buStayService) {
		this.buStayService = buStayService;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public BuWeixinluckdrawService getBuWeixinluckdrawService() {
		return buWeixinluckdrawService;
	}

	public void setBuWeixinluckdrawService(
			BuWeixinluckdrawService buWeixinluckdrawService) {
		this.buWeixinluckdrawService = buWeixinluckdrawService;
	}

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

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public BuWeixinconfigService getBuWeixinconfigService() {
		return buWeixinconfigService;
	}

	public void setBuWeixinconfigService(BuWeixinconfigService buWeixinconfigService) {
		this.buWeixinconfigService = buWeixinconfigService;
	}
	public BuWeixinconfig getBuWeixinconfig() {
		return buWeixinconfig;
	}
	public void setBuWeixinconfig(BuWeixinconfig buWeixinconfig) {
		this.buWeixinconfig = buWeixinconfig;
	}
	public BuWeixinactivityService getBuWeixinactivityService() {
		return buWeixinactivityService;
	}
	public void setBuWeixinactivityService(
			BuWeixinactivityService buWeixinactivityService) {
		this.buWeixinactivityService = buWeixinactivityService;
	}
	public List<BuWeixinactivity> getBuWeixinactivity_list() {
		return buWeixinactivity_list;
	}
	public void setBuWeixinactivity_list(
			List<BuWeixinactivity> buWeixinactivity_list) {
		this.buWeixinactivity_list = buWeixinactivity_list;
	}
	public BuWeixinactivity getBuWeixinactivity() {
		return buWeixinactivity;
	}
	public void setBuWeixinactivity(BuWeixinactivity buWeixinactivity) {
		this.buWeixinactivity = buWeixinactivity;
	}
	public BuWeixinvoteService getBuWeixinvoteService() {
		return buWeixinvoteService;
	}
	public void setBuWeixinvoteService(BuWeixinvoteService buWeixinvoteService) {
		this.buWeixinvoteService = buWeixinvoteService;
	}
	public VotingrecordService getVotingrecordService() {
		return votingrecordService;
	}
	public void setVotingrecordService(VotingrecordService votingrecordService) {
		this.votingrecordService = votingrecordService;
	}
	public List<Votingrecord> getVotingrecord_list() {
		return votingrecord_list;
	}
	public void setVotingrecord_list(List<Votingrecord> votingrecord_list) {
		this.votingrecord_list = votingrecord_list;
	}
	public boolean isIs() {
		return is;
	}
	public void setIs(boolean is) {
		this.is = is;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public OpinionService getOpinionService() {
		return opinionService;
	}
	public void setOpinionService(OpinionService opinionService) {
		this.opinionService = opinionService;
	}
	
}

