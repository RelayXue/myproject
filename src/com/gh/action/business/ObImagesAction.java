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
import com.gh.entity.ObImages;
import com.gh.service.ObImagesService;

/**
 * @author xiaofeng
 * @version 1.0
 * @since 1.0
 */


public class ObImagesAction extends Action {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ObImagesService obImagesService;
	private String otherId;
	private String uid;
	private String tname;
	private String id;
	private List<ObImages> obImages_list;
	private ObImages obImages;
	private ServletContext context;
	private File uploadImg;
	private String uploadImgFileName;
	
	/**
	 * @see 显示列表页
	 * @author xiao
	 */
	public String show(){
		obImages_list=obImagesService.execSql("select * from ob_Images where OTHERID='"+otherId+"'");
		return "obImg";
	}
	/**
	 * @see 修改及新增
	 * @author xiao
	 * @throws IOException 
	 */
	public String edit() throws IOException{
		String address = Upload.upload(uploadImg, uploadImgFileName, "/upload", context);
		if (address != null && address.length() > 0) {
			// 压缩图片
			ImageCompression mypic = new ImageCompression();
			String targetDirectory = context.getRealPath("upload");
			String imagename = address.split("/")[2];
			mypic.compressPic(targetDirectory + "/", targetDirectory + "/", imagename, "500-"+imagename, 500, 500, true);
			mypic.compressPic(targetDirectory + "/", targetDirectory + "/", imagename, "70-"+imagename, 70, 70, true);
			mypic.compressPic(targetDirectory + "/", targetDirectory + "/", imagename, "1500-"+imagename, 1500, 1500, true);
		}
		obImages=new ObImages();
		obImages.setAddress(address.split("/")[2]);
		obImages.setCreatedate(new Date());
		if(otherId!=null&&otherId.length()>0){
			obImages.setOtherid(otherId);
		}else{
			obImages.setOtherid(UUIDCreater.getUUID());
			otherId=obImages.getOtherid();
		}
		obImages.setFuid(UUIDCreater.getUUID());
		obImages.setTablename(tname);
		obImagesService.save(obImages);
		return "show";
	}
	public String delete(){
		obImagesService.delete(id);
		return "show";
	}
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public ObImagesService getObImagesService() {
		return obImagesService;
	}
	public void setObImagesService(ObImagesService obImagesService) {
		this.obImagesService = obImagesService;
	}
	public List<ObImages> getObImages_list() {
		return obImages_list;
	}
	public void setObImages_list(List<ObImages> obImages_list) {
		this.obImages_list = obImages_list;
	}
	public ObImages getObImages() {
		return obImages;
	}
	public void setObImages(ObImages obImages) {
		this.obImages = obImages;
	}
	public ServletContext getContext() {
		return context;
	}
	public void setContext(ServletContext context) {
		this.context = context;
	}
	public File getUploadImg() {
		return uploadImg;
	}
	public void setUploadImg(File uploadImg) {
		this.uploadImg = uploadImg;
	}
	public String getUploadImgFileName() {
		return uploadImgFileName;
	}
	public void setUploadImgFileName(String uploadImgFileName) {
		this.uploadImgFileName = uploadImgFileName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOtherId() {
		return otherId;
	}
	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
}
