package com.td.tradedistance.app.bean1;

import java.io.Serializable;

import android.widget.ImageView;
/**
 * <p>功能描述: 电子资料类</p>
 * @author  <a href="mailto:zhenghuiguo@yahoo.com.cn">郑惠国</a>
 * @version $Revision: 1.1 $
 */
public class DianZiZiLiao implements Serializable{

	/** <p>功能描述:</p>
	 */
	private static final long serialVersionUID = -4830602526199824745L;
	private String XinXiID;
	private String ZiLanMuID;
	private String ZuoZhe;
	private String ChuBanShe;
	private String JianJie;
	private String PingJia;
	private String IsTuiJian;
	private String TuPian;
	private String XiaZaiDiZhi;
	private String FaBuRiQi;
	private String GuoQiRiQi;
	private String ShunXu;
	private String DaXiao;
	private String ZiLiaoMing;
	private boolean isXiaZai;
	private String type;
	private String lmdm;
	private String ZiLianMuMing;
	private String JiaMiBanBenBianHao;
	private String a1;
	private String a2;
	private String a3;
	private String WenJianGengXinShiJian;
	private String urlStr;
	private ImageView img;
	public ImageView getImg() {
		return img;
	}
	public void setImg(ImageView img) {
		this.img = img;
	}
	public String getUrlStr() {
		return urlStr;
	}
	public void setUrlStr(String urlStr) {
		this.urlStr = urlStr;
	}
	public String getWenJianGengXinShiJian() {
		return WenJianGengXinShiJian;
	}
	public void setWenJianGengXinShiJian(String wenJianGengXinShiJian) {
		WenJianGengXinShiJian = wenJianGengXinShiJian;
	}
	public String getJiaMiBanBenBianHao() {
		return JiaMiBanBenBianHao;
	}
	public void setJiaMiBanBenBianHao(String jiaMiBanBenBianHao) {
		JiaMiBanBenBianHao = jiaMiBanBenBianHao;
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
	private int position;
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLmdm() {
		return lmdm;
	}
	public void setLmdm(String lmdm) {
		this.lmdm = lmdm;
	}
	public void setXiaZai(boolean isXiaZai) {
		this.isXiaZai = isXiaZai;
	}
	public boolean getIsXiaZai() {
		return isXiaZai;
	}
	public void setIsXiaZai(boolean isXiaZai) {
		this.isXiaZai = isXiaZai;
	}
	
	public String getZiLiaoMing() {
		return ZiLiaoMing;
	}
	public void setZiLiaoMing(String ziLiaoMing) {
		ZiLiaoMing = ziLiaoMing;
	}
	public String getXinXiID() {
		return XinXiID;
	}
	public void setXinXiID(String xinXiID) {
		XinXiID = xinXiID;
	}
	public String getZiLanMuID() {
		return ZiLanMuID;
	}
	public void setZiLanMuID(String ziLanMuID) {
		ZiLanMuID = ziLanMuID;
	}
	public String getZuoZhe() {
		return ZuoZhe;
	}
	public void setZuoZhe(String zuoZhe) {
		ZuoZhe = zuoZhe;
	}
	public String getChuBanShe() {
		return ChuBanShe;
	}
	public void setChuBanShe(String chuBanShe) {
		ChuBanShe = chuBanShe;
	}
	public String getJianJie() {
		return JianJie;
	}
	public void setJianJie(String jianJie) {
		JianJie = jianJie;
	}
	public String getPingJia() {
		return PingJia;
	}
	public void setPingJia(String pingJia) {
		PingJia = pingJia;
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
	public String getFaBuRiQi() {
		return FaBuRiQi;
	}
	public void setFaBuRiQi(String faBuRiQi) {
		FaBuRiQi = faBuRiQi;
	}
	public String getGuoQiRiQi() {
		return GuoQiRiQi;
	}
	public void setGuoQiRiQi(String guoQiRiQi) {
		GuoQiRiQi = guoQiRiQi;
	}
	public String getShunXu() {
		return ShunXu;
	}
	public void setShunXu(String shunXu) {
		ShunXu = shunXu;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getDaXiao() {
		return DaXiao;
	}
	public void setDaXiao(String daXiao) {
		DaXiao = daXiao;
	}
	public String getZiLianMuMing() {
		return ZiLianMuMing;
	}
	public void setZiLianMuMing(String ziLianMuMing) {
		ZiLianMuMing = ziLianMuMing;
	}
}
