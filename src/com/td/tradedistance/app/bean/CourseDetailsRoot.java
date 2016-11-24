package com.td.tradedistance.app.bean;

import java.util.List;

public class CourseDetailsRoot {
	private String JiaoYanZhi;

	private String ShouQuanMa;

	private String KeChengMingCheng;

	private String KeChengJiaoShi;

	private String KeChengZhaoPian;

	private String JiaoShiZhaoPian;

	private String KeChengJieShao;

	private String KeChengZhuangTai;

	private List<CourseDetailsShuju> ShuJu;

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

	public void setKeChengMingCheng(String KeChengMingCheng) {
		this.KeChengMingCheng = KeChengMingCheng;
	}

	public String getKeChengMingCheng() {
		return this.KeChengMingCheng;
	}

	public void setKeChengJiaoShi(String KeChengJiaoShi) {
		this.KeChengJiaoShi = KeChengJiaoShi;
	}

	public String getKeChengJiaoShi() {
		return this.KeChengJiaoShi;
	}

	public void setKeChengZhaoPian(String KeChengZhaoPian) {
		this.KeChengZhaoPian = KeChengZhaoPian;
	}

	public String getKeChengZhaoPian() {
		return this.KeChengZhaoPian;
	}

	public void setJiaoShiZhaoPian(String JiaoShiZhaoPian) {
		this.JiaoShiZhaoPian = JiaoShiZhaoPian;
	}

	public String getJiaoShiZhaoPian() {
		return this.JiaoShiZhaoPian;
	}

	public void setKeChengJieShao(String KeChengJieShao) {
		this.KeChengJieShao = KeChengJieShao;
	}

	public String getKeChengJieShao() {
		return this.KeChengJieShao;
	}

	public void setKeChengZhuangTai(String KeChengZhuangTai) {
		this.KeChengZhuangTai = KeChengZhuangTai;
	}

	public String getKeChengZhuangTai() {
		return this.KeChengZhuangTai;
	}

	public void setShuJu(List<CourseDetailsShuju> ShuJu) {
		this.ShuJu = ShuJu;
	}

	public List<CourseDetailsShuju> getShuJu() {
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
