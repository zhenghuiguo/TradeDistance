package com.td.tradedistance.app.bean;

public class Login {

	private String YongHuBiaoShi;

	private String ShouQuanMa;

	private int Ret;

	private String ErrInfo;

	public void setYongHuBiaoShi(String YongHuBiaoShi) {
		this.YongHuBiaoShi = YongHuBiaoShi;
	}

	public String getYongHuBiaoShi() {
		return this.YongHuBiaoShi;
	}

	public void setShouQuanMa(String ShouQuanMa) {
		this.ShouQuanMa = ShouQuanMa;
	}

	public String getShouQuanMa() {
		return this.ShouQuanMa;
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