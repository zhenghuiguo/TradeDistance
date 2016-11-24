package com.td.tradedistance.app.bean1;

import java.io.Serializable;
/**
 * <p>功能描述: 章节类</p>
 * @author  <a href="mailto:zhenghuiguo@yahoo.com.cn">郑惠国</a>
 * @version $Revision: 1.1 $
 */
public class ZhangJie implements Serializable{
	/** <p>功能描述:</p>
	 */
	private static final long serialVersionUID = -2773176533459507826L;
    private String ZhangJieID;
    private String ZhangJieMing;
    private String XiaZaiDiZhi;
    private String lmdm;
    private String ZhangJieXuHao;
    private String DaXiao;
    private String type;
    private String isRead;
    private boolean show = false;
    private boolean down = false;
    private String urlstr;
    
	public boolean getDown() {
		return down;
	}
	public void setDown(boolean down) {
		this.down = down;
	}
	public String getUrlStr() {
		return urlstr;
	}
	public void setUtrStr(String urlstr) {
		this.urlstr = urlstr;
	}
    private String KeChengDaiMa;
    private String WenJianGengXinShiJian;
    private String JiaMiBanBenBianHao;
    private String a1;
    private String a2;
    private String a3;
    public String getKeChengDaiMa() {
		return KeChengDaiMa;
	}
	public void setKeChengDaiMa(String keChengDaiMa) {
		KeChengDaiMa = keChengDaiMa;
	}
	public String getWenJianGengXinShiJian() {
		return WenJianGengXinShiJian;
	}
	public void setWenJianGengXinShiJian(String wenJianGengXinShiJian) {
		WenJianGengXinShiJian = wenJianGengXinShiJian;
	}
	public String getA1() {
		return a1;
	}
	public void setA1(String a1) {
		this.a1 = a1;
	}
	public String getA2() {
		return a2;
	}
	public void setA2(String a2) {
		this.a2 = a2;
	}
	public String getA3() {
		return a3;
	}
	public void setA3(String a3) {
		this.a3 = a3;
	}
	
    public String getJiaMiBanBenBianHao() {
		return JiaMiBanBenBianHao;
	}
	public void setJiaMiBanBenBianHao(String jiaMiBanBenBianHao) {
		JiaMiBanBenBianHao = jiaMiBanBenBianHao;
	}

	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	public boolean getShow() {
		return show;
	}
	public void setShow(boolean show) {
		this.show = show;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDaXiao() {
		return DaXiao;
	}
	public void setDaXiao(String daXiao) {
		DaXiao = daXiao;
	}
	public String getZhangJieID() {
		return ZhangJieID;
	}
	public void setZhangJieID(String zhangJieID) {
		ZhangJieID = zhangJieID;
	}
	public String getZhangJieMing() {
		return ZhangJieMing;
	}
	public void setZhangJieMing(String zhangJieMing) {
		ZhangJieMing = zhangJieMing;
	}
	public String getXiaZaiDiZhi() {
		return XiaZaiDiZhi;
	}
	public void setXiaZaiDiZhi(String xiaZaiDiZhi) {
		XiaZaiDiZhi = xiaZaiDiZhi;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getLmdm() {
		return lmdm;
	}
	public void setLmdm(String lmdm) {
		this.lmdm = lmdm;
	}
	public String getZhangJieXuHao() {
		return ZhangJieXuHao;
	}
	public void setZhangJieXuHao(String zhangJieXuHao) {
		ZhangJieXuHao = zhangJieXuHao;
	}
}
