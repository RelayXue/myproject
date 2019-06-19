package com.gh.entity;

import java.util.List;

public class Load {
	private String xy;
	private String length;
	private List<String> roadXy;
	private List<String> roadText;
	private String start_name;	//起点名称
	private String end_name;	//目的地名称
	private String start;	//起点经纬度 用,隔开
	private String end;	//目的地经纬度 用,隔开
	private String time;
	
	public String getXy() {
		return xy;
	}
	public void setXy(String xy) {
		this.xy = xy;
	}
	public List<String> getRoadXy() {
		return roadXy;
	}
	public void setRoadXy(List<String> roadXy) {
		this.roadXy = roadXy;
	}
	public List<String> getRoadText() {
		return roadText;
	}
	public void setRoadText(List<String> roadText) {
		this.roadText = roadText;
	}
	public String getLength() {
		return length;
	}
	public void setLength(String length) {
		this.length = length;
	}
	public String getStart_name() {
		return start_name;
	}
	public void setStart_name(String start_name) {
		this.start_name = start_name;
	}
	public String getEnd_name() {
		return end_name;
	}
	public void setEnd_name(String end_name) {
		this.end_name = end_name;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
}
