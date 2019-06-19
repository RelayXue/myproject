package com.gh.action.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.gh.base.Action;
import com.gh.common.UUIDCreater;
import com.gh.entity.BaseDatadictionary;
import com.gh.service.BaseDatadictionaryService;

/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


public class DatadictionaryAction  extends Action{
	
	
	private BaseDatadictionaryService baseDatadictionaryService;
	private String id;
	private String skey;
	private String code;
	private String type;
	private List<BaseDatadictionary> baseDatadictionary_list;
	private BaseDatadictionary baseDatadictionary;
	
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "Datadictionary");
		if(!com.getHisSelect()){
			return "error";
		}
		
		String SqlWhere=" length(code)=3";
		if(skey!=null&&skey.length()>0){
				SqlWhere+=" and fullname like '%"+skey+"%'";
		} 
		if(type!=null&&type.equals("")){
			
		}
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=baseDatadictionaryService.getRecordCount(SqlWhere);
		baseDatadictionary_list=baseDatadictionaryService.selectByPage(indexPage,pageSize, SqlWhere, "Sequence ");
		return "Type";
	}
	public String showChild2(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "Datadictionary");
		if(!com.getHisSelect()){
			return "error";
		}
		String SqlWhere=" (length(code)=6) and code like '"+code+"%'";
		if(skey!=null&&skey.length()>0){
			SqlWhere+=" and fullname like '%"+skey+"%'";
		} 
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=baseDatadictionaryService.getRecordCount(SqlWhere);
		baseDatadictionary_list=baseDatadictionaryService.selectByPage(indexPage,pageSize, SqlWhere, "Sequence ");
		return "Type2";
	}
	public String showChild3(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "Datadictionary");
		if(!com.getHisSelect()){
			return "error";
		}
	    String SqlWhere=" (length(code)=9) and code like '"+code+"%'";
	    if(skey!=null&&skey.length()>0){
	    	SqlWhere+=" and fullname like '%"+skey+"%'";
	    } 
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=baseDatadictionaryService.getRecordCount(SqlWhere);
		baseDatadictionary_list=baseDatadictionaryService.selectByPage(indexPage,pageSize, SqlWhere, "Sequence ");
		 return "Type3";
	}
	public String add(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "Datadictionary");
		if(!com.getHisSelect()){
			return "error";
		}
		baseDatadictionary.setFuid(UUIDCreater.getUUID());
		String code1=baseDatadictionary.getCode();
		if(code!=null&&!code.equals("null")){
			code1=code+baseDatadictionary.getCode();
		}
		baseDatadictionary.setCode(code1);
		baseDatadictionaryService.save(baseDatadictionary);
		ServletContext context=ServletActionContext.getServletContext();
		CompetenceManager a=new CompetenceManager();
		a.RefreshDatadictionary(context);
		return "show1";
	}
	public String addChild(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "Datadictionary");
		if(!com.getHisSelect()){
			return "error";
		}
		if(code!=null&&code.length()>0){
			baseDatadictionary.setFuid(UUIDCreater.getUUID());
			String code1=code+baseDatadictionary.getCode(); 
			baseDatadictionary.setCode(code1);
			baseDatadictionaryService.save(baseDatadictionary);
		}
		ServletContext context=ServletActionContext.getServletContext();
		CompetenceManager a=new CompetenceManager();
		a.RefreshDatadictionary(context);
		if(code!=null&&code.length()==3){
			return this.showChild2();
		}else if(code!=null&&code.length()==6){
			return this.showChild3();
		}else{
			return "show1";
		}
	}
	public String update(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "Datadictionary");
		if(!com.getHisSelect()){
			return "error";
		}
		BaseDatadictionary baseDatadictionary_new=baseDatadictionaryService.findById(id);
		if(code!=null){
			String code1=code+baseDatadictionary.getCode();
			baseDatadictionary_new.setCode(code1);
		}else{
			baseDatadictionary_new.setCode(baseDatadictionary.getCode());
		}
		baseDatadictionary_new.setFullname(baseDatadictionary.getFullname());
		baseDatadictionary_new.setSequence(baseDatadictionary.getSequence());
		baseDatadictionary_new.setDescription(baseDatadictionary.getDescription());
		baseDatadictionaryService.update(baseDatadictionary_new);
		ServletContext context=ServletActionContext.getServletContext();
		CompetenceManager a=new CompetenceManager();
		a.RefreshDatadictionary(context);
		if(code!=null&&code.length()==3){
			return this.showChild2();
		}else if(code!=null&&code.length()==6){
			return this.showChild3();
		}else{
			return "show1";
		}
	}
	public String update_show(){
		baseDatadictionary=baseDatadictionaryService.findById(id);
		return "TypeEditor";
	}
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "Datadictionary");
		if(!com.getHisSelect()){
			return "error";
		}
		baseDatadictionaryService.delete(id);
		ServletContext context=ServletActionContext.getServletContext();
		CompetenceManager a=new CompetenceManager();
		a.RefreshDatadictionary(context);
		if(code!=null&&code.length()==3){
			return this.showChild2();
		}else if(code!=null&&code.length()==6){
			return this.showChild3();
		}else{
			return "show1";
		}
	}
	public String IsExist() throws IOException{
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		int a=baseDatadictionaryService.getRecordCount(" code="+code);
		if (a>0) {
			out.print("true");
		} else {
			out.print("false");
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BaseDatadictionaryService getBaseDatadictionaryService() {
		return baseDatadictionaryService;
	}
	public void setBaseDatadictionaryService(BaseDatadictionaryService baseDatadictionaryService) {
		this.baseDatadictionaryService = baseDatadictionaryService;
	}
	public List<BaseDatadictionary> getBaseDatadictionary_list() {
		return baseDatadictionary_list;
	}
	public void setBaseDatadictionary_list(List<BaseDatadictionary> baseDatadictionary_list) {
		this.baseDatadictionary_list = baseDatadictionary_list;
	}
	public BaseDatadictionary getBaseDatadictionary() {
		return baseDatadictionary;
	}
	public void setBaseDatadictionary(BaseDatadictionary baseDatadictionary) {
		this.baseDatadictionary = baseDatadictionary;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
