package com.gh.action.weixin.backstage;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gh.base.Action;
import com.gh.common.TransformJSON;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuWeixinconfig;
import com.gh.entity.BuWeixinmass;
import com.gh.entity.BuWeixinmessage;
import com.gh.entity.weixin.SendContext;
import com.gh.entity.weixin.WXnews;
import com.gh.entity.weixin.WXnewsXX;
import com.gh.entity.weixin.WeiXinRetun;
import com.gh.entity.weixin.content;
import com.gh.entity.weixin.media;
import com.gh.service.BuWeixinconfigService;
import com.gh.service.BuWeixinmessageService;
import com.google.gson.Gson;

@SuppressWarnings("serial")
@Controller("WeixinActionId")
@Scope("prototype")
public class WeixinAction extends Action {

	private BuWeixinmessageService buWeixinmessageService;
	private BuWeixinconfigService buWeixinconfigService;

	/**
	 * 微信开发者验证
	 * 
	 * @return
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public String Verification() throws IOException,
			ParserConfigurationException, SAXException {
		/*
		 * // 微信加密签名 String signature = request.getParameter("signature"); //
		 * 随机字符串 String echostr = request.getParameter("echostr"); // 时间戳 String
		 * timestamp = request.getParameter("timestamp"); // 随机数 String nonce =
		 * request.getParameter("nonce");
		 * 
		 * String[] str = { TOKEN, timestamp, nonce }; Arrays.sort(str); //
		 * 字典序排序 String bigStr = str[0] + str[1] + str[2]; // SHA1加密 String
		 * digest = new
		 * SHA1().getDigestOfString(bigStr.getBytes()).toLowerCase(); //
		 * 确认请求来至微信 if (digest.equals(signature)) {
		 * response.getWriter().print(echostr); } return null;
		 */

		response.setContentType("text/xml");
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(request.getInputStream());
		getXmlString(doc);
		return null;
	}

	/**
	 * @see 解析消息 并返回各类信息
	 */
	public void getXmlString(Document doc) {
		// 开发者微信号
		NodeList _UserName = doc.getElementsByTagName("ToUserName");
		String ToUserName = _UserName != null && _UserName.getLength() > 0 ? _UserName
				.item(0).getFirstChild().getNodeValue()
				: "";
		// 发送方帐号
		NodeList _FromUserName = doc.getElementsByTagName("FromUserName");
		String FromUserName = _FromUserName != null
				&& _FromUserName.getLength() > 0 ? _FromUserName.item(0)
				.getFirstChild().getNodeValue() : "";
		// 创建时间
		NodeList _CreateTime = doc.getElementsByTagName("CreateTime");
		String CreateTime = _CreateTime != null && _CreateTime.getLength() > 0 ? _CreateTime
				.item(0).getFirstChild().getNodeValue()
				: "0";
		// 消息类型
		NodeList _MsgType = doc.getElementsByTagName("MsgType");
		String MsgType = _MsgType != null && _MsgType.getLength() > 0 ? _MsgType
				.item(0).getFirstChild().getNodeValue()
				: "";
		// 文本消息内容
		NodeList _Content = doc.getElementsByTagName("Content");
		String Content = _Content != null && _Content.getLength() > 0 ? _Content
				.item(0).getFirstChild().getNodeValue()
				: "";
		// 消息ID
		NodeList _MsgId = doc.getElementsByTagName("MsgId");
		String MsgId = _MsgId != null && _MsgId.getLength() > 0 ? _MsgId
				.item(0).getFirstChild().getNodeValue() : "";
		// 图片路径
		NodeList _PicUrl = doc.getElementsByTagName("PicUrl");
		String PicUrl = _PicUrl != null && _PicUrl.getLength() > 0 ? _PicUrl
				.item(0).getFirstChild().getNodeValue() : "";
		// 语音消息ID
		NodeList _MediaId = doc.getElementsByTagName("MediaId");
		String MediaId = _MediaId != null && _MediaId.getLength() > 0 ? _MediaId
				.item(0).getFirstChild().getNodeValue()
				: "";
		// 语音格式 如amr，speex等
		NodeList _Format = doc.getElementsByTagName("Format");
		String Format = _Format != null && _Format.getLength() > 0 ? _Format
				.item(0).getFirstChild().getNodeValue() : "";
		// 事件类型
		NodeList _Event = doc.getElementsByTagName("Event");
		String Event = _Event != null && _Event.getLength() > 0 ? _Event
				.item(0).getFirstChild().getNodeValue() : "";
		NodeList _EventKey = doc.getElementsByTagName("EventKey");
		String EventKey = _EventKey != null && _EventKey.getLength() > 0 ? _EventKey
				.item(0).getFirstChild().getNodeValue()
				: "";
		// 响应不同事件
		if (Event != null && Event.trim().length() > 0) {
			// 用户订阅
			if (Event.equals("subscribe")) {
				String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="
						+ GetAccessToken.AccessToken;
				
				// 发送消息类型
					SendContext sendContext = new SendContext();
				  sendContext.setMsgtype("text");
				  sendContext.setTouser(FromUserName);
				 
				// 发送消息内容
				/*MessageXX messageXX = new MessageXX();
				messageXX.setMsgtype("news");
				messageXX.setTouser(FromUserName);

				WXnews wxnews = new WXnews();

				List<WXnewsXX> wxnewsXX_List = new ArrayList<WXnewsXX>();
				WXnewsXX wxnewsXX = new WXnewsXX();
				wxnewsXX.setDescription("宁静又自信，幽美又大气，11月17日，乌镇西栅景区举行世界互联网大会实景演练，安保、服务、志愿者等各路人马各就各位，表现出从容不迫、有条不紊的气度。从明日起，世界各地的嘉宾、媒体记者陆续汇聚乌镇，江南水乡进入世界互联网大会时间。");
				wxnewsXX.setPicurl("http://60.12.184.19/upload/11.jpg");
				wxnewsXX.setTitle("我们都准备好了");
				wxnewsXX.setUrl("http://www.wicnews.cn/system/2014/11/17/020363486.shtml");
				wxnewsXX_List.add(wxnewsXX);
				wxnews.setArticles(wxnewsXX_List);
				messageXX.setNews(wxnews);
				String text = TransformJSON.toJSON(messageXX);*/
				// System.out.println(text);
				  String con="跟着微信，游玩乌镇！感谢您关注乌镇发布微信公众号！乌镇特色、旅游服务、自驾攻略、美图欣赏等全新内容为您提供最贴心的服务！";
				  List<BuWeixinconfig> list= buWeixinconfigService.execSql("select * from bu_Weixinconfig where type='005001' ");
				  if(list!=null&&list.size()>0){
					  con=list.get(0).getContent();
				  }
				  content content=new content(); 
				  content.setContent(con); 
				  sendContext.setText(content);
				  String text=TransformJSON.toJSON(sendContext);
				 
				postSendMessage(url, text);
			}
			// 用户取消订阅
			if (Event.equals("unsubscribe")) {

			}
			// 无事件 接受普通消息
		} else {
			BuWeixinmessage buWeixinmessage = new BuWeixinmessage();
			buWeixinmessage.setFuid(UUIDCreater.getUUID());
			buWeixinmessage.setContent(Content);
			buWeixinmessage.setFromusername(FromUserName);
			buWeixinmessage.setCreatedate(new Date());
			buWeixinmessage.setMessagetime(Integer.parseInt(CreateTime));
			buWeixinmessage.setMsgid(MsgId);
			buWeixinmessage.setMsgtype(MsgType);
			buWeixinmessage.setPicurl(PicUrl);
			buWeixinmessage.setMediaid(MediaId);
			buWeixinmessageService.save(buWeixinmessage);
		}

	}

	/**
	 * @see 上传下载多媒体文件
	 * @param fileType
	 *            文件类型
	 * @param filePath
	 *            文件路径
	 */
	public static String sendUpload(String fileType, String filePath)
			throws Exception {
		String result = null;
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) {
			throw new IOException("文件不存在");
		}
		/**
		 * 第一部分
		 */
		URL urlObj = new URL(
				"http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="
						+ GetAccessToken.AccessToken + "&type=" + fileType + "");
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); // post方式不能使用缓存
		// 设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		// 设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary="
				+ BOUNDARY);
		// 请求正文信息
		// 第一部分：
		StringBuilder sb = new StringBuilder();
		sb.append("--"); // 必须多两道线
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\""
				+ file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] head = sb.toString().getBytes("utf-8");
		// 获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		// 输出表头
		out.write(head);
		// 文件正文部分
		// 把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();
		// 结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
		out.write(foot);
		out.flush();
		out.close();
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		try {
			// 定义BufferedReader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
			throw new IOException("数据读取异常");
		} finally {
			if (reader != null) {
				reader.close();
			}
		}

		return result;
	}

	/**
	 * @see 上传图文消息素材
	 */
	public static String sendUploadNews(String media_id) throws Exception {
		String url = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token="
				+ GetAccessToken.AccessToken;
		// 发送消息内容
		WXnews wxnews = new WXnews();
		List<WXnewsXX> wxnewsXX_List = new ArrayList<WXnewsXX>();
		WXnewsXX wxnewsXX = new WXnewsXX();
		wxnewsXX.setThumb_media_id(media_id);
		wxnewsXX.setTitle("我们都准备好了");
		// wxnewsXX.setContent_source_url("http://www.wicnews.cn/system/2014/11/17/020363486.shtml");
		wxnewsXX.setContent("宁静又自信，幽美又大气，11月17日，乌镇西栅景区举行世界互联网大会实景演练，安保、服务、志愿者等各路人马各就各位，表现出从容不迫、有条不紊的气度。从明日起，世界各地的嘉宾、媒体记者陆续汇聚乌镇，江南水乡进入世界互联网大会时间。");
		wxnewsXX.setDigest("互联网大会");
		wxnewsXX.setShow_cover_pic("0");
		wxnewsXX_List.add(wxnewsXX);
		wxnews.setArticles(wxnewsXX_List);
		String text = TransformJSON.toJSON(wxnews);
		String result = postSendMessage(url, text);
		return result;

	}

	/**
	 * @see 发送信息至所有用户
	 * @param
	 * @throws Exception
	 */
	public String sendAllMember(List<BuWeixinmass> buWeixinmass_list)
			throws Exception {
		Gson gson = new Gson();
		WXnews wxnews = new WXnews();
		List<WXnewsXX> wxnewsXX_List = new ArrayList<WXnewsXX>();
		String Surl = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token="+ GetAccessToken.AccessToken;
		for (int a = 0; a < buWeixinmass_list.size(); a++) {
			// 第一步上传图片
			//获取tomcat路径
			String imgPath=System.getProperty("catalina.base")+"\\webapps\\ROOT\\upload\\"+"L_"+buWeixinmass_list.get(a).getPicurl();
			String re = sendUpload("thumb",imgPath);
			media me = gson.fromJson(re, media.class);
			String thumb_media_id = me.getThumb_media_id();
			// 第二步 上传图文消息素材
			// 组装json格式
			WXnewsXX wxnewsXX = new WXnewsXX();
			wxnewsXX.setThumb_media_id(thumb_media_id);
			wxnewsXX.setTitle(buWeixinmass_list.get(a).getTitle());
			wxnewsXX.setContent(buWeixinmass_list.get(a).getContent());
			wxnewsXX.setDigest(buWeixinmass_list.get(a).getDescription());
			wxnewsXX_List.add(wxnewsXX);
			wxnews.setArticles(wxnewsXX_List);
		}
		String text = TransformJSON.toJSON(wxnews);
		text=StringEscapeUtils.unescapeJava(text);
		String result = postSendMessage(Surl, text);
		media newsMe = gson.fromJson(result, media.class);
		String newMedia_id = newsMe.getMedia_id();
		
		
		  String json="";
		  json+="{";
		  json+="\"filter\":{";
		  json+="\"is_to_all\":true"; 
		  json+="},"; 
		  json+="\"mpnews\":{"; 
		  json+= "\"media_id\":\""+newMedia_id+"\""; 
		  json+="},"; 
		  json+="\"msgtype\":\"mpnews\""; 
		  json+="}";
		 
		/*String json = "";
		json += "{";
		json += "\"touser\":\"oSqiqtwzua9UCapHIfEWqP6VodWQ\",";
		json += "\"mpnews\":{";
		json += "\"media_id\":\"" + newMedia_id + "\"";
		json += "},";
		json += "\"msgtype\":\"mpnews\"";
		json += "}";*/
		// 第三步 群体发送
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token="+ GetAccessToken.AccessToken;
		//String url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token="+ GetAccessToken.AccessToken;
		// 发送消息类型
		String re= postSendMessage(url, json);
		WeiXinRetun weiXinRetun=gson.fromJson(re, WeiXinRetun.class);
		return weiXinRetun.getErrcode();

	}
	
	/**
	 * @see 预发送信息
	 * @param
	 * @throws Exception
	 */
	public String ysendMember(List<BuWeixinmass> buWeixinmass_list,String weixinNum)
			throws Exception {
		Gson gson = new Gson();
		WXnews wxnews = new WXnews();
		List<WXnewsXX> wxnewsXX_List = new ArrayList<WXnewsXX>();
		
		String Surl = "https://api.weixin.qq.com/cgi-bin/media/uploadnews?access_token="+ GetAccessToken.AccessToken;
		for (int a = 0; a < buWeixinmass_list.size(); a++) {
			// 第一步上传图片
			//获取tomcat路径
			String imgPath=System.getProperty("catalina.base")+"\\webapps\\ROOT\\upload\\"+"L_"+buWeixinmass_list.get(a).getPicurl();
			String re = sendUpload("thumb",imgPath);
			media me = gson.fromJson(re, media.class);
			String thumb_media_id = me.getThumb_media_id();
			// 第二步 上传图文消息素材
			// 组装json格式
			WXnewsXX wxnewsXX = new WXnewsXX();
			wxnewsXX.setThumb_media_id(thumb_media_id);
			wxnewsXX.setTitle(buWeixinmass_list.get(a).getTitle());
			wxnewsXX.setContent(buWeixinmass_list.get(a).getContent());
			wxnewsXX.setDigest(buWeixinmass_list.get(a).getDescription());
			wxnewsXX_List.add(wxnewsXX);
			wxnews.setArticles(wxnewsXX_List);
		}
		String text = TransformJSON.toJSON(wxnews);
		text=StringEscapeUtils.unescapeJava(text);
		String result = postSendMessage(Surl, text);
		media newsMe = gson.fromJson(result, media.class);
		String newMedia_id = newsMe.getMedia_id();
		
		
		  String json="";
		  
		  
		  json+="{";
		  json+="\"towxname\":\""+weixinNum+"\",";
		  json+="\"mpnews\":{"; 
		  json+= "\"media_id\":\""+newMedia_id+"\""; 
		  json+="},"; 
		  json+="\"msgtype\":\"mpnews\""; 
		  json+="}";
		 
		/*String json = "";
		json += "{";
		json += "\"touser\":\"oSqiqtwzua9UCapHIfEWqP6VodWQ\",";
		json += "\"mpnews\":{";
		json += "\"media_id\":\"" + newMedia_id + "\"";
		json += "},";
		json += "\"msgtype\":\"mpnews\"";
		json += "}";*/
		// 第三步 群体发送
		String url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token="+ GetAccessToken.AccessToken;
		//String url = "https://api.weixin.qq.com/cgi-bin/message/mass/preview?access_token="+ GetAccessToken.AccessToken;
		// 发送消息类型
		String re= postSendMessage(url, json);
		WeiXinRetun weiXinRetun=gson.fromJson(re, WeiXinRetun.class);
		return weiXinRetun.getErrcode();

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
			urlCon.setRequestMethod("POST");
			urlCon.setRequestProperty("Content-Length",
					String.valueOf(context.getBytes().length));
			urlCon.setUseCaches(false);
			urlCon.getOutputStream().write(context.getBytes("utf-8"));
			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlCon.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
				result += line;
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

	public static void main(String[] args) throws Exception {
	}

	public BuWeixinmessageService getBuWeixinmessageService() {
		return buWeixinmessageService;
	}

	public void setBuWeixinmessageService(
			BuWeixinmessageService buWeixinmessageService) {
		this.buWeixinmessageService = buWeixinmessageService;
	}

	public BuWeixinconfigService getBuWeixinconfigService() {
		return buWeixinconfigService;
	}

	public void setBuWeixinconfigService(BuWeixinconfigService buWeixinconfigService) {
		this.buWeixinconfigService = buWeixinconfigService;
	}
}