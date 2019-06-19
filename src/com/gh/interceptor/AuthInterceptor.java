package com.gh.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.gh.action.system.LoginAction;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Action action = (Action) invocation.getAction();
		HttpServletRequest request = ServletActionContext.getRequest();
		String userId =  (String)ActionContext.getContext().getSession().get("userid");
		if(action instanceof LoginAction){
			return invocation.invoke();
		}
		if(userId==null){
			if(isAjaxRequest(request)){
				System.out.println("ajax请求，登录超时啦！");
				return "json";
			}else{
				return "login";
			}
		}
		return invocation.invoke();
	}
	
	//判断是否为ajax请求
	public boolean isAjaxRequest(HttpServletRequest request){
		String header = request.getHeader("X-Requested-With");  
		if (header != null && "XMLHttpRequest".equals(header)){
			return true;
		}  else{
			return false;
		}
	}
	
}
