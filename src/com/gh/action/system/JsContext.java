package com.gh.action.system;

import java.io.IOException;
import java.io.PrintWriter;

import com.gh.base.Action;

public class JsContext extends Action {

	private static final long serialVersionUID = 1L;
	/**
	 * @return 基础数据字典
	 */
	public String DictionaryData() throws IOException {
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String data = (String) request.getSession().getServletContext().getAttribute("baseDatadictionary_list");
		out.print("var basetemp="+data);
		return null;
	}
	/**
	 * @return 货物类型
	 */
	public String GoodsData() throws IOException {
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		String data = (String) request.getSession().getServletContext().getAttribute("baseGoods_list");
		out.print("var goodstemp="+data);
		return null;
	}
}
