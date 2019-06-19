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
import com.gh.entity.BuNaattr;
import com.gh.entity.ObVideo;
import com.gh.service.ObVideoService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class ObVideoAction  extends Action{
	
	
	private ObVideoService obVideoService;
	private String id;
	private String skey;
	private List<ObVideo> obVideo_list;
	private ObVideo obVideo;
	
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "obVideo");
		if(!com.getHisSelect()){
			return "error";
		}
		String SqlWhere=" 0=0";
		if(skey!=null&&skey.length()>0){
			SqlWhere+=" and fullname like '%"+skey+"%'";
		} 
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=obVideoService.getRecordCount(SqlWhere);
		obVideo_list=obVideoService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		return "ObVideo";
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
		obVideo_list=obVideoService.execSql("select * from ob_Video  ");
		out.print(TransformJSON.toJSON(obVideo_list));
		return null;
	}
	
	public String showVideo(){
		obVideo=obVideoService.findById(id);
		return "showVideo";
	}
	public String edit(){
		String username=(String)request.getSession().getAttribute("username");
		String id=obVideo.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "obVideo");
			if(!com.getHisUpdate()){
				return "error";
			}
			obVideo.setModifydate(new Date());
			obVideo.setModifyuserrealname(username);
			obVideoService.updateSelective(obVideo);
		}else{
			com=CompetenceManager.getCom(roleid, "obVideo");
			if(!com.getHisUpdate()){
				return "error";
			}
			obVideo.setFuid(UUIDCreater.getUUID());
			obVideo.setCreatedate(new Date());
			obVideo.setModifydate(new Date());
			obVideo.setModifyuserrealname(username);
			obVideoService.save(obVideo);
		}
		return "show";
	}
	public String update_show(){
		obVideo=obVideoService.findById(id);
		return "ObVideoEditor";
	}
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "obVideo");
		if(!com.getHisDelete()){
			return "error";
		}
		obVideoService.delete(id);
		return "show";
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
	public ObVideoService getObVideoService() {
		return obVideoService;
	}
	public void setObVideoService(ObVideoService obVideoService) {
		this.obVideoService = obVideoService;
	}
	public List<ObVideo> getObVideo_list() {
		return obVideo_list;
	}
	public void setObVideo_list(List<ObVideo> obVideo_list) {
		this.obVideo_list = obVideo_list;
	}
	public ObVideo getObVideo() {
		return obVideo;
	}
	public void setObVideo(ObVideo obVideo) {
		this.obVideo = obVideo;
	}
}
