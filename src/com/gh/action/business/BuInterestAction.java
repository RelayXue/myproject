package com.gh.action.business;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import com.gh.action.system.CompetenceManager;
import com.gh.base.Action;
import com.gh.common.ImageCompression;
import com.gh.common.UUIDCreater;
import com.gh.common.Upload;
import com.gh.entity.BuAttractions;
import com.gh.entity.BuInterest;
import com.gh.entity.BuStreetlights;
import com.gh.entity.ObVideo;
import com.gh.service.BuAttractionsService;
import com.gh.service.BuInterestService;
import com.gh.service.BuStreetlightsService;
import com.gh.service.ObVideoService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */

public class BuInterestAction extends Action {

	private BuAttractionsService buAttractionsService;
	private ObVideoService obVideoService;
	private BuStreetlightsService buStreetlightsService;
	private BuInterestService buInterestService;
	private String uid;
	private String type;
	private String id;
	private String skey;
	private List<BuAttractions> buAttractions_list;
	private List<ObVideo> obVideo_list;
	private ServletContext context;
	private List<BuInterest> buInterest_list;
	private List<BuStreetlights> buStreetlights_list;
	private BuInterest buInterest;
	private File upload;
	private String uploadFileName;

	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		com = CompetenceManager.getCom(roleid, "buInterest?type=" + type);
		// --------------------操作权限控制
		if (!com.getHisSelect()) {
			return "error";
		}
		String SqlWhere = " ftype=" + type;
		if (skey != null && skey.length() > 0) {
			SqlWhere += "and  fullname like '%" + skey + "%'";
		}
		// --------------------
		pageSize = 10;
		indexPage = indexPage == 0 ? 1 : indexPage;
		totalPage = buInterestService.getRecordCount(SqlWhere);
		buInterest_list = buInterestService.selectByPage(indexPage, pageSize, SqlWhere, "MODIFYDATE desc");
		return "buInterest";
	}

	/**
	 * @see 设备设施统计
	 * @author xiao
	 */
	public String InterestCount() {
		// 设施
		buInterest_list = buInterestService.execSqlCount("select count(*) as count,ftype from bu_Interest group by ftype");
		// 景点
		buInterest = new BuInterest();
		int aa = buAttractionsService.getRecordCount("");
		buInterest.setAddress("9");
		buInterest.setFullname("景点");
		buInterest.setCount(aa + "");
		buInterest_list.add(buInterest);
		// 路灯
		int p = buStreetlightsService.getRecordCount("");
		buInterest = new BuInterest();
		buInterest.setAddress("9");
		buInterest.setFullname("路灯");
		buInterest.setCount(p + "");
		buInterest_list.add(buInterest);
		// 视频
		int v = obVideoService.getRecordCount("");
		buInterest = new BuInterest();
		buInterest.setAddress("9");
		buInterest.setFullname("视频");
		buInterest.setCount(v + "");
		buInterest_list.add(buInterest);
		return "BuInterestCount";
	}
	
	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException 
	 */
	public String edit() throws IOException {
		String username = (String) request.getSession().getAttribute("username");
		String id = buInterest.getFuid();
		String roleid = (String) request.getSession().getAttribute("roleid");
		String address = Upload.upload(upload, uploadFileName, "/upload",
				context);
		if (address != null && address.length() > 0) {
			// 压缩图片
			ImageCompression mypic = new ImageCompression();
			String targetDirectory = context.getRealPath("upload");
			String imagename = address.split("/")[2];
			mypic.compressPic(targetDirectory + "/", targetDirectory + "/", imagename, "500-"+imagename, 500, 500, true);
			mypic.compressPic(targetDirectory + "/", targetDirectory + "/", imagename, "1500-"+imagename, 1500, 1500, true);
		}
		buInterest.setImagesurl(address.split("/")[2]);
		if (id != null && id.length() > 0) {
			com = CompetenceManager.getCom(roleid, "buInterest?type=" + type);
			if (!com.getHisUpdate()) {
				return "error";
			}
			buInterest.setModifydate(new Date());
			buInterest.setModifyuserrealname(username);
			buInterestService.updateSelective(buInterest);
		} else {
			com = CompetenceManager.getCom(roleid, "buInterest?type=" + type);
			if (!com.getHisAdd()) {
				return "error";
			}
			buInterest.setFuid(UUIDCreater.getUUID());
			buInterest.setCreatedate(new Date());
			buInterest.setFtype(type);
			buInterest.setCreateuserrealname(username);
			buInterest.setModifydate(new Date());
			buInterest.setModifyuserrealname(username);
			buInterestService.save(buInterest);
		}
		return "show";
	}
	/**
	 * @see 景点地图页显示
	 * @author xiao
	 */
	public String showInter(){
		String SqlWhere="1=1";
		if(skey!=null&&skey.length()>0){
			SqlWhere+=" and ftype = '"+skey+"'";
		}else{
			SqlWhere+=" and ftype = '002001'";
		}
		buInterest_list=buInterestService.execSql("select * from bu_Interest where "+SqlWhere+" order by fullname");
		return "map_Other";
	}
	/**
	 * @see 显示详细页或修改页
	 * @author xiao
	 */
	public String update_show() {
		buInterest = buInterestService.findById(id);
		return "buInterestEditor";
	}

	/**
	 * @see 删除单条数据或多条
	 * @author xiao
	 */
	public String delete() {
		String roleid = (String) request.getSession().getAttribute("roleid");
		com = CompetenceManager.getCom(roleid, "buInterest?type=" + type);
		if (!com.getHisDelete()) {
			return "error";
		}
		if (id != null && id.length() > 0) {
			String ids[] = id.split(",");
			if (ids != null && ids.length > 0) {
				for (int a = 0; a < ids.length; a++) {
					buInterestService.delete(ids[a]);
				}
			} else {
				buInterestService.delete(id);
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

	public BuInterestService getBuInterestService() {
		return buInterestService;
	}

	public void setBuInterestService(BuInterestService buInterestService) {
		this.buInterestService = buInterestService;
	}

	public List<BuInterest> getBuInterest_list() {
		return buInterest_list;
	}

	public void setBuInterest_list(List<BuInterest> buInterest_list) {
		this.buInterest_list = buInterest_list;
	}

	public BuInterest getBuInterest() {
		return buInterest;
	}

	public void setBuInterest(BuInterest buInterest) {
		this.buInterest = buInterest;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BuAttractionsService getBuAttractionsService() {
		return buAttractionsService;
	}

	public void setBuAttractionsService(BuAttractionsService buAttractionsService) {
		this.buAttractionsService = buAttractionsService;
	}

	public List<BuAttractions> getBuAttractions_list() {
		return buAttractions_list;
	}

	public void setBuAttractions_list(List<BuAttractions> buAttractions_list) {
		this.buAttractions_list = buAttractions_list;
	}

	public ObVideoService getObVideoService() {
		return obVideoService;
	}

	public void setObVideoService(ObVideoService obVideoService) {
		this.obVideoService = obVideoService;
	}

	public BuStreetlightsService getBuStreetlightsService() {
		return buStreetlightsService;
	}

	public void setBuStreetlightsService(BuStreetlightsService buStreetlightsService) {
		this.buStreetlightsService = buStreetlightsService;
	}

	public List<ObVideo> getObVideo_list() {
		return obVideo_list;
	}

	public void setObVideo_list(List<ObVideo> obVideo_list) {
		this.obVideo_list = obVideo_list;
	}

	public List<BuStreetlights> getBuStreetlights_list() {
		return buStreetlights_list;
	}

	public void setBuStreetlights_list(List<BuStreetlights> buStreetlights_list) {
		this.buStreetlights_list = buStreetlights_list;
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
}
