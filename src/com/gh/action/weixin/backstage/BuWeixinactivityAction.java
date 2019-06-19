package com.gh.action.weixin.backstage;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuWeixinactivity;
import com.gh.service.BuWeixinactivityService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuWeixinactivityAction  extends Action{
	
	
	private BuWeixinactivityService buWeixinactivityService;
	private Integer isVote;
	private String uid;
	private String ord;
	private String id;
	private String skey;
	private List<BuWeixinactivity> buWeixinactivity_list;
	private BuWeixinactivity buWeixinactivity;
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buWeixinactivity");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		 String SqlWhere="";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+="  title like '%"+skey+"%' or content like '%"+skey+"%' or  author like '%"+skey+"%'  ";
		 } 
		//--------------------
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buWeixinactivityService.getRecordCount(SqlWhere);
		buWeixinactivity_list=buWeixinactivityService.selectByPage(indexPage,pageSize, SqlWhere, ord+" desc");
		 return "buWeixinactivity";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 */
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buWeixinactivity.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buWeixinactivity");
			if(!com.getHisUpdate()){
				return "error";
			}
			buWeixinactivityService.updateSelective(buWeixinactivity);
		}else{
			com=CompetenceManager.getCom(roleid, "buWeixinactivity");
			if(!com.getHisUpdate()){
				return "error";
			}
			buWeixinactivity.setFuid(UUIDCreater.getUUID());
			buWeixinactivityService.save(buWeixinactivity);
		}
		return "show";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buWeixinactivity=buWeixinactivityService.findById(id);
		return "buWeixinactivityEditor";
	}
	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buWeixinactivity");
		if(!com.getHisDelete()){
			return "error";
		}
		if(id!=null&&id.length()>0){
			String ids[]=id.split(",");
			if(ids!=null&&ids.length>0){
				for(int a=0;a<ids.length;a++){
					buWeixinactivityService.delete(ids[a]);
				}
			}else{
				buWeixinactivityService.delete(id);
			}
		}
		return "show";
	}
	
	/**
	 * @see 设置投票
	 * @return
	 */
	public String setVote(){
		BuWeixinactivity buWeixinactivity=buWeixinactivityService.findById(id);
		if(buWeixinactivity!=null){
			buWeixinactivity.setIsVote(isVote);
			buWeixinactivityService.updateSelective(buWeixinactivity);
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
	public BuWeixinactivityService getBuWeixinactivityService() {
		return buWeixinactivityService;
	}
	public void setBuWeixinactivityService(BuWeixinactivityService buWeixinactivityService) {
		this.buWeixinactivityService = buWeixinactivityService;
	}
	public List<BuWeixinactivity> getBuWeixinactivity_list() {
		return buWeixinactivity_list;
	}
	public void setBuWeixinactivity_list(List<BuWeixinactivity> buWeixinactivity_list) {
		this.buWeixinactivity_list = buWeixinactivity_list;
	}
	public BuWeixinactivity getBuWeixinactivity() {
		return buWeixinactivity;
	}
	public void setBuWeixinactivity(BuWeixinactivity buWeixinactivity) {
		this.buWeixinactivity = buWeixinactivity;
	}
	public String getOrd() {
		return ord;
	}
	public void setOrd(String ord) {
		this.ord = ord;
	}
	public Integer getIsVote() {
		return isVote;
	}
	public void setIsVote(Integer isVote) {
		this.isVote = isVote;
	}
}
