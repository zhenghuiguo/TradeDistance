package com.td.tradedistance.app.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JiaoXueJiHuaShuJu implements Serializable{
	private String Type;

	private List<JiaoXueJiHua> zShuJu;

	public void setType(String Type) {
		this.Type = Type;
	}

	public String getType() {
		return this.Type;
	}

	public void setZShuJu(List<JiaoXueJiHua> zShuJu) {
		this.zShuJu = zShuJu;
	}

	public List<JiaoXueJiHua> getZShuJu() {
		return this.zShuJu;
	}

	public static JiaoXueJiHuaShuJu fill(JSONObject jo) {
		JiaoXueJiHuaShuJu o = new JiaoXueJiHuaShuJu();
		if (jo.containsKey("Type")) {
			o.setType(jo.getString("Type"));
		}
		if (jo.containsKey("zShuJu")) {
			o.setZShuJu(jo.getJSONArray("zShuJu"));
		}
		return o;
	}

	public static List<JiaoXueJiHuaShuJu> fillList(JSONArray ja) {
		if (ja == null || ja.size() == 0)
			return null;
		List<JiaoXueJiHuaShuJu> sqs = new ArrayList<JiaoXueJiHuaShuJu>();
		for (int i = 0; i < ja.size(); i++) {
			sqs.add(fill(ja.getJSONObject(i)));
		}
		return sqs;
	}
}
