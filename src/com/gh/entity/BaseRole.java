package com.gh.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseRole {

	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column GHPORT.BASE_ROLE.FUID
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	private String fuid;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column GHPORT.BASE_ROLE.SYSTEMID
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	private String systemid;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column GHPORT.BASE_ROLE.ORGANIZEID
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	private String organizeid;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column GHPORT.BASE_ROLE.CODE
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	private String code;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column GHPORT.BASE_ROLE.REALNAME
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	private String realname;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column GHPORT.BASE_ROLE.CATEGORY
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	private String category;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column GHPORT.BASE_ROLE.SORTCODE
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	private int sortcode;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column GHPORT.BASE_ROLE.DELETEMARK
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	private int deletemark;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column GHPORT.BASE_ROLE.ENABLED
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	private int enabled;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column GHPORT.BASE_ROLE.DESCRIPTION
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	private String description;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column GHPORT.BASE_ROLE.CREATEDATE
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	private Date createdate;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column GHPORT.BASE_ROLE.CREATEUSERID
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	private String createuserid;
	
	private String createuserrealname;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column GHPORT.BASE_ROLE.MODIFYDATE
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	private Date modifydate;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column GHPORT.BASE_ROLE.MODIFYUSERID
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	private String modifyuserid;
	/**
	 * This field was generated by Abator for iBATIS. This field corresponds to the database column GHPORT.BASE_ROLE.MODIFYUSERREALNAME
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	private String modifyuserrealname;
	private List<Object> children=new ArrayList<Object>();
	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column GHPORT.BASE_ROLE.FUID
	 * @return  the value of GHPORT.BASE_ROLE.FUID
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public String getFuid() {
		return fuid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column GHPORT.BASE_ROLE.FUID
	 * @param fuid  the value for GHPORT.BASE_ROLE.FUID
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public void setFuid(String fuid) {
		this.fuid = fuid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column GHPORT.BASE_ROLE.SYSTEMID
	 * @return  the value of GHPORT.BASE_ROLE.SYSTEMID
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public String getSystemid() {
		return systemid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column GHPORT.BASE_ROLE.SYSTEMID
	 * @param systemid  the value for GHPORT.BASE_ROLE.SYSTEMID
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public void setSystemid(String systemid) {
		this.systemid = systemid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column GHPORT.BASE_ROLE.ORGANIZEID
	 * @return  the value of GHPORT.BASE_ROLE.ORGANIZEID
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public String getOrganizeid() {
		return organizeid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column GHPORT.BASE_ROLE.ORGANIZEID
	 * @param organizeid  the value for GHPORT.BASE_ROLE.ORGANIZEID
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public void setOrganizeid(String organizeid) {
		this.organizeid = organizeid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column GHPORT.BASE_ROLE.CODE
	 * @return  the value of GHPORT.BASE_ROLE.CODE
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public String getCode() {
		return code;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column GHPORT.BASE_ROLE.CODE
	 * @param code  the value for GHPORT.BASE_ROLE.CODE
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column GHPORT.BASE_ROLE.REALNAME
	 * @return  the value of GHPORT.BASE_ROLE.REALNAME
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public String getRealname() {
		return realname;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column GHPORT.BASE_ROLE.REALNAME
	 * @param realname  the value for GHPORT.BASE_ROLE.REALNAME
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public void setRealname(String realname) {
		this.realname = realname;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column GHPORT.BASE_ROLE.CATEGORY
	 * @return  the value of GHPORT.BASE_ROLE.CATEGORY
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column GHPORT.BASE_ROLE.CATEGORY
	 * @param category  the value for GHPORT.BASE_ROLE.CATEGORY
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public void setCategory(String category) {
		this.category = category;
	}


	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column GHPORT.BASE_ROLE.DESCRIPTION
	 * @return  the value of GHPORT.BASE_ROLE.DESCRIPTION
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column GHPORT.BASE_ROLE.DESCRIPTION
	 * @param description  the value for GHPORT.BASE_ROLE.DESCRIPTION
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column GHPORT.BASE_ROLE.CREATEDATE
	 * @return  the value of GHPORT.BASE_ROLE.CREATEDATE
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public Date getCreatedate() {
		return createdate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column GHPORT.BASE_ROLE.CREATEDATE
	 * @param createdate  the value for GHPORT.BASE_ROLE.CREATEDATE
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column GHPORT.BASE_ROLE.CREATEUSERID
	 * @return  the value of GHPORT.BASE_ROLE.CREATEUSERID
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public String getCreateuserid() {
		return createuserid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column GHPORT.BASE_ROLE.CREATEUSERID
	 * @param createuserid  the value for GHPORT.BASE_ROLE.CREATEUSERID
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public void setCreateuserid(String createuserid) {
		this.createuserid = createuserid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column GHPORT.BASE_ROLE.MODIFYDATE
	 * @return  the value of GHPORT.BASE_ROLE.MODIFYDATE
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public Date getModifydate() {
		return modifydate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column GHPORT.BASE_ROLE.MODIFYDATE
	 * @param modifydate  the value for GHPORT.BASE_ROLE.MODIFYDATE
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column GHPORT.BASE_ROLE.MODIFYUSERID
	 * @return  the value of GHPORT.BASE_ROLE.MODIFYUSERID
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public String getModifyuserid() {
		return modifyuserid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column GHPORT.BASE_ROLE.MODIFYUSERID
	 * @param modifyuserid  the value for GHPORT.BASE_ROLE.MODIFYUSERID
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public void setModifyuserid(String modifyuserid) {
		this.modifyuserid = modifyuserid;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method returns the value of the database column GHPORT.BASE_ROLE.MODIFYUSERREALNAME
	 * @return  the value of GHPORT.BASE_ROLE.MODIFYUSERREALNAME
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public String getModifyuserrealname() {
		return modifyuserrealname;
	}

	/**
	 * This method was generated by Abator for iBATIS. This method sets the value of the database column GHPORT.BASE_ROLE.MODIFYUSERREALNAME
	 * @param modifyuserrealname  the value for GHPORT.BASE_ROLE.MODIFYUSERREALNAME
	 * @abatorgenerated  Fri Mar 22 16:04:43 CST 2013
	 */
	public void setModifyuserrealname(String modifyuserrealname) {
		this.modifyuserrealname = modifyuserrealname;
	}

	public int getSortcode() {
		return sortcode;
	}

	public void setSortcode(int sortcode) {
		this.sortcode = sortcode;
	}

	public int getDeletemark() {
		return deletemark;
	}

	public void setDeletemark(int deletemark) {
		this.deletemark = deletemark;
	}

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public String getCreateuserrealname() {
		return createuserrealname;
	}

	public void setCreateuserrealname(String createuserrealname) {
		this.createuserrealname = createuserrealname;
	}

	public List<Object> getChildren() {
		return children;
	}

	public void setChildren(List<Object> children) {
		this.children = children;
	}
}