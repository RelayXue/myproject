package com.gh.action.business;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.TransformJSON;
import com.gh.common.UUIDCreater;
import com.gh.entity.ObPromptdata;
import com.gh.service.ObPromptdataService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class ObPromptdataAction  extends Action{
	
	
	private ObPromptdataService obPromptdataService;
	private String id;
	private String skey;
	private List<ObPromptdata> obPromptdata_list;
	private ObPromptdata obPromptdata;
	
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "portapplication");
		if(!com.getHisSelect()){
			return "error";
		}
		 String SqlWhere=" DELETEMARK=0";
		    if(skey!=null&&skey.length()>0){
		    	SqlWhere+=" and fullname like '%"+skey+"%'";
		    } 
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=obPromptdataService.getRecordCount(SqlWhere);
		obPromptdata_list=obPromptdataService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		 return "ObPromptdata";
	}
	public String edit(){
		String id=obPromptdata.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "portapplication");
			if(!com.getHisSelect()){
				return "error";
			}
			obPromptdataService.updateSelective(obPromptdata);
		}else{
			com=CompetenceManager.getCom(roleid, "portapplication");
			if(!com.getHisSelect()){
				return "error";
			}
			obPromptdata.setFuid(UUIDCreater.getUUID());
			obPromptdata.setCreatedate(new Date());
			obPromptdataService.save(obPromptdata);
		}
		return "show";
	}
	public String update_show(){
		obPromptdata=obPromptdataService.findById(id);
		return "obPromptdataEditor";
	}
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "portapplication");
		if(!com.getHisSelect()){
			return "error";
		}
		obPromptdataService.delete(id);
		return "show";
	}
	
	//自动提示
	public String AutoComplete() throws IOException{ 
		response.setContentType("text/json;charset=utf-8");
		PrintWriter out=ServletActionContext.getResponse().getWriter();
		 String SqlWhere=" ";
		 if(skey!=null&&skey.length()>0){
			 skey=URLDecoder.decode(skey, "utf-8");
		    SqlWhere+="  fullname like '%"+skey+"%'";
		    obPromptdata_list=obPromptdataService.selectByPage(1,8, SqlWhere, "fullname ");
			out.print(TransformJSON.toJSON(obPromptdata_list));
		  } 
		return null;
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
	public ObPromptdataService getObPromptdataService() {
		return obPromptdataService;
	}
	public void setObPromptdataService(ObPromptdataService obPromptdataService) {
		this.obPromptdataService = obPromptdataService;
	}
	public List<ObPromptdata> getObPromptdata_list() {
		return obPromptdata_list;
	}
	public void setObPromptdata_list(List<ObPromptdata> obPromptdata_list) {
		this.obPromptdata_list = obPromptdata_list;
	}
	public ObPromptdata getObPromptdata() {
		return obPromptdata;
	}
	public void setObPromptdata(ObPromptdata obPromptdata) {
		this.obPromptdata = obPromptdata;
	}
}
