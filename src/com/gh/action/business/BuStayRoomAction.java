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
 * 住宿房间BU_STAY_ROOM
 * 
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

public class BuStayRoomAction extends Action {

	private BuStayRoomService buStayRoomService;
	private String uid;
	private String id;
	private String skey;
	private List<BuStayRoom> buStayRoom_list;
	private BuStayRoom buStayRoom;
	private String stayid;

	private ServletContext context;
	private File uploadpic;
	private String uploadpicFileName;

	// ---图片上传---
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
		com = CompetenceManager.getCom(roleid, "buStay");
		// --------------------操作权限控制
		if (!com.getHisSelect()) {
			return "error";
		}
		String SqlWhere = " stayid='" + stayid + "'";
		if (skey != null && skey.length() > 0) {
			SqlWhere += " and fullname like '%" + skey + "%'";
		}
		// --------------------
		pageSize = 10;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buStayRoomService.getRecordCount(SqlWhere);
		buStayRoom_list = buStayRoomService.selectByPage(indexPage, pageSize,
				SqlWhere, "MODIFYDATE desc");
		return "buStayRoom";
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
		String id = buStayRoom.getFuid();
		String roleid = (String) request.getSession().getAttribute("roleid");
		if (id != null && id.length() > 0) {
			com = CompetenceManager.getCom(roleid, "buStay");
			if (!com.getHisUpdate()) {
				return "error";
			}
			// ------------图片修改
			BuStayRoom buStayRoom1 = buStayRoomService.findById(id);
			String dimg = buStayRoom1.getDimages();
			System.out.println(imgNum);
			boolean flag=false;
			String imgRe = Imgedit.modify(reNum, imgNum, dimg, img, imgFileName,flag);
			if (imgRe.length() > 0) {
				buStayRoom.setDimages(imgRe);
			} else {
				buStayRoom.setDimages("default.jpg");
			}
//			buStayRoom.setDimages(imgRe);

			buStayRoom.setModifydate(new Date());
			buStayRoomService.updateSelective(buStayRoom);

		} else {
			com = CompetenceManager.getCom(roleid, "buStay");
			if (!com.getHisAdd()) {
				return "error";
			}
			// 新建时多图上传
			FileUpload fileupload = new FileUpload();
			ArrayList<String> re = fileupload.inFile(img, "/upload",
					imgFileName);
			if (re != null && re.size() > 0) {
				String imges = "";
				for (int a = 0; a < re.size(); a++) {
					imges += re.get(a) + ",";
				}
				imges = imges.length() > 0 ? imges.substring(0,
						imges.length() - 1) : "";
				System.out.println("--------" + imges);
				buStayRoom.setDimages(imges);
				for(int i=0;i<re.size();i++){
					//压缩图片
					ImageCompression mypic = new ImageCompression();
					String targetDirectory = ServletActionContext.getServletContext().getRealPath("upload");
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "B_"+re.get(i), 900, 700, true);
					mypic.compressPic(targetDirectory + "/", targetDirectory + "/", re.get(i), "L_"+re.get(i), 300, 280, true);
					}
				
			}else{
				buStayRoom.setDimages("default.jpg");
			}
			buStayRoom.setFuid(UUIDCreater.getUUID());
			buStayRoom.setStayid(stayid);
			buStayRoom.setCreatedate(new Date());
			buStayRoom.setModifydate(new Date());
			buStayRoomService.save(buStayRoom);

		}
		return "show";
	}

	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show() {
		buStayRoom = buStayRoomService.findById(id);
		return "buStayRoomEditor";
	}

	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		com = CompetenceManager.getCom(roleid, "buStay");
		if (!com.getHisDelete()) {
			return "error";
		}
		if (uid != null && uid.length() > 0) {
			String ids[] = uid.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					buStayRoomService.delete(ids[a]);
				}
			}
		} else {
			if (id != null && id.length() > 0) {
				buStayRoomService.delete(id);
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

	public BuStayRoomService getBuStayRoomService() {
		return buStayRoomService;
	}

	public void setBuStayRoomService(BuStayRoomService buStayRoomService) {
		this.buStayRoomService = buStayRoomService;
	}

	public List<BuStayRoom> getBuStayRoom_list() {
		return buStayRoom_list;
	}

	public void setBuStayRoom_list(List<BuStayRoom> buStayRoom_list) {
		this.buStayRoom_list = buStayRoom_list;
	}

	public BuStayRoom getBuStayRoom() {
		return buStayRoom;
	}

	public void setBuStayRoom(BuStayRoom buStayRoom) {
		this.buStayRoom = buStayRoom;
	}

	public String getStayid() {
		return stayid;
	}

	public void setStayid(String stayid) {
		this.stayid = stayid;
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

}
