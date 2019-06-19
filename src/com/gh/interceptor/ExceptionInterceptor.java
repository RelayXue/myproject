package com.gh.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 
 * @ClassName ExceptionInterceptor 
 * @Description 异常拦截器
 * @author oriental_pearl
 * @date 2012-3-23
 */
public class ExceptionInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String result = null;
		try {
			result = invocation.invoke();
		} catch (Throwable e) {
			result = "exception";
		    e.printStackTrace();
			throw new Exception(e);
		}
		return result;
	}

}
