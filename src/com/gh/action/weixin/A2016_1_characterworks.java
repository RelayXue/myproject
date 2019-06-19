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

import javax.net.ssl.HttpsURLConnection;

import org.apache.struts2.ServletActionContext;

import com.gh.action.weixin.backstage.GetAccessToken;
import com.gh.action.weixin.backstage.SHA1;
import com.gh.action.weixin.front.WeixinUserInfo;
import com.gh.action.weixin.front.userAppidReturn;
import com.gh.base.Action;
import com.gh.common.FileUpload;
import com.gh.common.HtmlUtil;
import com.gh.common.ImageCompression;
import com.gh.common.TuJingUtil;
import com.gh.common.UUIDCreater;
import com.gh.entity.A20161activity;
import com.gh.entity.A20161characterworks;
import com.gh.entity.A20161click;
import com.gh.service.A20161activityService;
import com.gh.service.A20161characterworksService;
import com.gh.service.A20161clickService;
import com.google.gson.Gson;

public class A2016_1_characterworks extends Action{
	private String weixinid;
	private String code;
	private int type;
	private A20161activityService a20161activityService;//活动表
	private A20161characterworksService a20161characterworksService;//参与者表
	private A20161clickService a20161clickService;//参与投票者表
	private String nonceStr;
	private Long timestamp;
	private String signature;
	private A20161activity a20161activity;
	private A20161characterworks a20161characterworks;
	private List<A20161characterworks> list_works;
	private String id;
	private String operation;
	private String jsurl;
	private String jsapi_ticket;
	private String imageServerId;
	private int countUrl;
	
	private int pageSize;
	private int indexPage;
	private int totalPage;
	private String pageType;
	private String soushuo="";
	//图片
	private List<File> filedata;// 这里的"fileName"一定要与表单中的文件域名相同
	
	/*
	 * 获取微信ID
	 
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
			}
			weixinid="123";
		}
		return "show";
	}*/
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
	 * 首页，获取type判断
	 * type为0的时候，
	 */
	public String HomePage(){
		a20161activity=a20161activityService.findById("1");
		type=a20161activity.getType();
		return "NotOpenActivity";
	}
	/**
	 * 参与比赛跳转（获取微信id）
	 */
	public String UploadPictures(){
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
				
				
				re = postSendMessage("https://api.weixin.qq.com/cgi-bin/user/info?access_token="
						+ GetAccessToken.AccessToken + "&openid="
						+ weixinid + "&lang=zh_CN", "");
				System.out.println("------------------------"+re);
				WeixinUserInfo weixinUserInfo = gson.fromJson(re,WeixinUserInfo.class);
				String sub="0";
				sub=weixinUserInfo.getSubscribe();
				if("0".equals(sub)){
					return "QRcode";
				}
			}
			//weixinid="123";
		}
		return "UploadPictures";
	}
	/**
	 * 保存人物作品
	 * @throws IOException 
	 */
	public String saveWorks() throws IOException{
		System.out.println("---------------------1");
		if(weixinid==null || "".equals(weixinid)){
			operation="0";
			return "saveWorks";
		}
		boolean us=this.isVote(weixinid);
			if(us==true){
				System.out.println("---------------------2");
				File[] file=new File[filedata.size()];
				for(int i=0;i<filedata.size();i++){
					file[i]=filedata.get(i);
				}
				FileUpload fileupload=new FileUpload();
				ArrayList<String> re=fileupload.inFile(file, "/upload", imgFileName);
				if(re!=null&&re.size()>0){
					String imges="";
					for(int a=0;a<re.size();a++){
						imges+=re.get(a)+",";
					}
					imges=imges.length()>0?imges.substring(0,imges.length()-1):"";
					a20161characterworks.setImgurl(imges);
					for(int i=0;i<re.size();i++){
						//压缩图片
						ImageCompression mypic = new ImageCompression();
						String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
						mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
						mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
					}
				}
				a20161characterworks.setFuid(this.fuid());
				a20161characterworks.setWeixinid(weixinid);
				a20161characterworks.setNum(0);
				a20161characterworks.setType(0);
				a20161characterworks.setTotal(0.0);
				a20161characterworks.setExpertmark(0);
				a20161characterworks.setVotetype(0);
				a20161characterworks.setCreatetime(new Date());
				a20161characterworksService.save(a20161characterworks);
				type=4;
			}else{
				type=3;
			}
		return "saveWorks";
	}
	/**
	 * 获取媒体文件
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param media_id
	 *            媒体文件id
	 * @param savePath
	 *            文件在服务器上的存储路径
	 * */
	public static String downloadMedia(String mediaId, String savePath) {
		String filePath = null;
		String filename = "";
		// 拼接请求地址
		String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="
				+ GetAccessToken.AccessToken + "&media_id=" + mediaId;
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");

			if (!savePath.endsWith("/")) {
				savePath += "/";
			}
			// 根据内容类型获取扩展名
			String fileExt = GetAccessToken.getFileEndWitsh(conn.getHeaderField("Content-Type"));
			// 将mediaId作为文件名
			String n = TuJingUtil.getUUIDFileName();
			filePath = savePath + n + "." + fileExt;
			filename = n + "." + fileExt;
			BufferedInputStream bis = new BufferedInputStream(
					conn.getInputStream());
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			byte[] buf = new byte[8096];
			int size = 0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();

			conn.disconnect();
			String info = String.format("下载媒体文件成功，filePath=" + filePath);
			
			ImageCompression mypic = new ImageCompression();
			String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
			mypic.compressPic(targetDirectory + "/", targetDirectory + "/", n + "." + fileExt, "B_"+n + "." + fileExt, 900, 700, true);
			mypic.compressPic(targetDirectory + "/", targetDirectory + "/", n + "." + fileExt, "L_"+n + "." + fileExt, 300, 280, true);
			
		} catch (Exception e) {
			filePath = null;
			String error = String.format("下载媒体文件失败：%s", e);
		}
		return filename;
	}
	/**
	 * ajax判断该微信是否上传过作品
	 * @throws IOException 
	 */
	public String isWorks() throws IOException{
		PrintWriter out=response.getWriter();
		if(weixinid!=null || !("".equals(weixinid))){
			int count=a20161characterworksService.getRecordCount("weixinid='"+weixinid+"'");
			if(count>0){
				out.print("false");
				return null;
			}else{
				return null;
			}
		}else{
			out.print("false");
			return null;
		}
	}
	/**
	 * 展示作品
	 */
	public String exhibitionWorks(){
		
		soushuo=soushuo.replace(", ","");
		operation=operation.replace(", ","");
			pageSize = 4;
			indexPage = indexPage == 0 ? 1 : indexPage;
			String where="(fuid='"+soushuo+"' or peoplename like '%"+soushuo+"%')";
			if("0".equals(operation)){
				totalPage = a20161characterworksService.getRecordCount("");
				list_works = a20161characterworksService.selectByPage(indexPage, pageSize, where, "fuid");
			}else if("1".equals(operation)){
				where=where+"and votetype=1";
				totalPage = a20161characterworksService.getRecordCount("votetype=1");
				list_works = a20161characterworksService.selectByPage(indexPage, pageSize, where, "fuid");
			}
			if(list_works!=null && list_works.size()>0){
				for(int i=0;i<list_works.size();i++){
					list_works.get(i).setIntroduce(HtmlUtil.delHTMLTag(list_works.get(i).getIntroduce()));
					list_works.get(i).setOperation(operation);
					if(list_works.get(i).getImgurl()!=null){
						int count=selectCount(',', list_works.get(i).getImgurl());
						if(count>=1){
							list_works.get(i).setImgurl("L_"+list_works.get(i).getImgurl().substring(0, list_works.get(i).getImgurl().indexOf(',')));
						}else{
							list_works.get(i).setImgurl("L_"+list_works.get(i).getImgurl());
						}
					}
					if(list_works.get(i).getTitle().length()>4){
						list_works.get(i).setTitle(list_works.get(i).getTitle().substring(0, 4)+"…");
					}
					if(list_works.get(i).getIntroduce().length()>8){
						list_works.get(i).setIntroduce(list_works.get(i).getIntroduce().substring(0, 8)+"…");
					}
				}
			}
			if (pageType != null && pageType.equals("page")) {
				return "worksPage";
			} else {
				return "worksData";
			}
	}
	/**
	 * 查看详情
	 * @return
	 */
	public String worksDetails(){
		if (code != null && code.length() > 0) { 
			String re =postSendMessage(
					"https://api.weixin.qq.com/sns/oauth2/access_token?appid="
							+ GetAccessToken.AppId + "&secret="
							+ GetAccessToken.AppSecret + "&code=" + code
							+ "&grant_type=authorization_code", "");
			Gson gson = new Gson();
			userAppidReturn userAppid = gson.fromJson(re, userAppidReturn.class);
			weixinid = userAppid.getOpenid();
			
			/*-----------------------------------------------------------*/
			
			if(weixinid !=null && !("".equals(weixinid))){
				re = postSendMessage("https://api.weixin.qq.com/cgi-bin/user/info?access_token="
						+ GetAccessToken.AccessToken + "&openid="
						+ weixinid + "&lang=zh_CN", "");
				System.out.println("------------------------"+re);
				WeixinUserInfo weixinUserInfo = gson.fromJson(re,WeixinUserInfo.class);
				String sub="0";
				sub=weixinUserInfo.getSubscribe();
				if("0".equals(sub)){
					return "QRcode";
				}else{
					boolean us=this.isVote(weixinid);
					if(us==true){
						a20161characterworks=a20161characterworksService.findById(id);
						a20161characterworks.setNum(a20161characterworks.getNum()+1);
						a20161characterworksService.update(a20161characterworks);
						
						A20161click entity=new A20161click();
						entity.setFuid(UUIDCreater.getUUID());
						entity.setWeixinid(weixinid);
						entity.setWorksid(a20161characterworks.getFuid());
						entity.setCreatetime(new Date());
						a20161clickService.save(entity);
						type=2;
					}else{
						type=1;
					}
				}
			}
		}
		
		List<String> imgUrl=new ArrayList<String>();
		a20161characterworks=a20161characterworksService.findById(id);
		String url=a20161characterworks.getImgurl();
		int count=this.selectCount(',',url);
		for(int i=0;i<=count;i++){
			if(i<count){
				imgUrl.add("B_"+url.substring(0, url.indexOf(',')));
				url=url.substring(url.indexOf(',')+1, url.length());
			}else{
				imgUrl.add("B_"+url);
			}
		}
		request.setAttribute("imgUrl", imgUrl);
		
		if(count>=1){
			a20161characterworks.setImgurl(url);
		}
		countUrl=count+1;
		return "worksDetails";
	}
	/**
	 * 投票
	 * @return
	 */
	public String vote(){
		if (code != null && code.length() > 0) {
			String re =postSendMessage(
					"https://api.weixin.qq.com/sns/oauth2/access_token?appid="
							+ GetAccessToken.AppId + "&secret="
							+ GetAccessToken.AppSecret + "&code=" + code
							+ "&grant_type=authorization_code", "");
			Gson gson = new Gson();
			userAppidReturn userAppid = gson.fromJson(re, userAppidReturn.class);
			weixinid = userAppid.getOpenid();
			
			/*-----------------------------------------------------------*/
			
			re = postSendMessage("https://api.weixin.qq.com/cgi-bin/user/info?access_token="
					+ GetAccessToken.AccessToken + "&openid="
					+ weixinid + "&lang=zh_CN", "");
			System.out.println("------------------------"+re);
			WeixinUserInfo weixinUserInfo = gson.fromJson(re,WeixinUserInfo.class);
			String sub="0";
			sub=weixinUserInfo.getSubscribe();
			operation="1";
			if("0".equals(sub)){
				return "QRcode";
			}else{
				boolean us=this.isVote(weixinid);
				if(us==true){
					a20161characterworks=a20161characterworksService.findById(id);
					a20161characterworks.setNum(a20161characterworks.getNum()+1);
					a20161characterworksService.update(a20161characterworks);
					
					A20161click entity=new A20161click();
					entity.setFuid(UUIDCreater.getUUID());
					entity.setWeixinid(weixinid);
					entity.setWorksid(a20161characterworks.getFuid());
					entity.setCreatetime(new Date());
					a20161clickService.save(entity);
					type=2;
				}else{
					type=1;
				}
			}
			return "vote";
		}else{
			return "QRcode";
		}
	}
	/**
	 * ajax判断该微信是否投过票
	 * @return
	 * @throws IOException 
	 */
	public boolean isVote(String weixinid) {
		int count=a20161clickService.getRecordCount("weixinid='"+weixinid+"'");
		if(count>0){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 查看排名
	 * @return
	 */
	public String ranking(){
		String sql="select * from a2016_1_characterworks where type="+type+" order by total desc";
		list_works=a20161characterworksService.execSql(sql);
		return "ranking";
	}
	/**
	 * 无微信id
	 */
	public String QRcode(){
		return "QRcode";
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 辅助方法
	 * @return
	 */
	//获取id
	public String fuid(){
		String ret="";
		String sql="select * from a2016_1_characterworks order by fuid desc";
		list_works=a20161characterworksService.execSql(sql);
		if(list_works.size()==0 || list_works==null){
			ret="0001";
		}else{
			int num=Integer.parseInt(list_works.get(0).getFuid());
			num++;
			ret=num+"";
			if(ret.length()==1){
				ret="000"+ret;
			}else if(ret.length()==2){
				ret="00"+ret;
			}else if(ret.length()==3){
				ret="0"+ret;
			}
		}
		return ret;
	}
	//查询一个字符在一个字符串出现过几次
	public int selectCount(char a,String b){
		int count=0;
		if(b==null){
			return count;
		}
		for(int i=0;i<b.length();i++){
			if(a==b.charAt(i)){
				count++;
			}
		}
		return count;
	}
	/**
	 * get,set方法
	 * @return
	 */
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
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public A20161activityService getA20161activityService() {
		return a20161activityService;
	}
	public void setA20161activityService(A20161activityService a20161activityService) {
		this.a20161activityService = a20161activityService;
	}
	public A20161characterworksService getA20161characterworksService() {
		return a20161characterworksService;
	}
	public void setA20161characterworksService(A20161characterworksService a20161characterworksService) {
		this.a20161characterworksService = a20161characterworksService;
	}
	public A20161clickService getA20161clickService() {
		return a20161clickService;
	}
	public void setA20161clickService(A20161clickService a20161clickService) {
		this.a20161clickService = a20161clickService;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public Long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public A20161activity getA20161activity() {
		return a20161activity;
	}
	public void setA20161activity(A20161activity a20161activity) {
		this.a20161activity = a20161activity;
	}
	public A20161characterworks getA20161characterworks() {
		return a20161characterworks;
	}
	public void setA20161characterworks(A20161characterworks a20161characterworks) {
		this.a20161characterworks = a20161characterworks;
	}
	public List<A20161characterworks> getList_works() {
		return list_works;
	}
	public void setList_works(List<A20161characterworks> list_works) {
		this.list_works = list_works;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getIndexPage() {
		return indexPage;
	}
	public void setIndexPage(int indexPage) {
		this.indexPage = indexPage;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public String getPageType() {
		return pageType;
	}
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJsurl() {
		return jsurl;
	}
	public void setJsurl(String jsurl) {
		this.jsurl = jsurl;
	}
	public String getJsapi_ticket() {
		return jsapi_ticket;
	}
	public void setJsapi_ticket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
	}
	public String getImageServerId() {
		return imageServerId;
	}
	public void setImageServerId(String imageServerId) {
		this.imageServerId = imageServerId;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getCountUrl() {
		return countUrl;
	}
	public void setCountUrl(int countUrl) {
		this.countUrl = countUrl;
	}
	public String getSoushuo() {
		return soushuo;
	}
	public void setSoushuo(String soushuo) {
		this.soushuo = soushuo;
	}
	public List<File> getFiledata() {
		return filedata;
	}
	public void setFiledata(List<File> filedata) {
		this.filedata = filedata;
	}
}
