package com.gh.entity;

import java.util.ArrayList;
import java.util.List;

public class EasyUiTreeDataModel {

	private String id;
	private String text;
	private String iconCls;
	private List<EasyUiTreeDataModel> children = new ArrayList<EasyUiTreeDataModel>();
	private boolean checked = false;
	private String state = "open";
	private Object attributes =  new Object();

	 private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public List<EasyUiTreeDataModel> getChildren() {
		return children;
	}

	public void setChildren(List<EasyUiTreeDataModel> children) {
		this.children = children;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}


	 
}
