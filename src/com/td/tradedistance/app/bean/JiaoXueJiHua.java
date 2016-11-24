package com.td.tradedistance.app.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JiaoXueJiHua implements Serializable{
	private String KeChengZhuangTai;
	public String getKeChengZhuangTai() {
		return KeChengZhuangTai;
	}
	public void setKeChengZhuangTai(String keChengZhuangTai) {
		KeChengZhuangTai = keChengZhuangTai;
	}
	private String KeChengShuXing;

	private int XueWeiKe;

	private String KeChengDaiMa;

	private String KeChengMingCheng;

	private String KeChengZhaoPian;
   
	public void setKeChengShuXing(String KeChengShuXing){
	this.KeChengShuXing = KeChengShuXing;
	}
	public String getKeChengShuXing(){
	return this.KeChengShuXing;
	}
	public void setXueWeiKe(int XueWeiKe){
	this.XueWeiKe = XueWeiKe;
	}
	public int getXueWeiKe(){
	return this.XueWeiKe;
	}
	public void setKeChengDaiMa(String KeChengDaiMa){
	this.KeChengDaiMa = KeChengDaiMa;
	}
	public String getKeChengDaiMa(){
	return this.KeChengDaiMa;
	}
	public void setKeChengMingCheng(String KeChengMingCheng){
	this.KeChengMingCheng = KeChengMingCheng;
	}
	public String getKeChengMingCheng(){
	return this.KeChengMingCheng;
	}
	public void setKeChengZhaoPian(String KeChengZhaoPian){
	this.KeChengZhaoPian = KeChengZhaoPian;
	}
	public String getKeChengZhaoPian(){
	return this.KeChengZhaoPian;
	}
	public static JiaoXueJiHua fill(JSONObject jo){
		JiaoXueJiHua o = new JiaoXueJiHua();
	if (jo.containsKey("KeChengShuXing")) {
	o.setKeChengShuXing(jo.getString("KeChengShuXing"));
	}
	if (jo.containsKey("XueWeiKe")) {
	o.setXueWeiKe(jo.getInt("XueWeiKe"));
	}
	if (jo.containsKey("KeChengDaiMa")) {
	o.setKeChengDaiMa(jo.getString("KeChengDaiMa"));
	}
	if (jo.containsKey("KeChengZhuangTai")) {
		o.setKeChengDaiMa(jo.getString("KeChengZhuangTai"));
		}
	if (jo.containsKey("KeChengMingCheng")) {
	o.setKeChengMingCheng(jo.getString("KeChengMingCheng"));
	}
	if (jo.containsKey("KeChengZhaoPian")) {
	o.setKeChengZhaoPian(jo.getString("KeChengZhaoPian"));
	}
	return o;
	}
	public static List<JiaoXueJiHua> fillList(JSONArray ja) {
	if (ja == null || ja.size() == 0)
	return null;
	List<JiaoXueJiHua> sqs = new ArrayList<JiaoXueJiHua>();
	for (int i = 0; i < ja.size(); i++) {
	sqs.add(fill(ja.getJSONObject(i)));
	}
	return sqs;
	}
}
