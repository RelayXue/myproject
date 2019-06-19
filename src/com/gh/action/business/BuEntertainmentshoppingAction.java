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
 * 购物娱乐BU_ENTERTAINMENTSHOPPING
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

public class BuEntertainmentshoppingAction  extends Action{
	
	private BuEntertainmentshoppingService buEntertainmentshoppingService;
	private String uid;
	private String id;
	private String ids;
	private String names;
	private String types;
	private String skey;
	private String type;
	private List<BuEntertainmentshopping> buEntertainmentshopping_list;
	private BuEntertainmentshopping buEntertainmentshopping;
	
	private ServletContext context;
	private File uploadpic;
	private String uploadpicFileName;
	private String typeName;//搜索类型
	
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
		com=CompetenceManager.getCom(roleid, "buEntertainmentshopping?type="+type.substring(0,6));
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		String SqlWhere=" DELETEMARK=0 and type like "+"'"+type+"%'";
		
		if(typeName!=null&&typeName.length()>6){
			//System.out.println("typeName的类型长度是："+typeName.length());
			SqlWhere+=" and type="+typeName;
		}
		if(skey!=null&&skey.length()>0){
		    SqlWhere+=" and fullname like '%"+skey+"%'";
		 } 
		//--------------------
	    pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buEntertainmentshoppingService.getRecordCount(SqlWhere);
		buEntertainmentshopping_list=buEntertainmentshoppingService.selectByPage(indexPage,pageSize, SqlWhere, "DIMAGES_STATUS DESC, PRAISE DESC");
		 return "buEntertainmentshopping";
	}
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String showExamine(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buEntertainmentshopping!showExamine");
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
		totalPage=buEntertainmentshoppingService.getRecordCount(SqlWhere);
		buEntertainmentshopping_list=buEntertainmentshoppingService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		 return "buEntertainmentshoppingCode";
	}
	/**
	 * @see 生成二维码
	 * @author xiao
	 */
	public String generate() {
		String path = request.getSession().getServletContext().getRealPath("/") + "Barcode\\";
		if(ids!=null){
			String id[]=ids.split(",");
			String name[]=names.split(",");
			String type[]=types.split(",");
			if(id!=null&&id.length>0){
				for(int a=0;a<id.length;a++){
					String img1 = path + "//" + name[a] + ".png";
					System.out.println(type[a]);
					ZxingEncode.gocode(id[a], type[a], img1);
					buEntertainmentshopping=new BuEntertainmentshopping();
					buEntertainmentshopping.setFuid(id[a]);
					buEntertainmentshopping.setPrstatus("1");
					buEntertainmentshopping.setCodepath("/Barcode/"+name[a] + ".png");
					buEntertainmentshoppingService.updateSelective(buEntertainmentshopping);
				}
			}
		}
		return "showExamine";
	}
	/**
	 * @see 生成二维码
	 * @author xiao
	 */
	public String generateAll() {
		String path = request.getSession().getServletContext().getRealPath("/") + "Barcode\\";
		 List<BuEntertainmentshopping> buEntertainmentshopping_list=buEntertainmentshoppingService.execSql("select * from bu_Entertainmentshopping where PRSTATUS!='1'");
		if(buEntertainmentshopping_list!=null&&buEntertainmentshopping_list.size()>0){
			for(int a=0;a<buEntertainmentshopping_list.size();a++){
				String img1 = path + "//" + buEntertainmentshopping_list.get(a).getFullname() + ".png";
				ZxingEncode.gocode(buEntertainmentshopping_list.get(a).getFuid(),buEntertainmentshopping_list.get(a).getType(), img1);
				buEntertainmentshopping=buEntertainmentshopping_list.get(a);
				buEntertainmentshopping.setPrstatus("1");
				buEntertainmentshopping.setCodepath("/Barcode/"+buEntertainmentshopping_list.get(a).getFullname() + ".png");
				buEntertainmentshoppingService.updateSelective(buEntertainmentshopping);
			}
		}
		return "showExamine";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException 
	 */
	public String edit() throws IOException{
		String username=(String)request.getSession().getAttribute("username");
		String userid=(String)request.getSession().getAttribute("userid");
		String id=buEntertainmentshopping.getFuid();
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(id!=null&&id.length()>0){
			com=CompetenceManager.getCom(roleid, "buEntertainmentshopping?type="+type.substring(0,6));
			System.out.println(type.substring(0,6));
			if(!com.getHisUpdate()){
				return "error";
			}
			//------------图片修改
			BuEntertainmentshopping buEntertainmentshopping1 = buEntertainmentshoppingService.findById(id);
			String dimg = buEntertainmentshopping1.getDimages();
			System.out.println(imgNum);
			boolean flag=false;
			String imgRe = Imgedit.modify(reNum,imgNum, dimg, img, imgFileName,flag);
			if (imgRe.length() > 0) {
				buEntertainmentshopping.setDimages(imgRe);
				
			} else {
				buEntertainmentshopping.setDimages("");
			}
			
			buEntertainmentshopping.setModifydate(new Date());
			buEntertainmentshoppingService.updateSelective(buEntertainmentshopping);
			
		}else{
			com=CompetenceManager.getCom(roleid, "buEntertainmentshopping?type="+type.substring(0,6));
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
				buEntertainmentshopping.setDimages(imges);
				buEntertainmentshopping.setDimagesStatus(1);
				//压缩图片
				for(int i=0;i<re.size();i++){
					ImageCompression mypic = new ImageCompression();
					String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
					}
				
			}else{
				buEntertainmentshopping.setDimagesStatus(0);
			}
			buEntertainmentshopping.setFuid(UUIDCreater.getUUID());
			buEntertainmentshopping.setDeletemark(0);
			//buEntertainmentshopping.setType(type);
			buEntertainmentshopping.setCreatedate(new Date());
			buEntertainmentshopping.setModifydate(new Date());
			buEntertainmentshoppingService.save(buEntertainmentshopping);
			
		}
		return "show";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show(){
		buEntertainmentshopping=buEntertainmentshoppingService.findById(id);
		return "buEntertainmentshoppingEditor";
	}
	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buEntertainmentshopping?type="+type.substring(0,6));
		if(!com.getHisDelete()){
			return "error";
		}
//
//		if (uid != null && uid.length() > 0) {
//			String ids[] = uid.split(",");
//			if (ids != null && ids.length > 0) {
//				for (int a = 0; a < ids.length; a++) {
//					buEntertainmentshoppingService.delete(ids[a]);
//				}
//			}
//		} else {
//			if (id != null && id.length() > 0) {
//				buEntertainmentshoppingService.delete(id);
//			}
//		}
		BuEntertainmentshopping buEntertainmentshopping = buEntertainmentshoppingService.findById(id);
		buEntertainmentshopping.setDeletemark(1);
		buEntertainmentshoppingService.updateSelective(buEntertainmentshopping);
		
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
	public BuEntertainmentshoppingService getBuEntertainmentshoppingService() {
		return buEntertainmentshoppingService;
	}
	public void setBuEntertainmentshoppingService(BuEntertainmentshoppingService buEntertainmentshoppingService) {
		this.buEntertainmentshoppingService = buEntertainmentshoppingService;
	}
	public List<BuEntertainmentshopping> getBuEntertainmentshopping_list() {
		return buEntertainmentshopping_list;
	}
	public void setBuEntertainmentshopping_list(List<BuEntertainmentshopping> buEntertainmentshopping_list) {
		this.buEntertainmentshopping_list = buEntertainmentshopping_list;
	}
	public BuEntertainmentshopping getBuEntertainmentshopping() {
		return buEntertainmentshopping;
	}
	public void setBuEntertainmentshopping(BuEntertainmentshopping buEntertainmentshopping) {
		this.buEntertainmentshopping = buEntertainmentshopping;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	
}
