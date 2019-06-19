package com.gh.action.weixin.front;

import java.util.Date;
import java.util.List;
import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.StringUtil;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuWeixinconfig;
import com.gh.service.BuWeixinconfigService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

public class BuWeixinconfigAction  extends Action{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BuWeixinconfigService buWeixinconfigService;
	private String uid;
	private String id;
	private String skey;
	private List<BuWeixinconfig> buWeixinconfig_list;
	private BuWeixinconfig buWeixinconfig;
	private String type;//类型
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buWeixinconfig?type="+type);
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		if(StringUtil.isNotTrimBlank(type)){
			buWeixinconfig_list=buWeixinconfigService.execSql(" SELECT * FROM bu_weixinconfig WHERE TYPE = "+type);
			if(buWeixinconfig_list.size()>0){
				buWeixinconfig = buWeixinconfig_list.get(0);
			}
		}
		if(type.equals("005001")){
			return "addUI_1";//微信欢迎语添加页面
		}else if(type.equals("005002")){
			return "addUI_2";//微信预群发配置添加页面
		}else if(type.equals("005003")){
			return "editUI";
		}
		return null;
	}
	
	
	
	/**
	 * @see 修改及新增
	 * @author xiao
	 */
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buWeixinconfig.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buWeixinconfig?type="+type);
			if(!com.getHisUpdate()){
				return "error";
			}
			buWeixinconfig.setModifydate(new Date());
			//buWeixinconfigService.updateSelective(buWeixinconfig);
			
			buWeixinconfigService.update(buWeixinconfig);
		}else{
			com=CompetenceManager.getCom(roleid, "buWeixinconfig?type="+type);
			if(!com.getHisUpdate()){
				return "error";
			}
			buWeixinconfig.setFuid(UUIDCreater.getUUID());
			buWeixinconfig.setCreatedate(new Date());
			buWeixinconfig.setModifydate(new Date());
			buWeixinconfigService.save(buWeixinconfig);
		}
		return "show";
	}
	

	/*---set---get---*/
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
	public BuWeixinconfigService getBuWeixinconfigService() {
		return buWeixinconfigService;
	}
	public void setBuWeixinconfigService(BuWeixinconfigService buWeixinconfigService) {
		this.buWeixinconfigService = buWeixinconfigService;
	}
	public List<BuWeixinconfig> getBuWeixinconfig_list() {
		return buWeixinconfig_list;
	}
	public void setBuWeixinconfig_list(List<BuWeixinconfig> buWeixinconfig_list) {
		this.buWeixinconfig_list = buWeixinconfig_list;
	}
	public BuWeixinconfig getBuWeixinconfig() {
		return buWeixinconfig;
	}
	public void setBuWeixinconfig(BuWeixinconfig buWeixinconfig) {
		this.buWeixinconfig = buWeixinconfig;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
