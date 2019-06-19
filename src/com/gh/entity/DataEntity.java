package com.gh.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DataEntity 
 * @Description  TODO
 * @author ieastar
 * @date 2014-11-11
 * @version V1.0
 */
public class DataEntity {
	private String fuid;
	
	private String fullname;
	
	private String fx;
	
	private String fy;
	
	private String type;
	
	private String phone;
	
	private String address;
	
	private String dimages;
	
	private String supervisor;
	
	private String supervisorphone;
	
	private String praise;
	
	private String examine;
	
	private String consumption;
	
	private String star;
	
	private String comment;
	
	private String distance;
	
	private List<String> images = new ArrayList<String>();
	

	public String getFuid() {
		return fuid;
	}

	public void setFuid(String fuid) {
		this.fuid = fuid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getFx() {
		return fx;
	}

	public void setFx(String fx) {
		this.fx = fx;
	}

	public String getFy() {
		return fy;
	}

	public void setFy(String fy) {
		this.fy = fy;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDimages() {
		return dimages;
	}

	public void setDimages(String dimages) {
		this.dimages = dimages;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}

	public String getSupervisorphone() {
		return supervisorphone;
	}

	public void setSupervisorphone(String supervisorphone) {
		this.supervisorphone = supervisorphone;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPraise() {
		return praise;
	}

	public void setPraise(String praise) {
		this.praise = praise;
	}

	public String getConsumption() {
		return consumption;
	}

	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getExamine() {
		return examine;
	}

	public void setExamine(String examine) {
		this.examine = examine;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
}
