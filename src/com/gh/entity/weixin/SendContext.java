package com.gh.entity.weixin;


public class SendContext {

	private String touser;
	private String msgtype;
	private content text;
	private String image;
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public content getText() {
		return text;
	}
	public void setText(content text) {
		this.text = text;
	}
	 
}
