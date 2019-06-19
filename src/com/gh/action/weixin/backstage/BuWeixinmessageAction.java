package com.gh.action.weixin.backstage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuWeixinmessage;
import com.gh.service.BuWeixinmessageService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuWeixinmessageAction  extends Action{
	
	
	private BuWeixinmessageService buWeixinmessageService;
	private String uid;
	private String id;
	private String skey;
	private List<BuWeixinmessage> buWeixinmessage_list;
	private BuWeixinmessage buWeixinmessage;
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "weixinMessage");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		 String SqlWhere="";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+="  content like '%"+skey+"%'";
		 } 
		//--------------------
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buWeixinmessageService.getRecordCount(SqlWhere);
		buWeixinmessage_list=buWeixinmessageService.selectByPage(indexPage,pageSize, SqlWhere, "CREATEDATE desc");
		//转换微信时间（整型）
		if(buWeixinmessage_list!=null&&buWeixinmessage_list.size()>0){
			for(int a=0;a<buWeixinmessage_list.size();a++){
				long msgCreateTime = Long.parseLong(buWeixinmessage_list.get(a).getMessagetime()+"") * 1000L;  
			    buWeixinmessage_list.get(a).setCreatedate(new Date(msgCreateTime));
			}
		}
		 return "buWeixinmessage";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 */
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buWeixinmessage.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buWeixinmessage");
			if(!com.getHisUpdate()){
				return "error";
			}
			buWeixinmessageService.updateSelective(buWeixinmessage);
		}else{
			com=CompetenceManager.getCom(roleid, "buWeixinmessage");
			if(!com.getHisUpdate()){
				return "error";
			}
			buWeixinmessage.setFuid(UUIDCreater.getUUID());
			buWeixinmessage.setCreatedate(new Date());
			buWeixinmessageService.save(buWeixinmessage);
		}
		return "show";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buWeixinmessage=buWeixinmessageService.findById(id);
		return "buWeixinmessageEditor";
	}
	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buWeixinmessage");
		if(!com.getHisDelete()){
			return "error";
		}
		if(id!=null&&id.length()>0){
			String ids[]=id.split(",");
			if(ids!=null&&ids.length>0){
				for(int a=0;a<ids.length;a++){
					buWeixinmessageService.delete(ids[a]);
				}
			}else{
				buWeixinmessageService.delete(id);
			}
		}
		return "show";
	}
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public BuWeixinmessageService getBuWeixinmessageService() {
		return buWeixinmessageService;
	}
	public void setBuWeixinmessageService(BuWeixinmessageService buWeixinmessageService) {
		this.buWeixinmessageService = buWeixinmessageService;
	}
	public List<BuWeixinmessage> getBuWeixinmessage_list() {
		return buWeixinmessage_list;
	}
	public void setBuWeixinmessage_list(List<BuWeixinmessage> buWeixinmessage_list) {
		this.buWeixinmessage_list = buWeixinmessage_list;
	}
	public BuWeixinmessage getBuWeixinmessage() {
		return buWeixinmessage;
	}
	public void setBuWeixinmessage(BuWeixinmessage buWeixinmessage) {
		this.buWeixinmessage = buWeixinmessage;
	}
}
