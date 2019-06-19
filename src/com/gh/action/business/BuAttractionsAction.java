package com.gh.action.business;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.FileUpload;
import com.gh.common.ImageCompression;
import com.gh.common.Imgedit;
import com.gh.common.UUIDCreater;
import com.opensymphony.xwork2.ActionSupport;
import java.util.*;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
  
 

import com.gh.entity.*;
import com.gh.dao.*;
import com.gh.service.*;  

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuAttractionsAction  extends Action{
	
	private BuAttractionsService buAttractionsService;
	private String uid;
	private String id;
	private String skey;
	private String type;
	private List<BuAttractions> buAttractions_list;
	private BuAttractions buAttractions;
	
	private ServletContext context;
	//----图片上传----
	private File upload;
	private String uploadFileName;
	private String imgNum;
	private String reNum;
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buAttractions");
		//com=CompetenceManager.getCom(roleid, "buAttractions?type="+type);
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		String SqlWhere=" DELETEMARK=0";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+=" and fullname like '%"+skey+"%'";
		 } 
		//--------------------
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buAttractionsService.getRecordCount(SqlWhere);
		buAttractions_list=buAttractionsService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		type="002019001";//类型
		//System.out.println("---打印出来的数据是----"+type);
		 return "buAttractions";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException 
	 */
	public String edit() throws IOException{
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buAttractions.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buAttractions");
			if(!com.getHisUpdate()){
				return "error";
			}
			//------------图片修改
			BuAttractions buAttractions1 = buAttractionsService.findById(id);
			String dimg = buAttractions1.getDimages();
			/*if(dimg=="default.jpg"){
				dimg=dimg.substring(10);
			}*/
			boolean flag=false;//false：表示需要压缩
			String imgRe = Imgedit.modify(reNum,imgNum, dimg, img, imgFileName,flag);
			if(imgRe.length()>0){
			buAttractions.setDimages(imgRe);
			}else{
			buAttractions.setDimages("default.jpg");
			}
			buAttractions.setModifydate(new Date());
			buAttractionsService.updateSelective(buAttractions);
		}else{
			com=CompetenceManager.getCom(roleid, "buAttractions");
			if(!com.getHisAdd()){
				return "error";
			}
			//新建时多图上传
			FileUpload fileupload=new FileUpload();
			ArrayList<String> re=fileupload.inFile(img, "/upload", imgFileName);
			if(re!=null&&re.size()>0){
				String imges="";
				for(int a=0;a<re.size();a++){
					imges+=re.get(a)+",";
				}
				imges=imges.length()>0?imges.substring(0,imges.length()-1):"";
				//System.out.println("--------"+imges);
				buAttractions.setDimages(imges);
				for(int i=0;i<re.size();i++){
				//压缩图片
				ImageCompression mypic = new ImageCompression();
				String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
				mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
				mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
				}
			}else{
				buAttractions.setDimages("default.jpg");
			}
			buAttractions.setFuid(UUIDCreater.getUUID());
			buAttractions.setPraise(0);
			buAttractions.setBrowse(0);
			//buAttractions.setType(type);
			buAttractions.setDeletemark(0);
			buAttractions.setCreatedate(new Date());
			buAttractions.setModifydate(new Date());
			//System.out.println("------------"+buAttractions.getFuid());
			buAttractionsService.save(buAttractions);
		}
		return "show";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buAttractions=buAttractionsService.findById(id);
		return "buAttractionsEditor";
	}
	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buAttractions");
		if(!com.getHisDelete()){
			return "error";
		}
		if (uid != null && uid.length() > 0) {
			String ids[] = uid.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					buAttractions = buAttractionsService.findById(ids[a]);
					buAttractions.setDeletemark(1);
					buAttractionsService.updateSelective(buAttractions);
				}
			}
		} else {
			if (id != null && id.length() > 0) {
				buAttractions = buAttractionsService.findById(id);
				buAttractions.setDeletemark(1);
				buAttractionsService.updateSelective(buAttractions);
			}
		}
		return "show";
	}
	public BuAttractionsService getBuAttractionsService() {
		return buAttractionsService;
	}
	public void setBuAttractionsService(BuAttractionsService buAttractionsService) {
		this.buAttractionsService = buAttractionsService;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public List<BuAttractions> getBuAttractions_list() {
		return buAttractions_list;
	}
	public void setBuAttractions_list(List<BuAttractions> buAttractions_list) {
		this.buAttractions_list = buAttractions_list;
	}
	public BuAttractions getBuAttractions() {
		return buAttractions;
	}
	public void setBuAttractions(BuAttractions buAttractions) {
		this.buAttractions = buAttractions;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getImgNum() {
		return imgNum;
	}
	public void setImgNum(String imgNum) {
		this.imgNum = imgNum;
	}
	public String getReNum() {
		return reNum;
	}
	public void setReNum(String reNum) {
		this.reNum = reNum;
	}
	

	
}
