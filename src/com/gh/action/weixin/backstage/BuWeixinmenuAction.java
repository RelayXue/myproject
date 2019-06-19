package com.gh.action.weixin.backstage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.TransformJSON;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuWeixinmenu;
import com.gh.entity.weixin.MessageXX;
import com.gh.entity.weixin.WXnews;
import com.gh.entity.weixin.WXnewsXX;
import com.gh.entity.weixin.WeiXinRetun;
import com.gh.entity.weixin.mainMenu;
import com.gh.entity.weixin.oneMenu;
import com.gh.entity.weixin.operationMenu;
import com.gh.service.BuWeixinmenuService;
import com.google.gson.Gson;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

public class BuWeixinmenuAction extends Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BuWeixinmenuService buWeixinmenuService;
	private String uid;
	private String parentId;
	private String id;
	private String skey;
	private List<BuWeixinmenu> buWeixinmenu_list;
	private BuWeixinmenu buWeixinmenu;
	private int num=0;

	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		com = CompetenceManager.getCom(roleid, "weixinMenu");
		// --------------------操作权限控制
		if (!com.getHisSelect()) {
			return "error";
		}
		List<BuWeixinmenu> buWeixinmenu_parent = buWeixinmenuService.execSql("select * from bu_Weixinmenu where parentId=1 order by SORTCODE");
		List<BuWeixinmenu> buWeixinmenu_all = buWeixinmenuService.execSql("select * from bu_Weixinmenu order by SORTCODE");
		buWeixinmenu_list = new ArrayList<BuWeixinmenu>();
		if (buWeixinmenu_parent != null && buWeixinmenu_parent.size() > 0) {
			for (int a = 0; a < buWeixinmenu_parent.size(); a++) {
				buWeixinmenu_list.add(buWeixinmenu_parent.get(a));
				for (int b = 0; b < buWeixinmenu_all.size(); b++)
					if (buWeixinmenu_all.get(b).getParentid().equals(buWeixinmenu_parent.get(a).getFuid())) {
						buWeixinmenu_all.get(b).setParentid(buWeixinmenu_parent.get(a).getFullname());
						buWeixinmenu_list.add(buWeixinmenu_all.get(b));
					}
			}
		}
		return "buWeixinmenu";
	}

	/**
	 * @see 修改及新增
	 * @author xiao
	 */
	public String edit() {
		String username = (String) request.getSession().getAttribute("username");
		String userid = (String) request.getSession().getAttribute("userid");
		String id = buWeixinmenu.getFuid();
		String roleid = (String) request.getSession().getAttribute("roleid");
		if (id != null && id.length() > 0) {
			com = CompetenceManager.getCom(roleid, "weixinMenu");
			if (!com.getHisUpdate()) {
				return "error";
			}
			buWeixinmenu.setModifydate(new Date());
			buWeixinmenuService.updateSelective(buWeixinmenu);
		} else {
			com = CompetenceManager.getCom(roleid, "weixinMenu");
			if (!com.getHisUpdate()) {
				return "error";
			}
			if (parentId != null && parentId.length() > 0) {
				buWeixinmenu.setParentid(parentId);
			} else {
				buWeixinmenu.setParentid("1");
			}
			buWeixinmenu.setFuid(UUIDCreater.getUUID());
			buWeixinmenu.setCreatedate(new Date());
			buWeixinmenu.setModifydate(new Date());
			buWeixinmenuService.save(buWeixinmenu);
		}
		return "show";
	}

	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show() {
		buWeixinmenu = buWeixinmenuService.findById(id);
		return "buWeixinmenuEditor";
	}

	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		com = CompetenceManager.getCom(roleid, "weixinMenu");
		if (!com.getHisDelete()) {
			return "error";
		}
		if (id != null && id.length() > 0) {
			String ids[] = id.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					buWeixinmenuService.delete(ids[a]);
				}
			} else {
				buWeixinmenuService.delete(id);
			}
		}
		return "show";
	}

	/**
	 * @see ajax 验证菜单数量上限
	 * @return
	 * @throws IOException
	 */
	public String menuNum() throws IOException {
		PrintWriter out = response.getWriter();
		int num = buWeixinmenuService.getRecordCount(" parentId=1");
		out.print(num);
		return null;
	}
	public static void main(String[] args) throws Exception {
		//{"touser":"2","msgtype":"news","news":{"articles":[{"title":"互联网","description":"test","url":"www","picurl":"www."}]}}
		/*{
		    "touser":"OPENID",
		    "msgtype":"news",
		    "news":{
		        "articles": [
		         {
		             "title":"Happy Day",
		             "description":"Is Really A Happy Day",
		             "url":"URL",
		             "picurl":"PIC_URL"
		         },
		         {
		             "title":"Happy Day",
		             "description":"Is Really A Happy Day",
		             "url":"URL",
		             "picurl":"PIC_URL"
		         }
		         ]
		    }
		}*/
		MessageXX messageXX=new MessageXX();
		messageXX.setMsgtype("news");
		messageXX.setTouser("2");
		
		WXnews wxnews=new WXnews();
		
		List<WXnewsXX> wxnewsXX_List=new ArrayList<WXnewsXX>();
		WXnewsXX wxnewsXX=new WXnewsXX();
		wxnewsXX.setDescription("宁静又自信，幽美又大气，11月17日，乌镇西栅景区举行世界互联网大会实景演练，安保、服务、志愿者等各路人马各就各位，表现出从容不迫、有条不紊的气度。从明日起，世界各地的嘉宾、媒体记者陆续汇聚乌镇，江南水乡进入世界互联网大会时间。");
		wxnewsXX.setPicurl("http://60.12.184.19/upload/11.jpg");
		wxnewsXX.setTitle("我们都准备好了");
		wxnewsXX.setUrl("http://www.wicnews.cn/system/2014/11/17/020363486.shtml");
		wxnewsXX_List.add(wxnewsXX);
		//--
		wxnews.setArticles(wxnewsXX_List);
		//----
		messageXX.setNews(wxnews);
		//--
		String tt=TransformJSON.toJSON(messageXX);
		System.out.println(tt);
		
		/*mainMenu mainMenu=new mainMenu();
		List<oneMenu> oneMenu_list=new ArrayList<oneMenu>();
		oneMenu oneMenu=new oneMenu();
		oneMenu.setType("view");
		oneMenu.setName("乌镇概括");
		oneMenu.setUrl("http://www.wuzhen.com.cn/cn/about.aspx");
		
		oneMenu_list.add(oneMenu);
		
		oneMenu oneMenu1=new oneMenu();
		oneMenu1.setName("游在乌镇");
		operationMenu operationMenu=new operationMenu();
		operationMenu.setType("view");
		operationMenu.setName("新鲜速递");
		operationMenu.setUrl("http://www.wuzhen.com.cn/");
		List<Object> sub_button=new ArrayList<Object>();
		sub_button.add(operationMenu);
		oneMenu1.setSub_button(sub_button);
		oneMenu_list.add(oneMenu1);
		
		
		oneMenu oneMenu2=new oneMenu();
		oneMenu2.setName("旅游攻略");
		operationMenu operationMenu1=new operationMenu();
		operationMenu1.setType("view");
		operationMenu1.setName("出游天气");
		operationMenu1.setUrl("http://www.wuzhen.com.cn/");
		operationMenu operationMenu11=new operationMenu();
		operationMenu11.setType("view");
		operationMenu11.setName("地图导览 ");
		operationMenu11.setUrl("http://www.wuzhen.com.cn/");
		List<Object> sub_button1=new ArrayList<Object>();
		sub_button1.add(operationMenu1);
		sub_button1.add(operationMenu11);
		oneMenu2.setSub_button(sub_button1);
		oneMenu_list.add(oneMenu2);
		
		
		mainMenu.setButton(oneMenu_list);
		String tt=TransformJSON.toJSON(mainMenu);
		System.out.println(tt);
		
		
		String jsonStr=tt;
		GetAccessToken accessToken=new GetAccessToken();
		HttpsURLConnection urlCon = null;
		String httpsUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken.AccessToken;
		String result="";
		try {
			urlCon = (HttpsURLConnection) (new URL(httpsUrl)).openConnection();
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setRequestMethod("POST");
			urlCon.setRequestProperty("Content-Length", String.valueOf(jsonStr.getBytes().length));
			urlCon.setUseCaches(false);
			urlCon.getOutputStream().write(jsonStr.getBytes("utf-8"));
			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				result+=line;
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	/**
	 * @see 将菜单提交至微信公众平台
	 * @author xiao
	 * @throws IOException 
	 */
	public String subWeixin() throws IOException {
		PrintWriter out = response.getWriter();
		//组装菜单格式
		mainMenu mainMenu=new mainMenu();
		List<oneMenu> oneMenu_list=new ArrayList<oneMenu>();
		List<BuWeixinmenu> buWeixinmenu_parent = buWeixinmenuService.execSql("select * from bu_Weixinmenu where parentId=1 order by SORTCODE");
		List<BuWeixinmenu> buWeixinmenu_all = buWeixinmenuService.execSql("select * from bu_Weixinmenu order by SORTCODE");
		if (buWeixinmenu_parent != null && buWeixinmenu_parent.size() > 0) {
			for (int a = 0; a < buWeixinmenu_parent.size(); a++) {
				oneMenu oneMenu=new oneMenu();
				oneMenu.setName(buWeixinmenu_parent.get(a).getFullname());
				int t=0;
				List<Object> sub_button=new ArrayList<Object>();
				for (int b = 0; b < buWeixinmenu_all.size(); b++)
		 			if (buWeixinmenu_all.get(b).getParentid().equals(buWeixinmenu_parent.get(a).getFuid())) {
						t++;
						operationMenu operationMenu=new operationMenu();
						operationMenu.setType("view");
						operationMenu.setName(buWeixinmenu_all.get(b).getFullname()); 
						operationMenu.setUrl(URLEncoder.encode(buWeixinmenu_all.get(b).getWurl(),"utf-8"));
					//	operationMenu.setUrl(buWeixinmenu_all.get(b).getWurl());
						sub_button.add(operationMenu);
						oneMenu.setSub_button(sub_button);
					}
				//判断有无子目录
				if(t==0){
					oneMenu.setType("view");
					oneMenu.setUrl(buWeixinmenu_parent.get(a).getWurl());
				}
				oneMenu_list.add(oneMenu);
			}
		}
		mainMenu.setButton(oneMenu_list);
		String jsonStr = TransformJSON.toJSON(mainMenu);
		jsonStr=URLDecoder.decode(jsonStr,"utf-8");
		System.out.println("---------"+jsonStr);
		//https post提交
		GetAccessToken accessToken=new GetAccessToken();
		HttpsURLConnection urlCon = null;
		String httpsUrl = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=" + accessToken.AccessToken;
		try {
			urlCon = (HttpsURLConnection) (new URL(httpsUrl)).openConnection();
			urlCon.setDoInput(true);
			urlCon.setDoOutput(true);
			urlCon.setRequestMethod("POST");
			urlCon.setRequestProperty("Content-Length", String.valueOf(jsonStr.getBytes().length));
			urlCon.setUseCaches(false);
			urlCon.getOutputStream().write(jsonStr.getBytes("utf-8"));
			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();
			BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
			String line;
			String result="";
			while ((line = in.readLine()) != null) {
				result+=line;
			}
			System.out.println(result);
			 Gson gson = new Gson();
			 WeiXinRetun weixinRetun= gson.fromJson(result,WeiXinRetun.class);
			 String errcode=weixinRetun.getErrcode();
			 String errmsg=weixinRetun.getErrmsg();
			 //access_token失效  重新获取  只请求3次
			 if(num<=3){
				 if(errcode.equals("40001")){
					 GetAccessToken.newGetAccess();
					 num++;
					 this.subWeixin();
				 }
			 }
			 out.print(errcode+","+errmsg);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

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

	public BuWeixinmenuService getBuWeixinmenuService() {
		return buWeixinmenuService;
	}

	public void setBuWeixinmenuService(BuWeixinmenuService buWeixinmenuService) {
		this.buWeixinmenuService = buWeixinmenuService;
	}

	public List<BuWeixinmenu> getBuWeixinmenu_list() {
		return buWeixinmenu_list;
	}

	public void setBuWeixinmenu_list(List<BuWeixinmenu> buWeixinmenu_list) {
		this.buWeixinmenu_list = buWeixinmenu_list;
	}

	public BuWeixinmenu getBuWeixinmenu() {
		return buWeixinmenu;
	}

	public void setBuWeixinmenu(BuWeixinmenu buWeixinmenu) {
		this.buWeixinmenu = buWeixinmenu;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
