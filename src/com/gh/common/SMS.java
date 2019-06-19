package com.gh.common;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class SMS {

	/**
	 * @param usr 用户名
	 * @param pwd 密码
	 * @param message 包文结构
	 * @return  0,MSGID	发送成功
				-100,MSGID	系统错误
				-101,MSGID	用户名或密码错
				-102,MSGID	手机号码串长度非法
				-103,MSGID	短信为空
				-104,MSGID	授权余额不足
				-105,MSGID	手机号码格式不正确
				-106,MSGID	IP地址未绑定
				-107, MSGID	参数格式不对
				-108, MSGID	短信内容太长
				-110, MSGID	系统错误
	 */
	
	public static String send(String usr,String pwd,String mobile,String message){
		String ul="http://202.91.241.138:8099/smhttphandle.aspx";
		String result=null;
		String a[];
		URL url;
		String tel="";
		try {
			url = new URL(ul);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			a=mobile.split(",");
			
			if(a.length>0){
				for(int i=0;i<a.length;i++){
					tel=tel+a[i]+"/#/$//#/$/"+message+"/r/n/";
				}
			}
			OutputStream os = conn.getOutputStream();
			String request="usr="+usr+"&pwd="+pwd+"&message="+tel;
			os.write(request.getBytes("GBK"));
			os.flush();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream in = conn.getInputStream();
			int ch = 0;
			while ((ch = in.read()) != -1) {
				baos.write(ch);
			}
			byte[] buf = baos.toByteArray();
		    result = new String(buf, "GBK").trim();
			System.out.println(result); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("发送失败"); 
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("发送失败");
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * @see 奥易互通短信
	 * @param username 用户名称
	 * @param Password 用户密码
	 * @param phonelist 下发信息的手机号码，多个手机号用半角逗号分隔，每个提交包控制在1000个手机号码内
	 * @param msg 下发信息的内容，70个字，超长系统自动截取,按每67个字计费一条
	 * @param longnum 系统分配下行长号码，如果未分配可以填空字符串
	 */
	
	public static String sendAsms(String username, String password, String phonelist, String msg, String longnum){
		String ul="http://101.200.228.238/smsport/default.asmx/SendSms";
		username="hztj01";
		password="999999";
		String result=null;
		URL url;
		try {
			url = new URL(ul);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			OutputStream os = conn.getOutputStream();
			msg=msg!=null&&msg.length()>0?"【乌镇民情】"+msg:"";
			String request="username="+username+"&password="+password+"&phonelist="+phonelist+"&msg="+msg+"&longnum="+longnum;
			os.write(request.getBytes("utf-8"));
			os.flush();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream in = conn.getInputStream();
			int ch = 0;
			while ((ch = in.read()) != -1) {
				baos.write(ch);
			}
			byte[] buf = baos.toByteArray();
			result = new String(buf, "utf-8").trim();
			System.out.println(result); 
		} catch (MalformedURLException e) {
			System.out.println("发送失败"); 
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("发送失败");
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * @param usr 用户名
	 * @param pwd 密码
	 * @param mobile 手机号
	 * @param sms 短信内容
	 * @param extdsrcid 扩展号
	 * @return  0,MSGID	发送成功
				-100,MSGID	系统错误
				-101,MSGID	用户名或密码错
				-102,MSGID	手机号码串长度非法
				-103,MSGID	短信为空
				-104,MSGID	授权余额不足
				-105,MSGID	手机号码格式不正确
				-106,MSGID	IP地址未绑定
				-107, MSGID	参数格式不对
				-108, MSGID	短信内容太长
				-110, MSGID	系统错误
	 */
	public static String send(String usr,String pwd,String mobile,String sms,String extdsrcid){
		String ul="http://202.91.241.138:8099/smhttphandle.aspx?usr="+usr+"&pwd="+pwd+"&mobile="+mobile+"&sms="+sms+"&extdsrcid="+extdsrcid;
		String result=null;
		URL url;
		try {
			url = new URL(ul);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.connect();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			InputStream in = conn.getInputStream();
			int ch = 0;
			while ((ch = in.read()) != -1) {
				baos.write(ch);
			}
			byte[] buf = baos.toByteArray();
		    result = new String(buf, "GBK").trim();
			System.out.println(result); 
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("发送失败"); 
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("发送失败");
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	send("600068","tj99882","13656661844/#/$//#/$/test22");
		System.out.println(new Date());
		send("600068","tj99882","13656661844","test12222221","");
	}

}
