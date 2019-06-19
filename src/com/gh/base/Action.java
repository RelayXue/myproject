package com.gh.base;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.gh.entity.ComData;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 
 * @ClassName Action 
 * @Description 方法跳转控制基类
 * @author oriental_pearl
 * @date 2012-3-23
 */
public class Action extends ActionSupport{

	/**
	 * 微信参数
	 */
	public static final String TOKEN = "safger1234";
	public static final String AppId = "wx598df1869ba5231a";                        //开发者ID
	public static final String AppSecret = "f64a25f9d7abaedd823f5305d3b7fae4 ";
	/**
	 * 公用属性
	 */
	public static final long serialVersionUID = 1L;
	public static final String INPUT = "input";
	public static final String EDIT = "edit";
	public static final String TO_LIST = "toList";
	public static final String SELECT = "select";
	public static final String JSON = "json";
	public static final String INDEX = "index";
	public static final String VIEW = "view";
	public ComData com;
	//分页条数
	public HttpServletRequest request=ServletActionContext.getRequest();
	public HttpServletResponse response=ServletActionContext.getResponse();
	public String pageType;
	protected String json;
	protected int totalPage;
	protected int indexPage;
	protected int pageSize;
    protected File upload;
    protected String uploadFileName;
    
    protected String fileName;
	protected InputStream inputStream;
    
    
	 
    public File[] img; //上传的文件
    public String[] imgFileName; //文件名称
    public String[] imgContentType; //文件类型
	
	public String toJson(Object object){
		Gson gson = new Gson();
		return gson.toJson(object);
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public HttpServletRequest getHttpServletRequest()
	{
		return ServletActionContext.getRequest();
	}

	public HttpSession getHttpSession()
	{
		return getHttpServletRequest().getSession();
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
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
	public int getIndexPage() {
		return indexPage;
	}
	public void setIndexPage(int indexPage) {
		this.indexPage = indexPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public ComData getCom() {
		return com;
	}
	public void setCom(ComData com) {
		this.com = com;
	}
	public File[] getImg() {
		return img;
	}
	public void setImg(File[] img) {
		this.img = img;
	}
	public String[] getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(String[] imgFileName) {
		this.imgFileName = imgFileName;
	}
	public String[] getImgContentType() {
		return imgContentType;
	}
	public void setImgContentType(String[] imgContentType) {
		this.imgContentType = imgContentType;
	}
	public String getPageType() {
		return pageType;
	}
	public void setPageType(String pageType) {
		this.pageType = pageType;
	}
}
