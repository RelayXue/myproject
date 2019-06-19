package com.gh.action.business;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
 * 餐饮特色菜BU_DINING_SPECIALTY
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class BuDiningSpecialtyAction  extends Action{
	
	
	private BuDiningSpecialtyService buDiningSpecialtyService;
	private String uid;
	private String id;
	private String skey;
	private List<BuDiningSpecialty> buDiningSpecialty_list;
	private BuDiningSpecialty buDiningSpecialty;
	private String diningid;
	
	private ServletContext context;
	private File uploadpic;
	private String uploadpicFileName;
	
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
		com=CompetenceManager.getCom(roleid, "buDining");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		 String SqlWhere=" diningid='"+diningid+"'";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+=" and fullname like '%"+skey+"%'";
		 } 
		//--------------------
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buDiningSpecialtyService.getRecordCount(SqlWhere);
		buDiningSpecialty_list=buDiningSpecialtyService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
	
		
		 return "buDiningSpecialty";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException 
	 */
	public String edit() throws IOException{
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buDiningSpecialty.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buDining");
			if(!com.getHisUpdate()){
				return "error";
			}
			
			//------------图片修改
			BuDiningSpecialty buDiningSpecialty1 = buDiningSpecialtyService.findById(id);
			String dimg = buDiningSpecialty1.getDimages();
			System.out.println(imgNum);
			boolean flag=false;
			String imgRe = Imgedit.modify(reNum,imgNum, dimg, img, imgFileName,flag);
			if(imgRe.length()>0){
			buDiningSpecialty.setDimages(imgRe);
			}else{
			buDiningSpecialty.setDimages("default.jpg");
			}
			//System.out.println(imgRe);
			//----------------
			//buDiningSpecialty.setDimages(imgRe);
			buDiningSpecialty.setModifydate(new Date());
			buDiningSpecialtyService.updateSelective(buDiningSpecialty);
			
		}else{
			com=CompetenceManager.getCom(roleid, "buDining");
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
				System.out.println("--------"+imges);
				buDiningSpecialty.setDimages(imges);
				//压缩图片
				for(int i=0;i<re.size();i++){
					ImageCompression mypic = new ImageCompression();
					String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
					}
			}else{
				buDiningSpecialty.setDimages("default.jpg");
			}
			buDiningSpecialty.setFuid(UUIDCreater.getUUID());
			buDiningSpecialty.setDiningid(diningid);
			buDiningSpecialty.setCreatedate(new Date());
			buDiningSpecialty.setModifydate(new Date());
			buDiningSpecialtyService.save(buDiningSpecialty);
			
		
		}
		return "show";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buDiningSpecialty=buDiningSpecialtyService.findById(id);
		return "buDiningSpecialtyEditor";
	}
	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buDining");
		if(!com.getHisDelete()){
			return "error";
		}
		if (uid != null && uid.length() > 0) {
			String ids[] = uid.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					buDiningSpecialtyService.delete(ids[a]);
				}
			}
		} else {
			if (id != null && id.length() > 0) {
				buDiningSpecialtyService.delete(id);
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
	public BuDiningSpecialtyService getBuDiningSpecialtyService() {
		return buDiningSpecialtyService;
	}
	public void setBuDiningSpecialtyService(BuDiningSpecialtyService buDiningSpecialtyService) {
		this.buDiningSpecialtyService = buDiningSpecialtyService;
	}
	public List<BuDiningSpecialty> getBuDiningSpecialty_list() {
		return buDiningSpecialty_list;
	}
	public void setBuDiningSpecialty_list(List<BuDiningSpecialty> buDiningSpecialty_list) {
		this.buDiningSpecialty_list = buDiningSpecialty_list;
	}
	public BuDiningSpecialty getBuDiningSpecialty() {
		return buDiningSpecialty;
	}
	public void setBuDiningSpecialty(BuDiningSpecialty buDiningSpecialty) {
		this.buDiningSpecialty = buDiningSpecialty;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public File getUploadpic() {
		return uploadpic;
	}
	public void setUploadpic(File uploadpic) {
		this.uploadpic = uploadpic;
	}
	public String getUploadpicFileName() {
		return uploadpicFileName;
	}
	public void setUploadpicFileName(String uploadpicFileName) {
		this.uploadpicFileName = uploadpicFileName;
	}
	public String getDiningid() {
		return diningid;
	}
	public void setDiningid(String diningid) {
		this.diningid = diningid;
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
