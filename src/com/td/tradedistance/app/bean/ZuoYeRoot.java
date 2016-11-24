package com.td.tradedistance.app.bean;

import java.util.List;

public class ZuoYeRoot {
	private String ShouQuanMa;

	private String JiaoYanZhi;

	private List<ZuoYeShuju> ShuJu;

	private int Ret;

	private String ErrInfo;

	public void setShouQuanMa(String ShouQuanMa) {
		this.ShouQuanMa = ShouQuanMa;
	}

	public String getShouQuanMa() {
		return this.ShouQuanMa;
	}

	public void setJiaoYanZhi(String JiaoYanZhi) {
		this.JiaoYanZhi = JiaoYanZhi;
	}

	public String getJiaoYanZhi() {
		return this.JiaoYanZhi;
	}

	public void setShuJu(List<ZuoYeShuju> ShuJu) {
		this.ShuJu = ShuJu;
	}

	public List<ZuoYeShuju> getShuJu() {
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
