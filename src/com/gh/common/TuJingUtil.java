package com.gh.common;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.google.gson.Gson;

/**
 * @ClassName TuJingUtil 
 * @Description  常用方法
 * @author oriental_pearl
 * @date 2014-5-21
 * @version V1.0
 */
public class TuJingUtil {
	/**
	 * 取得request
	 */
	public static HttpServletRequest getHttpServletRequest(){
		return ServletActionContext.getRequest();
	}
	/**
	 * 取得servletContext
	 */
	public static ServletContext getServletContext(){
		return ServletActionContext.getServletContext();
	}
	/**
	 * 取得response
	 */
	public static HttpServletResponse getHttpServletResponse(){
		return ServletActionContext.getResponse();
	}
	/**
	 * 取得session
	 */
	public static HttpSession getHttpSession(){
		return getHttpServletRequest().getSession();
	}
	/**
	 * 取得当前用户ID
	 */
	/*public static String getCurrentUserId(){
		return (String) getHttpSession().getAttribute(SysCommonConstant.USER_ID);
	}*/
	/**
	 * 取得当前用户名
	 */
	/*public static String getCurrentUserName(){
		return (String) getHttpSession().getAttribute(SysCommonConstant.USER_NAME);
	}*/
	/**
	 * 取得当前用户类型
	 */
	/*public static String getCurrentUserType(){
		return (String) getHttpSession().getAttribute(SysCommonConstant.USER_TYPE);
	}*/
	/**
	 * 取得当前用户组织码
	 */
	/*public static String getCurrentOrgCode(){
		return (String) getHttpSession().getAttribute(SysCommonConstant.ORG_CODE);
	}*/
	
	/**
	 * 取得当前通讯录组织码
	 */
	/*public static String getCurrentBookGroupCode(){
		return (String) getHttpSession().getAttribute(SysCommonConstant.BOOK_GROUP);
	}*/
	
	/**
	 * 取得当前用户姓名
	 */
	/*public static String getCurrentUserRealName(){
		return (String) getHttpSession().getAttribute(SysCommonConstant.USER_REAL_NAME);
	}*/
	
	/**
	 * 取得当前用户角色IDs
	 */
	@SuppressWarnings("unchecked")
	/*public static List<String> getCurrentRoles(){
		return (List<String>) getHttpSession().getAttribute(SysCommonConstant.USER_ROLE_IDS);
	}*/
	/**
	 * 取得绝对路径
	 * @param path 相对路径
	 */
	public static String getPath(String path){
		if(StringUtil.isTrimBlank(path)){
			path = "/";
		}
		return getServletContext().getRealPath(path);
	}

	public static String bytesToHexString(byte[] src){  
		StringBuilder stringBuilder = new StringBuilder("");  
		if (src == null || src.length <= 0) {  
			return null;  
		}  
		for (int i = 0; i < src.length; i++) {  
			int v = src[i] & 0xFF;  
			String hv = Integer.toHexString(v);  
			if (hv.length() < 2) {  
				stringBuilder.append(0);  
			}  
			stringBuilder.append(hv);  
		}  
		return stringBuilder.toString();  
	}

	public static String java2json(Object object){
		String t ="";
		if(object!=null){
			Gson gson = new Gson();
			t = gson.toJson(object);
		}
		return t;
	}
	
	public static String getUUIDFileName(){
		Calendar now = Calendar.getInstance();
		String now_YEAR = "" + now.get(Calendar.YEAR);
		String now_MONTH = "" + (now.get(Calendar.MONTH) + 1);
		String now_DATE = "" + now.get(Calendar.DATE);
		String now_HOUR = "" + now.get(Calendar.HOUR_OF_DAY);
		String now_MINUTE = "" + now.get(Calendar.MINUTE);
		String now_SECOND = "" + now.get(Calendar.SECOND);

		String tmp = now_YEAR + now_MONTH + now_DATE + now_HOUR + now_MINUTE
		+ now_SECOND + new Random().nextInt(100);

		return tmp;
	}
	
	public static int getTime(Date s,Date e){
		if(s==null||e==null){
			return 0;
		}
		return (int)((e.getTime()-s.getTime())/1000);
	}
	
	public static Date getDayDate(int day){
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE)-day);
		return c.getTime();
	}
	
	/**
	 * 得到前day天的日期
	 * @param day 前几天
	 * @return
	 */
	public static String getDayDateString(int day){
		Calendar c = Calendar.getInstance();
		c.set(c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DATE)-day);
		String d = c.get(Calendar.YEAR)+"-"+String.format("%1$02d",(c.get(Calendar.MONTH)+1))+"-"+String.format("%1$02d",c.get(Calendar.DATE));
	    return d;
	}
	
	public static String getMonthDate(Date date){
		Calendar c = Calendar.getInstance();
		if (date != null) {
			c.setTime(date);
		}
		String d = c.get(Calendar.YEAR)+"-"+String.format("%1$02d",(c.get(Calendar.MONTH)+1));
		return d;
	}
	
	public static String getCurrentMonthDate(){
		Calendar c = Calendar.getInstance();
		String d = c.get(Calendar.YEAR)+"-"+String.format("%1$02d",(c.get(Calendar.MONTH)+1));
		return d;
	}
	
	/**
	 * 验证磁盘空间
	 * return 字节数(byte)
	 */
	public static long getFreeSpace(String path){
		long flag = -1;
		if(StringUtil.isNotTrimBlank(path)){
			File file = new File(path);
			flag = file.getFreeSpace();
		}
		return flag;
	}
	
	/**
	 * 验证是否空间不足
	 * @param path 磁盘路径
	 * @param data 上传字节数
	 * @return true：不足  false：足够
	 */
	public static boolean isOutOfFreeSpace(String path,long data){
		boolean flag = false;
		if(getFreeSpace(path)<data){
			flag = true;
		}
		return flag;
	}
	
	public static String getLineSeperator(){
		String lineSeparator = (String) java.security.AccessController.doPrivileged(
	               new sun.security.action.GetPropertyAction("line.separator"));
		return lineSeparator;
	}
	
	//判断是否为ajax请求
	public static boolean isAjaxRequest(HttpServletRequest request){
		String header = request.getHeader("X-Requested-With");  
		if (header != null && "XMLHttpRequest".equals(header)){
			return true;
		}  else{
			return false;
		}
	}
	
	/**
	 * sql注入验证
	 * @param sql 查询参数
	 * @return 参数
	 */
	/*public static String sqlInjection(String param){
		String str = "";
		if(!SqlInjection.sql_inj(param)){
			str = param;
		}
		return str;
	}*/
	
	/**
	 * sql过滤防止注入漏洞
	 * @param sql 查询语句
	 * @param params 参数
	 * @return sql语句
	 */
	/*public static String sqlFilter(String sql,String... params){
		if(countCharNum(sql,"?")!=params.length){
			return null;
		}
		for(int i=0;i<params.length;i++){
			sql = sql.replaceFirst("[?]",sqlInjection(params[i]));
		}
		return sql;
	}*/
	
	/**
	 * sql过滤防止注入漏洞
	 * @param sql 查询语句
	 * @param params 参数
	 * @return sql语句
	 */
	/*public static String sqlWhereFilter(String sql,String... params){
		for(int i=0;i<params.length;i++){
			if(StringUtil.isTrimBlank(params[i])){
				continue;
			}
			sql = sql.replaceFirst("[?]",sqlInjection(params[i]));
		}
		return sql;
	}*/
	
	/**
	 * 查找字符个数
	 * @param str 原字符串
	 * @param s 字符
	 * @return 个数
	 */
	public static int countCharNum(String str,String s){
		int t = 0;
		if(StringUtil.isNotTrimBlank(str)&&StringUtil.isNotTrimBlank(s)){
			int index = 0;  
			while (true) {  
				index = str.indexOf(s, index + 1);  
				if (index > 0) {  
					t++;  
				} else {  
					break;  
				}  
			}
		}
		return t;
	}
	
	public static int getAge(String id){
		int i = 0;
		int d = Calendar.getInstance().get(Calendar.YEAR);
		if(StringUtil.isNotTrimBlank(id)){
			if(id.length()==15){
				//一代身份证
				i = d - Integer.parseInt("19"+id.substring(6,8));
			}else if(id.length()==18){
				//二代身份证
				i = d - Integer.parseInt(id.substring(6,10));
			}
		}
		return i;
	}
}
