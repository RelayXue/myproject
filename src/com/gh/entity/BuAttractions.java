package com.gh.entity;

import java.util.ArrayList;
import java.util.List;


public class BuAttractions  {
	 
	
		private java.lang.String fuid;
		private java.lang.String fullname;
		private java.lang.String dimages;
		private java.lang.String introduction;
		private java.lang.String fx;
		private java.lang.String fy;
		private java.lang.String address;
		private java.util.Date createdate;
		private java.util.Date modifydate;
		private java.lang.String scenicid;
		private java.lang.Integer iszy;
		private java.lang.Integer praise;
		private java.lang.Integer browse;
		private java.lang.String type;
		private java.lang.Integer comment;
		private java.lang.Integer deletemark;
		private Double star;
		private List<String> images = new ArrayList<String>();
		
		private Integer sort;
		

		public void setFuid(java.lang.String value) {
			this.fuid = value;
		}
		
		public java.lang.String getFuid() {
			return this.fuid;
		}
		public void setFullname(java.lang.String value) {
			this.fullname = value;
		}
		
		public java.lang.String getFullname() {
			return this.fullname;
		}
		public void setDimages(java.lang.String value) {
			this.dimages = value;
		}
		
		public java.lang.String getDimages() {
			return this.dimages;
		}
		public void setIntroduction(java.lang.String value) {
			this.introduction = value;
		}
		
		public java.lang.String getIntroduction() {
			return this.introduction;
		}
		public void setFx(java.lang.String value) {
			this.fx = value;
		}
		
		public java.lang.String getFx() {
			return this.fx;
		}
		public void setFy(java.lang.String value) {
			this.fy = value;
		}
		
		public java.lang.String getFy() {
			return this.fy;
		}
		public void setAddress(java.lang.String value) {
			this.address = value;
		}
		
		public java.lang.String getAddress() {
			return this.address;
		}
		public void setCreatedate(java.util.Date value) {
			this.createdate = value;
		}
		
		public java.util.Date getCreatedate() {
			return this.createdate;
		}
		public void setModifydate(java.util.Date value) {
			this.modifydate = value;
		}
		
		public java.util.Date getModifydate() {
			return this.modifydate;
		}
		public void setScenicid(java.lang.String value) {
			this.scenicid = value;
		}
		
		public java.lang.String getScenicid() {
			return this.scenicid;
		}
		public void setPraise(java.lang.Integer value) {
			this.praise = value;
		}
		
		public java.lang.Integer getPraise() {
			return this.praise;
		}
		public void setBrowse(java.lang.Integer value) {
			this.browse = value;
		}
		
		public java.lang.Integer getBrowse() {
			return this.browse;
		}
		public void setType(java.lang.String value) {
			this.type = value;
		}
		
		public java.lang.String getType() {
			return this.type;
		}
		public void setDeletemark(java.lang.Integer value) {
			this.deletemark = value;
		}
		
		public java.lang.Integer getDeletemark() {
			return this.deletemark;
		}

		public List<String> getImages() {
			return images;
		}

		public void setImages(List<String> images) {
			this.images = images;
		}

		public java.lang.Integer getComment() {
			return comment;
		}

		public void setComment(java.lang.Integer comment) {
			this.comment = comment;
		}

		public Double getStar() {
			return star;
		}

		public void setStar(Double star) {
			this.star = star;
		}

		public java.lang.Integer getIszy() {
			return iszy;
		}

		public void setIszy(java.lang.Integer iszy) {
			this.iszy = iszy;
		}

		public Integer getSort() {
			return sort;
		}

		public void setSort(Integer sort) {
			this.sort = sort;
		}
	

}	 

