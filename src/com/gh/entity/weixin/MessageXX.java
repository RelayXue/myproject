package com.gh.entity.weixin;

import java.util.List;

public class MessageXX {
	private String touser;
	private String msgtype;
	private WXnews news;
	private media mpnews;
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
	public WXnews getNews() {
		return news;
	}
	public void setNews(WXnews news) {
		this.news = news;
	}
	public media getMpnews() {
		return mpnews;
	}
	public void setMpnews(media mpnews) {
		this.mpnews = mpnews;
	}
}
