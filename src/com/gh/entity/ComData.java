package com.gh.entity;

import java.util.HashMap;
import java.util.Map;

public class ComData {
	public  Boolean HisUpdate=false;
	public  Boolean HisDelete=false;
	public  Boolean HisAdd=false;
	public  Boolean HisSelect=false;
	public Map<String,Boolean> HisOther =new HashMap<String, Boolean>();
	public Boolean getHisUpdate() {
		return HisUpdate;
	}
	public void setHisUpdate(Boolean hisUpdate) {
		HisUpdate = hisUpdate;
	}
	public Boolean getHisDelete() {
		return HisDelete;
	}
	public void setHisDelete(Boolean hisDelete) {
		HisDelete = hisDelete;
	}
	public Boolean getHisAdd() {
		return HisAdd;
	}
	public void setHisAdd(Boolean hisAdd) {
		HisAdd = hisAdd;
	}
	public Boolean getHisSelect() {
		return HisSelect;
	}
	public void setHisSelect(Boolean hisSelect) {
		HisSelect = hisSelect;
	}
	public Map<String, Boolean> getHisOther() {
		return HisOther;
	}
	public void setHisOther(Map<String, Boolean> hisOther) {
		HisOther = hisOther;
	}
	
}
