package com.gh.action.business;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.FileUpload;
import com.gh.common.ImageCompression;
import com.gh.common.Imgedit;
import com.gh.common.UUIDCreater;
import com.gh.common.Upload;
import com.opensymphony.xwork2.ActionSupport;
import java.util.*;

import com.gh.entity.*;
import com.gh.dao.*;
import com.gh.service.*;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

public class BuBasicdataAction extends Action {

	private BuBasicdataService buBasicdataService;
	private String uid;
	private String id;
	private String skey;
	private String type;
	private List<BuBasicdata> buBasicdata_list;
	private BuBasicdata buBasicdata;
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
		com = CompetenceManager.getCom(roleid, "buBasicdata?type=" + type);
		// --------------------操作权限控制
		if (!com.getHisSelect()) {
			return "error";
		}
		String SqlWhere = " DELETEMARK=0 and type=" + type;
		if (skey != null && skey.length() > 0) {
			SqlWhere += " and fullname like '%" + skey + "%'";
			// SqlWhere+=" and fullname like '%"+skey+"%'";
		}
		// --------------------
		pageSize = 10;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buBasicdataService.getRecordCount(SqlWhere);
		buBasicdata_list = buBasicdataService.selectByPage(indexPage, pageSize,
				SqlWhere, "MODIFYDATE desc");
		return "buBasicdata";
	}

	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException
	 */
	public String edit() throws IOException {
		String id = buBasicdata.getFuid();
		String roleid = (String) request.getSession().getAttribute("roleid");
		
		if (id != null && id.length() > 0) {
			com = CompetenceManager.getCom(roleid, "buBasicdata?type=" + type);
			if (!com.getHisUpdate()) {
				return "error";
			}
			//------------图片修改
			BuBasicdata buBasicdata1 = buBasicdataService.findById(id);
			String dimg = buBasicdata1.getDimages();
			System.out.println(imgNum);
			boolean flag=false;
			String imgRe = Imgedit.modify(reNum,imgNum, dimg, img, imgFileName,flag);
			
			//System.out.println(imgRe);
			//----------------
			buBasicdata.setDimages(imgRe);
			buBasicdata.setModifydate(new Date());
			buBasicdataService.updateSelective(buBasicdata);
		} else {
			com = CompetenceManager.getCom(roleid, "buBasicdata?type=" + type);
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
				buBasicdata.setDimages(imges);
				for(int i=0;i<re.size();i++){
				//压缩图片
				ImageCompression mypic = new ImageCompression();
				String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
				mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 700, 500, true);
				mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 400, 350, true);
				}
			}
			buBasicdata.setFuid(UUIDCreater.getUUID());
			buBasicdata.setDeletemark(0);
			buBasicdata.setCreatedate(new Date());
			buBasicdata.setType(type);
			buBasicdata.setModifydate(new Date());
			buBasicdataService.save(buBasicdata);

		}

		return "show";
	}

	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show() {
		buBasicdata = buBasicdataService.findById(id);
		return "buBasicdataEditor";
	}

	/**
	 * @see 删除(逻辑删除)
	 * @author xiao
	 */
	public String delete() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		com = CompetenceManager.getCom(roleid, "buBasicdata?type=" + type);
		if (!com.getHisDelete()) {
			return "error";
		}
		if (uid != null && uid.length() > 0) {
			String ids[] = uid.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					buBasicdata = buBasicdataService.findById(ids[a]);
					buBasicdata.setDeletemark(1);
					buBasicdataService.updateSelective(buBasicdata);
				}
			}
		} else {
			if (id != null && id.length() > 0) {
				buBasicdata = buBasicdataService.findById(id);
				buBasicdata.setDeletemark(1);
				buBasicdataService.updateSelective(buBasicdata);
			}
		}
		return "show";
	}

	public BuBasicdataService getBuBasicdataService() {
		return buBasicdataService;
	}

	public void setBuBasicdataService(BuBasicdataService buBasicdataService) {
		this.buBasicdataService = buBasicdataService;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<BuBasicdata> getBuBasicdata_list() {
		return buBasicdata_list;
	}

	public void setBuBasicdata_list(List<BuBasicdata> buBasicdata_list) {
		this.buBasicdata_list = buBasicdata_list;
	}

	public BuBasicdata getBuBasicdata() {
		return buBasicdata;
	}

	public void setBuBasicdata(BuBasicdata buBasicdata) {
		this.buBasicdata = buBasicdata;
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