package com.gh.action.weixin;

import java.io.File;
import java.io.IOException;
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


public class BuNewsAction  extends Action{
	
	private BuNewsService buNewsService;
	private String uid;
	private String id;
	private String type;
	private String skey;
	private List<BuNews> buNews_list;
	private BuNews buNews;
	private String flag;
	
	//----图片上传----
	private String imgNum;
	private String reNum;
	

	
	/**
	 * 微信列表显示
	 */
	public String show(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(StringUtil.isNotTrimBlank(type)){
			type = type.trim();
		}
		com=CompetenceManager.getCom(roleid, "buNews?type="+type);
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		String SqlWhere=" DELETEMARK=0 and type like '"+type+"%'";
		if(skey!=null&&skey.length()>0){
		    SqlWhere+=" and fullname like '%"+skey+"%'";
		 } 
		
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buNewsService.getRecordCount(SqlWhere);
		buNews_list=buNewsService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		if(!(buNews_list==null||buNews_list.isEmpty())){
			buNews = buNews_list.get(0);
		}
		
		if(type.equals("004001")){
			return "buNewsModify";
		}else{
			return "buNews";
		}
	}
	
/**
	 * @see 微信  修改及新增
	 * @author xiao
	 * @throws IOException 
	 */
	public String edit() throws IOException{
		
		String roleid=(String)request.getSession().getAttribute("roleid");
		String id=buNews.getFuid();
		if(id!=null&&id.length()>0){
			type=type!=null&&type.length()>6?type.substring(0,6):type;
			com=CompetenceManager.getCom(roleid, "buNews?type="+type);
			if(!com.getHisUpdate()){
				return "error";
			}
			buNews.setModifydate(new Date());
			//---------图片上传--------
			FileUpload fileupload=new FileUpload();
			ArrayList<String> re=fileupload.inFile(img, "/upload", imgFileName);
				if(type.equals("004001")){
					if(StringUtil.isNotTrimBlank(flag)){
						String flags [] = flag.split("#");
						for(int i=0;i<flags.length;i++){
							if("1".equals(flags[i])){
								buNews.setImg1(re.get(i));
							}else if("2".equals(flags[i])){
								buNews.setImg2(re.get(i));
							}else if("3".equals(flags[i])){
								buNews.setImg3(re.get(i));
							}
							
							//压缩图片
							ImageCompression mypic = new ImageCompression();
							String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
							mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 500, 450, true);
							mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 223, 222, true);
						}
					}	
			}else{
				if(re.size()>0){
				buNews.setImg1(re.get(0));
				//压缩图片
				ImageCompression mypic = new ImageCompression();
				String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
				mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(0), "B_"+re.get(0), 500, 450, true);
				mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(0), "L_"+re.get(0), 223, 222, true);
				}
			}
			buNewsService.updateSelective(buNews);
				
		}else{
			com=CompetenceManager.getCom(roleid, "buNews?type="+type);
			if(!com.getHisUpdate()){
				return "error";
			}
			//------------图片上传-------------------
			FileUpload fileupload=new FileUpload();
			ArrayList<String> re=fileupload.inFile(img, "/upload", imgFileName);
			if(re.size()>0&&type.equals("004001")){
				if(StringUtil.isNotTrimBlank(flag)){
					String flags [] = flag.split("#");
					for(int i=0;i<flags.length;i++){
						if("1".equals(flags[i])){
							buNews.setImg1(re.get(i));
						}else if("2".equals(flags[i])){
							buNews.setImg2(re.get(i));
						}else if("3".equals(flags[i])){
							buNews.setImg3(re.get(i));
						}
						
						//压缩图片
						ImageCompression mypic = new ImageCompression();
						String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
						mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 500, 450, true);
						mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 223, 222, true);
					}
				}	
			}else{
				if(type.endsWith("004001")){
					buNews.setImg1("default.jpg");
					buNews.setImg2("default.jpg");
					buNews.setImg3("default.jpg");
				}
				if(re.size()>0){
					buNews.setImg1(re.get(0));
					//压缩图片
					ImageCompression mypic = new ImageCompression();
					String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(0), "B_"+re.get(0), 500, 450, true);
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(0), "L_"+re.get(0), 223, 222, true);
				}else{
					buNews.setImg1("default.jpg");
				}
				
			}
			buNews.setFuid(UUIDCreater.getUUID());
			buNews.setDeletemark(0);
			buNews.setPraise(0);
			buNews.setReadnum(0);
			buNews.setCreatedate(new Date());
			buNews.setModifydate(new Date());
			buNews.setType(type);
			buNewsService.save(buNews);
		}
		return "show";
	}
	
	
	
/**
 * 显示详细页或修改页
 * @return
 */
	public String update_show(){
		buNews=buNewsService.findById(id);
		return "buNewsEditor";
	}
	
	/**
	 * @see  微信删除单条数据或多条
	 * @author xiao
	 */
	public String delete(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "buNews?type="+type);
		if(!com.getHisDelete()){
			return "error";
		}
		if (uid != null && uid.length() > 0) {
			String ids[] = uid.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					buNews = buNewsService.findById(ids[a]);
					buNews.setDeletemark(1);
					buNewsService.updateSelective(buNews);
				}
			}
		} else {
			if (id != null && id.length() > 0) {
				buNews = buNewsService.findById(id);
				buNews.setDeletemark(1);
				buNewsService.updateSelective(buNews);
			}
		}
		return "show";
	}
	
	/***********************************************触摸板****************************************************/
	
	/**
	 * 触摸板列表显示
	 */
	public String shows(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(StringUtil.isNotTrimBlank(type)){
			type = type.trim();
		}
		com=CompetenceManager.getCom(roleid, "touchNews?type="+type);
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		String SqlWhere=" DELETEMARK=0 and type="+type;
		if(skey!=null&&skey.length()>0){
		    SqlWhere+=" and fullname like '%"+skey+"%'";
		 } 
		
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buNewsService.getRecordCount(SqlWhere);
		buNews_list=buNewsService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		if(!(buNews_list==null||buNews_list.isEmpty())){
			buNews = buNews_list.get(0);
		}
		
		if(type.equals("004007")){
			return "touchModify";
		}if(type.equals("004032")){
			return "phoneModify";
		}else{
			return "touchNews";
		}
	}
	
	/**
	 * @see 触摸板   修改及新增
	 * @author xiao
	 * @throws IOException
	 */
	public String edits() throws IOException {
		
		String roleid = (String) request.getSession().getAttribute("roleid");
		String id = buNews.getFuid();
		if (id != null && id.length() > 0) {
			com = CompetenceManager.getCom(roleid, "touchNews?type="+type);
			if (!com.getHisUpdate()) {
				return "error";
			}
			//------------图片修改
			BuNews buNews1 = buNewsService.findById(id);
			String dimg = buNews1.getImg1();
			//System.out.println(imgNum);
			boolean flag=false;  ///flag :false:表示图片进行压缩,     flag： true 表示图片不进行压缩
			String imgRe = Imgedit.modify(reNum,imgNum, dimg, img, imgFileName,flag);
			if (imgRe.length() > 0) {
				buNews.setImg1(imgRe);
			} else {
				buNews.setImg1("default.jpg");
			}
			//----------------
			
			buNews.setModifydate(new Date());
			buNewsService.updateSelective(buNews);
		} else {
			com = CompetenceManager.getCom(roleid, "touchNews?type="+type);
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
				buNews.setImg1(imges);
				for(int i=0;i<re.size();i++){
					//压缩图片
					ImageCompression mypic = new ImageCompression();
					String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
				}
				
			}else{
				buNews.setImg1("default.jpg");
			}
			buNews.setFuid(UUIDCreater.getUUID());
			buNews.setPraise(0);
			buNews.setReadnum(0);
			buNews.setDeletemark(0);
			buNews.setCreatedate(new Date());
			buNews.setType(type);
			buNews.setModifydate(new Date());
			buNewsService.save(buNews);

		}

		return "shows";
	}

	
	
/**
 * 触摸板显示详细页或修改页
 * @return
 */
	public String update_shows(){
		buNews=buNewsService.findById(id);
		return "touchEditor";
	}
	
	/**
	 * @see  触摸板 删除单条数据或多条数据
	 * @author xiao
	 */
	public String deletes(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "touchNews?type="+type);
		if(!com.getHisDelete()){
			return "error";
		}
		if (uid != null && uid.length() > 0) {
			String ids[] = uid.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					buNews = buNewsService.findById(ids[a]);
					buNews.setDeletemark(1);
					buNewsService.updateSelective(buNews);
				}
			}
		} else {
			if (id != null && id.length() > 0) {
				buNews = buNewsService.findById(id);
				buNews.setDeletemark(1);
				buNewsService.updateSelective(buNews);
			}
		}
		return "shows";
	}
	

/***********************************************其他新闻类型(电脑版管理,交通播报,路线推荐)****************************************************/
	
	/**
	 * 新闻列表显示
	 */
	public String show_s(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		if(StringUtil.isNotTrimBlank(type)){
			type = type.trim();
		}
		com=CompetenceManager.getCom(roleid, "OtherNews?type="+type);
		//--------------------操作权限控制
		if(!com.getHisSelect()){
			return "error";
		}
		String SqlWhere=" DELETEMARK=0 and type="+type;
		if(skey!=null&&skey.length()>0){
		    SqlWhere+=" and fullname like '%"+skey+"%'";
		 } 
		
		pageSize=10;
		indexPage=indexPage==0?1:indexPage;
		totalPage=buNewsService.getRecordCount(SqlWhere);
		buNews_list=buNewsService.selectByPage(indexPage,pageSize, SqlWhere, "MODIFYDATE desc");
		if(!(buNews_list==null||buNews_list.isEmpty())){
			buNews = buNews_list.get(0);
		}
		//如果type等于一下类型表示的是直接在原页面修改
		if(type.equals("004028")||type.equals("004029")||type.equals("004030")){
			return "ModifyNewsEditor";
		}
		if(type.equals("004034")||type.equals("004035")){
			return "ReplenishEditor";
		}
		return "OtherNews";
	}
	
	/**
	 * @see 新闻   修改及新增
	 * @author xiao
	 * @throws IOException
	 */
	public String edit_s() throws IOException {
		String roleid = (String) request.getSession().getAttribute("roleid");
		String id = buNews.getFuid();
		if (id != null && id.length() > 0) {
			com = CompetenceManager.getCom(roleid, "OtherNews?type="+type);
			if (!com.getHisUpdate()) {
				return "error";
			}
			//------------图片修改
			BuNews buNews1 = buNewsService.findById(id);
			String dimg = buNews1.getImg1();
			//System.out.println(imgNum);
			boolean flag=false;
			String imgRe = Imgedit.modify(reNum,imgNum, dimg, img, imgFileName,flag);
			if (imgRe.length() > 0) {
				buNews.setImg1(imgRe);
			} else {
				buNews.setImg1("default.jpg");
			}
			//----------------
			buNews.setModifydate(new Date());
			buNewsService.updateSelective(buNews);
		} else {
			com = CompetenceManager.getCom(roleid, "OtherNews?type="+type);
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
				buNews.setImg1(imges);
				for(int i=0;i<re.size();i++){
					//压缩图片
					ImageCompression mypic = new ImageCompression();
					String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
					}
				
			}else{
				buNews.setImg1("default.jpg");
			}
			buNews.setFuid(UUIDCreater.getUUID());
			buNews.setPraise(0);
			buNews.setReadnum(0);
			buNews.setDeletemark(0);
			buNews.setCreatedate(new Date());
			buNews.setType(type);
			buNews.setModifydate(new Date());
			buNewsService.save(buNews);

		}

		return "show_s";
	}

	
	
/**
 * 新闻显示详细页或修改页
 * @return
 */
	public String update_show_s(){
		buNews=buNewsService.findById(id);
		return "OtherNewsEditor";
	}
	
	/**
	 * @see  新闻 删除单条数据或多条数据
	 * @author xiao
	 */
	public String delete_s(){
		String roleid=(String)request.getSession().getAttribute("roleid");
		com=CompetenceManager.getCom(roleid, "OtherNews?type="+type);
		if(!com.getHisDelete()){
			return "error";
		}
		if (uid != null && uid.length() > 0) {
			String ids[] = uid.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					buNews = buNewsService.findById(ids[a]);
					buNews.setDeletemark(1);
					buNewsService.updateSelective(buNews);
				}
			}
		} else {
			if (id != null && id.length() > 0) {
				buNews = buNewsService.findById(id);
				buNews.setDeletemark(1);
				buNewsService.updateSelective(buNews);
			}
		}
		return "show_s";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
	public BuNewsService getBuNewsService() {
		return buNewsService;
	}
	public void setBuNewsService(BuNewsService buNewsService) {
		this.buNewsService = buNewsService;
	}
	public List<BuNews> getBuNews_list() {
		return buNews_list;
	}
	public void setBuNews_list(List<BuNews> buNews_list) {
		this.buNews_list = buNews_list;
	}
	public BuNews getBuNews() {
		return buNews;
	}
	public void setBuNews(BuNews buNews) {
		this.buNews = buNews;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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
