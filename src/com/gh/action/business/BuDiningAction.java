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
 * 餐饮BU_DINING
 * 
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

public class BuDiningAction extends Action {

	private BuDiningService buDiningService;
	private String uid;
	private String id;
	private String skey;
	private List<BuDining> buDining_list;
	private BuDining buDining;

	private ServletContext context;
	private File uploadpic;
	private String uploadpicFileName;
	private String typeName;//搜索类型
	
	//----图片上传----
	private File upload;
	private String uploadFileName;
	private String imgNum;
	private String reNum;
	private String ids;
	private String names;
	
	

	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		String ul="buDining";
		com = CompetenceManager.getCom(roleid, ul);
		// --------------------操作权限控制
		if (!com.getHisSelect()) {
			return "error";
		}
		String SqlWhere = " DELETEMARK=0";
		
		if(typeName!=null&&typeName.length()>6){
			//System.out.println("typeName的类型长度是："+typeName.length());
			SqlWhere+=" and type="+typeName;
		}
		
		if (skey != null && skey.length() > 0) {
			SqlWhere += " and fullname like '%" + skey + "%'";
		}
		// --------------------
		pageSize = 10;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buDiningService.getRecordCount(SqlWhere);
		buDining_list = buDiningService.selectByPage(indexPage, pageSize,SqlWhere, "DIMAGES_STATUS DESC, PRAISE DESC");
				
		return "buDining";
	}

	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException 
	 */
	public String edit() throws IOException {
		String username = (String) request.getSession()
				.getAttribute("username");
		String userid = (String) request.getSession().getAttribute("userid");
		String id = buDining.getFuid();
		String roleid = (String) request.getSession().getAttribute("roleid");
		if (id != null && id.length() > 0) {
			String ul="buDining";
			com = CompetenceManager.getCom(roleid, ul);
			if (!com.getHisUpdate()) {
				return "error";
			}
			//------------图片修改
			BuDining buDining1 = buDiningService.findById(id);
			String dimg = buDining1.getDimages();
			System.out.println(imgNum);
			boolean flag=false;
			String imgRe = Imgedit.modify(reNum,imgNum, dimg, img, imgFileName,flag);
			if(imgRe.length()>0){
				buDining.setDimages(imgRe);
			}else{
				buDining.setDimagesStatus(0);
			}
			//System.out.println(imgRe);
			//----------------
			//buDining.setDimages(imgRe);
			
			buDining.setModifydate(new Date());
			buDiningService.updateSelective(buDining);
			
		} else {
			String ul="buDining";
			com = CompetenceManager.getCom(roleid, ul);
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
				System.out.println("--------"+imges);
				buDining.setDimages(imges);
				buDining.setDimagesStatus(1);
				//压缩图片
				for(int i=0;i<re.size();i++){
					ImageCompression mypic = new ImageCompression();
					String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
					}
			}else{
				buDining.setDimagesStatus(0);
			}

			buDining.setFuid(UUIDCreater.getUUID());
			buDining.setDeletemark(0);
			buDining.setCreatedate(new Date());
			buDining.setModifydate(new Date());
			buDiningService.save(buDining);

		
		}
		return "show";
	}
	/**
	 * @see 显示二维码列表页
	 * @author xiao
	 */
	public String showExamine() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		String ul="buDining!showExamine";
		com = CompetenceManager.getCom(roleid, ul);
		// --------------------操作权限控制
		if (!com.getHisSelect()) {
			return "error";
		}
		String SqlWhere = " DELETEMARK=0";
		if (skey != null && skey.length() > 0) {
			SqlWhere += " and fullname like '%" + skey + "%'";
		}
		// --------------------
		pageSize = 10;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buDiningService.getRecordCount(SqlWhere);
		buDining_list = buDiningService.selectByPage(indexPage, pageSize,SqlWhere, "MODIFYDATE desc");
		return "buDiningCode";
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
			if(id!=null&&id.length>0){
				for(int a=0;a<id.length;a++){
					String img1 = path + "//" + name[a] + ".png";
					ZxingEncode.gocode(id[a],"002018", img1);
					buDining=new BuDining();
					buDining.setFuid(id[a]);
					buDining.setPrstatus("1");
					buDining.setCodepath("/Barcode/"+name[a] + ".png");
					buDiningService.updateSelective(buDining);
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
		 List<BuDining> buDining_list=buDiningService.execSql("select * from bu_Dining where PRSTATUS!='1'");
		if(buDining_list!=null&&buDining_list.size()>0){
			for(int a=0;a<buDining_list.size();a++){
				String img1 = path + "//" + buDining_list.get(a).getFullname() + ".png";
				ZxingEncode.gocode(buDining_list.get(a).getFuid(),buDining_list.get(a).getType(), img1);
				buDining=buDining_list.get(a);
				buDining.setPrstatus("1");
				buDining.setCodepath("/Barcode/"+buDining_list.get(a).getFullname() + ".png");
				buDiningService.updateSelective(buDining);
			}
		}
		return "showExamine";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show() {
		buDining = buDiningService.findById(id);
		return "buDiningEditor";
	}

	/**
	 * @see 删除(逻辑删除)
	 * @author xiao
	 */
	public String delete() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		String ul="buDining";
		com = CompetenceManager.getCom(roleid, ul);
		if (!com.getHisDelete()) {
			return "error";
		}
		BuDining buDining = buDiningService.findById(id);
		buDining.setDeletemark(1);
		buDiningService.updateSelective(buDining);
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

	public BuDiningService getBuDiningService() {
		return buDiningService;
	}

	public void setBuDiningService(BuDiningService buDiningService) {
		this.buDiningService = buDiningService;
	}

	public List<BuDining> getBuDining_list() {
		return buDining_list;
	}

	public void setBuDining_list(List<BuDining> buDining_list) {
		this.buDining_list = buDining_list;
	}

	public BuDining getBuDining() {
		return buDining;
	}

	public void setBuDining(BuDining buDining) {
		this.buDining = buDining;
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
	
	
}
