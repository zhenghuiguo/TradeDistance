package com.td.tradedistance.app.bean1;

import java.io.Serializable;
/**
 * <p>功能描述: 课程类</p>
 * @author  <a href="mailto:zhenghuiguo@yahoo.com.cn">郑惠国</a>
 * @version $Revision: 1.1 $
 */
public class KeCheng implements Serializable{
	/** <p>功能描述:</p>
	 */
	private static final long serialVersionUID = -6172279947184301549L;
    private String KeChengDaiMa;
    private String KeChengMing;
    private String ZiLanMuID;
    private String IsTuiJian;
    private String TuPian;
    private String XiaZaiDiZhi;
    private String JieShao;
    private String ZhanShiTuPian;
    private int ZhangJieShu;
    private String type;
    private String lmdm;
    private int YiXiaZaiShu;
	public int getYiXiaZaiShu() {
		return YiXiaZaiShu;
	}
	public void setYiXiaZaiShu(int yiXiaZaiShu) {
		YiXiaZaiShu = yiXiaZaiShu;
	}
	public String getLmdm() {
		return lmdm;
	}
	public void setLmdm(String lmdm) {
		this.lmdm = lmdm;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getZhangJieShu() {
		return ZhangJieShu;
	}
	public void setZhangJieShu(int zhangJieShu) {
		ZhangJieShu = zhangJieShu;
	}
	public String getZhanShiTuPian() {
		return ZhanShiTuPian;
	}
	public void setZhanShiTuPian(String zhangShiTuPian) {
		ZhanShiTuPian = zhangShiTuPian;
	}
	public String getKeChengDaiMa() {
		return KeChengDaiMa;
	}
	public void setKeChengDaiMa(String keChengDaiMa) {
		KeChengDaiMa = keChengDaiMa;
	}
	public String getKeChengMing() {
		return KeChengMing;
	}
	public void setKeChengMing(String keChengMing) {
		KeChengMing = keChengMing;
	}
	public String getZiLanMuID() {
		return ZiLanMuID;
	}
	public void setZiLanMuID(String ziLanMuID) {
		ZiLanMuID = ziLanMuID;
	}
	public String getIsTuiJian() {
		return IsTuiJian;
	}
	public void setIsTuiJian(String isTuiJian) {
		IsTuiJian = isTuiJian;
	}
	public String getTuPian() {
		return TuPian;
	}
	public void setTuPian(String tuPian) {
		TuPian = tuPian;
	}
	public String getXiaZaiDiZhi() {
		return XiaZaiDiZhi;
	}
	public void setXiaZaiDiZhi(String xiaZaiDiZhi) {
		XiaZaiDiZhi = xiaZaiDiZhi;
	}
	public String getJieShao() {
		return JieShao;
	}
	public void setJieShao(String jieShao) {
		JieShao = jieShao;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
