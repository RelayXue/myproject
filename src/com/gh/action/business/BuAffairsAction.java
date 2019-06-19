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

import org.apache.struts2.ServletActionContext;
  
 

import com.gh.entity.*;
import com.gh.dao.*;
import com.gh.service.*;  

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

public class BuAffairsAction  extends Action{
	
	private BuAffairsService buAffairsService;
	private String uid;
	private String id;
	private String skey;
	private List<BuAffairs> buAffairs_list;
	private BuAffairs buAffairs;
	//----处理图片前上传----
	private File upload;
	private String uploadFileName;
	private String imgNum;
	private String reNum;
	//----处理图片后上传-----
    public File[] img_; //上传的文件
	public String[] img_FileName; //文件名称
	public String[] img_ContentType; //文件类型
	private String imgNum_;
	private String reNum_;
	
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buAffairs");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		 String SqlWhere=" DELETEMARK=0";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+=" and reported like '%"+skey+"%'";
		 } 
		//--------------------
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buAffairsService.getRecordCount(SqlWhere);
		buAffairs_list=buAffairsService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		 return "buAffairs";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException 
	 */
	public String edit() throws IOException{
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buAffairs.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buAffairs");
			if(!com.getHisUpdate()){
				return "error";
			}
			
			BuAffairs buAffairs1 = buAffairsService.findById(id);
			
			//------------图片处理前修改-----
			String dimg = buAffairs1.getBeforeimage();
			System.out.println(imgNum);
			boolean flag=false;
			String imgRe = Imgedit.modify(reNum,imgNum, dimg, img, imgFileName,flag);
			if(imgRe.length()>0){
				buAffairs.setBeforeimage(imgRe);
			}else{
				buAffairs.setBeforeimage("default.jpg");
			}
			//-------------图片处理后修改----------------
			String dimg_ = buAffairs1.getAfterimage();
			System.out.println("-->"+imgNum_);
			String imgRe_ = Imgedit.modify_(reNum_,imgNum_,dimg_,img_,img_FileName);
			if(imgRe_.length()>0){
				buAffairs.setAfterimage(imgRe_);
			}else{
				buAffairs.setBeforeimage("default.jpg");
			}
			buAffairs.setModifydate(new Date());
			buAffairsService.updateSelective(buAffairs);
		}else{
			com=CompetenceManager.getCom(roleid, "buAffairs");
			if(!com.getHisAdd()){
				return "error";
			}
			
			//
			//处理图片前   --新建上传
			FileUpload fileupload=new FileUpload();
			ArrayList<String> re=fileupload.inFile(img, "/upload", imgFileName);
			if(re!=null&&re.size()>0){
				String imges="";
				for(int a=0;a<re.size();a++){
					imges+=re.get(a)+",";
				}
				imges=imges.length()>0?imges.substring(0,imges.length()-1):"";
				System.out.println("--------"+imges);
				buAffairs.setBeforeimage(imges);
				for(int i=0;i<re.size();i++){
					//压缩图片
					ImageCompression mypic = new ImageCompression();
					String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
					}
				//buAffairs.setAfterimage(imges);
			}else{
				buAffairs.setBeforeimage("default.jpg");
			}
			//-----------------------------------------------------------
			//处理图片后 ----新建上传
			
			ArrayList<String> re1=fileupload.inFile_(img_, "/upload", img_FileName);
			if(re1!=null&&re1.size()>0){
				String imges_="";
				for(int a=0;a<re1.size();a++){
					imges_+=re1.get(a)+",";
				}
				imges_=imges_.length()>0?imges_.substring(0,imges_.length()-1):"";
				System.out.println("--------"+imges_);
				buAffairs.setAfterimage(imges_);
				for(int i=0;i<re.size();i++){
					//压缩图片
					ImageCompression mypic = new ImageCompression();
					String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
					}
			}else{
				buAffairs.setAfterimage("default.jpg");
			}
			buAffairs.setFuid(UUIDCreater.getUUID());
			buAffairs.setDeletemark(0);
			buAffairs.setCreatedate(new Date());
			buAffairs.setModifydate(new Date());
			buAffairsService.save(buAffairs);
		}
		return "show";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buAffairs=buAffairsService.findById(id);
		return "buAffairsEditor";
	}
	/**
	 * @see 逻辑删除
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buAffairs");
		if(!com.getHisDelete()){
			return "error";
		}
		if(uid!=null&&uid.length()>0){
			String ids[]=uid.split(",");
			if(ids!=null&&ids.length>0){
				for(int a=0;a<ids.length;a++){
					buAffairs = buAffairsService.findById(ids[a]);
					buAffairs.setDeletemark(1);
					buAffairsService.updateSelective(buAffairs);
				}
			}
		}else{
			if(id!=null&&id.length()>0){
					buAffairs =buAffairsService.findById(id);
					buAffairs.setDeletemark(1);
					buAffairsService.updateSelective(buAffairs);
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
	public BuAffairsService getBuAffairsService() {
		return buAffairsService;
	}
	public void setBuAffairsService(BuAffairsService buAffairsService) {
		this.buAffairsService = buAffairsService;
	}
	public List<BuAffairs> getBuAffairs_list() {
		return buAffairs_list;
	}
	public void setBuAffairs_list(List<BuAffairs> buAffairs_list) {
		this.buAffairs_list = buAffairs_list;
	}
	public BuAffairs getBuAffairs() {
		return buAffairs;
	}
	public void setBuAffairs(BuAffairs buAffairs) {
		this.buAffairs = buAffairs;
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
	public File[] getImg_() {
		return img_;
	}
	public void setImg_(File[] img_) {
		this.img_ = img_;
	}
	public String[] getImg_FileName() {
		return img_FileName;
	}
	public void setImg_FileName(String[] img_FileName) {
		this.img_FileName = img_FileName;
	}
	public String[] getImg_ContentType() {
		return img_ContentType;
	}
	public void setImg_ContentType(String[] img_ContentType) {
		this.img_ContentType = img_ContentType;
	}
	public String getImgNum_() {
		return imgNum_;
	}
	public void setImgNum_(String imgNum_) {
		this.imgNum_ = imgNum_;
	}
	public String getReNum_() {
		return reNum_;
	}
	public void setReNum_(String reNum_) {
		this.reNum_ = reNum_;
	}
	
	
}
