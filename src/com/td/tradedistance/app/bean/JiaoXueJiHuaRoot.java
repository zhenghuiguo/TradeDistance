package com.td.tradedistance.app.bean;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JiaoXueJiHuaRoot {
	private String JiaoYanZhi;

	private String ShouQuanMa;

	private List<JiaoXueJiHuaShuJu> ShuJu;

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

	public void setShuJu(List<JiaoXueJiHuaShuJu> ShuJu) {
		this.ShuJu = ShuJu;
	}

	public List<JiaoXueJiHuaShuJu> getShuJu() {
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

	public static JiaoXueJiHuaRoot fill(JSONObject jo) {
		JiaoXueJiHuaRoot o = new JiaoXueJiHuaRoot();
		if (jo.containsKey("JiaoYanZhi")) {
			o.setJiaoYanZhi(jo.getString("JiaoYanZhi"));
		}
		if (jo.containsKey("ShouQuanMa")) {
			o.setShouQuanMa(jo.getString("ShouQuanMa"));
		}
		if (jo.containsKey("ShuJu")) {
			o.setShuJu(jo.getJSONArray("ShuJu"));
		}
		if (jo.containsKey("Ret")) {
			o.setRet(jo.getInt("Ret"));
		}
		if (jo.containsKey("ErrInfo")) {
			o.setErrInfo(jo.getString("ErrInfo"));
		}
		return o;
	}

	public static List<JiaoXueJiHuaRoot> fillList(JSONArray ja) {
		if (ja == null || ja.size() == 0)
			return null;
		List<JiaoXueJiHuaRoot> sqs = new ArrayList<JiaoXueJiHuaRoot>();
		for (int i = 0; i < ja.size(); i++) {
			sqs.add(fill(ja.getJSONObject(i)));
		}
		return sqs;
	}
}
