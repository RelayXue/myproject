package com.wuzhen.gmpath;

public class GMAreaModel {
	private GMBounds bound = new GMBounds();
	private int index = 0;

	public GMBounds getBound() {
		return bound;
	}

	public void setBound(GMBounds bound) {
		this.bound = bound;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
