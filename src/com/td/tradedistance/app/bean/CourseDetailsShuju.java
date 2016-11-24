package com.td.tradedistance.app.bean;

import java.io.Serializable;

public class CourseDetailsShuju implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String XuHao;
	public String getXuHao() {
		return XuHao;
	}

	public void setXuHao(String xuHao) {
		XuHao = xuHao;
	}
    private String url;
	public String getUrl() {
		return url;
	}
    
	public void setUrl(String url) {
		this.url = url;
	}
	private String ZhangJieMingCheng;
    private String Zhang;
    private String Jie;
	@SuppressWarnings("unused")
	private String TotalSize;
    @SuppressWarnings("unused")
	private String GuangKanBaiFenBi;
    private boolean isPlay;
    private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getPlay() {
		return isPlay;
	}

	public void setPlay(boolean isPlay) {
		this.isPlay = isPlay;
	}

	public String getTotalSize() {
		return TotalSize;
	}

	public void setTotalSize(String totalSize) {
		TotalSize = totalSize;
	}

	public String getGuangKanBaiFenBi() {
		return GuangKanBaiFenBi;
	}

	public void setGuangKanBaiFenBi(String guangKanBaiFenBi) {
		GuangKanBaiFenBi = guangKanBaiFenBi;
	}

	public String getZhang() {
		return Zhang;
	}

	public void setZhang(String zhang) {
		Zhang = zhang;
	}

	public String getJie() {
		return Jie;
	}

	public void setJie(String jie) {
		Jie = jie;
	}

	private String ShiPinDiZhi;

	private int ShiPinShiJian;

	private String BeiZhu;

	private String KeChengDaiMa;

	private String KeChengMuLuJiaoYanZhi;

	public void setZhangJieMingCheng(String ZhangJieMingCheng) {
		this.ZhangJieMingCheng = ZhangJieMingCheng;
	}

	public String getZhangJieMingCheng() {
		return this.ZhangJieMingCheng;
	}

	public void setShiPinDiZhi(String ShiPinDiZhi) {
		this.ShiPinDiZhi = ShiPinDiZhi;
	}

	public String getShiPinDiZhi() {
		return this.ShiPinDiZhi;
	}

	public void setShiPinShiJian(int ShiPinShiJian) {
		this.ShiPinShiJian = ShiPinShiJian;
	}

	public int getShiPinShiJian() {
		return this.ShiPinShiJian;
	}

	public void setBeiZhu(String BeiZhu) {
		this.BeiZhu = BeiZhu;
	}

	public String getBeiZhu() {
		return this.BeiZhu;
	}

	public void setKeChengDaiMa(String KeChengDaiMa) {
		this.KeChengDaiMa = KeChengDaiMa;
	}

	public String getKeChengDaiMa() {
		return this.KeChengDaiMa;
	}

	public void setKeChengMuLuJiaoYanZhi(String KeChengMuLuJiaoYanZhi) {
		this.KeChengMuLuJiaoYanZhi = KeChengMuLuJiaoYanZhi;
	}

	public String getKeChengMuLuJiaoYanZhi() {
		return this.KeChengMuLuJiaoYanZhi;
	}

}
