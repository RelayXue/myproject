package com.gh.entity;

import java.util.ArrayList;
import java.util.List;


public class BuNews  {
	 
	
		private java.lang.String fuid;
		private java.lang.String type;
		private java.lang.String fullname;
		private java.lang.String content;
		private java.util.Date createdate;
		private java.util.Date modifydate;
		private java.lang.String img1;
		private java.lang.String img2;
		private java.lang.Integer praise;
		private java.lang.String fy;
		private java.lang.String fx;
		private java.lang.Integer readnum;
		private java.lang.String img3;
		private java.lang.Integer deletemark;
		private Double star;
		private java.lang.String remark;
		private java.lang.Integer comment;
		private java.lang.Integer sort;
		private List<String> images = new ArrayList<String>();

		public void setFuid(java.lang.String value) {
			this.fuid = value;
		}
		
		public java.lang.String getFuid() {
			return this.fuid;
		}
		public void setType(java.lang.String value) {
			this.type = value;
		}
		
		public java.lang.String getType() {
			return this.type;
		}
		public void setFullname(java.lang.String value) {
			this.fullname = value;
		}
		
		public java.lang.String getFullname() {
			return this.fullname;
		}
		public void setContent(java.lang.String value) {
			this.content = value;
		}
		
		public java.lang.String getContent() {
			return this.content;
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
		public void setImg1(java.lang.String value) {
			this.img1 = value;
		}
		
		public java.lang.String getImg1() {
			return this.img1;
		}
		public void setImg2(java.lang.String value) {
			this.img2 = value;
		}
		
		public java.lang.String getImg2() {
			return this.img2;
		}
		public void setPraise(java.lang.Integer value) {
			this.praise = value;
		}
		
		public java.lang.Integer getPraise() {
			return this.praise;
		}
		public void setReadnum(java.lang.Integer value) {
			this.readnum = value;
		}
		
		public java.lang.Integer getReadnum() {
			return this.readnum;
		}
		public void setImg3(java.lang.String value) {
			this.img3 = value;
		}
		
		public java.lang.String getImg3() {
			return this.img3;
		}
		public void setDeletemark(java.lang.Integer value) {
			this.deletemark = value;
		}
		
		public java.lang.Integer getDeletemark() {
			return this.deletemark;
		}
		public void setRemark(java.lang.String value) {
			this.remark = value;
		}
		
		public java.lang.String getRemark() {
			return this.remark;
		}

		public List<String> getImages() {
			return images;
		}

		public void setImages(List<String> images) {
			this.images = images;
		}

		public java.lang.String getFy() {
			return fy;
		}

		public void setFy(java.lang.String fy) {
			this.fy = fy;
		}

		public java.lang.String getFx() {
			return fx;
		}

		public void setFx(java.lang.String fx) {
			this.fx = fx;
		}

		public java.lang.Integer getComment() {
			return comment;
		}

		public void setComment(java.lang.Integer comment) {
			this.comment = comment;
		}

		public java.lang.Integer getSort() {
			return sort;
		}

		public void setSort(java.lang.Integer sort) {
			this.sort = sort;
		}

		public Double getStar() {
			return star;
		}

		public void setStar(Double star) {
			this.star = star;
		}
	

}	 

