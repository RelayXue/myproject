package com.gh.action.business;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.entity.A20161click;
import com.gh.service.A20161clickService;

/**
 * @ClassName A20161clickAction
 * @Description 用于对A20161click对象逻辑跳转
 * @author tujing
 * @date 2015-04-01
 */
public class A20161clickAction extends Action{
	
	private static final long serialVersionUID = 1L;
	private A20161clickService a20161clickService;
    private A20161click a20161click;
    public String show(){
		return null;
	}
    
    
	/**
	 * getter、setter方法
	 */
    public A20161click getA20161click() {
		return a20161click;
	}
	public void setA20161click(A20161click a20161click) {
		this.a20161click = a20161click;
	}
	public void setA20161clickService(A20161clickService a20161clickService) {
		this.a20161clickService = a20161clickService;
	}
}
