package com.gh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseMenu {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column base_menu.FUID
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	private String fuid;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column base_menu.MENU_NAME
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	private String menuName;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column base_menu.MENU_PARENTID
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	private String menuParentid;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column base_menu.MENU_URL
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	private String menuUrl;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column base_menu.MENU_ORDER
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	private Double menuOrder;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column base_menu.CREATEDATE
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	private Date createdate;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column base_menu.MODIFYDATE
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	private Date modifydate;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column base_menu.CREATEUSERREALNAME
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	private String createuserrealname;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column base_menu.CREATEUSERID
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	private String createuserid;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column base_menu.MODIFYUSERREALNAME
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	private String modifyuserrealname;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column base_menu.MODIFYUSERID
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	private String modifyuserid;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column base_menu.images
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	private String images;

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column base_menu.FUID
	 * @return  the value of base_menu.FUID
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public String getFuid() {
		return fuid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column base_menu.FUID
	 * @param fuid  the value for base_menu.FUID
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public void setFuid(String fuid) {
		this.fuid = fuid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column base_menu.MENU_NAME
	 * @return  the value of base_menu.MENU_NAME
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column base_menu.MENU_NAME
	 * @param menuName  the value for base_menu.MENU_NAME
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column base_menu.MENU_PARENTID
	 * @return  the value of base_menu.MENU_PARENTID
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public String getMenuParentid() {
		return menuParentid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column base_menu.MENU_PARENTID
	 * @param menuParentid  the value for base_menu.MENU_PARENTID
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public void setMenuParentid(String menuParentid) {
		this.menuParentid = menuParentid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column base_menu.MENU_URL
	 * @return  the value of base_menu.MENU_URL
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public String getMenuUrl() {
		return menuUrl;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column base_menu.MENU_URL
	 * @param menuUrl  the value for base_menu.MENU_URL
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column base_menu.MENU_ORDER
	 * @return  the value of base_menu.MENU_ORDER
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public Double getMenuOrder() {
		return menuOrder;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column base_menu.MENU_ORDER
	 * @param menuOrder  the value for base_menu.MENU_ORDER
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public void setMenuOrder(Double menuOrder) {
		this.menuOrder = menuOrder;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column base_menu.CREATEDATE
	 * @return  the value of base_menu.CREATEDATE
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public Date getCreatedate() {
		return createdate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column base_menu.CREATEDATE
	 * @param createdate  the value for base_menu.CREATEDATE
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column base_menu.MODIFYDATE
	 * @return  the value of base_menu.MODIFYDATE
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public Date getModifydate() {
		return modifydate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column base_menu.MODIFYDATE
	 * @param modifydate  the value for base_menu.MODIFYDATE
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column base_menu.CREATEUSERREALNAME
	 * @return  the value of base_menu.CREATEUSERREALNAME
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public String getCreateuserrealname() {
		return createuserrealname;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column base_menu.CREATEUSERREALNAME
	 * @param createuserrealname  the value for base_menu.CREATEUSERREALNAME
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public void setCreateuserrealname(String createuserrealname) {
		this.createuserrealname = createuserrealname;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column base_menu.CREATEUSERID
	 * @return  the value of base_menu.CREATEUSERID
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public String getCreateuserid() {
		return createuserid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column base_menu.CREATEUSERID
	 * @param createuserid  the value for base_menu.CREATEUSERID
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public void setCreateuserid(String createuserid) {
		this.createuserid = createuserid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column base_menu.MODIFYUSERREALNAME
	 * @return  the value of base_menu.MODIFYUSERREALNAME
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public String getModifyuserrealname() {
		return modifyuserrealname;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column base_menu.MODIFYUSERREALNAME
	 * @param modifyuserrealname  the value for base_menu.MODIFYUSERREALNAME
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public void setModifyuserrealname(String modifyuserrealname) {
		this.modifyuserrealname = modifyuserrealname;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column base_menu.MODIFYUSERID
	 * @return  the value of base_menu.MODIFYUSERID
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public String getModifyuserid() {
		return modifyuserid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column base_menu.MODIFYUSERID
	 * @param modifyuserid  the value for base_menu.MODIFYUSERID
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public void setModifyuserid(String modifyuserid) {
		this.modifyuserid = modifyuserid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column base_menu.images
	 * @return  the value of base_menu.images
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public String getImages() {
		return images;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column base_menu.images
	 * @param images  the value for base_menu.images
	 * @abatorgenerated  Sun Apr 13 14:17:11 CST 2014
	 */
	public void setImages(String images) {
		this.images = images;
	}

	//---------tree
	private String id;
	private String text;
	private Boolean checked;
	private Boolean hasChild;
	private String state;
	//--------------
	
	private List<BaseOperating> Operating_list;
	
	private List<Object> children=new ArrayList<Object>();

	public List<Object> getChildren() {
		return children;
	}

	public void setChildren(List<Object> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Boolean getHasChild() {
		return hasChild;
	}

	public void setHasChild(Boolean hasChild) {
		this.hasChild = hasChild;
	}

	public List<BaseOperating> getOperating_list() {
		return Operating_list;
	}

	public void setOperating_list(List<BaseOperating> operating_list) {
		Operating_list = operating_list;
	}

}