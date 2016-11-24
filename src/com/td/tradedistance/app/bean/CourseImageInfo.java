package com.td.tradedistance.app.bean;

public class CourseImageInfo {
	private String fenlei;
	private String name1;
	private String name2;
	private int type;
	private int resId1;
	public String getName1() {
		return name1;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	private int resId2;
	public int getResId1() {
		return resId1;
	}
	public void setResId1(int resId1) {
		this.resId1 = resId1;
	}
	private int count;
	public String getFenlei() {
		return fenlei;
	}
	public void setFenlei(String fenlei) {
		this.fenlei = fenlei;
	}
	
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	
	public int getResId2() {
		return resId2;
	}
	public void setResId2(int resId2) {
		this.resId2 = resId2;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
