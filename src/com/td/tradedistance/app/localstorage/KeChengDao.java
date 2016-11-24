package com.td.tradedistance.app.localstorage;


import java.util.List;

import android.content.Context;

import com.td.tradedistance.app.bean.JiaoXueJiHua;
import com.td.tradedistance.app.bean.LoginRecord;

public class KeChengDao {
	
	public static final String TABLE_NAME = "KeCheng";
	public static final String COLUMN_ID = "id";
	public static final String COLUMN_XUEWWEIKE = "XueWeiKe";
	public static final String COLUMN_KECHENGDAIMA = "KeChengDaiMa";
	public static final String COLUMN_KECHENGMINGCHENG = "KeChengMingCheng";
	public static final String COLUMN_KECHENGZHAOPIAN = "KeChengZhaoPian";
	public static final String COLUMN_COUNT = "count";
	public static final String COLUMN_YHWYBS = "yhwybs";
	public static final String COLUMN_BEIYONG1 = "beiyong1";
	public static final String COLUMN_BEIYONG2 = "beiyong2";
	public static final String COLUMN_BEIYONG3 = "beiyong3";
	public KeChengDao() {
	}

	/**
	 * 保存课程表记录
	 */
	public void saveKeCheng(JiaoXueJiHua jxjh) {
		DBManager.getInstance().saveKeCheng(jxjh);
	}

	/**
	 * 更新课程记录
	 */
	public void updateKeCheng(String KeChengDaiMa ,JiaoXueJiHua jxjh) {
		DBManager.getInstance().updateKeCheng(jxjh);
	}
    /**
     * 获取课程记录 
     */
	public boolean getKeChengItem(String KeChengDaiMa) {
		if(DBManager.getInstance().getKeChengItem(KeChengDaiMa)==null){
			return false;
		}
		return true;
	}
	/**
	 * 获取课程列表
	 */
	public List<JiaoXueJiHua> getKeChengList() {
		return DBManager.getInstance().getKeChengList();
	}
	/**
     * 删除课程信息
     */
	public void deleteKeCheng(String KeChengDaiMa) {
		DBManager.getInstance().deleteKeChengItem(KeChengDaiMa);
	}
	
}
