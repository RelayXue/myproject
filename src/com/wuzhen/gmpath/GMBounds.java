package com.wuzhen.gmpath;

public class GMBounds {
	private double top = 0;
	private double bottom = 0;
	private double left = 0;
	private double right = 0;

	public double getTop() {
		return top;
	}

	public void setTop(double top) {
		this.top = top;
	}

	public double getBottom() {
		return bottom;
	}

	public void setBottom(double bottom) {
		this.bottom = bottom;
	}

	public double getLeft() {
		return left;
	}

	public void setLeft(double left) {
		this.left = left;
	}

	public double getRight() {
		return right;
	}

	public void setRight(double right) {
		this.right = right;
	}

	public GMBounds(double top, double left, double bottom, double right) {
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
	}

	public GMBounds() {
	}

	public double getWidth() {
		return this.right - this.left;
	}

	public double getHeight() {
		return this.top - this.bottom;
	}

	/*
	 * 根据点集合所组成面，获取包含面的最小矩形范围
	 */
	public static GMBounds getMaxBounds(GMPointCollection pointS) {
		GMBounds bnd = new GMBounds(Double.MAX_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MAX_VALUE);
		if (pointS != null) {
			for (int u = 0; u < pointS.size(); u++) {
				GMPoint point = pointS.get(u);
				if (point.getX() < bnd.getLeft()) {
					bnd.setLeft(point.getX());
				}
				if (point.getX() > bnd.getRight()) {
					bnd.setRight(point.getX()); 
				}
				if (point.getY() < bnd.getBottom()) {
					bnd.setBottom(point.getY());
				}
				if (point.getY() > bnd.getTop()) {
					bnd.setTop(point.getY());
				}
			}
		}
		return bnd;
	}
	/*
	 * 判断点是否在集合内
	 */
	public boolean isInBounds(GMPoint point) {
		if (point != null) {
			if (point.getX() >= this.getLeft() && point.getX() <= this.getRight() && point.getY() >= this.getBottom() && point.getY() <= this.getTop()) {
				return true;
			}
		}
		return false;
	}
}
