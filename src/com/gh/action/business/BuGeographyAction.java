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


public class BuGeographyAction  extends Action{
	
	
	private BuGeographyService buGeographyService;
	private String uid;
	private String id;
	private String skey;
	private List<BuGeography> buGeography_list;
	private BuGeography buGeography;
	private String type;
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
	public String show() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		
		com = CompetenceManager.getCom(roleid, "buGeography?type=" + type.substring(0,6));
		// --------------------操作权限控制
		if (!com.getHisSelect()) {
			return "error";
		}
		String SqlWhere=" DELETEMARK=0 and type like "+"'"+type.substring(0,6)+"%'";
		//String SqlWhere = " DELETEMARK=0 and type=" + type;
		if (skey != null && skey.length() > 0) {
			SqlWhere += " and fullname like '%" + skey + "%'";
			
		}
		// --------------------
		pageSize = 10;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buGeographyService.getRecordCount(SqlWhere);
		buGeography_list = buGeographyService.selectByPage(indexPage, pageSize,SqlWhere, "MODIFYDATE desc");
		return "buGeography";
	}

	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException
	 */
	public String edit() throws IOException {
		String id = buGeography.getFuid();
		String roleid = (String) request.getSession().getAttribute("roleid");
		
		if (id != null && id.length() > 0) {
			com = CompetenceManager.getCom(roleid, "buGeography?type=" + type.substring(0,6));
			if (!com.getHisUpdate()) {
				return "error";
			}
			//------------图片修改
			BuGeography buGeography1 = buGeographyService.findById(id);
			String dimg = buGeography1.getDimages();
			System.out.println(imgNum);
			boolean flag=false;
			String imgRe = Imgedit.modify(reNum,imgNum, dimg, img, imgFileName,flag);
			if(imgRe.length()>0){
			buGeography.setDimages(imgRe);
			}else{
			buGeography.setDimages("default.jpg");
			}
			buGeography.setModifydate(new Date());
			buGeographyService.updateSelective(buGeography);
		} else {
			System.out.println("---type的类型----"+type);
			com = CompetenceManager.getCom(roleid, "buGeography?type=" + type.substring(0,6));
			if (!com.getHisAdd()) {
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
				buGeography.setDimages(imges);
				for(int i=0;i<re.size();i++){
				//压缩图片
				ImageCompression mypic = new ImageCompression();
				String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
				mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
				mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
				}
			}else{
				buGeography.setDimages("default.jpg");
			}
			
			if(type.equals("002019")||type.equals("002003")||type.equals("002007")||type.equals("002008")||type.equals("002009")){
				buGeography.setType(type);
			}
			buGeography.setFuid(UUIDCreater.getUUID());
			buGeography.setDeletemark(0);
			buGeography.setCreatedate(new Date());
			//buGeography.setType(type);
			buGeography.setModifydate(new Date());
			buGeographyService.save(buGeography);

		}

		return "show";
	}

	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show() {
		buGeography = buGeographyService.findById(id);
		type=buGeography.getType().substring(0, 6);
		return "buGeographyEditor";
	}

	/**
	 * @see 删除(逻辑删除)
	 * @author xiao
	 */
	public String delete() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		com = CompetenceManager.getCom(roleid, "buGeography?type=" + type.substring(0,6));
		if (!com.getHisDelete()) {
			return "error";
		}
		if (uid != null && uid.length() > 0) {
			String ids[] = uid.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					buGeography = buGeographyService.findById(ids[a]);
					buGeography.setDeletemark(1);
					buGeographyService.updateSelective(buGeography);
				}
			}
		} else {
			if (id != null && id.length() > 0) {
				buGeography = buGeographyService.findById(id);
				buGeography.setDeletemark(1);
				buGeographyService.updateSelective(buGeography);
			}
		}
		return "show";
	}

	/**********************************************未分类基础数据管理****************************************************************/
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String shows() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		
		com = CompetenceManager.getCom(roleid, "buGeographys");
		// --------------------操作权限控制
		if (!com.getHisSelect()) {
			return "error";
		}
		String SqlWhere=" DELETEMARK=0 and TYPE=100  ";
		//String SqlWhere = " DELETEMARK=0 and type=" + type;
		if (skey != null && skey.length() > 0) {
			SqlWhere += " and fullname like '%" + skey + "%'";
			
		}
		// --------------------
		pageSize = 10;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buGeographyService.getRecordCount(SqlWhere);
		buGeography_list = buGeographyService.selectByPage(indexPage, pageSize,SqlWhere, "MODIFYDATE desc");
		return "BuOther";
	}

	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException
	 */
	public String edits() throws IOException {
		String id = buGeography.getFuid();
		String roleid = (String) request.getSession().getAttribute("roleid");
		
		if (id != null && id.length() > 0) {
			com = CompetenceManager.getCom(roleid, "buGeographys");
			if (!com.getHisUpdate()) {
				return "error";
			}
			//------------图片修改
			BuGeography buGeography1 = buGeographyService.findById(id);
			String dimg = buGeography1.getDimages();
			System.out.println(imgNum);
			boolean flag=false;
			String imgRe = Imgedit.modify(reNum,imgNum, dimg, img, imgFileName,flag);
			if(imgRe.length()>0){
			buGeography.setDimages(imgRe);
			}else{
			buGeography.setDimages("default.jpg");
			}
			buGeography.setModifydate(new Date());
			buGeographyService.updateSelective(buGeography);
		} else {
			com = CompetenceManager.getCom(roleid, "buGeographys");
			if (!com.getHisAdd()) {
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
				buGeography.setDimages(imges);
				for(int i=0;i<re.size();i++){
				//压缩图片
				ImageCompression mypic = new ImageCompression();
				String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
				mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
				mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
				}
			}else{
				buGeography.setDimages("default.jpg");
			}
			buGeography.setFuid(UUIDCreater.getUUID());
			buGeography.setDeletemark(0);
			buGeography.setCreatedate(new Date());
			buGeography.setType("100");//不知道类别的默认为100
			buGeography.setModifydate(new Date());
			buGeographyService.save(buGeography);

		}

		return "shows";
	}

	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_shows() {
		buGeography = buGeographyService.findById(id);
		return "BuOtherEditor";
	}

	/**
	 * @see 删除(逻辑删除)
	 * @author xiao
	 */
	public String deletes() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		com = CompetenceManager.getCom(roleid, "buGeographys");
		if (!com.getHisDelete()) {
			return "error";
		}
		if (uid != null && uid.length() > 0) {
			String ids[] = uid.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					buGeography = buGeographyService.findById(ids[a]);
					buGeography.setDeletemark(1);
					buGeographyService.updateSelective(buGeography);
				}
			}
		} else {
			if (id != null && id.length() > 0) {
				buGeography = buGeographyService.findById(id);
				buGeography.setDeletemark(1);
				buGeographyService.updateSelective(buGeography);
			}
		}
		return "shows";
	}
	/**************************************************************************************************************/
	public BuGeographyService getBuGeographyService() {
		return buGeographyService;
	}

	public void setBuGeographyService(BuGeographyService buGeographyService) {
		this.buGeographyService = buGeographyService;
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

	public List<BuGeography> getBuGeography_list() {
		return buGeography_list;
	}

	public void setBuGeography_list(List<BuGeography> buGeography_list) {
		this.buGeography_list = buGeography_list;
	}

	public BuGeography getBuGeography() {
		return buGeography;
	}

	public void setBuGeography(BuGeography buGeography) {
		this.buGeography = buGeography;
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