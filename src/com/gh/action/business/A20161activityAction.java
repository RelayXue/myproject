package com.gh.action.business;

import java.util.List;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.entity.A20161activity;
import com.gh.entity.A20161characterworks;
import com.gh.service.A20161activityService;
import com.gh.service.A20161characterworksService;


/**
 * @ClassName A20161activityAction
 * @Description 用于对A20161activity对象逻辑跳转
 * @author tujing
 * @date 2015-04-01
 */
public class A20161activityAction extends Action{
	
	private static final long serialVersionUID = 1L;
	private A20161activityService a20161activityService;
    private A20161activity a20161activity;
    private List<A20161activity> a20161activitie_list;
	private A20161characterworksService a20161characterworksService;
    private A20161characterworks a20161characterworks;
    private List<A20161characterworks> a20161characterworks_list;
    private String skey;
    private int type;
    private String id;
    public String show(){
    	String roleid=(String)request.getSession().getAttribute("roleid");
		
		com=CompetenceManager.getCom(roleid, "activity");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		String sql="select * from a2016_1_activity";
		
		a20161activitie_list=a20161activityService.execSql(sql);
		if(a20161activitie_list!=null&&a20161activitie_list.size()>0){
			a20161activity=a20161activitie_list.get(0);
		}
		return "activity";
    }
    public String addType(){
    	a20161activity=a20161activityService.findById(id);
    	if((Integer)type!=null&&a20161activity!=null){
    		a20161activity.setType(type+1);
    		if(a20161activity.getType()==4){
    			String sql="select * from a2016_1_characterworks";
    			a20161characterworks_list=a20161characterworksService.execSql(sql);
    			for(A20161characterworks a20161characterwor: a20161characterworks_list){
    			int num=0;
    			Double total=0.0;
    			int Expertmark=0;
    			if(a20161characterwor.getExpertmark()!=null){
    				Expertmark=a20161characterwor.getExpertmark();
    			}
    			if(a20161characterwor.getNum()==null){
    				a20161characterwor.setNum(0);
    			}
    			if(a20161characterwor.getNum()<=10){
    				num=5;
    			}else if(a20161characterwor.getNum()>10&&a20161characterwor.getNum()<=20){
    				num=10;
    			}else if(a20161characterwor.getNum()>20&&a20161characterwor.getNum()<=30){
    				num=15;
    			}else if(a20161characterwor.getNum()>30&&a20161characterwor.getNum()<=40){
    				num=20;
    			}else if(a20161characterwor.getNum()>40&&a20161characterwor.getNum()<=60){
    				num=25;
    			}else if(a20161characterwor.getNum()>60&&a20161characterwor.getNum()<=80){
    				num=30;
    			}else if(a20161characterwor.getNum()>80&&a20161characterwor.getNum()<=100){
    				num=35;
    			}else if(a20161characterwor.getNum()>100){
    				num=40;
    			}
    			total=(Expertmark*0.6)+num;
    			a20161characterwor.setTotal(total);
    			a20161characterworksService.updateSelective(a20161characterwor);
    			}
    		}
        	a20161activityService.update(a20161activity);
    	}
    	return "activity";
    }
    public String save(){
    	String content=a20161activity.getIntroduce();
    	a20161activity=a20161activityService.findById(id);
    	a20161activity.setIntroduce(content);
    	a20161activityService.update(a20161activity);
    	return "activity";
    }
	/**
	 * getter、setter方法
	 */
    public A20161activity getA20161activity() {
		return a20161activity;
	}
	public void setA20161activity(A20161activity a20161activity) {
		this.a20161activity = a20161activity;
	}
	public void setA20161activityService(A20161activityService a20161activityService) {
		this.a20161activityService = a20161activityService;
	}

	public String getSkey() {
		return skey;
	}

	public void setSkey(String skey) {
		this.skey = skey;
	}

	public A20161activityService getA20161activityService() {
		return a20161activityService;
	}

	public List<A20161activity> getA20161activitie_list() {
		return a20161activitie_list;
	}

	public void setA20161activitie_list(List<A20161activity> a20161activitie_list) {
		this.a20161activitie_list = a20161activitie_list;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public A20161characterworksService getA20161characterworksService() {
		return a20161characterworksService;
	}
	public void setA20161characterworksService(
			A20161characterworksService a20161characterworksService) {
		this.a20161characterworksService = a20161characterworksService;
	}
	public A20161characterworks getA20161characterworks() {
		return a20161characterworks;
	}
	public void setA20161characterworks(A20161characterworks a20161characterworks) {
		this.a20161characterworks = a20161characterworks;
	}
	public List<A20161characterworks> getA20161characterworks_list() {
		return a20161characterworks_list;
	}
	public void setA20161characterworks_list(
			List<A20161characterworks> a20161characterworks_list) {
		this.a20161characterworks_list = a20161characterworks_list;
	}
}
