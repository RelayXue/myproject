package com.gh.action.business;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.TransformJSON;
import com.gh.common.UUIDCreater;
import com.gh.entity.BuStreetlights;
import com.gh.service.BuStreetlightsService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuStreetlightsAction  extends Action{
	
	
	private BuStreetlightsService buStreetlightsService;
	private String id;
	private String skey;
	private List<BuStreetlights> buStreetlights_list;
	private BuStreetlights buStreetlights;
	
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buStreetlights");
		if(!com.getHisSelect()){
			return "error";
		}
		 String SqlWhere=" 0=0";
		    if(skey!=null&&skey.length()>0){
		    	SqlWhere+=" and fullname like '%"+skey+"%'";
		    } 
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buStreetlightsService.getRecordCount(SqlWhere);
		buStreetlights_list=buStreetlightsService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		 return "BuStreetlights";
	}
	/**
	 * @author xiao
	 * @see 地图上显示所有视频
	 * @return
	 * @throws IOException
	 */
	public String showAll() throws IOException{
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		buStreetlights_list=buStreetlightsService.execSql("select * from bu_Streetlights  ");
		out.print(TransformJSON.toJSON(buStreetlights_list));
		return null;
	}
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buStreetlights.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buStreetlights");
			if(!com.getHisSelect()){
				return "error";
			}
			buStreetlights.setModifydate(new Date());
			buStreetlights.setModifyuserrealname(username);
			buStreetlightsService.updateSelective(buStreetlights);
		}else{
			com=CompetenceManager.getCom(roleid, "buStreetlights");
			if(!com.getHisSelect()){
				return "error";
			}
			buStreetlights.setFuid(UUIDCreater.getUUID());
			buStreetlights.setCreatedate(new Date());
			buStreetlights.setCreateuserrealname(username);
			buStreetlights.setModifydate(new Date());
			buStreetlights.setModifyuserrealname(username);
			buStreetlightsService.save(buStreetlights);
		}
		return this.show();
	}
	public String update_show(){
		buStreetlights=buStreetlightsService.findById(id);
		return "buStreetlightsEditor";
	}
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buStreetlights");
		if(!com.getHisSelect()){
			return "error";
		}
		buStreetlightsService.delete(id);
		return this.show();
	}
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSkey() {
		return skey;
	}
	public void setSkey(String skey) {
		this.skey = skey;
	}
	public BuStreetlightsService getBuStreetlightsService() {
		return buStreetlightsService;
	}
	public void setBuStreetlightsService(BuStreetlightsService buStreetlightsService) {
		this.buStreetlightsService = buStreetlightsService;
	}
	public List<BuStreetlights> getBuStreetlights_list() {
		return buStreetlights_list;
	}
	public void setBuStreetlights_list(List<BuStreetlights> buStreetlights_list) {
		this.buStreetlights_list = buStreetlights_list;
	}
	public BuStreetlights getBuStreetlights() {
		return buStreetlights;
	}
	public void setBuStreetlights(BuStreetlights buStreetlights) {
		this.buStreetlights = buStreetlights;
	}
}
