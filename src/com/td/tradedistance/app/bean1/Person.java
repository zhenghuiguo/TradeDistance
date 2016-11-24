package com.td.tradedistance.app.bean1;

import java.io.Serializable;

import android.graphics.Bitmap;

/**
 * <p>功能描述: 个人基本信息</p>
 * @author  <a href="mailto:zhenghuiguo@yahoo.com.cn">郑惠国</a>
 * @version $Revision: 1.1 $
 */
public class Person implements Serializable{
	/**
	 * 用户唯一标识
	 */
	private String YongHuBiaoShi;
	/**
	 * 终端推送码
	 */
	private String ZhongDuanTuiSongMa;
	/**
	 * 姓名
	 */
	private String XingMing;
	/**
	 * 昵称
	 */
	private String NiCheng;
	/**
	 * 头像Url地址
	 */
	private String TouXiang;
	/**
	 * 学号
	 */
	private String XueHao;
	/**
	 * 班级 
	 */
	private String BanJi;
	/**
	 * 总积分 
	 */
	private String JiFen;
	/**
	 * 有问有答积分
	 */
	private String YouWenYouDaJiFen;
	/**
	 * msg type
	 */
	private String  msgType;
	private String text;
	private String date;
	private String CurrentYhwybs;
	private String GongSi;
	private String JiGou;
	private String ZhiWei;
	private String DianHua;
	private boolean hqsj = false;
	private String address;
	private String birthday;
	private Bitmap head;
	private int id;
	private int isRead;
	public int getIsRead() {
		return isRead;
	}
	public void setIsRead(int isRead) {
		this.isRead = isRead;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Bitmap getHead() {
		return head;
	}
	public void setHead(Bitmap head) {
		this.head = head;
	}
	private boolean isHideWeiliao = false;
	public boolean isHideWeiliao() {
		return isHideWeiliao;
	}
	public void setHideWeiliao(boolean isHideWeiliao) {
		this.isHideWeiliao = isHideWeiliao;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public boolean isHqsj() {
		return hqsj;
	}
	public void setHqsj(boolean hqsj) {
		this.hqsj = hqsj;
	}
	/**
	 * 提问数
	 */
	private String TiWenShu;
	
	/**
	 * 回答数
	 */
	private String HuiDaShu;
	/**
	 * 顶贴数
	 */
	private String DingTieShu;
	/**
	 * 收藏数
	 */
	private String ShouCangShu;
	
	public String getGongSi() {
		return GongSi;
	}
	public void setGongSi(String gongSi) {
		GongSi = gongSi;
	}
	public String getJiGou() {
		return JiGou;
	}
	public void setJiGou(String jiGou) {
		JiGou = jiGou;
	}
	public String getZhiWei() {
		return ZhiWei;
	}
	public void setZhiWei(String zhiWei) {
		ZhiWei = zhiWei;
	}
	public String getDianHua() {
		return DianHua;
	}
	public void setDianHua(String dianHua) {
		DianHua = dianHua;
	}
	public String getCurrentYhwybs() {
		return CurrentYhwybs;
	}
	public void setCurrentYhwybs(String currentYhwybs) {
		CurrentYhwybs = currentYhwybs;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getJiFen() {
		return JiFen;
	}
	public void setJiFen(String jiFen) {
		JiFen = jiFen;
	}
	public String getYouWenYouDaJiFen() {
		return YouWenYouDaJiFen;
	}
	public void setYouWenYouDaJiFen(String youWenYouDaJiFen) {
		YouWenYouDaJiFen = youWenYouDaJiFen;
	}
	public String getXueHao() {
		return XueHao;
	}
	public void setXueHao(String xueHao) {
		XueHao = xueHao;
	}
	public String getBanJi() {
		return BanJi;
	}
	public void setBanJi(String banJi) {
		BanJi = banJi;
	}
	public String getYongHuBiaoShi() {
		return YongHuBiaoShi;
	}
	public void setYongHuBiaoShi(String yongHuBiaoShi) {
		YongHuBiaoShi = yongHuBiaoShi;
	}
	public String getZhongDuanTuiSongMa() {
		return ZhongDuanTuiSongMa;
	}
	public void setZhongDuanTuiSongMa(String zhongDuanTuiSongMa) {
		ZhongDuanTuiSongMa = zhongDuanTuiSongMa;
	}
	public String getXingMing() {
		return XingMing;
	}
	public void setXingMing(String xingMing) {
		XingMing = xingMing;
	}
	public String getNiCheng() {
		return NiCheng;
	}
	public void setNiCheng(String niCheng) {
		NiCheng = niCheng;
	}
	public String getTouXiang() {
		return TouXiang;
	}
	public void setTouXiang(String touXiang) {
		TouXiang = touXiang;
	}
	
	public String getTiWenShu() {
		return TiWenShu;
	}
	public void setTiWenShu(String tiWenShu) {
		TiWenShu = tiWenShu;
	}
	public String getHuiDaShu() {
		return HuiDaShu;
	}
	public void setHuiDaShu(String huiDaShu) {
		HuiDaShu = huiDaShu;
	}
	public String getDingTieShu() {
		return DingTieShu;
	}
	public void setDingTieShu(String dingTieShu) {
		DingTieShu = dingTieShu;
	}
	public String getShouCangShu() {
		return ShouCangShu;
	}
	public void setShouCangShu(String shouCangShu) {
		ShouCangShu = shouCangShu;
	}
}
