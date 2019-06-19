package com.gh.phone;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

/**
 * 返回结果封装类
 * @author wjc
 *
 */
public class ReturnInfo {
	String id = "";	//标识返回对象id
	String hint = "";	//返回提示信息
	int code = -1;	//返回标识
	Object o = null;	//返回对象json
	Map<String, Object> os = null;	//返回对象json
	
	/**
	 * 
	 * @param n 44表示输出(toJson)为null
	 */
	public ReturnInfo(int n){
		code = n;
	} 
	public ReturnInfo(){} 
	public String toJson(){
		if(code == 44){return null;}
		return new Gson().toJson(this);
	}
	
	public String toJsonToJava(){
		o = null;
		return new Gson().toJson(this);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getO() {
		return o;
	}

	public void setO(Object o) {
		this.o = o;
	}

	public Map<String, Object> getOs() {
		if(os == null) os = new HashMap<String, Object>();
		return os;
	}

	public void setOs(Map<String, Object> os) {
		this.os = os;
	}
	
	
	/**
	 * 流输出
	 * @param request
	 * @param response
	 * @param json_str
	 * @param is_callback 跨域访问需true
	 */
	public static void printJson(HttpServletRequest request, HttpServletResponse response, ReturnInfo ri, boolean is_callback){
//		response.setContentType("application/json");
//		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = null;
		String callback = request.getParameter("callback");
		if(ri == null) ri = new ReturnInfo();
		String json_str = ri.toJson();
		System.out.println("print - " + json_str);
		
		try {
			out = response.getWriter();
			if(is_callback){
				out.println(callback+"("+json_str+")");
			}else{
				out.println(json_str);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(out != null){
				ReturnInfo return_info = new ReturnInfo();
				return_info.setCode(-99);
				return_info.setHint("未知异常");
				if(is_callback){
					out.println(callback+"("+return_info.toJson()+")");
				}else{
					out.println(return_info.toJson());
				}
			}
		} finally{
			if(out != null){
				out.flush();
				out.close();
			} 
		}
	}
	
}
