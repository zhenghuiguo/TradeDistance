package com.td.tradedistance.app.bean;

public enum KeChengShuXing {
	BiXiuKe(1,"必修课"), XuanXiuKe(2,"绿色"), KaoChaKe(3,"考查课");
	private String name;
	private int index;

	private KeChengShuXing(int index,String name){
		this.index = index;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
