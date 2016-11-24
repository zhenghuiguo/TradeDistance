package com.td.tradedistance.app.bean;

import java.util.List;

public class GuangGaoWeiRoot {
	private String JiaoYanZhi;

	private String ShouQuanMa;

	private List<GuangGaoWeiShuJu> ShuJu;

	private int Ret;

	private String ErrInfo;

	public void setJiaoYanZhi(String JiaoYanZhi) {
		this.JiaoYanZhi = JiaoYanZhi;
	}

	public String getJiaoYanZhi() {
		return this.JiaoYanZhi;
	}

	public void setShouQuanMa(String ShouQuanMa) {
		this.ShouQuanMa = ShouQuanMa;
	}

	public String getShouQuanMa() {
		return this.ShouQuanMa;
	}

	public void setShuJu(List<GuangGaoWeiShuJu> ShuJu) {
		this.ShuJu = ShuJu;
	}

	public List<GuangGaoWeiShuJu> getShuJu() {
		return this.ShuJu;
	}

	public void setRet(int Ret) {
		this.Ret = Ret;
	}

	public int getRet() {
		return this.Ret;
	}

	public void setErrInfo(String ErrInfo) {
		this.ErrInfo = ErrInfo;
	}

	public String getErrInfo() {
		return this.ErrInfo;
	}
}
