package com.gh.entity.weixin;


public class SendUser {

	private String touser[];
	private SendUserXX mpnews;
	private String msgtype;
	public String[] getTouser() {
		return touser;
	}
	public void setTouser(String[] touser) {
		this.touser = touser;
	}
	public SendUserXX getMpnews() {
		return mpnews;
	}
	public void setMpnews(SendUserXX mpnews) {
		this.mpnews = mpnews;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
}
