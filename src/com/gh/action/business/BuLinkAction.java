package com.gh.action.business;

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
import com.gh.common.StringUtil;
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


public class BuLinkAction  extends Action{
	
	
	private BuLinkService buLinkService;
	private String uid;
	private String id;
	private String skey;
	private List<BuLink> buLink_list;
	private BuLink buLink;
	
	
	//----图片上传----
	private String imgNum;
	private String reNum;
	
	/**
	 * 友情链接列表显示
	 * @return
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		
		com=CompetenceManager.getCom(roleid, "buLink");
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		String SqlWhere=" ";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+=" and name like '%"+skey+"%'";
		 } 
		
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buLinkService.getRecordCount(SqlWhere);
		buLink_list=buLinkService.selectByPage(indexPage,pageSize, SqlWhere, " ");
		return "buLink";
	}
	/**
	 * @see    修改及新增
	 * @author xiao
	 * @throws IOException
	 */
	public String edit() throws IOException {
		String roleid = (String) request.getSession().getAttribute("roleid");
		String id = buLink.getFuid();
		if (id != null && id.length() > 0) {
			com = CompetenceManager.getCom(roleid, "buLink");
			if (!com.getHisUpdate()) {
				return "error";
			}
			//------------图片修改
			BuLink bulink1 = buLinkService.findById(id);
			//System.out.println(buLink.getContent()+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			String dimg = bulink1.getLogo();
			//System.out.println(imgNum);
			boolean flag=false;
			String imgRe = Imgedit.modify(reNum,imgNum, dimg, img, imgFileName,flag);
			if (imgRe.length() > 0) {
				buLink.setLogo(imgRe);
			} else {
				buLink.setLogo("default.jpg");
			}
			//----------------
			buLink.setCreateTime(new Date());
			buLinkService.updateSelective(buLink);
		} else {
			com = CompetenceManager.getCom(roleid, "buLink");
			if (!com.getHisUpdate()) {
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
				buLink.setLogo(imges);
				for(int i=0;i<re.size();i++){
					//压缩图片
					ImageCompression mypic = new ImageCompression();
					String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
					}
			}else{
				buLink.setLogo("default.jpg");
			}
			buLink.setFuid(UUIDCreater.getUUID());
			buLink.setCreateTime(new Date());
			buLinkService.save(buLink);

		}
		return "show";
	}

	
	
/**
 * 跳转修改页
 * @return
 */
	public String update_show(){
		buLink=buLinkService.findById(id);
		System.out.println(buLink.getName()+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		return "buLinkEditor";
	}
	
	/**
	 * @see  新闻 删除单条数据或多条数据
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buLink");
		if(!com.getHisDelete()){
			return "error";
		}
		if (uid != null && uid.length() > 0) {
			String ids[] = uid.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					buLinkService.delete(ids[a]);
				}
			}
		} else {
			if (id != null && id.length() > 0) {
				 buLinkService.delete(id);
				
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
	public BuLinkService getBuLinkService() {
		return buLinkService;
	}
	public void setBuLinkService(BuLinkService buLinkService) {
		this.buLinkService = buLinkService;
	}
	public List<BuLink> getBuLink_list() {
		return buLink_list;
	}
	public void setBuLink_list(List<BuLink> buLink_list) {
		this.buLink_list = buLink_list;
	}
	public BuLink getBuLink() {
		return buLink;
	}
	public void setBuLink(BuLink buLink) {
		this.buLink = buLink;
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
